/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquewords;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class UniqueWords {

    /**
     * @param args the command line arguments
     */
    public static int wordCount(String[] arr, String str) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = input.next();
        }
        int unique = 0;
        for (int i = 0; i < str.length; i++) {
            if (wordCount(str, str[i]) == 1) {
                unique++;
            }
        }
        System.out.println(unique);
    }
}
