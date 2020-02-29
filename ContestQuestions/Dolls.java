package dolls;

import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Dolls {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] dolls = new int[n];
        for(int i = 0; i < n; i++){
            dolls[i] = input.nextInt();
        }
        
        HashMap<Integer, Integer> prices = new HashMap<>();
        
        for (int i = 0; i < n; i++){
            if (prices.containsKey(dolls[i])){
                prices.put(dolls[i], prices.get(dolls[i]) + 1);
            } else {
                prices.put(dolls[i], 1);
            }            
        }
        
        System.out.println(prices);
    
        int largest = Collections.max(prices.values());
        int smallest = Collections.min(prices.values());

        System.out.println(largest);
        System.out.println(smallest);
        
    }
}
