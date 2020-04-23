package GamePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//TODO: field to hold where indexed player pawns are. dictionary? map?
public class Board {

    private ArrayList<String> citylist = new ArrayList<>();
    private int difficulty; //how many epidemic cards in player deck TODO: move to game.
    //TODO: add loss flag.

    private int numOutbreak = 0; //how many outbreaks we've had. 8 is a loss condition
    private int outbreaksToLose = 8;
    private int infectionRateIndex = 0; //TODO: move to game.
    private final int[] infectionRateArray = {2, 2, 2, 3, 3, 4, 4}; //how many infection cards flip each turn TODO: move to game.
    private int[] cures = {0,0,0,0}; //Blue, Red, Yellow, Black TODO: switch to boolean?
    private Deck infectionDeck; //TODO: move to game.
    private Deck playerDeck; //TODO: move to game.
    private WorldMap gameMap;

    private int[] petriDish; //TODO: stay in board. change representaion?


    public Board(){
        populateCityList();
        for (String city:citylist){
            System.out.println(city);
        }
        int numCubes = citylist.size()/2;
        petriDish = new int[]{numCubes, numCubes, numCubes, numCubes};
    }

    //TODO: move to game.
    private void populateCityList() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("cityindex.txt"));

            String line = br.readLine();
            while (line != null) {
                citylist.add(line);
                line = br.readLine();
            }
            br.close();
        }catch (IOException ex){
            System.err.println(ex);
        }
    }

    //TODO: move to Game.
    private int[][] makeAdj(){
        int[][] adj = new int[48][48];
        try {
            BufferedReader br = new BufferedReader(new FileReader("StandardMap.txt"));
            int city = 0;
            String[] broken_line = null; //TODO: apparently redundant?
            String line = br.readLine();
            while (line != null) {
                broken_line = line.split(" ");
                for (int i=0;i<broken_line.length;i++){
                    adj[city][i] = Integer.parseInt(broken_line[i]);
                }
                city++;
                line = br.readLine();
            }
            br.close();
        }catch (IOException ex){
            System.err.println(ex);
        }
        return adj;
    }

    public ArrayList<String> getCityList(){ return this.citylist; } //TODO: delete. redundant.

    public int getDifficulty(){ return this.difficulty; } //TODO: move to game.

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

    //TODO: move to game.
    public void infectionRateUp(){
        //Step 1 of 3 in an epidemic
        if(this.infectionRateIndex < this.infectionRateArray.length){
            this.infectionRateIndex++;
        }
    }

    //TODO: review. move to game?
    public void infectCities(){
        //gets name of drawn card. uses name to get index from cityList. uses index to infect city in map.
        String cityToInfect;
        for(int i = 0; i<this.infectionRateArray[infectionRateIndex]; i++){
            cityToInfect = this.infectionDeck.drawCard();
            this.numOutbreak += this.gameMap.infectCity(citylist.indexOf(cityToInfect)); //TODO: review combining these lines. clearer while seperate?
            if(this.numOutbreak > this.outbreaksToLose){ //TODO: review moving this check to game?
                //TODO: trigger loss here
            }
            //TODO: check if petri dishes are negative. loss condition.
        }
    }

    public static void main(String[] args){
        Board newBoard = new Board();
    }
}
