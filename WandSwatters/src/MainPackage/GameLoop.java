package MainPackage;
import DataTypes.*;
import Entities.Abstract.*;
import Entities.*;
import Resources.Maps.MapLoader;
import Utility.Collider;
import Utility.GraphicsEngine;
import Utility.Util;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameLoop extends JPanel implements Runnable { 
    Thread thread;
    long fpsTracker;
    long currFPS;
    
    boolean shapeToggle = false;
    
    Map currMap;
    ArrayList<Entity> entities;
    MapLoader mapLoader;
    TestPolygonEntity manguyman;
    TestSphericalEntity testplayer;
    
    public GameLoop() {
        
        mapLoader = new MapLoader();
        
        thread = new Thread(this); //initializes the thread and puts Class1 into it
        thread.start(); //begins the thread
        
    }
    public void paintComponent(Graphics gc){
        setOpaque(false); //wipes everything on the frame
        super.paintComponent(gc); //creates the class for painting indavidual objects in the frame
        gc.setColor(Color.WHITE);
        gc.fillRect(0, 0, 920, 920);
        
        if(!shapeToggle){
            GraphicsEngine.drawMap(gc, currMap);
        }else{
            GraphicsEngine.drawEntity(gc, new TestSphericalEntity(new Coord(0,0), testplayer.getRadius(), Color.black));
            GraphicsEngine.drawEntity(gc, new TestSphericalEntity(new Coord(200,100), testplayer.getRadius(), Color.black));
            GraphicsEngine.drawEntity(gc, new TestSphericalEntity(new Coord(100,300), testplayer.getRadius(), Color.black));
            GraphicsEngine.drawEntity(gc, new TestSphericalEntity(new Coord(200,200), testplayer.getRadius(), Color.black));
            
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[0].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[0].getShadows(testplayer.getRadius())[1]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[1].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[1].getShadows(testplayer.getRadius())[1]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[2].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[2].getShadows(testplayer.getRadius())[1]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[3].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[3].getShadows(testplayer.getRadius())[1]);
        }
        entities.forEach((e) -> GraphicsEngine.drawEntity(gc, e));
    }
    public void keyPressed(KeyEvent evt){
        switch(evt.getKeyCode()){
            case KeyEvent.VK_D:
                shapeToggle = true;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_A:
                break;
            case KeyEvent.VK_UP:
                testplayer.setVelocity(new Line(new Coord(0,0), new Coord(0,-3)));
                break;
            case KeyEvent.VK_DOWN:
                testplayer.setVelocity(new Line(new Coord(0,0), new Coord(0,3)));
                break;
            case KeyEvent.VK_LEFT:
                testplayer.setVelocity(new Line(new Coord(0,0), new Coord(-3,0)));
                break;
            case KeyEvent.VK_RIGHT:
                testplayer.setVelocity(new Line(new Coord(0,0), new Coord(3,0)));
                break;
        }
    }

    public void keyReleased(KeyEvent evt){
        switch(evt.getKeyCode()){
            case KeyEvent.VK_D:
                shapeToggle = false;
                break;
            case KeyEvent.VK_A:
                break;
        }
    }
    
    @Override
    public void run(){
        fpsTracker = System.currentTimeMillis();
        
        entities = new ArrayList<>();
        currMap = mapLoader.getMap("Map 0");
        
        testplayer = new TestSphericalEntity(new Coord(250,250), 10);
        //testplayer.getCurveRef().setDeg(5);
        entities.add(testplayer);
        
        manguyman = new TestPolygonEntity(currMap.getWalls().get(0).coords[0],currMap.getWalls().get(0), Color.BLUE);
        manguyman.setRotation(new Angle(1));
        manguyman.getCenterRef().moveTo(150, 150);
        manguyman.setVelocity(new Line(new Coord(0,0), new Coord(0,5)));
        manguyman.getCurveRef().setDeg(-5);
        //entities.add(manguyman);
        
        while(true){
            long currTime = System.currentTimeMillis();
            if(currTime != fpsTracker){
                currFPS = 1000/(currTime - fpsTracker);
                fpsTracker = System.currentTimeMillis();
                Util.Sysout(currFPS);
            }
            
            repaint();
            entities.forEach((e) -> e.Tick());
            for(Entity e : entities){
                Collider.hit(e, currMap);
            }
            try {
                //**/Thread.sleep(30);/*/
                Thread.sleep(16,500000);//*/
            } catch (InterruptedException ex) {
                Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
} 
        
