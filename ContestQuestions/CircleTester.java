/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point;

/**
 *
 * @author Glen
 */
public class CircleTester {
    public static void main(String[] args){
    Circle c1 = new Circle(10,12,13);
    System.out.println(c1.toString()); // Circle now calls toString() method from Point
    }
}
