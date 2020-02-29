/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coolnumbers;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class CoolNumbers {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        int count = 0;
            for(int i = a; i <= b; i++){
                if(Math.sqrt(i) % 1 == 0 && Math.cbrt(i) % 1 == 0){
                    count++;
                }
            }
        System.out.println(count);
    }
}