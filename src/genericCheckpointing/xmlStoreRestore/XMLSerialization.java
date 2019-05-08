package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.util.ArrayList;

import genericCheckpointing.util.SerializableObject;

public class XMLSerialization implements SerStrategy {

	@Override
	public ArrayList<String> processInput(SerializableObject sObject) {
		ArrayList<String> serializedVal = new ArrayList<>();
		SerializeTypes serTypes = new SerializeTypes();
		serializedVal.add("<DPSerialization>");
		serializedVal.add("\t<complexType xsi:type=\"" + sObject.getClass().getName() + "\">");
		Field[] f = sObject.getClass().getDeclaredFields();
		for (int i = 0; i < f.length; i++) {
			try {
				Field field = f[i];
				String value;
				field.setAccessible(true);
				String type = field.getType().toString();
				if (type.equals("int")) {
					value = serTypes.serializeInt(field.get(sObject).toString(), field.getName(), type);
				} else if (type.equals("double")) {
					value = serTypes.serializeDouble(field.get(sObject).toString(), field.getName());
				} else if (type.equals("long")) {
					value = serTypes.serializeLong(field.get(sObject).toString(), field.getName());
				} else {
					value = serTypes.serializeOther(field.get(sObject).toString(), field.getName(), type);
				}

				if (!value.equals("")) {
					serializedVal.add(value);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.err.println("Error while serialization");
				e.printStackTrace();
			}
		}
		serializedVal.add("\t</complexType>");
		serializedVal.add("</DPSerialization>");

		return serializedVal;
	}

}
