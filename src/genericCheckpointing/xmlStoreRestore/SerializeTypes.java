package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SerializeTypes {

	/**
	 * serialize int data
	 * 
	 * @param value
	 * @param tagName
	 * @param typeName
	 * @return
	 */
	public String serializeInt(String value, String tagName, String typeName) {
		if ((Integer.parseInt(value) >= 10)) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:" + typeName + "\">" + value + "</" + tagName + ">");
		}
		return "";
	}

	/**
	 * serialize double data
	 * 
	 * @param value
	 * @param tagName
	 * @return
	 */
	public String serializeDouble(String value, String tagName) {
		if ((Double.parseDouble(value) >= 10)) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:double\">" + value + "</" + tagName + ">");
		}
		return "";
	}

	/**
	 * serialize long data
	 * 
	 * @param value
	 * @param tagName
	 * @return
	 */
	public String serializeLong(String value, String tagName) {
		if ((Long.parseLong(value) >= 10)) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:long\">" + value + "</" + tagName + ">");
		}
		return "";
	}

	/**
	 * serialize all the datatype except int, double and long
	 * 
	 * @param value
	 * @param tagName
	 * @param typeName
	 * @return
	 */
	public String serializeOther(String value, String tagName, String typeName) {
		if (typeName.equals("class java.lang.String")) {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:string\">" + value + "</" + tagName + ">");
		} else {
			return ("\t\t<" + tagName + " xsi:type=\"xsd:" + typeName + "\">" + value + "</" + tagName + ">");
		}
	}

	/**
	 * deserialize based on the Parameters provided
	 * 
	 * @param typeName
	 * @param tagName
	 * @param clas
	 * @param value
	 * @param object
	 * @return
	 */
	public Method deserialize(String typeName, String tagName, Class<?> clas, String value, Object object) {
		try {
			Method methodInt = null;
			if (typeName.equals("int")) {
				methodInt = clas.getMethod("set" + tagName, Integer.TYPE);
				methodInt.invoke(object, Integer.parseInt(value));
				// int v = (Integer.TYPE) value;
			} else if (typeName.equals("double")) {
				methodInt = clas.getMethod("set" + tagName, Double.TYPE);
				methodInt.invoke(object, Double.parseDouble(value));
			} else if (typeName.equals("long")) {
				methodInt = clas.getMethod("set" + tagName, Long.TYPE);
				methodInt.invoke(object, Long.parseLong(value));
			} else if (typeName.equals("string")) {
				methodInt = clas.getMethod("set" + tagName, java.lang.String.class);
				methodInt.invoke(object, value);
			} else if (typeName.equals("float")) {
				methodInt = clas.getMethod("set" + tagName, Float.TYPE);
				methodInt.invoke(object, Float.parseFloat(value));
			} else if (typeName.equals("short")) {
				methodInt = clas.getMethod("set" + tagName, Short.TYPE);
				methodInt.invoke(object, Short.parseShort(value));
			} else if (typeName.equals("char")) {
				methodInt = clas.getMethod("set" + tagName, Character.TYPE);
				methodInt.invoke(object, value.charAt(0));
			} else if (typeName.equals("boolean")) {
				methodInt = clas.getMethod("set" + tagName, Boolean.TYPE);
				methodInt.invoke(object, Boolean.parseBoolean(value));
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.err.println("Error while deserializing");
			e.printStackTrace();
		}
		return null;
	}
}
