/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import Entities.Abstract.SphericalEntity;
import DataTypes.*;
public abstract class Collider {
    public static void hit(SphericalEntity e, Map m){
        m.getWalls().forEach((n) -> hit(e, n));
    }
    
    public static void hit(SphericalEntity e, Net n){
        n.getLinesList().forEach((l) -> hit(e, l));
    }
    
    public static void hit(SphericalEntity e, Line l){
        double y = Math.sin(l.getAng().getRad()) * e.getRadius();
        double x = Math.cos(l.getAng().getRad()) * e.getRadius();
        
        Coord p1_1 = l.getP1();
        Coord p1_2 = l.getP1();
        Coord p2_1 = l.getP2();
        Coord p2_2 = l.getP2();
        
        p1_1.moveBy(x, y);
        p2_1.moveBy(x, y);
        
        p1_2.moveBy(-x, -y);
        p2_2.moveBy(-x, -y);
        
        Line shadow_1 = new Line(p1_1, p2_1);
        Line shadow_2 = new Line(p1_2, p2_2);
        
        if(hit(shadow_1, l)){
            
        }
    }
    
    public static boolean hit(Line l1, Line l2){
        double x,y;
        if(l1.getSlope() == l2.getSlope()){
            return false;
        }
        if(l1.getSlope() == Double.POSITIVE_INFINITY){
            x = l1.getP1().getX();
            y = (x*l2.getSlope())+l2.getInt();
            if(Util.Inside(l1.getP1().getY(), y, l1.getP2().getY())){
                return true;
            }else{
                return false;
            }
        }else if(l2.getSlope() == Double.POSITIVE_INFINITY){
           x = l2.getP1().getX();
           y = (x*l1.getSlope())+l1.getInt();
           if(Util.Inside(l2.getP1().getY(), y, l2.getP2().getY())){
               return true;
           }else{
               return false;
           }
        }else{
            x = (l2.getInt() - l1.getInt())/(l1.getSlope() - l2.getSlope());
            if(Util.Inside(l1.getP1().getX(), x, l1.getP2().getX())){
                if(Util.Inside(l2.getP1().getX(), x, l2.getP2().getX())){
                    return true;
                }
            }
            return false;
        }
    }
}

