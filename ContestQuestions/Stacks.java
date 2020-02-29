/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacks;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author Glen
 */
public class Stacks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Stack<Integer> num = new Stack<Integer>();
        num.push(45);
        System.out.println(num);
        num.push(20);
        System.out.println(num.peek());
        num.pop(); // Removes and returns
        System.out.println(num.peek());
        System.out.println(num.size());
        
        System.out.println(num.isEmpty());
        
        for(int i = 0; i < 5; i++){
            int x = input.nextInt();
            num.push(x);
        }
        
        System.out.println(num);
        
        while(!num.isEmpty()){
            System.out.println(num.pop());
            
        }
    }
    
}
