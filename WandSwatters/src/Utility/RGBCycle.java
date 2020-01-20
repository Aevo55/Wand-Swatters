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
                Cycle(5);
                break;
            case Slow:
                Cycle(10);
                break;
            case Normal:
                Cycle(15);
                break;
            case Fast:
                Cycle(20);
                break;
            case VeryFast:
                Cycle(25);
                break;
        }
    }
    
    private void Cycle(int i){
        if(b == 255 && g == 15){
            r+=(i);
            if(r > 255)r=255;
        }
        if(b == 15 && g == 255){
            r-=i;
            if(r<15)r=15;
        }
        if(r == 255 && b == 15){
            g+=i;
            if(g>255)g=255;
        }
        if(r == 15 && b == 255){
            g-=i;
            if(g<15)g=15;
        }
        if(g == 255 && r == 15){
            b+=i;
            if(b>255)b=255;
        }
        if(g == 15 && r == 255){
            b-=i;
            if(b<15)b=15;
        }
    }
    
    public Color GetColor(){
        return new Color(r,g,b);
    }
}
