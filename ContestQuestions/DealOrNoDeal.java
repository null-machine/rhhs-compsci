/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealornodeal;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class DealOrNoDeal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int n = input.nextInt();
        
        int total = 1691600;
        int amount = 0;
        
        for(int i = 0; i < n; i++){
            amount = input.nextInt();
            if(amount == 1){
                total -= 100;
            }
            if(amount == 2){
                total -= 500;
            }
            if(amount == 3){
                total -= 1000;
            }
            if(amount == 4){
                total -= 5000;
            }
            if(amount == 5){
                total -= 1000;
            }
            if(amount == 6){
                total -= 25000;
            }
            if(amount == 7){
                total -= 50000;
            }
            if(amount == 8){
                total -= 100000;
            }
            
            if(amount == 9){
                total -= 500000;
            }
            if(amount == 10){
                total -= 1000000;
            }
        }
        
        int offer = input.nextInt();
        
        if(total / n < offer){
            System.out.println("deal");
        } else {
            System.out.println("no deal");
        }
        
    }
    
}
