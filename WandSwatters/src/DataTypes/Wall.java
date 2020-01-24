package DataTypes;

import java.awt.Color;
/**
 *
 * @author dawsp
 */
public class Wall extends Net{
    private Color color;
    private double bounciness;
    
    public Wall(Net n, Color c, double s){
        super(new Net(n));
        color = c;
        bounciness = s;
    }
    
    public Wall(Net n, Color c){
        super (new Net(n));
        color = c;
    }
    
    public void setCol(Color c){
        color = c;
    }
    
    public Color getCol(){
        return color;
    }
    
    public void setSpring(double s){
        bounciness = s;
    }
    
    public double getSpring(){
        return bounciness;
    }
}
