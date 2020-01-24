/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReturnTypes;

import DataTypes.Angle;
import DataTypes.Coord;

/**
 *
 * @author dawsp
 */
public class CoordAngle {
    public Angle ang;
    public Coord coord;
    public CoordAngle(Angle _ang, Coord _c){
        ang = _ang;
        coord = _c;
    }
    public CoordAngle(){
        ang = new Angle();
        coord = new Coord();
    }
}
