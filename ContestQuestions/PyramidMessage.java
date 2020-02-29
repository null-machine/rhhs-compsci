package pyramidmessage;

import java.util.Scanner;

public class PyramidMessage {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int l, n, p, max;
        String s[], hold[];
        l = input.nextInt();
        for (int i = 0; i < l; i++) {
            n = input.nextInt();
            s = new String[n];
            hold = new String[n];
            for (int j = 0; j < n; j++) {
                hold[j] = input.nextLine();
            }
            max = 0;
            s[0] = hold[n - 1];
            p = 0;

            for (int j = 0; j < n; j++) {
                int look = 0;
                while (look <= p && !hold[i].equals(s[look])) {
                    look++;
                }
                s[look] = hold[i];
                p = look;
                if (p >= max) {
                    max = p;
                }
            }
            System.out.println((n * 10 - max * 20) * 2);
        }
    }
}
