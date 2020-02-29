/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerpurchase;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class ComputerPurchase {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] name = new String[n];
        int[] r = new int[n];
        int[] s = new int[n];
        int[] d = new int[n];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            name[i] = input.next();
            r[i] = input.nextInt();
            s[i] = input.nextInt();
            d[i] = input.nextInt();
            p[i] = 2 * r[i] + 3 * s[i] + d[i];
        }

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p.length - 1; j++) {
                int temp;
                String pc = "";
                if (p[j] >= p[j + 1]) {
                    temp = p[j];
                    pc = name[j];
                    p[j] = p[j + 1];
                    p[j + 1] = temp;
                    name[j + 1] = pc;
                } else if (p[j] == p[j + 1]) {
                    pc = "";
                    if (name[j].compareTo(name[j + 1]) > 0) {
                        pc = name[j];
                        name[j] = name[j + 1];
                        name[j + 1] = pc;
                    }
                }
            }
        }
        System.out.println(name[name.length - 1] + "\n" + name[name.length - 2]);
    }
}
