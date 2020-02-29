/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primaryarithmetic;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class PrimaryArithmetic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        while (num1 != 0 && num2 != 0) {
            int count = 0;
            int carry = 0;
            int[] arr1 = new int[Integer.toString(num1).length()];
            int[] arr2 = new int[Integer.toString(num2).length()];
            for (int i = arr1.length - 1; i >= 0; i--) {
                arr1[i] = num1 % 10;
                num1 = num1 / 10;
            }
            for (int j = arr2.length - 1; j >= 0; j--) {
                arr2[j] = num2 % 10;
                num2 = num2 / 10;
            }
            for (int i = arr1.length - 1; i >= 0; i--) {
                if (arr1[i] + arr2[i] + carry > 9) {
                    count++;
                    carry = (arr1[i] + arr2[i] % 10);
                }
            }
            System.out.println(count + " carry operations");
            num1 = input.nextInt();
            num2 = input.nextInt();
        }
    }

}
