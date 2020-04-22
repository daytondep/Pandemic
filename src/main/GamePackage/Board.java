package GamePackage;

import GamePackage.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Board {

    private ArrayList<String> citylist = new ArrayList<String>();
    private int difficulty;

    private int epidemic = 0;
    private int[] infectionRate = {2, 2, 2, 3, 3, 4, 4};
    private int[] cures = {0,0,0,0};
    private Deck infection;
    private Deck playerDeck;
    //private WorldMap gameMap;


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

    public ArrayList<String> getCityList(){
        return this.citylist;
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public Colour colourAssign(int index){
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

    public static void main(String[] args){
        Board newBoard = new Board();
    }
}
