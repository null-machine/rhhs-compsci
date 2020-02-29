/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordframe;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class WordFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        String str = input.next();
        String[] pattern = new String[str.length() + 2];
        pattern[0] = "*";
        pattern[pattern.length - 1] = "*";
        for (int i = 0; i < str.length(); i++) {
            pattern[i + 1] = str.substring(i, i + 1);
        }
        int framesize = pattern.length;

        for (int i = 0; i < pattern.length; i++) {
            if (i == 0) {
                for (int j = 0; j < pattern.length; j++) {
                    System.out.println(pattern[j] + " ");
                }
                System.out.println();
            } else if (i == pattern.length - 1) {
                for (int j = 0; j < pattern.length; j++) {
                    System.out.print(reverse[j] + " ");
                }
                System.out.println();
            } else {
                System.out.println(reverse[i] + " ");
                for (int a = 0; a < pattern.length - 2; a++) {
                    System.out.println("* ");
                }
                System.out.print(pattern[i]);
                System.out.println();
            }
        }

    }
}
