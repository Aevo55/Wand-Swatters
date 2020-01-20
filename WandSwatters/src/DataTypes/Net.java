/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import Utility.Util;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 *
 * @author dawsp
 */
public class Net {
    public Coord[] coords;
    public Line[] lines;
    public Net(Coord... _coords){
        recalc(_coords);
    }
    
    public Net(Line... _lines){
        recalc(_lines);
    }
    
    public void recalc(Coord... _coords){
        coords = new Coord[_coords.length];
        lines = new Line[_coords.length];
        for (int x = 0; x < _coords.length; x++) {
            coords[x] = _coords[x].copy();
        }
        lines[coords.length - 1] = new Line(coords[coords.length - 1], coords[0]);
        for(int x = 0; x < coords.length - 1; x++){
            lines[x] = new Line(coords[x], coords[x+1]);
        }
    }
    
    public void recalc(Line... _lines){
        ArrayList<Coord> _tempcoords = new ArrayList<>();
        for(Line l : _lines){
            _tempcoords.add(l.getP1());
            _tempcoords.add(l.getP2());
        }
        ArrayList<String> hashes = new ArrayList<>();
        for(Coord c : _tempcoords){
            hashes.add(c.toString());
        }
        ArrayList<String> hashset = new ArrayList<>(new LinkedHashSet<>(hashes));
        ArrayList<Coord> _coords = new ArrayList<>(Util.HashToCoord(hashset, _tempcoords));
        recalc(_coords.toArray(new Coord[_coords.size()]));
    }
    
    public Net(Net n){
        this(n.coords);
    }
    
    public int[] getXs(){
        int[] xs = new int[coords.length];
        for(int n = 0; n < coords.length; n++){
            xs[n] = (int)coords[n].getX();
        }
        return xs;
    }
    
    public int[] getYs(){
        int[] ys = new int[coords.length];
        for(int n = 0; n < coords.length; n++){
            ys[n] = (int)coords[n].getY();
        }
        return ys;
    }
    
    public Coord[] getCoords(){
        Coord[] c = new Coord[coords.length];
        for(int x = 0; x < coords.length; x++){
            c[x] = coords[x].copy();
        }
        return c;
    }
    
    public void rotate(Coord center, Angle angle){
        Line anchor = new Line(center, this.lines[0].getP1());
        anchor.rotateBy(angle);
        for(int x = 0; x < lines.length; x++){
            lines[x].rotateBy(angle);
            if(x == 0){
                lines[0].moveTo(anchor.getP2());
            }else{
                lines[x].moveTo(lines[x-1].getP2());
            }
        }
        recalc(lines);
    }
    
    public void moveBy(Line l){
        l.moveTo(lines[0].getP1());
        for(int x = 0; x < lines.length; x++){
            if(x == 0){
                lines[0].moveTo(l.getP2());
            }else{
                lines[x].moveTo(lines[x-1].getP2());
            }
        }
    }
    
    public int getSize(){
        return lines.length;
    }
    
    public ArrayList<Line> getLinesList(){
        ArrayList<Line> l = new ArrayList<Line>();
        for (Line _l : lines){
            l.add(_l);
        }
        return l;
    }
}
