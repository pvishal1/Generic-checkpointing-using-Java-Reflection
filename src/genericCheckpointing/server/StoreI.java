package genericCheckpointing.server;

import java.util.ArrayList;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface StoreI extends StoreRestoreI {
	ArrayList<String> writeObj(MyAllTypesFirst aRecord, int authID, String wireFormat);

	ArrayList<String> writeObj(MyAllTypesSecond bRecord, int authID, String wireFormat);
}