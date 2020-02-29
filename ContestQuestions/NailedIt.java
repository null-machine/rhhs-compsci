package nailedit;

import java.util.Scanner;

public class NailedIt {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] lengths = new int[n];
        for (int i = 0; i < n; i++) {
            lengths[i] = input.nextInt();
        }
        int[] sums = new int[n * n];
        int length = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    sums[i] = lengths[j] + lengths[k];
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(sums[i] == sums[j]){
                    length++;
                }
            }
        }
        
        
        
    }
}
