/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freckles;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *
 * @author Nanthi
 */
public class Freckles {

   // Number of vertices in the graph
  int V;
  
  public Freckles(int n){
      V = n;
  }
 
    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(double key[], Boolean mstSet[])
    {
        // Initialize min value
        double min = Integer.MAX_VALUE;
        int min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed MST stored in
    // parent[]
   public  void printMST(int parent[], int n, double graph[][])
    {
        double total = 0;
        System.out.println("Edge   Weight");
        for (int i = 1; i < V; i++)
        {
            System.out.println(parent[i]+" - "+ i+"    "+
                               graph[i][parent[i]]);
            total = total + graph[i][parent[i]];
        }
        System.out.println(total);
    }
 
    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    public void primMST(double graph[][])
    {
        // Array to store constructed MST
        int parent[] = new int[V];
 
        // Key values used to pick minimum weight edge in cut
        double key[] = new double [V];
 
        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];
 
        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is
                        // picked as first vertex
        parent[0] = -1; // First node is always root of MST
 
        // The MST will have V vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);
 
            // Add the picked vertex to the MST Set
            mstSet[u] = true;
 
            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)
 
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v]!=0 && mstSet[v] == false &&
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
 
        // print the constructed MST
        printMST(parent, V, graph);
    }
   
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        int ncities = input.nextInt();
        Freckles f1 = new Freckles(ncities);
        double [][] dist = new double[ncities][ncities];
        for(int i = 0; i < dist.length; i++){
            for(int j = 0; j < dist[i].length; j++){
                dist[i][j] = 0;
                dist[j][i] = 0;
            }
        }
        
        double [] xcord = new double[ncities];
        double [] ycord = new double[ncities];
        for(int i = 0; i < ncities ; i++){
            xcord[i] = input.nextDouble();
            ycord[i] = input.nextDouble();
            
        }
        
        //Now we need a Two D array that contains distance from each cities
        // to each other cities
        for(int i = 0; i < dist.length; i++){
            for(int j = 0; j < dist.length; j++){
                if(i!=j){
                    // Not the diatnce between the same points
                   double xdiff = xcord[i] - xcord[j];
                   double ydiff = ycord[i] - ycord[j];
                   dist[i][j] = Math.sqrt(xdiff*xdiff + ydiff*ydiff);
                   dist[i][j] = Math.sqrt(xdiff*xdiff + ydiff*ydiff);
                }
            }
        }
        
        //Let print the dist array
        for(int i = 0; i < dist.length; i++){
            for(int j = 0; j < dist[i].length; j++){
                System.out.print(dist[i][j]+ "\t");
            }
            System.out.println("");
        }
        f1.primMST(dist);
    }
}
