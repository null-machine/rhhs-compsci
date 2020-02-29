package spellcheck;

import java.util.Scanner;

public class SpellCheck {

    public static String correctWord(String str) {
        int index1 = str.indexOf("ie");
        int index2 = str.indexOf("ei");
        if(index1 >= 0){
            if(str.charAt(index1 - 1) == 'c'){
                return str.substring(0, index1) + "e" + str.substring(0, 5);
            }else{
                return str;
            }
        }
        else if (index2 >= 0) {
        
        }
        return "a";
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String word = input.next();
        int count = 1;
        while (!(word.equals("No More Words!"))) {
            if (isCorrect(word)) {
                System.out.println("Word " + count + " is correct.");
            } else {
                System.out.println(corectWord(word));
            }
            word = input.next();
            count++;
        }
    }
}
