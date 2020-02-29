package poetry;

import java.util.Scanner;

public class Poetry {


    public static String syllable(String str) {
        String vowel = "aeiouAEIOU";
        int index = -1;
        for (int i = 0; i < str.length(); i++) {
            if (vowel.indexOf(str.charAt(i)) >= 0) {
                index = i;
            }
        }
        if (index >= 0) {
            return str.substring(index + 1);
        }
        return str.split(" ")[(str.split(" ").length - 1)];
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int npoem = input.nextInt();
        String[] poem = new String[4];
        poem[0] = input.nextLine();
        for (int i = 0; i < npoem; i++) {
            String[] rhymes = new String[4];
            for (int j = 0; j < 4; j++) {
                poem[i] = input.nextLine().toLowerCase();
                rhymes[i] = syllable(poem[i]);
            }
            if (rhymes[0].equals(rhymes[1]) && rhymes[1].equals(rhymes[2]) && rhymes[2].equals(rhymes[3])) {
                System.out.println("perfect");
            } else if (rhymes[0].equals(rhymes[2]) && rhymes[1].equals(rhymes[3])) {
                System.out.println("cross");
            } else if (rhymes[0].equals(rhymes[1]) && rhymes[2].equals(rhymes[3])) {
                System.out.println("even");
            } else if (rhymes[0].equals(rhymes[3]) && rhymes[1].equals(rhymes[2])) {
                System.out.println("shell");
            }
        }
    }
}
