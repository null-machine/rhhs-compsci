package patterngenerator;

import java.util.Scanner;

public class PatternGenerator {

    public static String binary(int x, int n) {
        String str = "";
        while (x > 0) {
            str = str + x % 2;
            x = x / 2;
        }

        String temp = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            temp = temp + str.charAt(i);
        }
        int d = temp.length();
        String temp1 = "";
        if (temp.length() != n) {
            for (int i = 0; i < n - d; i++) {
                temp1 = temp1 + 0;
            }
        }
        return temp1 + temp;
    }

    public static boolean isValid(String s, int k) {
        return true;
    }

    public static int decimal(String str) {
        int x = 0;
        int index = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '0') {
                x = x + 0 * (int) (Math.pow(2, index));
            } else if (str.charAt(i) == '1') {
                x += 1 * (int) (Math.pow(2, index));
            }
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int ntest = input.nextInt();

        for (int i = 0; i < ntest; i++) {
            int n = input.nextInt();
            int k = input.nextInt();
            String str = "";
            for (int a = 0; a < k; a++) {
                str += "1";
            }
            for (int b = 0; b < n - k; b++) {
                str += 0;
            }
            int d = decimal(str);
            for (int c = d; c >= 0; c--) {
                if (isValid(binary(c, n), k)) {
                    System.out.println(binary(c, n));
                }
            }
        }
    }

}
