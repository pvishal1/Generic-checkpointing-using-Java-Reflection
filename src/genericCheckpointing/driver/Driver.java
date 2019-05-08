
package genericCheckpointing.driver;

import java.util.Vector;

import genericCheckpointing.fileProcess.Results;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

// import the other types used in this file

public class Driver {

	public static void main(String[] args) {
		if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 argumnets.");
			System.exit(0);
		}

		ProxyCreator pc = new ProxyCreator();

		String mode = args[0];
		int NUM_OF_OBJECTS = Integer.parseInt(args[1]);
		String fileName = args[2];

		// create a proxy
		StoreRestoreI cpointRef = pc.createProxy(new Class[] { StoreI.class, RestoreI.class },
				new StoreRestoreHandler(fileName));

		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler storeRestore = new StoreRestoreHandler(fileName);

		if (mode.equals("serdeser")) {
			MyAllTypesFirst myFirst;
			MyAllTypesSecond mySecond;

			// System.getProperty("user.dir") + "/src/checkpoint.txt"
			Results result = new Results(fileName);
			Vector<SerializableObject> vector = new Vector<>();
			for (int i = 0; i < NUM_OF_OBJECTS; i++) {
				myFirst = new MyAllTypesFirst(getRandomIntBetweenRange(), getRandomLongBetweenRange(),
						"Design Patterns", false, getRandomIntBetweenRange(), getRandomLongBetweenRange());
				mySecond = new MyAllTypesSecond(getRandomDoubleBetweenRange(), getRandomFloatBetweenRange(),
						getRandomShortBetweenRange(), 'P', getRandomDoubleBetweenRange());

				vector.add(myFirst);
				vector.add(mySecond);

				result.setResultList(((StoreI) cpointRef).writeObj(myFirst, 13, "XML"));
				result.getResultList().addAll(((StoreI) cpointRef).writeObj(mySecond, 17, "XML"));

				result.writeToFile();
			}
			result.close();

			SerializableObject myRecordRet;
			Vector<SerializableObject> vectorNew = new Vector<>();

			for (int j = 0; j < 2 * NUM_OF_OBJECTS; j++) {

				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
				vectorNew.add(myRecordRet);
			}
			storeRestore.closeFile();

			if (checkEquals(vector, vectorNew)) {
				System.out.println("The serialized and deserialized files are equal");
			} else {
				System.out.println("The serialized and deserialized files are not equal");
				System.out.println("Vector before serialization: " + vector);
				System.out.println("Vector after deserialization: " + vectorNew);
			}
		} else if (mode.equals("deser")) {
			SerializableObject myRecordRet;
			Vector<SerializableObject> vector_new = new Vector<>();

			for (int j = 0; j < NUM_OF_OBJECTS; j++) {

				myRecordRet = ((RestoreI) cpointRef).readObj("XML");
				vector_new.add(myRecordRet);
			}
			storeRestore.closeFile();

			System.out.println(vector_new);
		}

	}

	/**
	 * Check if objects in Vectors are same or not
	 * 
	 * @param vectorOldIn
	 * @param vectorNewIn
	 * @return
	 */
	public static boolean checkEquals(Vector<SerializableObject> vectorOldIn, Vector<SerializableObject> vectorNewIn) {
		Vector<SerializableObject> vectorTest = new Vector<>();
		for (SerializableObject serializableObject : vectorNewIn) {
			vectorTest.add(serializableObject);
		}
		// vectorTest = vectorNewIn;
		for (SerializableObject serializableObject : vectorOldIn) {
			for (SerializableObject serializableObject1 : vectorNewIn) {
				if (serializableObject.equals(serializableObject1)) {
					vectorTest.remove(serializableObject1);
				}
			}
		}

		if (vectorTest.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * get random int value between Max and Min value
	 * 
	 * @return
	 */
	public static int getRandomIntBetweenRange() {
		int x = (int) ((Math.random() * ((Integer.MAX_VALUE - 1) + 1)) + 1);
		return x;
	}

	/**
	 * get random double value between Max and Min value
	 * 
	 * @return
	 */
	public static double getRandomDoubleBetweenRange() {
		double x = ((Math.random() * ((Double.MAX_VALUE - 1) + 1)) + 1);
		return x;
	}

	/**
	 * get random float value between Max and Min value
	 * 
	 * @return
	 */
	public static float getRandomFloatBetweenRange() {
		float x = (float) ((Math.random() * ((Float.MAX_VALUE - 1) + 1)) + 1);
		return x;
	}

	/**
	 * get random long value between Max and Min value
	 * 
	 * @return
	 */
	public static long getRandomLongBetweenRange() {
		long x = (long) ((Math.random() * ((Long.MAX_VALUE - 1) + 1)) + 1);
		return x;
	}

	/**
	 * get random short value between Max and Min value
	 * 
	 * @return
	 */
	public static short getRandomShortBetweenRange() {
		short x = (short) ((Math.random() * ((Short.MAX_VALUE - 1) + 1)) + 1);
		return x;
	}
}