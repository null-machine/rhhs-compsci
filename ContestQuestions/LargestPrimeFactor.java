package largestprimefactor;

import java.util.Scanner;

public class LargestPrimeFactor {

    public static boolean isPrime(int n) {
        int nFactor = 0;
        for (int i = 0; i <= (int) (Math.sqrt(n)); i++) {
            if (n % i == 0) {
                nFactor++;
            }
        }
        return nFactor == 1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int max = 0;

        for (int i = 0; i <= n; i++) {
            if (n % i == 0 && isPrime(i)) {
                max = i;
            }
        }
        System.out.println(max);
    }
}
