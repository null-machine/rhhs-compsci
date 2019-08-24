import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Decoder.java
 * A toolkit for parsing and decoding .MZIP files.
 * @author Glen Wang
 * March 22, 2019
 */
public class Decoder {
	
	// parsed
	private String fileName;
	private String treeDna;
	private int extraBits;
	private Queue encoded; 
	
	// created
	private HuffmanTree map;
	private Queue decoded;
	
	/**
     * parse
     * Extracts the file name, tree string, extra bit count, and encoded binary from the specified file.
     * Stores the extracted information in class variables, because java doesn't support tuples easily.
     * @param The file name, including extension.
     */
	public void parse(String zipName) throws IOException {
		
		FileInputStream in = null;
		
		try {
			in = new FileInputStream(zipName);
			int c;
			
			// parse fileName
			fileName = "";
			while((c = in.read()) != 10) {
				fileName += (char)c;
			}
			fileName = fileName.substring(0, fileName.length() - 1);
			
			// parse treeDna
			treeDna = "";
			while((c = in.read()) != 10) {
				treeDna += (char)c;
			}
			treeDna = treeDna.substring(1, treeDna.length() - 1); // strips outer brackets
			
			// parse extraBits
			c = in.read();
			extraBits = Integer.parseInt("" + (char)c);
			in.read();
			in.read();
			
			// parse encoded
			encoded = new Queue<Character>();
			String binChar;
			while((c = in.read()) != -1) {
				binChar = convBinary((char)c);
				for(int i = 0; i < binChar.length(); ++i) {
					encoded.enqueue(binChar.charAt(i));
				}
			}
			
			System.out.println("parsed info:\n\tfileName: " + fileName + "\n\ttreeDna: " + treeDna + "\n\textraBits: " + extraBits + "\n\tencoded: " + encoded);
			
		} finally {
			if(in != null) {
				in.close();
			}
		}
	}
	
	/**
     * createTree
     * Creates a Huffman tree using the parsed tree string.
     * Stores the tree in a class variable.
     */
	public void createTree() {
		System.out.println("creating tree");
		map = new HuffmanTree();
		String strNum; // for parsing numbers in the tree string, because they take multiple characters
		
		for(int i = 0; i < treeDna.length(); ++i) {
			char current = treeDna.charAt(i);
			strNum = "";
			while(Character.isDigit(current)) {
				current = treeDna.charAt(i);
				strNum += current;
				++i;
			}
			if(!strNum.equals("")) {
				strNum = strNum.substring(0, strNum.length() - 1);
				map.add(Integer.parseInt(strNum));
				i -= 2; // compensates for while loop
			} else if(current == '(') {
				map.add(null);
			} else if(current == ')') {
				map.moveUp();
			}
		}
	}
	
	/**
     * decode
     * Decodes the parsed binary using a Huffman tree.
     * Stores the decoded information in a class variable.
     */
	public void decode() {
		decoded = new Queue<Integer>();
		System.out.println("decoding ");
		int bitCount = encoded.size() - extraBits;
		
		for(int i = 0; i < bitCount; ++i) {
			char c = (char)encoded.dequeue();
			if(c == '0') {
				if(map.spyLeft()) {
					decoded.enqueue(map.spyItem());
					map.spyRoot();
				}
			} else if(c == '1') {
				if(map.spyRight()) {
					decoded.enqueue(map.spyItem());
					map.spyRoot();
				}
			}
		}
	}
	
	/**
     * writeFile
     * Creates a file according to the parsed file name and decoded binary.
     */
	public void writeFile() throws IOException {
		System.out.print("writing file to " + fileName + "\n");
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			int charCount = decoded.size();
			char decodedChar;
			for(int i = 0; i < charCount; ++i) {
				decodedChar = (char)(int)decoded.dequeue();
				out.write(decodedChar);
				//System.out.print(decodedChar);
			}
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	private String convBinary(char c) {
		String res = "";
		for (int i = 7; i >= 0; --i) { 
			if ((c & (char)Math.pow(2, i)) > 0) {
				res += "1";
			} else {
				res += "0";
			}
		}
		return res;
	}
}
