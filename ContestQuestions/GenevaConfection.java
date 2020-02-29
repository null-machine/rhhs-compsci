/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genevaconfection;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author Glen
 */
public class GenevaConfection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Stack <Integer> MT = new Stack<Integer>();
        Stack <Integer> BR = new Stack<Integer>();
        for(int i = 0; i < n; i++){
            int x = input.nextInt();
            MT.push(x);
        }
        int nextTrain = 1;
        while(!MT.empty()){
            if(MT.peek() == nextTrain){
                MT.pop();
                nextTrain++;
            }else{
                BR.push(MT.pop());
            }
        }
        boolean isOk = true;
        while(!BR.isEmpty()){
            if(BR.peek() == nextTrain){
                BR.pop();
                nextTrain++;
            }else{
                isOk = false;
                BR.pop();
            }
            if(isOk){
                System.out.println("Y");
            }else{
                System.out.println("N");
            }
        }
    }
}