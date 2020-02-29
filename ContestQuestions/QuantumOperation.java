package quantumoperation;

import java.util.Scanner;

public class QuantumOperation {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] product = new int[1024][1024];
        int[][] a = new int[1024][1024];
        int[][] b = new int[1024][1024];
        int[][] rowSum = new int[1024][1024];
        int[][] colSum = new int[1024][1024];
        int maxElement, minElement, maxRowSum, maxColSum, minRowSum, minColSum;
        int n, r, c, pr, pc;
        pr = 1;
        pc = 1;
        b[0][0] = 1;

        n = input.nextInt();
        for (int k = 0; k < n; k++) {
            r = input.nextInt();
            c = input.nextInt();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    a[i][j] = input.nextInt();
                }
            }
            for (int p = 0; p < r * pr; p++) {
                for (int q = 0; q < c * pc; q++) {
                    product[p][q] = b[p % pr][q % pc] * a[p / pr][q / pc];
                }
            }
            pr *= r;
            pc *= c;
            // b = product;

            for (int p = 0; p < pr; p++) {
                for (int q = 0; q < pc; q++) {
                    b[p][q] = product[p][q];
                }
            }
            minElement = product[0][0];
            maxElement = product[0][0];
            for (int i = 0; i < pr; i++) {
                for (int j = 0; j < pc; j++) {
                    rowSum[i] += product[i][j];
                    colSum[i] += product[i][j];
                    if (product[i][j] > maxElement) {
                        maxElement = product[i][j];
                    }
                    if (product[i][j] < minElement) {
                        minElement = product[i][j];
                    }
                    maxRowSum = rowSum[0];
                    minRowSum = rowSum[0];
                    maxRowSum = colSum[0];
                    minColSum = colSum[0];

                }
            }
        }
    }
}
