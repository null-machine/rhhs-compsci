package favoritetimes;

import java.util.Scanner;

public class Main {

    public static boolean isArithmetic(int n) {
        String s = Integer.toString(n);
        int diff = s.charAt(0) - s.charAt(1);

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) - s.charAt(i + 1) != diff) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int min = input.nextInt();
        int count = 0;

        if(min / 1440 > 0){
            count += min / 1440 * 29;
            min = min / 1440 + min % 1440;
        }
        
        if (min < 60) {
            for (int i = 200; i <= 1200 + min; i++) {
                if (isArithmetic(i)) {
                    count++;
                }
            }
        } else {
            count++;
            for (int i = 0; i <= min / 60 * 100 + min % 60; i++) { // Doesn't allow invalid times, e.g. 4:68
                if (isArithmetic(i)) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
