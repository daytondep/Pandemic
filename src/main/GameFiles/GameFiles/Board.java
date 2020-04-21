package GameFiles;

import GameFiles.Deck;

public class Board {

    private String[] cityList; //String list of city names //TODO: should be read in from file
    private int difficulty;

    private int epidemic = 0;
    private int[] infectionRate = {2, 2, 2, 3, 3, 4, 4};
    private int[] cures = {0,0,0,0};
    private Deck infection;
    private Deck playerDeck;
    private WorldMap gameMap;


    public Board(){

    }

    public String[] getCityList(){
        return this.cityList;
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public Colour colourAssign(int index){
        Colour cityColour = null;
        switch (index%(cityList.length/4)) {
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
                //exception handling
                break;
        }
        return cityColour;
    }
}
