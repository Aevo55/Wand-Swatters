/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataTypes.Coord;
import Entities.Abstract.SphericalEntity;
import java.awt.Color;

/**
 *
 * @author dawsp
 */
public class TestSphericalEntity extends SphericalEntity{

    public TestSphericalEntity(Coord start, double rad, Color _col){
        super(start, rad);
        this.col = _col;
    }
    
    public TestSphericalEntity(Coord start, double rad) {    
        super(start, rad);
    }

    public TestSphericalEntity(){
        super(new Coord(500,500), 20);
    }
    
    @Override
    public void PostUpdate() {
        this.curve.rotateByDeg(0);
    }

    @Override
    public void DieMod() {
        
    }
    
}
