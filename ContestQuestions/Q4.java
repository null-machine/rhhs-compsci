package q4;

import java.util.Scanner;

public class Q4 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String season = input.nextLine();
        String team[] = new String[10];
        double score[][] = new double[10][7]; // [Team Number][Games Played, At Bats, Runs, Total Hits, Two-Base Hits, Three-Base Hits, Home Runs]
        for (int i = 0; i < 10; i++) { 
            team[i] = input.next();
            for (int j = 0; j < 7; j++) {
                score[i][j] = input.nextInt();
            }
        }

        double bat[] = new double[10];
        double slug[] = new double[10];
        for (int i = 0; i < 10; i++) {
            bat[i] = (score[i][3] / score[i][1]);
            slug[i] = ((score[i][3] - score[i][4] - score[i][5] - score[i][6]) + score[i][4] * 2 + score[i][5] * 3 + score[i][6] * 4) / score[i][1];
        }
        double tBat = 0;
        double tSlug = 0;
        for(int i = 0; i < 10; i++){
            tBat += bat[i];
            tSlug += slug[i];
        }
        tBat = tBat / 10;
        tSlug = tSlug / 10;
        
        System.out.println(season + "\n ====================");
        for (int i = 0; i < 10; i++) {
            System.out.println(team[i] + ": " + bat[i] + " " + slug[i]);
        }
        System.out.println("==================== \n Big 10 Av: " + tBat + " " + tSlug);
        
    }
}
