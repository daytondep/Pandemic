package GamePackage;


import java.util.ArrayList;

public class WorldMap {

    private Board game;
    private ArrayList<String> cityList; //String list of city names //TODO:refactor into game object. remove here?
    private int[][] adjacency; //Adjacency matrix of cities. 1 is touching.
    private Node[] nodeMap; //Array of nodes that have references to nodes they're touching.

    public WorldMap(Board game, int[][] Adjmap){
        this.game = game;
        this.cityList = game.getCityList();
        this.adjacency = Adjmap;
        this.nodeMap = makeMap();
    }



    public Node[] makeMap() {
        Node[] nodeList = new Node[cityList.size()];
        for(int i=0; i<cityList.size(); i++){
            nodeList[i] = new Node(cityList.get(i), game.colourAssign(i)); //TODO: review if this should call game.getCityList every time? my heart says no.
        }
        for (int i=0; i<cityList.size(); i++){
           for (int j=0; j<adjacency[i].length; j++) {
               if (adjacency[i][j] == 1) {
                   nodeList[i].addNext(nodeList[j]);
               }
           }
        }
        return nodeMap;
    }

}