package Resources.Maps;
import DataTypes.*;
import java.awt.Color;
import java.util.ArrayList;

public class MapLoader {
    ArrayList<Map> mapList;
    
    Net m0n0;
    Net m0n1;
    Net background;
    Wall m0w0;
    Wall m0w1;
    Wall _background;
    Map m0;
    public MapLoader(){
        mapList = new ArrayList<>();
    
        background = new Net(new Coord[]{new Coord(0,0), new Coord(0,720), new Coord(1000,720), new Coord(1000,0)});
        m0n0 = new Net(new Coord[]{new Coord(200,200), new Coord(200,300), new Coord(300,300), new Coord(300,200)});
        
        m0w0 = new Wall(m0n0, Color.RED, 0);
        _background = new Wall(background, Color.WHITE, 1);
        m0 = new Map("Map 0", /*_background,*/ m0w0 /*whats this?*/);

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


