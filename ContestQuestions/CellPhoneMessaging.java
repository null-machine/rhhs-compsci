// Doesn't work in PEG

package cellphonemessaging;

import java.util.Scanner;

public class CellPhoneMessaging {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        String temp1 = ""; // Used to convert char to string
        String temp2 = "";
        int time = 0;

        while (!(word.equals("halt"))) {

            for (int i = 0; i < word.length(); i++) {
                temp1 = "";
                temp2 = "";
                temp1 = temp1 + word.charAt(i);
                if (i > 0) {
                    temp2 = temp2 + word.charAt(i - 1);
                }

                if ((temp1.equals("a") || temp1.equals("b") || temp1.equals("c")) && (temp2.equals("a") || temp2.equals("b") || temp2.equals("c"))) {
                    time += 2;
                } else if ((temp1.equals("d") || temp1.equals("e") || temp1.equals("f")) && (temp2.equals("d") || temp2.equals("e") || temp2.equals("f"))){
                    time += 2;
                } else if ((temp1.equals("g") || temp1.equals("h") || temp1.equals("i")) && (temp2.equals("g") || temp2.equals("h") || temp2.equals("i"))){
                    time += 2;
                } else if ((temp1.equals("j") || temp1.equals("k") || temp1.equals("l")) && (temp2.equals("j") || temp2.equals("k") || temp2.equals("l"))){
                    time += 2;
                } else if ((temp1.equals("m") || temp1.equals("n") || temp1.equals("o")) && (temp2.equals("m") || temp2.equals("n") || temp2.equals("o"))){
                    time += 2;
                } else if ((temp1.equals("j") || temp1.equals("k") || temp1.equals("l")) && (temp2.equals("j") || temp2.equals("k") || temp2.equals("l"))){
                    time += 2;
                } else if ((temp1.equals("p") || temp1.equals("q") || temp1.equals("r") || temp1.equals("s")) && ((temp2.equals("p") || temp2.equals("q") || temp2.equals("r") || temp2.equals("s")))){
                    time += 2;
                } else if ((temp1.equals("t") || temp1.equals("u") || temp1.equals("v")) && (temp2.equals("t") || temp2.equals("u") || temp2.equals("v"))){
                    time += 2;
                } else if ((temp1.equals("w") || temp1.equals("x") || temp1.equals("y") || temp1.equals("z")) && ((temp2.equals("w") || temp2.equals("x") || temp2.equals("y") || temp2.equals("z")))){
                    time += 2;
                }
                
                if (temp1.equals("a") || temp1.equals("d") || temp1.equals("g") || temp1.equals("j") || temp1.equals("m") || temp1.equals("p") || temp1.equals("t") || temp1.equals("w")) {
                    time++;
                } else if (temp1.equals("b") || temp1.equals("e") || temp1.equals("h") || temp1.equals("k") || temp1.equals("n") || temp1.equals("q") || temp1.equals("u") || temp1.equals("v")) {
                    time += 2;
                } else if (temp1.equals("c") || temp1.equals("f") || temp1.equals("i") || temp1.equals("l") || temp1.equals("o") || temp1.equals("r") || temp1.equals("v") || temp1.equals("y")) {
                    time += 3;
                } else if (temp1.equals("s") || temp1.equals("z")) {
                    time += 4;
                }
            }

            System.out.println(time);
            time = 0;
            word = input.nextLine();
        }

    }
}
