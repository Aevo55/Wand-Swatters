package Entities.Abstract;
import DataTypes.*;
import Utility.GraphicsEngine;
import java.awt.Color;
import java.awt.Graphics2D;

public abstract class PolygonalEntity extends Entity{
    protected Net outline;
    protected Angle rotation = new Angle(0);
    public PolygonalEntity(Coord start, Net net, Color _col){
        col = _col;
        outline = new Net(net.getCoords());
        velocity = new Line(start,start);
        center = new Coord();
    }
    
    @Override
    public void Update(){
        if(!collided){
            this.outline.rotate(center, rotation);
            this.outline.moveBy(velocity);
            this.velocity.rotateBy(curve);
            this.center.moveBy(velocity.getRun(), velocity.getRise());
            this.outline.recalc(outline.lines);
        }else{
            collided = false;
        }
    }
    
    @Override
    public abstract void PostUpdate();
    
    @Override
    public abstract void DieMod();
    
    private void updateCenter(){
        //do the math
    }
    
    public Net getOutline(){
        return new Net(outline.coords.clone());
    }
    
    public Net getOutlineRef(){
        return outline;
    }
    
    public Angle getRotation(){
        return rotation.copy();
    }
    
    public Angle getRotationRef(){
        return rotation;
    }
    
    public void setRotation(Angle a){
        rotation = a.copy();
    }
    
    public abstract void DrawExtra(Graphics2D g);
    
    public void Draw(Graphics2D g){
        GraphicsEngine.drawEntity(g, this);
        DrawExtra(g);
    }
}
