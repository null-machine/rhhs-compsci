/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class Recursion {

    /**
     * @param args the command line arguments
     */
    public static int sum(int n){
        if(n == 0){ // Base case, recursion should reach case eventually, else stack overflow
            return 0;
        }else{
            return n + sum(n - 1);
        }
    }
    
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }else{
            return n * factorial(n - 1);
        }
    }
    
    public static int fibonacci(int n){
        if(n == 1 || n == 2){
            return 1;
        }else{
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    
    public static String strFibonacci(int n){
        if(n == 0){
            return "0";
        }else if(n == 1){
            return "1";
        }else{
            return strFibonacci(n - 1) + strFibonacci(n - 2);
        }
    }
    
    public static boolean isPalindrome(String str){
        if(str.length() == 1 || (str.length() == 2 && str.charAt(0) == str.charAt(1))){
            return true;
        }else if(str.charAt(0) != str.charAt(str.length() - 1)){
            return false;
        }else{
            return isPalindrome(str.substring(1, str.length() -1));
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        System.out.println(isPalindrome("aabbaa"));
    }
    
}
