package lottery;

import java.util.Scanner;

public class Lottery {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] equations = new String[n];
        
        for (int i = 0; i < n; i++) {
            equations[i] = input.nextLine();
        }
        
        // if an 'X' is found, insert parentheses around the two numbers beside it
        
        
    }
}
