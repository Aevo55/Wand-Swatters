/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import Entities.Abstract.*;
import DataTypes.*;
import ReturnTypes.CoordAngle;
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
        for(Wall w: m.getWalls()){
            CoordAngle a = hit(e, w);
            if(a != null){
                flag = true;
                //e.collided = true;
                //e.STOP = true;
                Angle newAng = new Angle((2 * a.ang.getDeg()) - e.getVelocity().getAng().getDeg());
                Angle newAng2 = e.getVelocity().getAng().closerMirror(a.ang);
                
                double newMag = Math.abs(Math.cos(a.ang.distToMirrored(e.getVelocity().getAng()).getRad()) * e.getVelocity().getMag());
                e.getVelocityRef().rotateTo(newAng2);
                
                e.getVelocityRef().setMag(newMag);
                e.getVelocityRef().mulMag(w.getSpring());
            }
        }
        return flag;
    }
    
    public static CoordAngle hit(SphericalEntity e, Net n){
        for(Line l : n.lines){
            CoordAngle a = hit(e, l);
            if(a != null){
                return a;
            }
        }
        return null;
    }
    
    public static CoordAngle hit(SphericalEntity e, Line l){
        Line[] shadows = l.getShadows(e.getRadius());
        CoordAngle a = new CoordAngle();
        if(hit(e.getVelocity(), shadows[0]) != null){
            a.coord = hit(e.getVelocity(), shadows[0]);
            a.ang = shadows[0].getAng().copy();
            return a;
        }else if(hit(e.getVelocity(), shadows[1]) != null){
            a.coord = hit(e.getVelocity(), shadows[1]);
            a.ang = shadows[1].getAng().copy();
            return a;
        }else if(e.getVelocity().getP2().distanceTo(l.getP1()) <= e.getRadius()){
            a.coord = l.getP1().copy();
            a.ang = new Line(e.getCenter(), l.getP1()).getAng().offset(-90).copy();
            return a;
        }else{
            return null;
        }
    }
    
    public static Coord hit(Line l1, Line l2){
        double x,y;
        if(l1.getSlope() == l2.getSlope()){
            return null;
        }
        if(l1.getSlope() == Double.POSITIVE_INFINITY){
            x = l1.getP1().getX();
            y = l2.getYAtX(x);
            if(Util.Inside(l1.getP1().getY(), y, l1.getP2().getY()) && Util.Inside(l2.getP1().getX(), x, l2.getP2().getX())){
                return new Coord(x,y);
            }else{
                return null;
            }
        }else if(l2.getSlope() == Double.POSITIVE_INFINITY){
           x = l2.getP1().getX();
           y = l1.getYAtX(x);
           if(Util.Inside(l2.getP1().getY(), y, l2.getP2().getY()) && Util.Inside(l1.getP1().getX(), x, l1.getP2().getX())){
                return new Coord(x,y);
           }else{
               return null;
           }
        }else{
            x = (l2.getInt() - l1.getInt())/(l1.getSlope() - l2.getSlope());
            if(Util.Inside(l1.getP1().getX(), x, l1.getP2().getX())){
                if(Util.Inside(l2.getP1().getX(), x, l2.getP2().getX())){
                    return new Coord(x,l1.getYAtX(x));
                }
            }
            return null;
        }
    }
    
    static public double normalDistance(Line l, Coord c){
        double d1 = Math.abs((l.getSlope() * c.getX())-(c.getY())+(l.getInt()));
        double d2 = Math.sqrt(Math.pow(l.getSlope(), 2)+1);
        return d1 / d2;
    }
}
