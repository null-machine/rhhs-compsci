/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question020318;

import java.util.*;
import java.io.*;


/**
 *
 * @author Glen
 */
public class Question020318 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int pos [] = new int[1000001], lis[] = new int[1000001];
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= N; i++)
            pos[Integer.parseInt(st.nextToken())] = i;
            int M = Integer.parseInt(in.readLine()), cnt = 0;
            st = new StringTokenizer(in.readLine());
            for(int i = 1; i <= M; i++){
                int x = Integer.parseInt(st.nextToken());
                if(pos[x] == 0) continue;
                if(cnt == 0 || pos[x] > lis[cnt-1]) lis[cnt++] = pos[x];
                else{
                    int p = Arrays.binarySearch(lis, 0, cnt, pos[x]);
                    lis[-(p+1)] = pos[x];
                }
            }
            System.out.println(cnt);
            }
        }
