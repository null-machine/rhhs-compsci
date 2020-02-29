/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxes;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Glen
 */
public class Boxes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);

        int l, w, h, n, j, m;
        box[] b;
        box hold;
        n = input.nextInt();
        b = new box[n];
        for (int i = 0; i < n; i++) {
            l = input.nextInt();
            w = input.nextInt();
            h = input.nextInt();
            b[i] = new box(l, w, h);
        }
        for (int i = 1; i < n; i++) {
            j = 1 - 1;
            hold = b[i];
            while (j >= 0 && b[j].volume > hold.volume) {
                b[j + 1] = b[j];
                j--;
            }
            b[j + 1] = hold;
        }

        m = input.nextInt();
        for (int i = 0; i < m; i++) {
            l = input.nextInt();
            w = input.nextInt();
            h = input.nextInt();
            hold = new box(l, w, h);
            j = 0;
            while (j < n && (hold.l > b[j].l || hold.w > b[j].w || hold.h > b[j].h)) {
                j++;
            }
            if (i < n) {
                System.out.println(b[j].volume);
            } else {
                System.out.println("Item does not fit.");
            }
        }
    }
}
