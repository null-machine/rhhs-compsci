package rollingencryption;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class RollingEncryption {

    public static String frequent(String str) {
        Map<String, Integer> chars = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (chars.containsKey(str.substring(1, i + 1))) {
                chars.put(str.substring(i, i + 1), chars.get(str.substring(i, i + 1)));
            } else {
                chars.put(str.substring(i, i + 1), 1);
            }
        }

        String temp = "";
        int max = 0;
        for (String k : chars.keySet()) {
            if (chars.get(k) > max) {
                max = chars.get(k);
                temp = k;
            } else {
                if (k.compareTo(temp) < 0) {
                    temp = k;
                }
            }
        }

        return temp;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        map1.put(1, 1);
        map1.put(2,1);
        if(map1.containsKey(1)){
            map1.put(1, map1.get(1) + 1);
        }
        for(int k: map1.keySet()){
            System.out.println(k + "\t" + map1.get(k));
        }
         */
        String str = input.next();
        int n = input.nextInt();
        String text = input.next();
        String alphabet = "abcdefghjklmnopqrstuvwxyz";
        String encrypt = "";
        encrypt = encrypt = str.substring(0, n);
        for (int i = n - 1; i < str.length(); i++) {
            String x = frequent(str.substring(i - n, i));
            int index = alphabet.indexOf(x) + 1;
            encrypt = encrypt + alphabet.charAt(alphabet.indexOf(text.charAt(i) + index /* % */));
        }
        
        System.out.println(encrypt);

    }

}
