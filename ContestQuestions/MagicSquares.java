/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicsquares;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class MagicSquares {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int[][] square = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                square[i][j] = input.nextInt();
            }
        }

        int[] rSum = new int[4];
        int[] cSum = new int[4];
        for (int i = 0; i < 4; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < 4; j++) {
                row = row + square[i][j];
                col = col + square[j][i];
            }
            rSum[i] = row;
            cSum[i] = col;
        }
        int sum = rSum[0];
        boolean magic = true;
        for (int i = 0; i < 4; i++) {
            if (rSum[i] != sum || cSum[i] != sum) {
                magic = false;
            }
        }
        if (magic) {
            System.out.println("magic");
        } else {
            System.out.println("not magic");
        }
    }
}
