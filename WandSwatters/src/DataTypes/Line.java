/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;
import DataTypes.*;
import ReturnTypes.CoordAngle;
import Utility.Collider;
/**
 *
 * @author dawsp
 */
public class Line {
    private double slope;
    private double intercept;
    
    private Angle angle;
    private double rise, run;
    private Coord p1, p2;
    private double mag;
    
    public Line(){
        recalc(new Coord(0,0), new Coord(0,0));
    }
    
    public Line(Coord _p1, Coord _p2, double _mag){
        recalc(_p1, _p2);
        recalc(p1, angle, _mag);
    }
    
    public Line(Line l){
        recalc(l.getP1(), l.getP2());
    }
    
    public Line(Coord _p1, Coord _p2){
        recalc(_p1, _p2);
    }
    public Line(Coord _p1, Angle a, double mag){
        recalc(_p1, a, mag);
    }
    public void recalc(Coord _p1, Coord _p2){
        p1 = _p1.copy();
        p2 = _p2.copy();
        
        rise = p2.getY() - p1.getY();
        run = p2.getX() - p1.getX();
        
        if(run == 0){
            slope = Double.POSITIVE_INFINITY;
        }else{
            slope = rise / run;
        }
        intercept = p1.getY() - (p1.getX()*slope);
        //intercept *= (int)(slope / Math.abs(slope));
        
        double _angle = ((Math.atan2(rise, run))/(Math.PI)*180);
        angle = new Angle();
        angle.setDeg(_angle);
        mag = Math.sqrt(Math.pow(rise,2)+Math.pow(run,2));                 
    }
        
    public void recalc(Coord _p1, Angle a, double _mag){
        p1 = _p1.copy();
        angle = new Angle(a.getDeg());
        mag = _mag;
        if(a.getDeg() == 90.0){
            slope = Double.POSITIVE_INFINITY;
            rise = _mag;
            run = 0;
            intercept = p1.getY();
            p2 = p1.offset(0, rise);
        }else if(a.getDeg() == 270){
            slope = Double.POSITIVE_INFINITY;
            rise = -_mag;
            run = 0;
            intercept = p1.getY();
            p2 = p1.offset(0, rise);
        }else{
            rise = Math.sin(angle.getRad())*mag;
            run = Math.cos(angle.getRad())*mag;
            slope = rise / run;
            intercept = p1.getY() - (p1.getX()*slope);
            //intercept *= (int)(slope / Math.abs(slope));
            p2 = p1.offset(run, rise);
        }
    }
    
    public void moveTo(Coord c){
        p1 = c.copy();
        p2 = p1.offset(run, rise);
        recalc(p1, p2);
    }
    
    public void moveBy(double x, double y){
        p1.moveBy(x, y);
        p2.moveBy(x, y);
        recalc(p1, p2);
    }
    
    public Line addTo(Line l){
        p2 = new Coord(p2.getX() + l.run, p2.getY() + l.rise);
        recalc(p1, p2);
        return this;
    }
    
    public Line addTo(double x, double y){
        p2 = new Coord(p2.getX() + x, p2.getY() + y);
        recalc(p1, p2);
        return this;
    }
    
    public void addTo(Angle a, double m){
        Line temp = new Line(p2, a, m);
        p2 = temp.p2.copy();
        recalc(p1,p2);
    }
    
    public void setTo(Line l){
        recalc(l.getP1(), l.getP2());
    }
    
    public void setRise(double y){
        recalc(p1, p2.offset(0, y));
    }
    
    public void setRun(double x){
        recalc(p1, p2.offset(x, 0));
    }
    
    public double getSlope(){
        return slope;
    }
    
    public double getInt(){
        return intercept;
    }
    
    public Angle getAng(){
        return new Angle(angle.getDeg());
    }
    
    public double getRise(){
        return rise;
    }
    
    public double getRun(){
        return run;
    }
    
    public double getMag(){
        return mag;
    }
    
    public void setMag(double m){
        recalc(this.p1, this.angle, m);
    }
    
    public Coord getP1(){
        return p1.copy();
    }
    
    public Coord getP2(){
        return p2.copy();
    }
    
    public double getTanSlope(){
        return -(1/slope);
    }
    
    public Angle getTanAngle(){
        return new Angle(angle.getDeg() + 90);
    }
    
    public Line copy(){
        return new Line(p1, p2);
    }
    
    public double getYAtX(double x){
        return (slope * x) + intercept;
    }
    
    public void rotateBy(Angle a){
        recalc(this.getP1(), this.angle.offset(a), this.mag);
    }
    
    public void rotateTo(Angle a){
        recalc(this.getP1(), a, this.mag);
    }
    
    public Line[] getShadows(double r){
        Line[] shadows = new Line[2];
        
        Line anchor_1 = new Line(getP1(), getAng().offset(90), r);
        Line anchor_2 = new Line(getP1(), getAng().offset(-90), r);
        
        shadows[0] = new Line(anchor_1.getP2(), getAng(), getMag());
        shadows[1] = new Line(anchor_2.getP2(), getAng(), getMag());
        
        return shadows;
    }
    
    public Line mulMag(double d){
        setMag(mag * d);
        return this;
    }
}
