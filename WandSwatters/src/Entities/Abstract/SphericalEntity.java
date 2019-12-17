package Entities.Abstract;
import DataTypes.*;

public abstract class SphericalEntity extends Entity{
    double radius;
    
    
    public SphericalEntity(Coord start, double rad){
        center = start.getLoc();
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
        this.center.moveTo(velocity.getP2().getX(), velocity.getP2().getY());
        this.velocity.rotateBy(curve);
        this.velocity.moveTo(center);
    }
    
    @Override
    public abstract void PostUpdate();
    
    @Override
    public abstract void DieMod();
}
