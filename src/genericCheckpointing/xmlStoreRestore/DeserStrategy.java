package genericCheckpointing.xmlStoreRestore;

import java.util.ArrayList;

import genericCheckpointing.fileProcess.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public interface DeserStrategy {
	SerializableObject processInput(ArrayList<String> serObj);
}