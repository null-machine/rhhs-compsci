package railwaysort;

import java.util.Scanner;

public class RailwaySort {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] perm = new int[N];
        for (int i = 0; i < N; i++) {
            perm[i] = in.nextInt() - 1;
        }
        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            boolean move = false;
            int pos = 0;
            while (perm[pos] != i) {
                if (perm[pos++] > i) {
                    move = true;
                }
                if (move) {
                    ans += pos;
                    for (int j = pos; j > 0; j--) {
                        perm[j] = perm[j - 1];
                        perm[0] = i;
                    }
                }
                System.out.println(ans);
            }
        }
    }
}
