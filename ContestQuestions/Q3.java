package q3;

import java.util.*;

/**
 *
 * @author Lily
 */
public class Q3 {

    public static String before(String a, String b, int c) {
        if (c > b.length() - 1) {
            return b;
        } else if (c > a.length() - 1) {
            return a;
        }
        if (b.charAt(c) < a.charAt(c)) {
            return b;
        } else if (b.charAt(c) == a.charAt(c)) {
            return before(a, b, c + 1);
        } else {
            return a;
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        HashMap<String, String> users = new HashMap<String, String>();
        HashMap<String, Integer> clubs = new HashMap<String, Integer>();
        String in = input.nextLine();

        String current = "";
        while (!in.equals("1")) {
            if (in != "1") {
                if (in.equals(in.toLowerCase())) {
                    if (users.containsKey(in)) {
                        if (!current.equals(users.get(in))) {
                            users.remove(in);
                            users.put(in, "none");
                        }
                    } else {
                        users.put(in, current);
                    }
                } else {
                    clubs.put(in, 0);
                    current = in;
                }
            }
            in = input.nextLine();
        }
        for (String k : users.keySet()) {
            String str = users.get(k);
            if (str != "none" && clubs.containsKey(str)) {
                int counter = clubs.get(str);
                clubs.put(str, counter + 1);
            }
        }
        while (!clubs.isEmpty()) {
            String key = "";
            int max = -1;
            for (String k : clubs.keySet()) {
                if (clubs.get(k) > max) {
                    key = k;
                    max = clubs.get(k);
                } else if (clubs.get(k) == max) {
                    key = before(k, key, 0);
                }
            }
            System.out.println(key + " " + clubs.get(key));
            clubs.remove(key);
        }

    }

}
