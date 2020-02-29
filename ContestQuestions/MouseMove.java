/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousemove;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class MouseMove {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int xmax = input.nextInt();
        int ymax = input.nextInt();

        int x = input.nextInt();
        int y = input.nextInt();

        int xoriginal = 0;
        int yoriginal = 0;

        while (x != 0 && y != 0) {
            if (xoriginal + x <= xmax && xoriginal + x >= 0) {
                xoriginal = xoriginal + x;
            } else {
                if (x < 0) {
                    xoriginal = 0;
                } else {
                    xoriginal = xmax;
                    }
                }
            if (yoriginal + y <= ymax && yoriginal + y >= 0) {
                yoriginal = yoriginal + y;
            } else {
                if (y < 0) {
                    yoriginal = 0;
                } else {
                    yoriginal = ymax;
                    }
                }
                System.out.println(xoriginal + "\t" + yoriginal);
                x = input.nextInt();
                y = input.nextInt();
                }
    }
}
