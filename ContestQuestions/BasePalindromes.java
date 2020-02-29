package basepalindromes;

import java.util.Scanner;

public class BasePalindromes {

    public static boolean isPalindrome(String str){
        if (str.length() == 1 || str.length() == 0) {
            return true;
        } else if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return isPalindrome(str.substring(1, str.length() - 1)); // Compares first and last characters
        } else {
            return false;
        }
    }
    
            
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        for(int i = 2 ; i < x; i++){
            if(isPalindrome(Integer.toString(x, i))){
                System.out.println(i);
            }
        }
        
    }
}
