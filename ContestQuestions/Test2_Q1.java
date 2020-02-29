package test2_q1;

import java.util.Scanner;
import java.math.BigInteger;

public class Test2_Q1 {

    public static boolean isPrime(BigInteger n) { // Checks if n really is prime or not

        // I don't know "for loop" syntax with BigInteger
        for (BigInteger i = new BigInteger("0"); i.compareTo(n.divide("2") = 1; i.add("1")) {
            if ((n.divideAndRemainder(i)).equals(0)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BigInteger n = new BigInteger("1");
        while (!(n.equals("0"))) { // Parses input
            n = input.nextBigInteger();
            for (int a = 3; a < n.subtract("1"); a++) {
                if ((Math.pow(a, n).divideAndRemainder(n)).equals(a) && isPrime(n)) { // Checks using the Fermat method
                    System.out.println("The number " + n + "is a a Carmichael number.");
                } else {
                    System.out.println(n + " is normal.");
                }
            }
        }
    }
}
