/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataTypes.Coord;
import DataTypes.Net;
import Entities.Abstract.*;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 *
 * @author dawsp
 */
public class TestPolygonEntity extends PolygonalEntity {
    public TestPolygonEntity(Coord c, Net n, Color col){
        super(c,n,col);
    }
    
    public void DieMod(){
        //Just die, dummy
    }
    
    public void PostUpdate(){
        //Just update, dummy
    }
    
    public void DrawExtra(Graphics2D g){
        //Just draw, dummy
    }
}
