package node;

import java.util.Scanner;
import java.text.DecimalFormat;

public class TreeTester {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        Tree x = new Tree();
        long sum = 0;
        for (int k = 1; k <= t; k++) {
            sum += x.add(input.nextInt()) + 1;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        double ans = (double) (sum) / t;
        System.out.println(df.format(ans));
    }
}
