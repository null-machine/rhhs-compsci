/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgetransport;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class BridgeTransport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int maxWeight = input.nextInt();
        int n = input.nextInt();
        int[] carWeight = new int[n + 3];

        for (int i = 3; i < n + 3; i++) {
            carWeight[i] = input.nextInt();
        }
        int i = 3;
        int carAcross = 0;
        int totalWeight = carWeight[i - 3] + carWeight[i - 2] + carWeight[i - 1] + carWeight[i];

        while (totalWeight <= maxWeight) {
            carAcross++;
            i++;
            totalWeight = carWeight[i - 3] + carWeight[i - 2] + carWeight[i - 1] + carWeight[i];
        }
        System.out.println(carAcross);
    }
}
