/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termsofoffice;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class TermsOfOffice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();
        int Y = input.nextInt();
        for(int i = X; i <= Y; i += 60){
            System.out.println("All positions change in year " + i);
        }
    }
}
