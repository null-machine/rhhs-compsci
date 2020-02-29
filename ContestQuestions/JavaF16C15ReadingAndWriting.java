/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaf16c15readingandwriting;

import java.io.*;

/**
 *
 * @author Nanthi
 */
public class JavaF16C15ReadingAndWriting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {

            FileWriter fw = new FileWriter("input1.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Hello");
            pw.println("World");
            pw.close();
        } catch (IOException e) {
        }

    }

}
