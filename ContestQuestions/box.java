/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxes;

/**
 *
 * @author Glen
 */
public class box {
    public int l, w, h;
    public long volume;
    
    public box (int a, int b, int c){
    int t;
    l = a;
    w = b;
    h = c;
    if(l > w){
        t = w;
        w = h;
        h = t;
    }
    if (w > h){
        t = w;
        w = h;
        h = t;
    }
    volume = l * w * h;
    }
}
