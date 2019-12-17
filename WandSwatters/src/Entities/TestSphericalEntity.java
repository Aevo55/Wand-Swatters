/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataTypes.Coord;
import Entities.Abstract.SphericalEntity;

/**
 *
 * @author dawsp
 */
public class TestSphericalEntity extends SphericalEntity{

    public TestSphericalEntity(Coord start, double rad) {    
        super(start, rad);
        System.out.println("FOUND IT");
    }

    public TestSphericalEntity(){
        super(new Coord(500,500), 20);
        System.out.println("Found it!!!!");
    }
    
    @Override
    public void PostUpdate() {
        this.curve.rotateByDeg(0);
    }

    @Override
    public void DieMod() {
        
    }
    
}
