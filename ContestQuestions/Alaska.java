/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaska;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Glen
 */
public class Alaska {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        
        for(int i = 0; i < n; i++){
            int nstation = input.nextInt();
            int [] miles = new int[nstation];
            for(int j = 0; j < nstation; j++){
                miles[j] = input.nextInt();
            }
            Arrays.sort(miles);
            int distance = 0;
            for(int a = 0; a < miles.length - 1; a++){
                if(miles[a + 1] - miles[a] <= 200){
                    distance = distance + 200;
                    System.out.println(distance);
                }
                if(distance >= 1422){
                System.out.println("POSSIBLE");
                } else {
                System.out.println("IMPOSSIBLE");
                }
            }
        }
    }
    
}
