package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genericCheckpointing.util.SerializableObject;

public class XMLDeserialization implements DeserStrategy {

	@Override
	public SerializableObject processInput(ArrayList<String> serObj) {
		SerializeTypes serType = new SerializeTypes();
		Object object = null;
		Class<?> clas = null;
		for (String serLine : serObj) {
			Pattern tagNamePattern = Pattern.compile("<(.*?) xsi:");
			Pattern typeNamePattern = Pattern.compile("xsd:(.*?)\">");
			Pattern valuePattern = Pattern.compile(">(.*?)<");
			if (serLine.contains("<complexType")) {
				String[] splitString = serLine.split("type=\"");
				String className = splitString[1].split("\">")[0];
				try {
					clas = Class.forName(className);
					Constructor<?> ctor = clas.getConstructor();
					object = ctor.newInstance();
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Exception while deserializing and creating new instance of class");
					e.printStackTrace();
				}
			} else if (serLine.contains("</complexType>")) {
				break;
			} else {
				String tagName = null, value = null, typeName = null;

				Matcher match = tagNamePattern.matcher(serLine);
				if (match.find()) {
					tagName = match.group(1);
				}
				match = typeNamePattern.matcher(serLine);
				if (match.find()) {
					typeName = match.group(1);
				}
				match = valuePattern.matcher(serLine);
				if (match.find()) {
					value = match.group(1);
				}

				tagName = tagName.substring(0, 1).toUpperCase() + tagName.substring(1);

				try {
					serType.deserialize(typeName, tagName, clas, value, object);
				} catch (SecurityException | IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		return (SerializableObject) object;
	}

}
