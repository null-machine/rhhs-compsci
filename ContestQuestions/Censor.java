/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package censor;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class Censor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String str = input.nextLine();
        for (int i = 0; i < n; i++) {
            str = input.nextLine();
            String[] strarr = str.split(" "); // Splits string into words
            String encoded = "";
            for (int j = 0; j < strarr.length; j++) {
                if (strarr[j].length() == 4) {
                    encoded = encoded + "**** ";
                } else {
                    encoded = encoded + strarr[j] + " ";
                }
            }
            System.out.println(encoded);
        }
    }
}
