package GamePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private int difficulty; //how many epidemic cards in player deck
    private boolean gameLost;
    private boolean gameWon;
    private boolean[] cures = {false,false,false,false}; //Blue, Red, Yellow, Black
    private int infectionRateIndex = 0; //TODO: move to game.
    private final int[] infectionRateArray = {2, 2, 2, 3, 3, 4, 4}; //how many infection cards flip each turn TODO: move to game.
    private int numOutbreak;
    private WorldMap gameMap;
    private ArrayList<String> citylist = new ArrayList<>();
    private int[][] adjMap;

    private Deck infectionDeck; //TODO: move to game. *Will be of type "infectionDeck"*
    private Deck playerDeck; //TODO: move to game. *Will be of type "playerDeck"*

    public Board board;

    public Game(){
        populateCityList();
        this.adjMap = makeAdj();

        this.board = new Board(adjMap,citylist,infectionDeck,playerDeck);
        this.gameMap = new WorldMap(board,adjMap);
    }



    public int getDifficulty(){ return this.difficulty; }

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

    //TODO: move to Game. (FROM BOARD)
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

    //FROM BOARD
    public void infectionRateUp(){
        //Step 1 of 3 in an epidemic
        if(this.infectionRateIndex < this.infectionRateArray.length){
            this.infectionRateIndex++;
        }
    }

    //TODO: review. move to game? (FROM BOARD)
    public void infectCities(){
        //gets name of drawn card. uses name to get index from cityList. uses index to infect city in map.
        String cityToInfect;
        for(int i = 0; i<this.infectionRateArray[infectionRateIndex]; i++){
            cityToInfect = this.infectionDeck.drawCard();
            this.numOutbreak += (board.getNumOutbreak() +this.gameMap.infectCity(citylist.indexOf(cityToInfect))); //TODO: review combining these lines. clearer while seperate?
            if(board.getNumOutbreak() > board.getOutbreaksToLose()){ //TODO: review moving this check to game?
                //TODO: trigger loss here
                gameLost = true;
            }
            //TODO: check if petri dishes are negative. loss condition.
            //since this is an element of this method, may be beneficial to leave it in board.
        }
    }

    /*
    //TODO: this method should be in board or map. bad scoping connor!
    public void epidemicTrigger() {
        if(this.deckType==CardType.INFECTIONCARD){
            Card tripleInfect = this.deck.get(0);
            //TODO: implement the abilty to show what card was drawn?
            //TODO: call infect 3 times on tripleInfect city
            this.discard.add(tripleInfect);
            Collections.shuffle(discard);
            intensify();
        }
    }*/
}
