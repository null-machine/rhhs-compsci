package readinglogs;

import java.util.Scanner;

public class ReadingLogs {

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int days = input.nextInt();
        int pages = input.nextInt();
        String code = input.next();
        int n = input.nextInt();
        String [] name = new String[n];
        int [] pr = new int[n];
        String [] ccode = new String[n];
        
        for(int i = 0; i < n; i++){
            name[i] = input.next();
            pr[i] = input.nextInt();
            ccode[i] = input.next();
        }
        int min = Integer.MAX_VALUE;
        String pen = "";
        for(int i = 0; i < pr.length; i++){
            if(pages / pr[i] <= days){
                if(min <= pr[i]){
                    pen = name[i];
                }
            }   
        }
        System.out.println("the most efficient reader is " + pen + ".");
        System.out.println("This is the most efficient reader.");
    }
}
