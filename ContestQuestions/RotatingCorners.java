package ccc1402184;

/*
to determine rotation, only need to look at numbers in corners
determine rotation, then output rotated grid
store grid in 2d array
*/

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] data = new int[n][n]; // data[column][row]
        
        for(int i = 0; i <= (n - 1); i++){
            for(int j = 0; j <= (n - 1); j++){
                data[i][j] = input.nextInt();
            }
        }
        
        int tl = data[0][0];
        int tr = data[0][n-1];
        int bl = data[n-1][0];
        int br = data[n-1][n-1];
        int rotation; // 0 -> 0, 1 -> 90, 2 -> 180, 3 -> 270; clockwise
        
        if((tl < tr) && (tl < bl)){
            rotation = 0;
        } else if((tr < tl) && (tr < br)){
            rotation = 1;
        } else if((br < bl) && (br < tr)){
            rotation = 2;
        } else {
            rotation = 3;
        }
        
        // System.out.println(rotation);
        
        if(rotation == 0){
            for(int i = 0; i <= (n - 1); i++){
                for(int j = 0; j <= (n - 1); j++){
                    System.out.print(data[i][j] + " ");
                }
                System.out.println();
            }
        }
        
        if(rotation == 1){
            for(int i = n-1; i >= 0; i--){
                for(int j = 0; j <= (n - 1); j++){
                    System.out.print(data[j][i] + " ");
                }
                System.out.println();
            }
        }
        
        if(rotation == 2){
            for(int i = n-1; i >= 0; i--){
                for(int j = n-1; j >= 0; j--){
                    System.out.print(data[i][j] + " ");
                }
                System.out.println();
            }
        }
        
        if(rotation == 3){
            for(int i = 0; i <= n-1; i++){
                for(int j = n-1; j >= 0; j--){
                    System.out.print(data[j][i] + " ");
                }
                System.out.println();
            }
        } // could've used if elses to cut back one if statement but naaaa
        
    }

}

