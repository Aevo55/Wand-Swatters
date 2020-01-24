package Utility;

import DataTypes.*;
import ReturnTypes.CoordAngle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
public class GraphicsEngine3D {
    public static void DrawMap(Graphics2D g, Line view, Map m){
        ArrayList<Line> lineList = new ArrayList<>();
        ArrayList<Coord> visibleCoords = new ArrayList<Coord>();
        for(Wall w : m.getWalls()){ // iterate over every line in the map
            for(Line line : w.getLinesList()){
                
                Angle a = view.getAng();
                Angle b = new Line(view.getP1(), line.getP1()).getAng();
                double a1 = a.distTo(b).getDeg();
               
                Angle c = new Line(view.getP1(), line.getP2()).getAng();
                double a2 = a.distTo(c).getDeg();
                
                if(a1 < 30){
                    if(a2 < 30){
                        lineList.add(line);
                        visibleCoords.add(line.getP1());
                        visibleCoords.add(line.getP2());
                    }
                    else{
                        visibleCoords.add(line.getP1());
                        
                        Coord temp1 = Collider.hit(view.copy().mulMag(100).rotateBy(new Angle(30)), line);
                        Coord temp2 = Collider.hit(view.copy().mulMag(100).rotateBy(new Angle(-30)), line);
                        
                        if(temp1 != null){
                            visibleCoords.add(temp1);
                        }else if(temp2 != null){
                            visibleCoords.add(temp2);
                        }
                        
                    }
                }else if(a2 < 30){
                    
                    
                    Coord temp1 = Collider.hit(view.copy().mulMag(100).rotateBy(new Angle(30)), line);
                    Coord temp2 = Collider.hit(view.copy().mulMag(100).rotateBy(new Angle(-30)), line);

                    if(temp1 != null){
                        visibleCoords.add(temp1);
                    }else if(temp2 != null){
                        visibleCoords.add(temp2);
                    }
                    
                    visibleCoords.add(line.getP2());
                }
            }
        }
        visibleCoords = new ArrayList<>(
            visibleCoords
                .stream()
                .distinct()
                //.sorted((c1, c2) -> (int)(c1.distanceTo(view.getP1()) - c2.distanceTo(view.getP1())))
                .collect(Collectors.toList()));
        
        GraphicsEngine.drawWall(g, new Wall(new Net(visibleCoords.toArray(new Coord[visibleCoords.size()])), Color.GREEN));
        
        /*
        ArrayList<Coord> coordsList = new ArrayList<>();
        for(Line l : lineList){
            coordsList.add(l.getP1());
            coordsList.add(l.getP2());
        }
        //generates list of sorted coordinantes from visible lines
        coordsList = new ArrayList<>(
            coordsList
                .stream()
                .distinct()
                .sorted((c1, c2) -> (int)(c1.distanceTo(view.getP1()) - c2.distanceTo(view.getP1())))
                .collect(Collectors.toList()));
        */
        /*for(int i = 0; i < coordsList.size(); i++){
            for(Line l : lineList){
                if(l.foundIn(coordsList.subList(0, i))){
                    drawLine(g, view, l);
                    lineList.remove(l);
                }
            }
        }*/
    }
    
    public static void drawLine(Graphics2D g, Line view, Line l){
        double a1 = view.getAng().distTo(new Angle(view.getP1().angleTo(l.getP1()))).getDeg();
        double a2 = view.getAng().distTo(new Angle(view.getP1().angleTo(l.getP2()))).getDeg();
        
        double x1 = 1000-(a1 / 60 * 1000); // window is 1000 pixels wide
        double x2 = 1000-(a2 / 60 * 1000);
        
        double d1 = l.getP1().distanceTo(view.getP1());
        double d2 = l.getP2().distanceTo(view.getP2());
        
        ArrayList<Coord> c = new ArrayList<>();
        c.add(new Coord(x1, 360-(360-d1)));
        c.add(new Coord(x2, 360-(360-d2)));
        c.add(new Coord(x2, 360+(360-d2)));
        c.add(new Coord(x1, 360+(360-d1)));
        
        Net n = new Net(c.toArray(new Coord[4]));
        Wall w = new Wall(n, Color.BLACK, 1);
        GraphicsEngine.drawWall(g, w);
    }
}
