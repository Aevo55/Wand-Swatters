/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Abstract;
import DataTypes.*;
import java.awt.Color;
/**
 *
 * @author dawsp
 */
public abstract class Entity {
    protected boolean dead = false;
    protected Line velocity = new Line(new Coord(0,0), new Coord(0,0));
    protected Coord center;
    protected Color col = Color.BLACK;
    protected Angle curve = new Angle(0);
    
    public void Tick(){
        Update();
        PostUpdate();
    }
    
    public void die(){
        dead = true;
        DieMod();
    }
    
    public abstract void Update();
    
    public abstract void PostUpdate();
    
    public abstract void DieMod();
    
    public boolean isDead(){
        return dead;
    }
    
    public Line getVelocity(){
        return velocity.copy();
    }
    
    public Line getVelocityRef(){
        return velocity;
    }
    
    public void setVelocity(Line l){
        velocity = l.copy();
    }
    
    public Coord getCenter(){
        return center.getLoc();
    }
    
    public Coord getCenterRef(){
        return center;
    }
    
    public Color getCol(){
        return new Color(col.getRGB());
    }
    
    public void setCol(Color _col){
        col = new Color(_col.getRGB());
    }
    
    public Color getColRef(){
        return col;
    }
    
    public Angle getCurve(){
        return curve.copy();
    }
    
    public Angle getCurveRef(){
        return curve;
    }
}
