package mode;

import java.util.Scanner;
import java.util.ArrayList;

public class Mode {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Integer> num = new ArrayList<Integer>();
        int n = input.nextInt();
        while (n != -1) {
            num.add(n);
            n = input.nextInt();
        }

        int max = num.get(0);
        for (int i = 0; i < num.size(); i++) {
            if (num.get(i) >= max) {
                max = num.get(i);
            }
        }
        int[] arr = new int[max + 1];
        for (int i = 0; i < num.size(); i++) {
            arr[num.get(i)]++;
        }

        int mode = -1;
        int maximum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= maximum) {
                mode = i;
                maximum = arr[i];
            }
        }

        System.out.println(mode);
    }
}
