package longestsubstring;

import java.util.ArrayList;
import java.util.Scanner;

public class LongestSubstring {

    public static boolean isUnique(ArrayList<Integer> arr, int min, int max) {

        boolean isU = true;
        for (int i = min; i < max; i++) {
            for (int j = min; j < max; j++) {
                if (i != j && arr.get(i) == arr.get(j)) {
                    isU = false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>();

        /*
        String n = "";
        
        String user = input.nextLine();
        while(!user.equals("0")){
            n = n.concat(user);
            user = input.nextLine();
        }
         */
        while (x != 0) {
            arr.add(x);
            x = input.nextInt();
        }

        int maxlength = 0;
        String temp = "";
        String longest = "";
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size(); j++) {
                if (isUnique(arr, i, j)) {
                    for (int a = i; a <= j; a++) {
                        temp = temp + arr.get(a);
                        if (temp.length() > maxlength) {
                            maxlength = temp.length();
                            longest = temp;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < longest.length(); i++) {
            System.out.println(longest.charAt(i));
        }

    }
}
