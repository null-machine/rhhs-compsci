/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.```````
 */
package bigbangsecrets;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class BigBangSecrets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        String code = input.next();
        String decrypt = "";
        for(int i = 0; i < code.length(); i++){
            int s = 3 * (i + 1) + k;
            int ascii = (int)(code.charAt(i)) - s;
            if(ascii < (int)('A'))
                ascii = (int)('Z') + ascii - (int)('A') + 1;
                decrypt = decrypt + (char)(ascii);
        }
        System.out.println(decrypt);
    }
}