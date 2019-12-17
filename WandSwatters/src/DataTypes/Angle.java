package DataTypes;
import Utility.Util;
import java.awt.*;
public class Angle {
    private double deg;
    public Angle(){
        deg = 0;
    }
    public Angle(double _deg){
        deg = _deg;
        fix();
    }
    public double getDeg(){
        return deg;
    }
    public double getRad(){
        return Math.toRadians(deg);
    }
    public void rotateByDeg(double a){
        setDeg(getDeg() + a);
    }
     /**There are Math.toDegrees and Math.toRadians methods*/
    public void setDeg(double _val){
        deg = _val;
        fix();
    }
    
    private void fix(){
        if(deg < 0){
            deg = (deg % 360) + 360;
        }else{
            deg = deg % 360;
        }
        roundAngle();
    }
    
    public Angle offset(Angle ang){
        Angle a = new Angle(getDeg() + ang.getDeg());
        return(a);
    }
    
    public Angle offset(double ang){
        return new Angle(getDeg() + ang);
    }
    
    public void roundAngle(){
        Double d = getDeg();
        d = d * 10000;
        d = (double)Math.round(d);
        d = d / 10000;
        deg = d;
    }
    
    public Angle copy(){
        return new Angle(getDeg());
    }
}
