/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainswapping;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class TrainSwapping {
    
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for(int i = 0; i < n; i++){
            int nt = input.nextInt();
            int swap = 0;
            int [] intarr = new int[nt];
            for(int j = 0; j < nt; j++){
                intarr[j] = input.nextInt();
            }
            for(int a = 0; a < nt; a++){
                for(int b = 0; b < nt - 1; b++){
                    if(intarr[b] > intarr[b+1]){
                        int temp = intarr[b];
                        intarr[b] = intarr[b + 1];
                        intarr[b + 1] = temp;
                        swap++;
                    }
                }
            }
            System.out.println("Optimal train swapping takes " + swap + " swap(s).");
        }
    }
}
