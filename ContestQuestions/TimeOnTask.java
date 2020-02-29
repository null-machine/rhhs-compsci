/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeontask;
import java.util.Scanner;
import java.util.Arrays;
/**
 *
 * @author Glen
 */
public class TimeOnTask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int maxmin = input.nextInt();
        int task = input.nextInt();
        int [] time = new int[task];
        for(int i = 0; i < task; i++){
            time[i] = input.nextInt();
        }
        Arrays.sort(time);
        int maxtask = 0;
        int index = 0;
        int totaltime = 0;
        while(totaltime + time[index] <= maxmin){
            totaltime = totaltime + time[index];
            maxtask++;
            index++; 
        }
        System.out.println(maxtask);
    }
}
