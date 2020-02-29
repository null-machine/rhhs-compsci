package chocolatechewsday;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class ChocolateChewsday {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Map<String, Integer> mymap = new HashMap<String,Integer>();
        ArrayList<Integer[]> score = new ArrayList<Integer[]>();
        ArrayList<String> name = new ArrayList<String>();
        String str = input.next();
        while(!str.equals("*")){
            if(!str.equals("J")){
                name.add(str);
            } else if (str.equals("J")){
                Integer [] arr = new Integer[3];
                for(int i = 0; i < 3; i++){
                    int s = input.nextInt(); // Total Score
                    Integer x1 = new Integer(s);
                    arr[i] = s;
                }
                score.add(arr);
            }
            str = input.next();
        }
        

    }

}
