/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asciidemo;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class AsciiDemo { // American Standard Code for Information Interchange

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        char x1 = 'A';
        int y1 = x1;
        System.out.println((char)((int)(y1) + 3)); // Prints ASCII value
        String zebra = "ZEBRA";
        for(int i = 0; i < zebra.length(); i++){
            if((int)(zebra.charAt(i)) + 3 > 90){
                System.out.print((char)((int)(zebra.charAt(i)) - 23));
            } else {
                System.out.print((char)((int)(zebra.charAt(i)) + 3));
            }
        }
    }
}
