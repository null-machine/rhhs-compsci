/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachines;

import java.util.Scanner;

public class SlotMachines {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int aMinus = input.nextInt();
        int bMinus = input.nextInt();
        int cMinus = input.nextInt();
        int a = 35 - aMinus;
        int b = 60 - bMinus;
        int c = 10 - cMinus;

        a = Math.abs(a);
        a = Math.abs(b);
        a = Math.abs(c);

        int turn = 0;
        int plays = 0;

        while (n > 0) {

            if (turn == 3) {
                turn = 0;
            }
            if (turn == 0) {
                a--;
            }
            if (turn == 1) {
                b--;
            }
            if (turn == 2) {
                c--;
            }

            if (a < 0) {
                n += 35;
                a = 35;
            }
            if (b < 0) {
                n += 60;
                b = 60;
            }
            if (c < 0) {
                n += 9;
                c = 10;
            }

            turn++;
            n--;
            plays++;
        }
        System.out.println(plays);
    }
}
