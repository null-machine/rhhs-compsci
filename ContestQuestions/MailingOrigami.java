/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailingorigami;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Glen
 */
public class MailingOrigami {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int nvertex = input.nextInt();
        int[] xcor = new int[nvertex];
        int[] ycor = new int[nvertex];
        for(int i = 0; i < nvertex; i++){
            xcor[i] = input.nextInt();
            ycor[i] = input.nextInt();
        }
        Arrays.sort(xcor);
        Arrays.sort(ycor);
        System.out.println((xcor[xcor.length-1] = xcor[0]) * (ycor[ycor.length - 1] - ycor[0]));
    }
}