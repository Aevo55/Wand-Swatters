/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import Entities.Abstract.*;
import DataTypes.*;
import java.util.ArrayList;
public abstract class Collider {
    public static boolean hit(Entity e, ArrayList<Entity> _e){
        boolean flag = false;
        for(Entity __e : _e){
            flag = flag || hit(e, __e);
        }
        return flag;
    }
    
    public static boolean hit(Entity e, Entity _e){
        if(SphericalEntity.class.isInstance(e)){
            if(SphericalEntity.class.isInstance(_e)){
                return hit((SphericalEntity)e,(SphericalEntity)_e);
            }else{
                return hit((SphericalEntity)e,(PolygonalEntity)_e);
            }
        }else{
            if(SphericalEntity.class.isInstance(_e)){
                return hit((PolygonalEntity)e,(SphericalEntity)_e);
            }else{
                return hit((PolygonalEntity)e,(PolygonalEntity)_e);
            }
        }
    }
    
    public static boolean hit(Entity e, Map m){
        if(SphericalEntity.class.isInstance(e)){
            return hit((SphericalEntity)e,m);
        }else{
            return hit((PolygonalEntity)e,m);
        }
    }
    public static boolean hit(SphericalEntity e, Map m){
        boolean flag = false;
        for(Net n: m.getWalls()){
            Angle a = hit(e, n);
            if(a != null){
                flag = true;
                e.getVelocityRef().rotateTo(new Angle((2 * a.getDeg()) - e.getVelocity().getAng().getDeg()));
            }
        }
        return flag;
    }
    
    public static Angle hit(SphericalEntity e, Net n){
        for(Line l : n.lines){
            Angle a = hit(e, l);
            if(a != null){
                return a;
            }
        }
        return null;
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
