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
public class Circle extends Point {
    int r;
    public Circle(int x1, int y1, int r1){
        super(x1, y1); // Calls constructor, initializes x1, y1
        r = r1;
    }
    public String toString(){
        return "x: " + this.getx() + " y: " + this.gety() + " r: " + r;
    }
    
}