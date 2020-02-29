package spreadsheet;

import java.util.Scanner;

public class Spreadsheet {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String sheet[][] = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sheet[i][j] = input.next();
            }
        }
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //if(sheet[i][j] isn't an integer)
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(sheet[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
