package genericCheckpointing.xmlStoreRestore;

import java.util.ArrayList;

import genericCheckpointing.util.SerializableObject;

public interface SerStrategy {
	ArrayList<String> processInput(SerializableObject sObject);
}