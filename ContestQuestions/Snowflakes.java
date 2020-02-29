/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snowflakes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class Snowflakes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        int ntestcase = input.nextInt();
        for(int i = 0; i < ntestcase; i++){
            int nflakes = input.nextInt();
            Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
            for(int j = 0; i < nflakes; j++){
                int flakesize = input.nextInt();
                if(myMap.containsKey(flakesize)){
                    myMap.put(flakesize, myMap.get(flakesize) + 1);
                } else {
                    myMap.put(flakesize, 1);
                }
            }
            System.out.println(myMap.keySet().size());
        }
    }
    
}
