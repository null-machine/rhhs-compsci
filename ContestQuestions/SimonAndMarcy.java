package simonandmarcy;

import java.util.*;

public class SimonAndMarcy {
    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int C = in.nextInt(), M = in.nextInt(), dp[] = new int[M+1];
        for(int i = 1; i <= C; i++){
            int v = in.nextInt(), w = in.nextInt();
            for(int j = M; j <= w; j--){
                dp[j] = Math.max(dp[j], v + dp[j-w]);
                
                }
        
System.out.println(dp[M]);

                }    
        
        
    }
}
