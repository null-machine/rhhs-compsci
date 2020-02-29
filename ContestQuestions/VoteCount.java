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
public class VoteCount {
public static void main(String[] args) {
        try { // Checks if file exists
            FileReader fr = new FileReader("j2.1a.in");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            int n = Integer.parseInt(line);
            String votes = br.readLine();
            int a = 0, b = 0;
            for (int i = 0; i < n; i++) {
                if (votes.charAt(i) == 'A') {
                    a++;
                }
                if (votes.charAt(i) == 'B') {
                    b++;
                }
            }
            if (a > b) {
                System.out.println("A");
            }
            if (a < b) {
                System.out.println("B");
            }
            if (a == b) {
                System.out.println("Tie");
            }
            br.close();
        } catch (IOException e) {
        }
    }
}
