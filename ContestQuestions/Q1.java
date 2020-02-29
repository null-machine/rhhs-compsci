package q1;

import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        double weight[] = new double[4];
        for (int i = 0; i < 4; i++) {
            weight[i] = input.nextDouble() / 100;
        }
        int n = input.nextInt();
        double marks[][] = new double[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                marks[i][j] = input.nextDouble();
            }
        }

        double average[] = new double[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                average[i] = (marks[i][0] * weight[0] + marks[i][1] * weight[1] + marks[i][2] * weight[2] + marks[i][3] * weight[3]) / 100;
            }
            if (average[i] >= 0.50) {
                count++;
            }
        }

        System.out.println(count);
        
    }
}
