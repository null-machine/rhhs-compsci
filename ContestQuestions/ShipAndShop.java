/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipandshop;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Glen
 */
public class ShipAndShop {

    int V;

    public ShipAndShop(int n) {
        V = n;
    }
    public int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }
    public void printSolution(int dist[], int n) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++) // Update dist[v] only if is not in sptSet, there is an
            // edge from u to v, and total weight of path from src to
            // v through u is smaller than current value of dist[v]
            {
                if (!sptSet[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // print the constructed distance array
        printSolution(dist, V);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int nCities = input.nextInt();
        int nRoute = input.nextInt();
        ShipAndShop S = new ShipAndShop(nRoute);
        int[][] dist = new int[nCities][nCities];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                dist[i][j] = 0;
            }
        }

        for (int i = 0; i < nRoute; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int c = input.nextInt();
            dist[x - 1][y - 1] = c;
            dist[y - 1][x - 1] = c;
        }

        int pencil = input.nextInt();
        int[] costPencil = new int[nCities];
        for (int i = 0; i < costPencil.length; i++) {
            costPencil[i] = 0;
        }
        for (int i = 0; i < pencil; i++) {
            int c = input.nextInt();
            int cost = input.nextInt();
        }
        int source = input.nextInt();
        S.dijkstra(dist, source - 1);
        System.out.println(S.dijkstra(dist, costPencil, source - 1));
    }

}
