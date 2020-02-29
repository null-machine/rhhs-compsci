/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackdemo;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author Glen
 */
public class StackDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Stack<Integer> num = new Stack<Integer>();
        num.push(1);
        num.push(2);
        System.out.println(num.peek());
        num.pop();
        System.out.println(num.peek());
        System.out.println(num.size());
        num.pop();
        for(int i = 1; i <= 10; i++){
            num.push(i);
        }
        int sum = 0;
        while(!num.isEmpty()){
            sum += num.peek();
            num.pop();
        }
        System.out.println(sum);
    }
}
