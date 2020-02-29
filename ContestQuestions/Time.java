/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

/**
 *
 * @author Glen
 */
public class Time {

    int hour;
    int minute;

    /* Complete required constructors and methods here */
 /*
      *Default constructor that sets time to 1200.  
     */
    public Time() {
        hour = 12;
        minute = 10;
    }

    public Time(int h, int m){
        if(h >= 0 && h <= 23){
            hour = h;
        }else{
            hour = 0;
        }
        if(m >= 0 && m <= 59){
            minute = m;
        }else{
            minute = 0;
        }
    }

    public String toString() {
        String str = "";
        if(hour >= 0 && hour <= 9){
            str = str + "0" + hour;
        }else{
            str = str + hour;
        }
        if(minute >= 0 && minute <= 9){
            str = str + "0" + minute;
        }else{
            str = str + minute;
        }
        return str;
    }

    public String convert() {
        String str = "";
        if(hour == 0){
            str = str + 12;
        }else if(hour > 0 && hour <= 9){
            str = str + "0" + hour;
        }else if(hour >= 10 && hour <= 12){
            str = str + hour;
        }
        str = str + ":";
        if(minute >= 0 && minute <= 9){
            str = str + "0" + minute;
        }else{
            str = str + minute;
        }
        if(hour >= 0 && hour <= 11){
            str = str + "am";
        }else{
            str = str + "pm";
        }
        return str;
    }

    public void increment() {
        minute++;
        hour = hour + minute / 60;
        minute = minute % 60;
        if(hour == 24){
            hour = 0;
        }
    }
}
