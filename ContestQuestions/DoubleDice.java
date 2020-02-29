/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaf16c15readingandwriting;

import java.io.*;

/**
 *
 * @author Glen
 */
public class DoubleDice {

    public static void main(String[] args) {
        try { // Checks if file exists
            int a1, a2, a3; // Angles in triangle
            FileReader fr = new FileReader("j3.1.in");
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            int nrounds = Integer.parseInt(line);
            int D = 100, A = 100;
            int ds, as;
            for (int i = 0; i < nrounds; i++) {
                line = br.readLine();
                String[] str = line.split(" "); // Splits string into array by spaces
                as = Integer.parseInt(str[0]);
                ds = Integer.parseInt(str[1]);
                if (as > ds) {
                    D = D - as;
                } else if (ds > as) {
                    A = A - ds;
                }
                System.out.println(A);
                System.out.println(D);
            }
            br.close();
        } catch (IOException e) {
        }
    }
}
