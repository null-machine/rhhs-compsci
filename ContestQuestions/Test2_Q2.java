package test2_q2;

import java.util.Scanner;

public class Test2_Q2 {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String line = input.nextLine();
        
        for (int i = 0; i < n; i++) {
            line = input.nextLine();
            
            String[] words = line.split(" ");
            
            String censor = "";
            for (int j = 0; j < words.length; j++) {
                if (words[j].length() == 4) {
                    censor = censor + "**** ";
                } else {
                    censor = censor + words[j] + " ";
                }
            }
            
            System.out.println(censor);
        }
    }
}
