package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

import genericCheckpointing.fileProcess.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {
	
	FileProcessor deserFile;
	ArrayList<String> serObj;
	
	public StoreRestoreHandler(String fileIn) {
		deserFile = new FileProcessor(fileIn);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();

		if (methodName.equals("readObj")) {
			if (args[0].equals("XML")) {
				DeserStrategy strategy = new XMLDeserialization();
				readFile();
				return strategy.processInput(serObj);
			}
		}

		if (methodName.equals("writeObj")) {
			if (args[2].equals("XML")) {
				SerStrategy strategy = new XMLSerialization();
				return strategy.processInput((SerializableObject) args[0]);
			}
		}
		return null;
	}
	
	public void readFile() {
		serObj = deserFile.readLine();
	}
	
	public void closeFile() {
		deserFile.close();
	}

}
