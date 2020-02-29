/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lettercounting;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Glen
 */
public class LetterCounting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        str = str.toLowerCase();
        str = str.replaceAll(" ", "");
        System.out.println(str);
        HashMap<String, Integer> myMap = new HashMap<String, Integer>();
        for (int i = 0; i < str.length(); i++) {
            if (myMap.containsKey(str.substring(i, i + 1))) {
                myMap.put(str.substring(i, i + 1), myMap.get(str.substring(i, i + 1)));
            } else {
                myMap.put(str.substring(i, i + 1), 1);
            }
        }
        int max = 0;
        for (String str1 : myMap.keySet()) {
            if (myMap.get(str1) >= max) {
                max = myMap.get(str1);
            }
        }
        String display = "";
        for (String str2 : myMap.keySet()) {
            if (myMap.get(str2) == max) {
                display = display + " " + str2.toUpperCase();
            }
        }
        System.out.println(display + " occurs(s) " + max + " times.");
    }
}
