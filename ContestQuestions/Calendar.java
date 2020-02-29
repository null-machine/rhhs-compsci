package calendar;

import java.util.Scanner;

public class Calendar {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int start = input.nextInt();
        int end = input.nextInt();
        int day = 0;

        System.out.println("Sun\tMon\tTue\tWed\tThr\tFri\tSat");

        for (int i = 0; i < start - 1; i++) {
            System.out.print("\t");
            day++;
        }

        for (int i = 1; i < end + 1; i++) {
            if (day > 6) {
                System.out.println();
                day = 0;
            }
            System.out.print(i + "\t");
            day++;
        }
        
    }
}