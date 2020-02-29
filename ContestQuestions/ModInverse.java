package modinverse;

import java.util.Scanner;

public class ModInverse {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int m = input.nextInt();
        int n = 1;
        
        while ((n < m) && (x * n % m == 1)) {
            n++;
        }
        
        if (n >= m) {
            System.out.println("No such integer exists");
        } else {
            System.out.println(n);
        }
    }
}
