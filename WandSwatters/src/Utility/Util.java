/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import DataTypes.Coord;
import java.util.ArrayList;

/**
 *
 * @author dawsp
 */
public abstract class Util {
    
    public static void Sysout(Object... objs){
        for (Object obj : objs) {
            System.out.println(obj.toString());
        }
    }
 
    public static int GreaterOf(int a, int b){
        if(a >= b){
            return a;
        }
        return b;
    }
    
    public static boolean Inside(int a, int b, int c){
        return Inside((double)a, (double)b, (double)c);
    }
    
    public static boolean Inside(double a, double b, double c){
        return (a <= b && b <= c) || (c <= b && b <= a);
    }
    
    public static ArrayList<Coord> HashToCoord(ArrayList<String> _i, ArrayList<Coord> _c){
        ArrayList<Coord> coords = new ArrayList<>();
        for(String i : _i){
            for(Coord c : _c){
                if(i == null ? c.toString() == null : i.equals(c.toString())){
                    coords.add(c);
                    break;
                }
            }
        }
        return coords;
    }
}
