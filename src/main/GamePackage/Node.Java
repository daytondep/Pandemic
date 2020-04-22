package GamePackage;

import java.util.ArrayList;

public class Node{
    private final String cityName;
    private final Colour cityColour;
    private ArrayList<Node> nextTo = new ArrayList<>();
    private boolean canInfect = true;
    private int blue = 0;
    private int red = 0;
    private int yellow = 0;
    private int black = 0;

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

    public int infectCity(Colour disease){
        int epidemicCount = 0; //how many epidemics were recursively caused here.
        if (this.canInfect){
            switch(disease){
                case BLUE:
                    if(this.blue==3){
                        epidemicCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.blue++;
                    }
                    break;
                case RED:
                    if(this.red==3){
                        epidemicCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.red++;
                    }
                    break;
                case YELLOW:
                    if(this.yellow==3){
                        epidemicCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.yellow++;
                    }
                    break;
                case BLACK:
                    if(this.black==3){
                        epidemicCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.black++;
                    }
                    break;
                default:
                    //TODO: error handling
                    break;
            }
        }
        return epidemicCount;
    }

    private int outBreak(Colour disease){
        int epidemicCount = 0;
        for(Node city:nextTo){
            epidemicCount+=city.infectCity(disease);
        }
        return epidemicCount++;
    }
}