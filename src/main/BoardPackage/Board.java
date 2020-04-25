package BoardPackage;

import GamePackage.Colour;

import java.util.ArrayList;

//TODO: field to hold where indexed player pawns are. dictionary? map?
public class Board {

    private ArrayList<String> citylist;
    private int[][]adjMap;


    private boolean gamelost;
    private int numOutbreak = 0; //how many outbreaks we've had. 8 is a loss condition
    private int outbreaksToLose = 8;

    private WorldMap gameMap;

    private int[] petriDish; //TODO: stay in board. change representaion?


    public Board(int[][] adj, ArrayList<String> citylist){
        this.citylist = citylist;
        this.adjMap = adj;

        int numCubes = citylist.size()/2;
        petriDish = new int[]{numCubes, numCubes, numCubes, numCubes};
    }


    public ArrayList<String> getCityList(){ return this.citylist; } //TODO: delete. redundant.

    public int getNumOutbreak(){ return this.numOutbreak; }

    public int getOutbreaksToLose(){ return this.outbreaksToLose; }

    //TODO: move to WorldMap and copy to game? or leave here?
    public Colour colourAssign(int index){
        //assigns a colour enum. based on the design decision to have the order of cities based on blue, red, yellow, black.
        Colour cityColour = null;
        switch (index%(citylist.size()/4)) {
            case 1:
                cityColour = Colour.BLUE;
                break;
            case 2:
                cityColour = Colour.RED;
                break;
            case 3:
                cityColour = Colour.YELLOW;
                break;
            case 4:
                cityColour = Colour.BLACK;
                break;
            default:
                //TODO: exception handling
                break;
        }
        return cityColour;
    }
//
//    //TODO: review. move to game?
//    /*
//    Still want to go over and see whether this is better in game or board (in both atm)
//     */
//    public void infectCities(){
//        //gets name of drawn card. uses name to get index from cityList. uses index to infect city in map.
//        String cityToInfect;
//        for(int i = 0; i<this.infectionRateArray[infectionRateIndex]; i++){
//            Card drawn = this.infectionDeck.drawCard();
//            cityToInfect = drawn.getName();
//            this.numOutbreak += this.gameMap.infectCity(citylist.indexOf(cityToInfect)); //TODO: review combining these lines. clearer while seperate?
//            if(this.numOutbreak > this.outbreaksToLose){ //TODO: review moving this check to game?
//                //TODO: trigger loss here
//                gamelost = true;
//            }
//            //TODO: check if petri dishes are negative. loss condition.
//        }
//    }

}
