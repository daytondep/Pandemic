package GameFiles;

import java.util.ArrayList;

public class Node{
    private final String cityName;
    private final Colour cityColour;
    private ArrayList<Node> nextTo = new ArrayList<>();

    public Node(String name, Colour colour){
        this.cityName = name;
        this.cityColour = colour;
    }

    public void addNext(Node next){
        this.nextTo.add(next);
    }

    public String getName(){
        return this.cityName;
    }

    public Colour getColour(){
        return this.cityColour;
    }
}