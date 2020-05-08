package GamePackage;

import BoardPackage.Board;
import Player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private int difficulty; //how many epidemic cards in player deck
    private boolean gameLost;
    private boolean gameWon;
    private boolean[] cures = {false,false,false,false}; //Blue, Red, Yellow, Black
    private int infectionRateIndex = 0;
    private final int[] infectionRateArray = {2, 2, 2, 3, 3, 4, 4}; //how many infection cards flip each turn
    private int numOutbreak;
    //private WorldMap gameMap;
    private ArrayList<String> cityList = new ArrayList<>();
    private int[][] adjMap;

    private InfectionDeck infectionDeck; // *Will be of type "infectionDeck"*
    private PlayerDeck playerDeck; // *Will be of type "playerDeck"*

    private ArrayList<Player> playerList; //List of players in each game, can iterate through the list every turn to have players execute moves
    public Board board;

    public Game(){
        populateCityList();
        this.adjMap = makeAdj();

        this.board = new Board(adjMap,cityList);
        //this.gameMap = new WorldMap(board,adjMap); //TODO: move to board.
    }

    //TODO: layer between player and deck for discarding playercards. brains of actions.
    /*
    Might be easiest to, when a user's turn comes up, populate a list of off possible moves for the player, and have the player choose from them
     */
    private void chooseMove(){ //TODO: determine parameters
        //generate move list TODO: how will we show a move/how will it be executed

        //wrap a players move(function) with a front-end clickable event

        /*Pseudo code:
        if(action == endmove){
            break
        }

        if(Player.actioncount > 0 && Player.actioncount - costOfAction>0){
            execute move
        }else{
            print("Cannot perform action, choose another or end your turn")
        }
         */

        //from user input (choosing a move from the list)

    }

    /*
    Method to conduct a round of the game
    Go through each player in playerList, for each player, until their actionCount is at 0 or they voluntarily
     */
    public void conductRound(){
        /*
        for (Player p: playerList){
            while (actionCount > 0){ //not sure if this should be dealt with here or in chooseMove()
                chooseMove(Player p);
            }
         }

         TODO: how to save state. will we update as things happen, or will it be in a method (resolveRound())
         */
    }

    /*
    public void resolveRound(){

    }
    */
    public ArrayList<String> getCityList(){ return this.cityList; }

    public int getDifficulty(){ return this.difficulty; }

    private void populateCityList() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("StandardMap-cityindex.txt")); //TODO: pass filename.

            String line = br.readLine();
            while (line != null) {
                cityList.add(line);
                line = br.readLine();
            }
            br.close();
        }catch (IOException ex){
            System.err.println(ex);
        }
    }

    private int[][] makeAdj(){
        int[][] adj = new int[48][48];
        try {
            BufferedReader br = new BufferedReader(new FileReader("StandardMap-AdjMap.txt")); //TODO: pass filename.
            int city = 0;
            String[] broken_line;
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

    public Colour colourAssign(int index){
        //assigns a colour enum. based on the design decision to have the order of cities based on blue, red, yellow, black.
        Colour cityColour = null;
        switch (index%(cityList.size()/4)) {
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

    //FROM BOARD
    public void infectionRateUp(){
        //Step 1 of 3 in an epidemic
        if(this.infectionRateIndex < this.infectionRateArray.length){
            this.infectionRateIndex++;
        }
    }

    public void infectCities(){
        //gets name of drawn card. uses name to get index from cityList. uses index to infect city in map.
        String cityToInfect;
        for(int i = 0; i<this.infectionRateArray[infectionRateIndex]; i++){
            Card drawn = this.infectionDeck.drawCard();
            cityToInfect = drawn.getName();
            this.board.infectCity(cityToInfect);
            if(board.getGameLost()){
                //TODO: we lost. what now?
            }
        }
    }

    public void triggerEpidemic() {
        Card tripleInfect = this.infectionDeck.drawLast();
        this.board.infectCity(tripleInfect.getName());
        this.board.infectCity(tripleInfect.getName());
        this.board.infectCity(tripleInfect.getName());
        this.infectionDeck.discardCard(tripleInfect);
        this.infectionDeck.intensify();
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }
}
