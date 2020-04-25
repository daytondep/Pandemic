package BoardPackage;

import GamePackage.Colour;

import java.util.ArrayList;

//TODO: field to hold where indexed player pawns are. dictionary? map?
public class Board {

    private ArrayList<String> citylist;
    private int[][]adjMap;

    private boolean gamelost = false;
    private int numOutbreak = 0; //how many outbreaks we've had. 8 is a loss condition
    private int outbreaksToLose = 8;

    private WorldMap gameMap;

    private int numCubes;
    private int[] petriDish; //TODO: stay in board. change representaion?


    public Board(int[][] adj, ArrayList<String> citylist){
        this.citylist = citylist;
        this.adjMap = adj;

        this.numCubes = citylist.size()/2;
        petriDish = new int[]{numCubes, numCubes, numCubes, numCubes};
    }


    public ArrayList<String> getCityList(){ return this.citylist; } //TODO: delete? redundant.

    public int getNumOutbreak(){ return this.numOutbreak; }

    public int getOutbreaksToLose(){ return this.outbreaksToLose; }

    public boolean getGameLost(){ return this.gamelost; }

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

    private int colourToIndex(Colour colour){
        //Takes in a disease colour and returns an index
        switch (colour){
            case BLUE:
                return 0;
            case RED:
                return 1;
            case YELLOW:
                return 2;
            case BLACK:
                return 3;
            default:
                return -1;
            //TODO: error handling
        }
    }

    public void infectCity(String cityToInfect) {
        //infects city and updates petridish counts.
        this.numOutbreak += this.gameMap.infectCity(citylist.indexOf(cityToInfect));
        int[] survey = this.gameMap.cubeSurvey();
        for(int i=0; i<this.petriDish.length; i++) {
            this.petriDish[i] = numCubes-survey[i];
        }
        this.gamelost = checkLoss();
    }

    public boolean checkLoss(){
        //checks the two ways board can know if the game has been lost.
        if(this.getNumOutbreak() > this.getOutbreaksToLose()){
            return true;
        }
        for(int i: petriDish) {
            if (this.petriDish[i]<0){
                return true;
            }
        }
        return false;
    }
}
