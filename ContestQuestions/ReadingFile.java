/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaf16c15readingandwriting;

import java.io.*; // Used for file management

/**
 *
 * @author Nanthi
 */
public class ReadingFile {

    public static void main(String[] args) {
        try { // Checks if file exists
            int a1, a2, a3; // Angles in triangle
            FileReader fr = new FileReader("j1.3.in");
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            a1 = Integer.parseInt(line);
            line = br.readLine();
            a2 = Integer.parseInt(line);
            line = br.readLine();
            a3 = Integer.parseInt(line);

            if (a1 + a2 + a3 == 180) {
                if (a1 == a2 && a1 == a3 && a2 == a3) {
                    System.out.println("Equilateral");
                } else if (a1 == a2 || a1 == a3 || a2 == a3) {
                    System.out.println("Isoceles");
                } else {
                    System.out.println("Scalene");
                }
            } else {
                System.out.println("Error");
            }

            br.close();
        } catch (IOException e) {
        }
    }
}
