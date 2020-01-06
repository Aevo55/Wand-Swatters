/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import DataTypes.*;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Entities.Abstract.*;
public abstract class GraphicsEngine {
    public static void drawMap(Graphics gc, Map m){
        ArrayList<Wall> walls = m.getWalls();
        for(Wall w : walls){
            drawWall(gc, w);
        }
    }
    
    public static void drawEntity(Graphics gc, Entity e){
        if(PolygonalEntity.class.isAssignableFrom(e.getClass())){
            drawPolygonalEntity(gc, (PolygonalEntity)e);
        }else if(SphericalEntity.class.isAssignableFrom(e.getClass())){
            drawSphericalEntity(gc, (SphericalEntity)e);
        }else{
            Util.Sysout("Could not find suitable draw method for class " + e.getClass());
        }
    }
    
    public static void drawLine(Graphics gc, Line l){
        gc.setColor(Color.black);
        gc.drawLine((int)l.getP1().getX(), (int)l.getP1().getY(), (int)l.getP2().getX(), (int)l.getP2().getY());
    }
    
    public static void drawPolygonalEntity(Graphics gc, PolygonalEntity e){
        gc.setColor(e.getCol());
        fillPolygon(gc, e.getOutline());
    }
    
    public static void drawSphericalEntity(Graphics gc, SphericalEntity e){
        gc.setColor(e.getCol());
        fillOval(gc, e);
    }
    
    public static void drawWall(Graphics gc, Wall w){
        gc.setColor(w.getCol());
        fillPolygon(gc, w);
    }
    
    private static void fillPolygon(Graphics gc, Net n){
        gc.fillPolygon(n.getXs(), n.getYs(), n.getXs().length);
    }
    
    private static void drawPolygon(Graphics gc, Net n){
        gc.drawPolygon(n.getXs(), n.getYs(), n.getSize());
    }
    
    private static void drawOval(Graphics gc, SphericalEntity e){
        gc.drawOval((int)(e.getCenter().getX()-e.getRadius()), (int)(e.getCenter().getY()-e.getRadius()), (int)e.getRadius(), (int)e.getRadius());
    }
    
    private static void fillOval(Graphics gc, SphericalEntity e){
        gc.fillOval((int)(e.getCenter().getX()-e.getRadius()), (int)(e.getCenter().getY()-e.getRadius()), (int)e.getRadius()*2, (int)e.getRadius()*2);
    }
    
    private static void setColour(Graphics gc, Color c){
        gc.setColor(c);
    }
}
