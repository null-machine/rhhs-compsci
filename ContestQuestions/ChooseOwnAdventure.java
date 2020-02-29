package ccc1402185;

import java.util.Scanner;

public class Main {
        
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[][] pages = new int[n][n]; // [number of pages][space for page pointers]
        
        for(int i = 0; i < n-1; i++){
            if(input.nextInt() == 0) pages[i][0] = 0;
            else{
                for(int j = 0; j < input.nextInt() - 1; j++){ 
                    pages[i][j] = input.nextInt();
                }
            }
        }
        
        boolean reachable = true;
        boolean[] reached = new boolean[n];
        reached[0] = true;
        
        for(int i = 0; i < n-1; i++){ // spider script
            for(int j = 1; j < n - 1; j++){
                if(pages[n][j] != 0){
                    reached[j] = true;
                }
            }
        }
        
        for(int i = 0; i < n-1; i++){
            if(!reached[i]) reachable = false;
        }
        
        if(reachable) System.out.println("Y");
        else System.out.println("N");
        
    }
}

