


public class MapBuilder {
    private String[] cityList; //String list of city names
    private int[][] adjacency; //Adjacency matrix of cities. 1 is touching.
    private Node[] nodeMap; //Array of nodes that have refrences to nodes they're touching.

    public MapBuilder(String[] cities, int[][] map){
        this.cityList = cities;
        this.adjacency = map;
        this.nodeMap = makeMap();
    }

    public Node[] makeMap() {
        Node[] nodeList = new Node[cityList.length];
        for(int i=0; i<cityList.length; i++){
            nodeList[i] = new Node(cityList[i]);
        }
        for (int i=0; i<cityList.length; i++){
           for (int j=0; j<adjacency[i].length; j++) {
               if (adjacency[i][j] == 1) {
                   nodeList[i].addNext(nodeList[j]);
               }
           }
        }
        return nodeMap;
    }


}