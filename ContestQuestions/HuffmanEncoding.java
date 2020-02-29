package huffmanencoding;

import java.util.Scanner;

public class HuffmanEncoding {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        char[] letter = {'A', 'B', 'C', 'D', 'E'};
        String[] code = {"00", "01", "10", "110", "111"};
        String cipherMsg, plainMsg;
        
        System.out.println("Enter a Huffman coded message: ");
        cipherMsg = input.next();
        
        plainMsg = "";
        while(cipherMsg.length() > 0){
            int i = 0;
            while(i < code.length && !cipherMsg.startsWith(code[i])){
                i++;
            }
            if(i < code.length){
                plainMsg = plainMsg
            }
        }
    }
}
