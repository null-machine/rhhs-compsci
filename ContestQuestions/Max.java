package max;

import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Max {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0000");
        int n = input.nextInt();
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextDouble();
        }
        Arrays.sort(arr);

        System.out.println(df.format(arr[arr.length - 1]));
    }
}