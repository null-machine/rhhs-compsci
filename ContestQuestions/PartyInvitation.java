/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaf16c15readingandwriting;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Glen
 */
public class PartyInvitation {

    public static void main(String[] args) {
        int numfriends;
        int removal;
        ArrayList<Integer> friends = new ArrayList<Integer>();
        try { // Checks if file exists
            FileReader fr = new FileReader("j4.1.in");
            BufferedReader br = new BufferedReader(fr);
            numfriends = Integer.parseInt(br.readLine());
            removal = Integer.parseInt(br.readLine());
            int[] rarr = new int[removal];
            for (int i = 1; i <= numfriends; i++) {
                friends.add(i);
            }
            for (int i = 0; i < removal; i++) { // Get removals
                rarr[i] = Integer.parseInt(br.readLine());
            }
            for (int i = 0; i < removal; i++) {
                for (int j = 0; j < friends.size(); j++) {
                    if ((j + 1) % rarr[i] == 0) {
                        friends.set(j, 0);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
        }
    }
}
