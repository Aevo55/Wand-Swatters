package Resources.Maps;
import DataTypes.*;
import java.awt.Color;
import java.util.ArrayList;

public class MapLoader {
    ArrayList<Map> mapList;
    
    Net m0n0;
    Net m0n1;
    Net m0n2;
    Net m0n3;
    Net background;
    Wall m0w0;
    Wall m0w1;
    Wall m0w2;
    Wall m0w3;
    Wall _background;
    Map m0;
    public MapLoader(){
        mapList = new ArrayList<>();
    
        background = new Net(new Coord[]{new Coord(0,0), new Coord(0,720), new Coord(1000,720), new Coord(1000,0)});
        m0n0 = new Net(new Coord[]{new Coord(200,200), new Coord(200,300), new Coord(300,300), new Coord(300,200)});
        m0n1 = new Net(new Coord[]{new Coord(600,600), new Coord(600,700), new Coord(700,700), new Coord(700,600), new Coord(650, 600), new Coord(650,625), new Coord(675, 625), new Coord(675, 675), new Coord(625, 675), new Coord(625, 625), new Coord(650, 625), new Coord(650, 600)});
        m0n2 = new Net(new Coord[]{new Coord(200,350), new Coord(200,450), new Coord(300,450), new Coord(300,350)});
        m0n3 = new Net(new Coord[]{new Coord(350,200), new Coord(350,450), new Coord(450,450), new Coord(450,200)});
        
        m0w0 = new Wall(m0n0, Color.RED, 1);
        m0w1 = new Wall(m0n1, Color.RED, 0);
        m0w2 = new Wall(m0n2, Color.RED, 1);
        m0w3 = new Wall(m0n3, Color.RED, 1);
        _background = new Wall(background, Color.WHITE, 1);
        m0 = new Map("Map 0", _background, m0w0 /*whats this?*/,m0w1, m0w2, m0w3);

        mapList.add(m0);
    }
    
    public Map getMap(String mapName){
        for(Map m : mapList){
            if(m.name.equals(mapName)){
                return m;
            }
        }
        return null;
    }
}


