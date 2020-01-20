package Entities.Abstract;
import DataTypes.*;
import Utility.GraphicsEngine;
import java.awt.Graphics2D;

public abstract class SphericalEntity extends Entity{
    double radius;
    
    
    public SphericalEntity(Coord start, double rad){
        center = start.copy();
        radius = rad;
    }
    
    public double getRadius(){
        return radius;
    }
    
    public void setRadius(double r){
        radius = r;
    }
    
    public void Update(){
        this.velocity.moveTo(center);
        if(!collided){
            this.center.moveTo(velocity.getP2().getX(), velocity.getP2().getY());
            this.velocity.rotateBy(curve);
            this.velocity.moveTo(center);
        }else{
            collided = false;
        }
    }
    
    @Override
    public abstract void PostUpdate();
    
    @Override
    public abstract void DieMod();
    
    public abstract void DrawExtra(Graphics2D g);
    
    public void Draw(Graphics2D g){
        GraphicsEngine.drawEntity(g, this);
        DrawExtra(g);
    }
}
