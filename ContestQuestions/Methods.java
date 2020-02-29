/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

/**
 *
 * @author Glen
 */
public class Methods {

    /**
     * @param args the command line arguments
     */
    
    public static int sum(int a, int b){
        return a + b;
    }
    
    public static int GCF(int a, int b){
        int gcf = 1;
        for(int i = 1; i <= a && i <= b; i++){
            if(a % i == 0 && b % i == 0){
                gcf = i;
            }
        }
        return gcf;
    }
    public static int LCM(int a, int b){
        return a * b / GCF(a, b);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(sum(10, 5));
        System.out.println(GCF(20, 20));
    }
    
}
