import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

class Serializer {
	
	public static <T> void save(String fileName, ArrayList<T> list) {
		try {
			File file = new File(fileName + ".sav");
			file.createNewFile();
			FileOutputStream fileOutput = new FileOutputStream(fileName);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(list); // :dab:
			fileOutput.close();
			objectOutput.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static <T> ArrayList<T> load(String fileName) {
		ArrayList<T> list = new ArrayList<T>();
		try {
			FileInputStream fileInput = new FileInputStream(fileName + ".sav");
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			list = (ArrayList)objectInput.readObject(); // unsafe but thats ur problem now
			objectInput.close();
			fileInput.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return list;
	}
}
