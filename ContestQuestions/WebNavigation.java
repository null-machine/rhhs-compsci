/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webnavigation;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Glen
 */
public class WebNavigation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Stack<String> back = new Stack<>();
        Stack<String> forward = new Stack<>();
        String strInitial = "http://www.acm.org/";
        String command = input.nextLine();
        while (!command.equals("QUIT")) {
            if (command.indexOf("VISIT") >= 0) {
                back.push(strInitial);
                strInitial = command.substring(command.indexOf(" "));
            } else if (command.equals("BACK") && (!back.isEmpty())) {
                forward.push(strInitial);
                strInitial = back.pop();
            } else if(command.equals("FORWARDS") && !forward.isEmpty()){
                back.push(strInitial);
                strInitial = forward.pop();
            } else {
                System.out.println("IGNORED");
            }
            
        }
    }

}
