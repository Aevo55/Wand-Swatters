/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.Color;

/**
 *
 * @author dawsp
 */
public class RGBCycle {
    private int r;
    private int g;
    private int b;
    public enum StartColor{RED, BLUE, GREEN};
    public enum Speed{VerySlow, Slow, Normal, Fast, VeryFast};
    public RGBCycle(){
        r = 15;
        g = 15;
        b = 255;
    }
    
    public RGBCycle(StartColor col){
        if(col == StartColor.RED){
            r = 255;
            g = 15;
            b = 15;
        }else if(col == StartColor.GREEN){
            r = 15;
            g = 255;
            b = 15;
        }else{
            r = 15;
            g = 15;
            b = 255;
        }
    }
    
    public void Cycle(Speed s){
        switch(s){
            case VerySlow:
        }
    }
    
    private void Cycle(int i){
        if(b == 255 && g == 15){
            r+=(i);
        }
        if(b == 15 && g == 255){
            r-=i;
        }
        if(r == 255 && b == 15){
            g+=i;
        }
        if(r == 15 && b == 255){
            g-=i;
        }
        if(g == 255 && r == 15){
            b+=i;
        }
        if(g == 15 && r == 255){
            b-=i;
        }
    }
    
    private void Check(){
        
    }
    
    public Color GetColor(){
        return new Color(r,g,b);
    }
}
