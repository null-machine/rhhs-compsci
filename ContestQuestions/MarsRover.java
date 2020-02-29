package marsrover;

import java.util.Scanner;

public class MarsRover {

    public static void right() {
        System.out.println(2);
    }

    public static void left() {
        System.out.println(3);
    }

    public static void move(int w) {
        System.out.println(1);
        System.out.println(Math.abs(w));
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            int dir = 0;
            int x = 0;
            int y = 0;
            int command = input.nextInt();

            while (command != 0) {
                if (command == 1) {
                    command = input.nextInt();

                    if (dir == 0) {
                        x += command;
                    } else if (dir == 90) {
                        y += command;
                    } else if (dir == 180) {
                        x -= command;
                    } else {
                        y -= command;
                    }
                } else if (command == 2) {
                    dir = dir - 90;
                    if (dir < 0) {
                        dir = 270;
                    }
                } else if (command == 3) {
                    dir += 90;
                    if (dir == 360) {
                        dir = 0;
                    }
                }
                command = input.nextInt();
            }

            System.out.println("Distance is " + (Math.abs(x) + Math.abs(y)));
            if (y > 0 && x > 0 && dir == 0) {
                right();
                move(y);
                right();
                move(x);
            } else if (y > 0 && x > 0 && dir == 90) {
                left();
                move(x);
                left();
                move(y);
            } else if (y > 0 && x > 0 && dir == 180) {
                move(x);
                left();
            } else if (y > 0 && x > 0 && dir == 270) {
                move(y);
                right();
                move(x);
            } else if (y > 0 && x > 0 && dir == 0) {
                move(x);
                right();
                move(y);
            }
        }
    }
}
