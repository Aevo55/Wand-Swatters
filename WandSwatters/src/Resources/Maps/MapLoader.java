package Resources.Maps;
import DataTypes.*;
import java.awt.Color;
import java.util.ArrayList;

public class MapLoader {
    ArrayList<Map> mapList;
    
    Net m0n0;
    Net m0n1;
    Wall m0w0;
    Wall m0w1;
    Map m0;
    public MapLoader(){
        mapList = new ArrayList<>();
    
        m0n0 = new Net(new Coord[]{new Coord(0,0), new Coord(100,300), new Coord(200,200), new Coord(200,100)});

        m0w0 = new Wall(m0n0, Color.BLACK, 1);

        m0 = new Map("Map 0", m0w0 /*whats this?*/);

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


