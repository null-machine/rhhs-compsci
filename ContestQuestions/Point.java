/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point;
import java.util.Scanner;
/**
 *
 * @author Glen
 */
public class Point {
    
    private int x, y; // Instance variables of Point Object
    
    public Point (int x1, int y1){ // Constructors
        x = x1;
        y = y1;
    }
    
    public Point(){
        x = 0;
        y = 0;
    }
    public String toString(){
        return "X: " + x + " Y: " + y;
    }
    
    public void translate(int dx, int dy){
        x = x + dx;
        y = y + dy;
    }
    public double distance(Point P){
        double xdiff = P.x - this.x; // Refers to object within class
        double ydiff = P.y - this.y;
        return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
    }
    
    public void setX(int x1){ // "Mutator" or "Set" method
        x = x1;
    }
    public void setY(int y1){
        y = y1;
    }
    
    public int getx(){
        return x;
    }
    public int gety(){ // "Accessor" or "Get" method
        return y;
    }
    
}
