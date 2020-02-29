/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Glen
 */
public class Maps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Map<String, Integer> myMap = new HashMap<String, Integer>(); // Declaring a map
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            String str = input.next();
            if (myMap.containsKey(str)) { // Checks is value exists
                myMap.put(str, myMap.get(str) + 1); // Returns value associated with key value
            } else {
                myMap.put(str, i);
            }
        }
        int unique = 0;
        System.out.println("Key" + "\t" + "Value");
        for(String str : myMap.keySet()){
            System.out.println(str + "\t" + myMap.get(str));
            if(myMap.get(str) == 1){
                unique++;
            }
            System.out.println(unique);
        }
    }
}
