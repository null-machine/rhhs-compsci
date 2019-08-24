import java.util.Scanner;

public class Main {

    public static boolean isPalindrome(String str) {
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
        String word = input.next();
        String palindrome = "";
        int max = 0;
        
        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j < word.length(); j++) {
                if (isPalindrome(word.substring(i, j + 1))) { // Checks every possible combination
                    if (word.substring(i, j + 1).length() > max) { // Updates max length
                        max = word.substring(i, j + 1).length();
                        palindrome = word.substring(i, j + 1);
                    }
                }
            }
        }
        
        System.out.println(palindrome);
        System.out.println(max);
    }
}
