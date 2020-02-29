/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Glen
 */
public class Graph {

    boolean[][] adjMatrix; // Represents connection
    int[][] adjWeight; // Weight between vertices
    private int vertices; // Number of vertices
    private int edges; // Number of edges
    private int weight; // Weight between two vertices 
    
    public Graph(int n) {
        vertices = n;
        edges = 0;
        adjMatrix = new boolean[n][n];
        adjWeight = new int[n][n];
        for (int i
                = 0; i < vertices; i++) {
            for (int j
                    = 0; j < vertices; j++) {
                adjMatrix[i][j]
                        = false;
                adjWeight[i][j] = 0;
            }
        }
    }
    
    public void printAdjMatrix(){
        for(int i = 0; i < adjMatrix.length; i++){
            for(int j = 0; j < adjMatrix.length; j++){
                System.out.println(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public void printAdjWeight(){
        for(int i = 0; i < adjWeight.length; i++){
            for(int j = 0; j < adjWeight.length; j++){
                System.out.println(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

    }
}
