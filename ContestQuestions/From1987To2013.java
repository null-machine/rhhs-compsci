/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */     
package from1987to2013;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class From1987To2013 {

    /**
     * @param args the command line arguments
     */
    public static boolean isUnique(String str) {
        boolean unique = true;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    unique = false;
                }
            }
        }
        return unique;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int year = input.nextInt() + 1;
        String strYear = Integer.toString(year);
        while(!isUnique(strYear)){
            year++;
            strYear = Integer.toString(year);
        }
        System.out.println(year);
    }
}
