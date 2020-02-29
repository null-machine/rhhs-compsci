/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

/**
 *
 * @author Glen
 */
public class BinaryTree {
    
    Node root; // Binary trees have a root node
    
    public void BinaryTree(){ // Creates empty binary tree
        root = null;
    }
    
    public Node insert(Node node, int data){
        if(node == null){ // If null create new value
            node = new Node(data);
        } else if(node.data < data){
            node.right = insert(node.right, data);
        } else {
            node.left = insert(node.left, data);
        }
        return node;
    }

    public boolean lookup(Node node, int x){
        if(node == null){
            return false;
        }else if(x == node.data){
            return true;
        }else if(node.data > x){
            return lookup(node.right,x);
        }else{
            return lookup(node.left,x);
        }
    }

    public int height(Node node){
        if(node == null) return 0;
        else return 1 + height(node.right) + height(node.left);
    }
    /*
    public int max(Node node){
        if(node == null){
            return 0;
        }
    }
    
    public int min(Node node){
        if(node == null){
            return 0;
        }
    }
    
    public boolean isBinary(Node node){
    }
    
    public int depth(Node node){
        if(node == null){
            return 0;
        }
    }
    */
}