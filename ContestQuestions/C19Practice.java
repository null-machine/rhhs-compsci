package c19practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class C19Practice {

    public static void fun2(Map<String, String> m1) {

    }

    public static boolean isUnique(Map<String, String> m1) {
        boolean unique = true;
        for (String k1 : m1.keySet()) {
            for (String k2 : m1.keySet()) {
                if (!k1.equals(k2) && m1.get(k1).equals(m1.get(k2))) {
                    unique = false;
                }
            }
        }
        return unique;
    }

    public static int maxOccurances(List<Integer> ll) {
        Map<Integer, Integer> m1 = new HashMap<>();
        for (int i = 0; i < ll.size(); i++) {
            if (m1.containsKey(m1.get(i))) {
                m1.put(ll.get(i), m1.get(1) + 1);
            } else {
                m1.put(ll.get(i), 1);
            }
        }
        int max = 0;
        for (int k : m1.keySet()) {
            if (m1.get(k) > max) {
                max = m1.get(k);
            }
        }
        return max;

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, String> m1 = new HashMap<>();
        m1.put("Marty", "Stepp");
        m1.put("Stuart", "Regers");
        m1.put("Kendrick", "Stepp");
        if (!isUnique(m1)) {
            System.out.println("Pass");
        }
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(2);
        l1.add(2);
        l1.add(3);
        if (maxOccurances(l1) == 2) {
            System.out.println("Pass");
        }

        Map<String, Integer> tough = new HashMap<>();
        tough.put("John", 1);
        tough.put("Beaver", 2);
        tough.put("Thomas", 3);
        tough.put("Justin", 4);
        Map<String, Integer> fun = new HashMap<>();
        
        
        
    }

}
