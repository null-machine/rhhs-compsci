package test2_q3;

import java.util.Scanner;

public class Test2_Q3 {

    // Forgot syntax for graphs
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {

            int n = input.nextInt(); // Parsing input
            int mountains[] = new int[n];
            for (int j = 0; j < n; j++) {
                mountains[j] = input.nextInt();
            }

            int count = 0; // Remembering and deciding index of mountain with best view
            int bestCount = 0;
            int bestMountain = 0;

            for (int index = 0; index < n; index++) {
                for (int j = 0; j < index; j++) { // Mountains on right
                    if (mountains[index] > mountains[j]) {
                        count++;
                    }
                }
                for (int j = n; j > index; j--) { // Mountains on left
                    if (mountains[index] < mountains[j]) {
                        count++;
                    }
                }

                if (count > bestCount) {
                    bestCount = count;
                    bestMountain = index + 1;
                }
            }

            System.out.println(bestMountain);

        }
    }
}
