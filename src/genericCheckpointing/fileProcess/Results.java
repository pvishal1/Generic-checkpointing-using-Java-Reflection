package genericCheckpointing.fileProcess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Result class to store the total efforts as result of orientation selected
 * 
 * @author pragyavishalakshi
 *
 */
public class Results implements FileDisplayInterface {

	/**
	 * arrayList to store the course alloted and graduation status of each student
	 */
	private ArrayList<String> resultList = null;
	BufferedWriter br = null;
	
	/**
	 * Constructor of Results
	 */
	public Results(String fileIn) {
		resultList = new ArrayList<>();
		try {
			br = new BufferedWriter(new FileWriter(fileIn));
		} catch (IOException e) {
			System.err.println("Error");
			e.printStackTrace();
		}
	}
	
	@Override
	public void writeToFile() {
		try {
//			br = new BufferedWriter(new FileWriter(fileIn));
			for (String data : resultList) {
				br.write(data+"\n");
			}
			resultList.clear();
		} catch (IOException e) {
			System.err.println("Error");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			br.close();
		} catch (IOException e) {
			System.err.println("Error");
			e.printStackTrace();
		}
	}

	public ArrayList<String> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<String> resultList) {
		this.resultList = resultList;
	}

}
