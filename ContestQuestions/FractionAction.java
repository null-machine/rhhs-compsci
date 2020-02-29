/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractionaction;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class FractionAction {

    public static int GCF(int a, int b) {
        int gcf = 1;
        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcf = i;
            }
        }
        return gcf;
    }

    public static int LCM(int a, int b) {
        return a * b / GCF(a, b);
    }

    /**
     * @param args the command line arguments    */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int d = input.nextInt();
        if (n % d == 0) {
            System.out.println(n / d);
        } else if (n > d) {
            System.out.println(n / d + " " + (n % d) / GCF(n, d) + "/" + d / GCF(n, d));
        } else {
            System.out.println((n % d) / GCF(n, d) + "/" + d / GCF(n, d));
        }
    }
}
