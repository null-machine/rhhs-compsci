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
public class UniqueIntegers {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            if (map.containsKey(x)) { // Checks is value exists
                map.put(x, map.get(x) + 1); // Returns value associated with key value
            } else {
                map.put(x, i);
            }
        }
        int unique = 0;
        for(Integer x : map.keySet()){
            if(map.get(x) == 1){
                unique++;
            }
            System.out.println(unique);
        }
    }
}
