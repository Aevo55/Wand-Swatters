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
    
        m0n0 = new Net(new Coord[]{new Coord(100,100), new Coord(100,200), new Coord(200,200), new Coord(200,100)});
        m0n1 = new Net(new Coord[]{new Coord(300,300), new Coord(300,500), new Coord(500,500), new Coord(500,300)});

        m0w0 = new Wall(m0n0, Color.BLACK, 1);
        m0w1 = new Wall(m0n1, Color.BLUE, 1);

        m0 = new Map("Map 0", m0w0 /*whats this?*/, m0w1);

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


