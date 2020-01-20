package MainPackage;
import DataTypes.*;
import Entities.Abstract.*;
import Entities.*;
import Resources.Maps.MapLoader;
import Utility.Collider;
import Utility.GraphicsEngine;
import Utility.Input;
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
    TestCircularEntity testplayer;
    
    boolean w = false, ww = false,
            a = false, aa = false,
            s = false, ss = false,
            d = false, dd = false,
            left = false, _left = false,
            right = false, _right = false;
    
    public GameLoop() {
        
        mapLoader = new MapLoader();
        
        thread = new Thread(this); //initializes the thread and puts Class1 into it
        thread.start(); //begins the thread
        
    }
    public void paintComponent(Graphics g){
        setOpaque(false); //wipes everything on the frame
        Graphics2D gc = (Graphics2D)g;
        super.paintComponent(gc);
        if(!shapeToggle){
            GraphicsEngine.drawMap(gc, currMap);
        }else{
            GraphicsEngine.drawEntity(gc, new TestCircularEntity(new Coord(0,0), testplayer.getRadius(), Color.WHITE));
            GraphicsEngine.drawEntity(gc, new TestCircularEntity(new Coord(200,100), testplayer.getRadius(), Color.WHITE));
            GraphicsEngine.drawEntity(gc, new TestCircularEntity(new Coord(100,300), testplayer.getRadius(), Color.WHITE));
            GraphicsEngine.drawEntity(gc, new TestCircularEntity(new Coord(10,10), testplayer.getRadius(), Color.WHITE));
            
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[0].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[0].getShadows(testplayer.getRadius())[1]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[1].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[1].getShadows(testplayer.getRadius())[1]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[2].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[2].getShadows(testplayer.getRadius())[1]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[3].getShadows(testplayer.getRadius())[0]);
            GraphicsEngine.drawLine(gc, currMap.getWalls().get(0).lines[3].getShadows(testplayer.getRadius())[1]);
        }
        entities.forEach((e) -> e.Draw(gc));
        //GraphicsEngine.drawLine(gc, testplayer.getVelocity().mulMag(100));
    }
    public void keyPressed(KeyEvent evt){
        switch(evt.getKeyCode()){
            case KeyEvent.VK_SPACE:
                shapeToggle = true;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_W:
                w = true;
                ww = true;
                ss = false;
                break;
            case KeyEvent.VK_A:
                a = true;
                aa = true;
                dd = false;
                break;
            case KeyEvent.VK_S:
                s = true;
                ss = true;
                ww = false;
                break;
            case KeyEvent.VK_D:
                d = true;
                dd = true;
                aa = false;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                _left = true;
                _right = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                _right = true;
                _left = false;
                break;
            case KeyEvent.VK_SHIFT:
                Input.input.shift = true;
        }
    }

    public void keyReleased(KeyEvent evt){
        switch(evt.getKeyCode()){
            case KeyEvent.VK_SPACE:
                shapeToggle = false;
                break;
            case KeyEvent.VK_W:
                w = false;
                ww = false;
                if(s){
                    ss = true;
                }
                break;
            case KeyEvent.VK_A:
                a = false;
                aa = false;
                if(d){
                    dd = true;
                }
                break;
            case KeyEvent.VK_S:
                s = false;
                ss = false;
                if(w){
                    ww = true;
                }
                break;
            case KeyEvent.VK_D:
                d = false;
                dd = false;
                if(a){
                    aa = true;
                }
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                _left = false;
                if(right){
                    _right = true;
                }
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                _right = false;
                if(left){
                    _left = true;
                }
                break;
            case KeyEvent.VK_SHIFT:
                Input.input.shift = false;
        }
    }
    
    @Override
    public void run(){
        fpsTracker = System.currentTimeMillis();
        
        entities = new ArrayList<>();
        currMap = mapLoader.getMap("Map 0");
        
        testplayer = new TestCircularEntity(new Coord(550,250), 10);
        //testplayer.getCurveRef().setDeg(5);
        entities.add(testplayer);
        
        manguyman = new TestPolygonEntity(currMap.getWalls().get(0).coords[0],currMap.getWalls().get(0), Color.BLUE);
        manguyman.setRotation(new Angle(1));
        manguyman.getCenterRef().moveTo(150, 150);
        manguyman.setVelocity(new Line(new Coord(0,0), new Coord(0,5)));
        manguyman.getCurveRef().setDeg(-5);
        //entities.add(manguyman);
        
        entities.forEach((e) -> e.Tick());
        
        while(true){
            long currTime = System.currentTimeMillis();
            if(currTime != fpsTracker){
                currFPS = 1000/(currTime - fpsTracker);
                fpsTracker = System.currentTimeMillis();
                //Util.Sysout(currFPS);
            }
            
            //Util.Sysout(testplayer.getView().getAng().distToMirrored(new Angle(0)).getDeg());
            //Util.Sysout(testplayer.getView().getAng().distTo(new Angle(0)).getDeg());
            //Util.Sysout("");
            
            repaint();
            
            Input.input.w = ww;
            Input.input.a = aa;
            Input.input.s = ss;
            Input.input.d = dd;
            Input.input.left = _left;
            Input.input.right = _right;
            
            
            boolean colliderFlag = true;
            while(colliderFlag == true){
                for(Entity e : entities){
                    colliderFlag = Collider.hit(e, currMap);
                    //colliderFlag = colliderFlag || Collider.hit(e, new ArrayList<Entity>(entities.subList(entities.indexOf(e), entities.size())));
                }
            }
            
            
            entities.forEach((e) -> e.Tick());
            //*
            try {
                //Thread.sleep(16,500000);
                Thread.sleep(16);
                //Thread.sleep(8,250000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
            //*/
        }
    } 
} 
        
