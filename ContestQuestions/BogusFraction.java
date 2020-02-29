/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bogusfraction;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class BogusFraction {

    public static boolean isBogus(String num, String den) {
        double n = Integer.parseInt(num);
        double d = Integer.parseInt(num);
        double bogus = n / d;
        boolean possible = false;
        for (int i = 0; i < den.length(); i++) {
            if (num.indexOf(den.charAt(i)) >= 0) {
                int index = num.indexOf(den.charAt(i));
                String strd = den.substring(0, i) + den.substring(i + 1, den.length());
                String strn = den.substring(0, index) + den.substring(index + 1, den.length());
                double x1 = Integer.parseInt(strd);
                double y1 = Integer.parseInt(strd);
                if (bogus == y1 / x1) {
                    possible = true;
                }
            }
        }
        return possible;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        String num = str.substring(0, 3);
        String den = str.substring(4, 7);

        if (isBogus(num, den)) {
            for (int i = 0; i < den.length(); i++) {
                if (num.indexOf(den.charAt(i)) >= 0) {
                    int index = num.indexOf(den.charAt(i));
                    String strd = den.substring(0, i) + den.substring(i + 1, den.length());
                    String strn = den.substring(0, index) + den.substring(index + 1, den.length());
                    System.out.println(strn + "/" + strd);
                }
            }
        } else {
            System.out.println(num + "/" + den + " cannot be simplified using bogus fraction");
        }
    }

}
