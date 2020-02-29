/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class BinaryTester {
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        BinaryTree t1 = new BinaryTree();
        Node n1 = new Node(10);
        t1.root = n1;
        t1.insert(n1,20);
        t1.insert(n1,35);
        if(t1.lookup(n1,35)){
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
        
    }
    
}
