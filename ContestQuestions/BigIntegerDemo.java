/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigintegerdemo;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class BigIntegerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num1 = (int) (Math.pow(2, 31));
        System.out.println(num1);
        BigInteger num2 = new BigInteger("100");
        BigInteger num3 = new BigInteger("300");
        System.out.println(num2.add(num3));
        BigInteger num4 = input.nextBigInteger();
        BigInteger num5 = input.nextBigInteger();
        System.out.println(num4.add(num5).toString().length());
        System.out.println(num4.subtract(num5));
        System.out.println(num4.divide(num5));
        System.out.println(num4.divideAndRemainder(num5)[0]);
        System.out.println(num4.divideAndRemainder(num5)[1]);
        System.out.println(num4.multiply(num5));
    }
}