package robberslanguage;

import java.util.Scanner;
public class RobbersLanguage{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.next();
        String vowels = "aeiou";
        String a = "bcdfghjklmnpqrstvwxyz";
        String b = "aaeeeiiiiooooouuuuuuu";
        String c = "cdfghjklmnpqrstvwxyzz";
        String d = "";
        for(int i = 0; i < word.length(); i++){
            if(vowels.indexOf(word.charAt(i)) >= 0){
                d = d + word.charAt(i);
            } else {
                d = d + word.charAt(i) +
                    b.charAt(a.indexOf(word.charAt(i)))+
                    c.charAt(a.indexOf(word.charAt(i)));
            }
        }
        System.out.println(d);
    }
}