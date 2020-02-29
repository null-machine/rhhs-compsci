package modehashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModeHashMap {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<Integer, Integer> mode = new HashMap<Integer, Integer>();
        int n = input.nextInt();
        while (n != -1) {
            if (mode.containsKey(n)) {
                mode.put(n, mode.get(n) + 1);
            } else {
                mode.put(n, 1);
            }
            n = input.nextInt();
        }
        
        int max = Integer.MIN_VALUE;
        for(int k : mode.keySet()){
            if(mode.get(k) >= max){
                max = k;
            }
        }
        
        System.out.println(max);

    }
}