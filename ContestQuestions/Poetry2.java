/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poetry2;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class Poetry2 {

    /**
     * @param args the command line arguments
     */
    public static int VowelIndex(String str) {
        String vowel = "aeiouAEIOU";
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (vowel.indexOf(str.charAt(i)) >= 0) {
                index = i;
            }
        }
        return index;
    }

    public static String syllable(String str) {
        if (VowelIndex(str) >= 0) {
            return str.substring(VowelIndex(str) + 1);
        } else {
            String[] strarr = str.split(" ");
            return strarr[strarr.length - 1];
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ntest = input.nextInt();
        String[] arr = new String[4];
        arr[0] = input.nextLine();
        for (int i = 0; i < ntest; i++) {
            for (int a = 0; a < 4; a++) {
                arr[a] = input.nextLine();
            }
            String s1 = syllable(arr[0]);
            String s2 = syllable(arr[1]);
            String s3 = syllable(arr[2]);
            String s4 = syllable(arr[3]);
            
            if (s1.equals(s2) && s2.equals(s3) && s3.equals(s4)) {
                System.out.println("perfect");
            } else if (s1.equals(s2) && s3.equals(s4)) {
                System.out.println("even");
            } else if (s1.equals(s3) && s2.equals(s4)) {
                System.out.println("cross");
            } else if (s1.equals(s4) && s2.equals(s3)) {
                System.out.println("shell");
            } else {
                System.out.println("free");
            }

        }
    }
}
