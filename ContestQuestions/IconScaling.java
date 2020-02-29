/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iconscaling;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class IconScaling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int scale = input.nextInt();
        String space = "";
        String x = "";
        String ast = "";
        
        for(int i = 0; i < scale; i++){
            space = space + " ";
            x = x + "x";
            ast = ast + "*";
        }
        for(int i = 0; i < scale; i++){
            System.out.println(ast + x + ast);
        }
        for(int i = 0; i < scale; i++){
            System.out.println(space + x + x);
        }
        for(int i = 0; i < scale; i++){
            System.out.println(ast + space + ast);
        }
    }
    
}
