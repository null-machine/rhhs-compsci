package vitosfamily;

import java.util.Arrays;
import java.util.Scanner;

public class VitosFamily {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ntest = input.nextInt();
        for (int i = 0; i < ntest; i++) {
            int minsum = 0;
            int nhouses = input.nextInt();
            int[] dis = new int[nhouses];
            for (int a = 0; a < nhouses; a++) {
                dis[a] = input.nextInt();
            }
            Arrays.sort(dis);

            int median = 0;
            if (dis.length % 2 == 0) {
                median = (dis[dis.length / 2] + dis[(dis.length / 2) - 1]) / 2;
            } else {
                median = dis[dis.length / 2];
            }
            for (int k = 0; k < dis.length; k++) {
                minsum += (Math.abs(median - dis[k]));
            }
            System.out.println(minsum);
        }
    }
}
