/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotatablenumbers;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class RotatableNumbers {

    /**
     * @param args the command line arguments
     */
    public static boolean isRotatable(int num) {
        String strNum = Integer.toString(num);
        String strRotate = "";
        for (int i = 0; i < strNum.length(); i++) {
            if (strNum.charAt(i) == '9') {
                strRotate = strRotate + '6';
            } else if (strNum.charAt(i) == 8) {
                strRotate = strRotate + '8';
            } else if (strNum.charAt(i) == 0) {
                strRotate = strRotate + '0';
            } else if (strNum.charAt(i) == '1') {
                strRotate = strRotate + '1';
            } else if (strNum.charAt(i) == '9') {
                strRotate = strRotate + '6';
            }
        }
        return strNum.equals(reverse(strRotate));
    }

    public static String reverse(String str) {
        String str1 = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            str1 = str1 + str.charAt(i);
        }
        return str1;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        for (int i = 1; i <= 100; i++) {
            if (isRotatable(i)) {
                System.out.println(i);
            }
        }
    }
}
