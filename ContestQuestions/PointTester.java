/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Glen
 */
public class PointTester {
    public static void main(String[] args ){
        // Recall
        Scanner input = new Scanner(System.in);
        int [] arr = new int[5];
        Point P1 = new Point(10, 10);
        //P1.x = 10;
        //P1.y = 20;
        
        Point P2 = new Point();
        System.out.println("X1: " + P1.getx() +" Y1: " + P1.gety());
        System.out.println("X2: " + P1.getx() +" Y2: " + P2.gety());
        System.out.println(P1.toString()); // Objects have modifiable toString()
        P1.translate(-10, -100);
        System.out.println(P1.toString());
        Point A = new Point(0, 0);
        Point B = new Point(3, 4);
        System.out.println(A.distance(B)); // Point A is referred by "this"
        
        Point [] parr = new Point[5]; // Point array
        ArrayList<Point> plist = new ArrayList<Point>();
        
        for(int i = 0; i < 5; i++){
            int x = input.nextInt();
            int y = input.nextInt();
            Point P = new Point(x, y);
            parr[i] = P;
        }
        for(int i = 0; i < parr.length; i++){
            System.out.println(plist.get(i).toString());
        }
    }
}
