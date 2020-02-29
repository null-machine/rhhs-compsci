/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donotpassmetheball;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class DoNotPassMeTheBall {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println((n + 1) * (n - 2) * (n - 3) / 6);
    }
}
