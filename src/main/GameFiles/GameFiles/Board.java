package GameFiles;
import java.io.*;

import java.util.ArrayList;

import GameFiles.Deck;
import GameFiles.*;

public class Board {

    private ArrayList<String> citylist = new ArrayList<String>();
    private int difficulty;

    private int epidemic = 0;
    private int[] infectionRate = {2, 2, 2, 3, 3, 4, 4};
    private int[] cures = {0,0,0,0};
    private Deck infection ;
    private Deck playerDeck;
    private WorldMap gameMap;


    public Board(){
        populateCityList();
        for (String city:citylist){
            System.out.println(city);
        }
    }

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

    public ArrayList<String> getCityList(){
        return this.citylist;
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public Colour colourAssign(int index){
        Colour cityColour = null;
        switch (index%(citylist.length/4)) {
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

    public static void main(String args[]){
        Board newBoard = new Board();

    }
}
