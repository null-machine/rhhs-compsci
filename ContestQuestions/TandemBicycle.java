/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tandembicycle;
import java.util.Scanner;
import java.util.Arrays;
/**
 *
 * @author Glen
 */
public class TandemBicycle {

    /**
     * @param args the command line arguments
     */
    public static int max(int a, int b){
        if(a > b){
            return a;
        } else {
            return b;
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int qn = input.nextInt();
        int np = input.nextInt();
        int [] D = new int[np];
        int [] P = new int[np];
        for(int i = 0; i < np; i++){
            D[i] = input.nextInt();
        }
        for(int i = 0; i < np; i++){
            P[i] = input.nextInt();
        }
        Arrays.sort(D);
        Arrays.sort(P);
        int count = 0;
        if(qn == 2){
            for(int i = 0; i < D.length; i++){
                count = count + max(D[i], P[D.length - 1 - i]);
            }
        }else if(qn == 1){
            for(int i = 0; i < D.length; i++){
                count = count + max(D[i], P[i]);
            }
        }
        System.out.println(count);
    }
}