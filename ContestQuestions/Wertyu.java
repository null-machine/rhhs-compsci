/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wertyu;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class Wertyu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        String alpha = "QWERTYUIOPASDFGHJKL;'ZXCVBNM,./";
        String sen = input.nextLine(); // Sentence
        String temp = "";
        for (int i = 0; i < sen.length(); i++) {
            if(sen.charAt(i) == ' '){
                temp = temp + sen.charAt(i);
            } else {
                int index = alpha.indexOf(sen.charAt(i));
                temp = temp + alpha.charAt(index - 1);
            }
        }
        System.out.println(temp);
    }
}
