package exactlyelectrical;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int x1 = input.nextInt();
        int y1 = input.nextInt();
        int x2 = input.nextInt();
        int y2 = input.nextInt();
        int charge = input.nextInt();

        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);

        //System.out.println(distance);
        
        if (charge < distance) {
            System.out.println("N");
        } else if (charge == distance) {
            System.out.println("Y");
        } else if (((charge - distance) % 2) == 0) {
            System.out.println("Y");
        } else {
            System.out.println("N");
        }

    }

}
