/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2020318;
import java.util.*;
import java.io.*;

/**
 *
 * @author Glen
 */
public class Question2020318 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int h[] = new int[N+1], e[] = new int [N+1], p[] = new int[N++];
        for(int i = 1; i <=N; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            h[i] = Integer.parseInt(st.nextToken());
            e[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
            
        }
        int M = Integer.parseInt(in.readLine());
        int dp[] [] = new int [N+1] [M+1], t[][] = new int [N+1][M+1];
        for(int i = 1; i <= N; i++){
            for(int j = 0; j <= M; j++){dp[i][j] = dp[i-1][j]; t[i][j] = t[i-1][j]; }
            for(int v = 0, w = p[i], d=1; h[i] >= 0); h[i] -= e[i], w+=p[i], d++){
            v+= h[i];
            for(int j = 2; j <= M; j++){
                int val = dp[i-1][j-w]+v, dur = t[i-1][j-w] +d;
                if(val > dp[i][j])
            }
        }
        }
    }
    
}