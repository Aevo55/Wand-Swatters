/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

/**
 *
 * @author dawsp
 */
public class Vector extends Line{
    private double decay;
    public Vector(Line l, double d){
        super(l);
        decay = d;
    }
    
    public double getDecay(){
        return decay;
    }
    
    public void setDecay(double d){
        decay = d;
    }
    
    public void decay(){
        recalc(this.getP1(), this.getAng(), this.getMag() *  decay);
    }
}
