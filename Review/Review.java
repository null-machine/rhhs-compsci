package files;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
// import java.util.Collections;

public class Review {
	public static void main(String[] args) throws Exception {
		ArrayList<String> students = inputFromFile();
		for (int i = students.size() - 1; i >= 1; i--) {
			int randomNum = randomNum(0, i);
			students = swap(students, i, randomNum);
		}
		// Collections.shuffle(students);
		outputToFile(students);
	}

	public static ArrayList<String> inputFromFile() throws Exception {
		Scanner scanner = new Scanner(new File("src/textfiles/ClassList.txt"));
		ArrayList<String> students = new ArrayList<String>();
		while (scanner.hasNext()) {
			students.add(scanner.nextLine());
		}
		scanner.close();
		return students;
	}
	
	public static void outputToFile(ArrayList<String> students) throws Exception {
		FileWriter fileWriter = new FileWriter("src/textfiles/RandomizedClassList.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (String student : students) {
			printWriter.println(student);
		}
		printWriter.close();
	}
	
	public static int randomNum(int start, int end) {
		int range = (end - start) + 1;
		return (int)((Math.random() * range) + start);
	}
	
	public static ArrayList<String> swap(ArrayList<String> students, int i, int j) {
		String student = students.get(j);
		students.set(j, students.get(i));
		students.set(i, student);
		return students;
	}

}
