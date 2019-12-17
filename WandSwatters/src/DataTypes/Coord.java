package DataTypes;

import java.awt.*;
import java.io.*;

public class Coord {
    private double X, Y;
    public Coord() { 
        X = 0;
        Y = 0;
    }
    
    public Coord(double _x, double _y){
        X = _x;
        Y = _y;
    }
    
    public Coord(Coord c){
        X = c.getX();
        Y = c.getY();
    }
    
    public void moveBy(double x, double y){
        X += x;
        Y += y;
    }
    
    public void moveTo(double x, double y){
        X = x;
        Y = y;
    }
    
    public double getX(){
        return X;
    }
    
    public double getRoundX(){
        return (double)Math.round(X * 1000)/1000;
    }
    
    public double getY(){
        return Y;
    }
    
    public double getRoundY(){
        return (double)Math.round(Y * 1000)/1000;
    }
    
    public void setX(double x){
        X = x;
    }
    
    public void setY(double y){
        Y = y;
    }
    
    public Coord getLoc(){
        return new Coord(getX(), getY());
    }
    
    public boolean equals(Coord c){
        return (X == c.getX() && Y == c.getY());
    }
    
    public Coord offset(double x, double y){
        return new Coord(getX() + x, getY() + y);
    }
    
    
    
    @Override
    public String toString(){
        
        return "(" + getRoundX() + "," + getRoundY() + ")";
    }
    
    @Override
    public int hashCode(){
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return equals((Coord)obj);
    }
}
