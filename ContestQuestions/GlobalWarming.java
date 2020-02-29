package globalwarming;

import java.util.Scanner;

public class GlobalWarming {

    public static int [] diff (int [] arr){
        int [] temp = new int [arr.length-1];
        for(int i = 0; i < temp.length; i++){
            temp[i] = arr[i+1] - arr [i];
        }
        return temp;
    }
    
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int [] arr = new int[n];
        
        for(int i = 0; i < arr.length; i++){
            arr[i] = input.nextInt();
        }
        int [] arr2 = diff(arr);
        
        int begin = arr2[0];
        int pattern = 0;
        if(n == 0){
            
        }
    }

}
