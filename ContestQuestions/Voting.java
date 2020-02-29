package voting;

import java.util.ArrayList;
import java.util.Scanner;

public class Voting {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, spoiled = 0;

        for (int i = 0; i < n; i++) {
            String vote = input.next();
            if(vote.equals("A")){
                a++;
            } else if(vote.equals("B")) {
                b++;
            } else if(vote.equals("C")) {
                c++;
            } else if(vote.equals("D")) {
                d++;
            } else if(vote.equals("E")) {
                e++;
            } else if(vote.equals("F")) {
                f++;
            } else {
                spoiled++;
            }
        }
        
        System.out.println(a + "\n" + b + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n");
    }
}
