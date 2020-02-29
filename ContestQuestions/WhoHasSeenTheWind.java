/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whohasseenthewind;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class WhoHasSeenTheWind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int h = input.nextInt();
        int m = input.nextInt();
        int t = 1;
        double A = -6 * Math.pow(t, 4) + h * Math.pow(t, 3) + 2 * Math.pow(t, 2) + t;
        while(A > 0 && t < m){
            A = -6 * Math.pow(t, 4) + h * Math.pow(t, 3) + 2 * Math.pow(t, 2) + t;
            t++;
        }
        if(A < 1){
            System.out.println("The balloon first touches ground at hour:\n" + (t - 1));
        } else {
            System.out.println("The balloon does not touch ground in the given time.");
        }
    }
    
}