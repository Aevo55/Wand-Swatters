/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataTypes.Angle;
import DataTypes.Coord;
import DataTypes.Line;
import Entities.Abstract.SphericalEntity;
import Utility.GraphicsEngine;
import Utility.Input;
import Utility.RGBCycle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;

/**
 *
 * @author dawsp
 */
public class TestCircularEntity extends SphericalEntity{
    
    private Line view = new Line();
    RGBCycle c = new RGBCycle();
    public TestCircularEntity(Coord start, double rad, Color _col){
        super(start, rad);
        this.col = _col;
        view = new Line(start, new Angle(0), 100);
    }
    
    public TestCircularEntity(Coord start, double rad) {    
        super(start, rad);
    }

    public TestCircularEntity(){
        super(new Coord(500,500), 20);
    }
    
    @Override
    public void PostUpdate() {
        if(Input.input.w || Input.input.a || Input.input.s || Input.input.d){
            Line lw = new Line(new Coord(0,0), new Angle(0).offset(view.getAng()), Input.input.w ? 1 : 0);
            Line la = new Line(new Coord(0,0), new Angle(270).offset(view.getAng()), Input.input.a ? 1 : 0);
            Line ls = new Line(new Coord(0,0), new Angle(180).offset(view.getAng()), Input.input.s ? 1 : 0);
            Line ld = new Line(new Coord(0,0), new Angle(90).offset(view.getAng()), Input.input.d ? 1 : 0);

            Line temp = new Line();

            temp.addTo(lw);
            temp.addTo(la);
            temp.addTo(ls);
            temp.addTo(ld);
            
            temp.setMag(1);
            
            double mag = velocity.getMag();
            if(!STOP){
                velocity.addTo(temp);   
            }else{
                STOP = false;
            }
            double max;
            if(Input.input.shift){
                max = 10;
            }else{
                max = 5;
            }
            if(mag > max){
                velocity.setMag(mag);
            }
            
        }
        if(Input.input.left){
            view.rotateBy(new Angle(-5));
        }else if(Input.input.right){
            view.rotateBy(new Angle(5));
        }
        view.setMag(20);
        view.moveTo(center);
        this.getVelocityRef().mulMag(0.95);
        c.Cycle(RGBCycle.Speed.VeryFast);
        this.setCol(c.GetColor());
    }

    @Override
    public void DieMod() {
        
    }
    
    public Line getView(){
        return view.copy();
    }
    
    public Line getViewRef(){
        return view;
    }
    
    public void DrawExtra(Graphics2D g){
        GraphicsEngine.drawLine(g, view);
    }
}
