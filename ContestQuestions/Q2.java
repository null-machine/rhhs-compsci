package q2;

import java.util.Arrays;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int location[] = new int[n];
        for (int i = 0; i < n; i++) {
            location[i] = input.nextInt();
        }
        Arrays.sort(location);

        int possible = 0;
        for (int i = 1; i < n; i++) {
            if ((location[i] - location[i - 1]) > 200) {
                possible = 1;
            }
        }

        if (possible == 0) {
            System.out.println("POSSIBLE");
        } else if (possible == 1) {
            System.out.println("IMPOSSIBLE");
        }
        
    }
}
