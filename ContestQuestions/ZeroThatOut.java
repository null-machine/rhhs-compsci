/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerothatout;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author Glen
 */
public class ZeroThatOut {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        Stack<Integer> list = new Stack<Integer>();
        for(int i = 0; i < k; i++){
            int num = input.nextInt();
            if(num == 0){
                list.pop();
            } else {
            list.push(num);
            }
        }
        int sum = 0;
        while(!list.isEmpty()){
            sum += list.peek();
            list.pop();
        }
        System.out.println(sum);
    }
}