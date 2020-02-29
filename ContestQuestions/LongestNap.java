package longestnap;

import java.util.Scanner;

public class LongestNap {

    public static int duration(String stime, String etime) {
        int sh = Integer.parseInt(stime.substring(0, 2));
        int sm = Integer.parseInt(stime.substring(3, 5));
        int eh = Integer.parseInt(stime.substring(0, 2));
        int em = Integer.parseInt(stime.substring(3, 5));
        int hour = eh - sh;
        int min = 0;
        if (em >= sm) {
            min = em - sm;
        } else {
            hour = hour - 1;
            min = em + 60 - sm;
        }
        return hour * 60 + min;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int app = input.nextInt();
        int n = input.nextInt();
        String[] times = new String[n];
        for (int i = 0; i < n; i++) {

        }
    }
}
