package ciphertexts;

import java.util.Scanner;

public class CipherTexts {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        String plain, c1, c2;
        int x;
        
        plain = input.nextLine();
        c1 = input.nextLine();
        c2 = input.nextLine();        
        
        for(int i = 1; i < c2.length(); i++){
            x = c1.indexOf(c2.charAt(i));
            if(x == -1){
                System.out.println(".");
            } else {
                System.out.println(plain.charAt(x));
            }
        }
    }
    
}
