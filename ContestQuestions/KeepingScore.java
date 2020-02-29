package keepingscore;

import java.util.Scanner;

public class KeepingScore {

    public static int score(String str) {
        int points = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                points += 4;
            } else if (str.charAt(i) == 'K') {
                points += 3;
            } else if (str.charAt(i) == 'Q') {
                points += 2;
            } else if (str.charAt(i) == 'J') {
                points += 1;
            }
        }
        if (str.length() == 2) {
                points += 2;
            } else if (str.length() == 1) {
                points += 1;
            } else if (str.equals(null)) {
                points += 3;
            }
        return points;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String str = input.next();
        String c = "";
        int cIndex = 0;
        String d = "";
        int dIndex = 0;
        String h = "";
        int hIndex = 0;
        String s = "";
        int sIndex = 0;

        cIndex = str.indexOf('C');
        dIndex = str.indexOf('D');
        hIndex = str.indexOf('H');
        sIndex = str.indexOf('S');

        c = str.substring(cIndex + 1, dIndex);
        d = str.substring(dIndex + 1, hIndex);
        h = str.substring(hIndex + 1, sIndex);
        s = str.substring(sIndex + 1, cIndex);
        
        System.out.println("Cards dealt:    Points");
        System.out.println("Clubs " + c + "\t" + score(c));
        System.out.println("Diamonds " + d + "\t" + score(d));
        System.out.println("Hearts " + h + "\t" + score(h));
        System.out.println("Spades " + s + "\t" + score(s));
        System.out.println("        Total " + (score(c) + score(d) + score(h) + score(s)));
        
    }
}
