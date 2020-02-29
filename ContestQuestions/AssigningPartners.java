/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigningpartners;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class AssigningPartners {

    /**
     * @param args the command line arguments
     */
    public static int pos(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] n1 = new String[n];
        String[] n2 = new String[n];
        boolean isGood = true;
        for (int i = 0; i < n; i++) {
            n1[i] = input.next();
        }
        for (int i = 0; i < n; i++) {
            n2[i] = input.next();
        }
        int i = 0;
        while (i < n && isGood) {
            int position = pos(n1, n2[i]);
            if (!n1[i].equals(n2[position]) || position == i) {
                isGood = false;
            }
            i++;
        }
        if (isGood) {
            System.out.println("good");
        } else {
            System.out.println("bad");
        }
    }
}
