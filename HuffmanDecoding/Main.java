import java.io.IOException;
import java.util.Scanner;

/**
 * Main.java
 * Launches a decoder.
 * @author Glen Wang
 * March 22, 2019
 */
public class Main {
	
	/**
	 * main
	 * Asks the user for the file name of the .MZIP and decodes it.
	 * @param String[] args
	 */
	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		Decoder decoder = new Decoder();
		
		System.out.print("Input the file name to be decoded: ");
		
		decoder.parse(in.next());
		decoder.createTree();
		decoder.decode();
		decoder.writeFile();
	}
}
