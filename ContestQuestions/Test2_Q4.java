package test2_q4;

import java.util.Scanner;

public class Test2_Q4 {

    public static int toInteger(String numeral) {

        int number = 0;
        int next = 0; // Always add last value

        for (int i = numeral.length() - 1; i > -1; i--) { // Parse values from right to left
            switch (numeral.charAt(i)) {
                case 'I':
                    if (next > 1) { // If current value is less than next value, subtract current value
                        number--;
                    } else {
                        number++; // If current value is greater than or equal to next value, add current value
                    }
                    next = 1; // Update next value
                    break;
                case 'V':
                    if (next > 5) {
                        number -= 5;
                    } else {
                        number += 5;
                    }
                    next = 5;
                    break;
                case 'X':
                    if (next > 10) {
                        number -= 10;
                    } else {
                        number += 10;
                    }
                    next = 10;
                    break;
                case 'L':
                    if (next > 50) {
                        number -= 50;
                    } else {
                        number += 50;
                    }
                    next = 50;
                    break;
                case 'C':
                    if (next > 100) {
                        number -= 100;
                    } else {
                        number += 100;
                    }
                    next = 100;
                    break;
                case 'D':
                    if (next > 100) {
                        number -= 100;
                    } else {
                        number += 100;
                    }
                    next = 100;
                    break;
                case 'M':
                    if (next > 1000) {
                        number -= 1000;
                    } else {
                        number += 1000;
                    }
                    next = 1000;
                    break;
            }
        }
        return (number);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        
        for (int i = 0; i < n + 1; i++) {
            String equation = input.nextLine();

            int index1 = equation.indexOf("+"); // Isolate first numeral
            String numeral1 = (equation.substring(0, index1 + 1));
            int value1 = toInteger(numeral1);

            int index2 = equation.indexOf("="); // Isolate second numeral
            String numeral2 = (equation.substring(index1 + 1, index2 + 1));
            int value2 = toInteger(numeral2);

            if (value1 + value2 > 1000) {
                System.out.println("CONCORDIA CUM VERITATE");
            } else {
                System.out.println(value1 + value2);
            }

        }
    }
}
