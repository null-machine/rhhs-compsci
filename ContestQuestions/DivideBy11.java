package divideby11;

import java.math.BigInteger;
import java.util.Scanner;

public class DivideBy11 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BigInteger num1 = new BigInteger("10");
        BigInteger num2 = input.nextBigInteger();
        BigInteger unitDigit;
        
        while(num2.toString().length() > 2){
            unitDigit = num2.divideAndRemainder(num1)[1];
            num2 = num2.divide(num1);
            num2 = num2.subtract(unitDigit);
        }
        
        if(num2.toString().equals("11")){
            
        }
    }
    
}
