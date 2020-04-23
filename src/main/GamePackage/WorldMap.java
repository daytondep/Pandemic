package GamePackage;


import java.util.ArrayList;

public class WorldMap {

    private Board board;
    private ArrayList<String> cityList; //String list of city names
    private int[][] adjacency; //Adjacency matrix of cities. 1 is touching. //TODO: not required. remove. (sent from game)
    private City[] cityMap; //Array of nodes that have references to nodes they're touching. //TODO: change to arraylist?

    public WorldMap(Board board, int[][] Adjmap){
        this.board = board;
        this.cityList = board.getCityList();
        this.adjacency = Adjmap;
        this.cityMap = makeMap();
    }

    public City[] makeMap() { //TODO: pass adjmap
        //creates cities with names and colours. puts them in the cityMap
        City[] nodeList = new City[cityList.size()];
        for(int i=0; i<cityList.size(); i++){
            nodeList[i] = new City(cityList.get(i), board.colourAssign(i)); //TODO: review if this should call game.getCityList every time? my heart says no.
        }
        //takes the cities from the cityMap and using the adjMap, gives them links to all adjacent cities.
        for (int i=0; i<cityList.size(); i++){
           for (int j=0; j<adjacency[i].length; j++) {
               if (adjacency[i][j] == 1) {
                   nodeList[i].addNext(nodeList[j]);
               }
           }
        }
        return cityMap;
    }

    public int infectCity(int index){
        //Looks in the City node array at index, and infects with colour of that city.
        return cityMap[index].infectCity(cityMap[index].getColour());
    }

}