/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smilewithsimilies;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class SmileWithSimilies {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        String [] x = new String [n];
        String [] y = new String [m];
        for(int i = 0; i < n; i++){
            x[i] = input.next();
        }
        for(int i = 0; i < m; i++){
            y[i] = input.next();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println(x[i] + " as " + y[j]);
            }
        }
    }
}
