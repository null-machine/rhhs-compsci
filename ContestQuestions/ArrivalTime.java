/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrivaltime;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class ArrivalTime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        String departure = input.next();
        int dHour = parseInt(departure.substring(1, 2));
        int dMin = parseInt(departure.substring(4, 5));
        int aHour = 0;
        int aMin = 0;
        if (dHour >= 10 && dHour <= 13 || dHour <= 5 || dHour >= 19) {
            aHour = dHour + 2;
            aMin = dMin;
        } else {
            
        }
        
        if(aHour < 10 && aMin < 10){
            System.out.println("0" + aHour + ":0" + aMin);
        } else if (aHour < 10){
            System.out.println("0" + aHour + ":" + aMin);
        } else if (aMin < 10) {
            System.out.println(aHour + ":0" + aMin);
        } else {
            System.out.println(aHour + ":" + aMin);
        }
    }
}
