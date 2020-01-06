/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import Entities.Abstract.*;
import DataTypes.*;
public abstract class Collider {
    public static void hit(Entity e, Map m){
        if(SphericalEntity.class.isInstance(e)){
            hit((SphericalEntity)e,m);
        }
    }
    public static void hit(SphericalEntity e, Map m){
        m.getWalls().forEach((n) -> hit(e, n));
    }
    
    public static void hit(SphericalEntity e, Net n){
        boolean flag = false;
        while(flag == false){
            flag = true;
            for(Line l : n.lines){
                Angle a = hit(e, l);
                if(a != null){
                    flag = false;
                    e.getVelocityRef().rotateTo(new Angle((2 * a.getDeg()) - e.getVelocity().getAng().getDeg()));
                }
            }
        }
    }
    
    public static Angle hit(SphericalEntity e, Line l){
        Line[] shadows = l.getShadows(e.getRadius());
        
        if(hit(e.getVelocity(), shadows[0])){
            return shadows[0].getAng();
        }else if(hit(e.getVelocity(), shadows[1])){
            return shadows[1].getAng();
        }else if(e.getVelocity().getP2().distanceTo(l.getP1()) <= e.getRadius()){
            return new Line(e.getCenter(), l.getP1()).getAng().offset(-90);
        }else{
            return null;
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
            if(Util.Inside(l1.getP1().getY(), y, l1.getP2().getY()) && Util.Inside(l2.getP1().getX(), x, l2.getP2().getX())){
                return true;
            }else{
                return false;
            }
        }else if(l2.getSlope() == Double.POSITIVE_INFINITY){
           x = l2.getP1().getX();
           y = (x*l1.getSlope())+l1.getInt();
           if(Util.Inside(l2.getP1().getY(), y, l2.getP2().getY()) && Util.Inside(l1.getP1().getX(), x, l1.getP2().getX())){
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
