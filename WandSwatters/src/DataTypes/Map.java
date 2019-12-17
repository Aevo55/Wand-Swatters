/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import java.util.ArrayList;
import DataTypes.*;
/**
 *
 * @author dawsp
 */
public class Map {
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Wall> decor = new ArrayList<>();
    public String name;
    public Map(String _name, Wall... w){
        name = _name;
        for(Wall _W : w){
            walls.add(_W);
        }
    }
    
    public void addWalls(Wall... w){
        for(Wall _w : w){
            walls.add(_w);
        }
    }
    
    public ArrayList<Wall> getWalls(){
        return walls;
    }
    
    public ArrayList<Wall> getDecor(){
        return decor;
    }
}

