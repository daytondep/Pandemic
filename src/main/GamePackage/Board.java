package GamePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Board {

    private ArrayList<String> citylist = new ArrayList<>();
    private int difficulty;

    private int numEpidemic = 0;
    private int epidemicsToLose = 8;
    private int infectionRateIndex = 0;
    private final int[] infectionRateArray = {2, 2, 2, 3, 3, 4, 4};
    private int[] cures = {0,0,0,0};
    private Deck infectionDeck;
    private Deck playerDeck;
    private WorldMap gameMap;

    private int[] petriDish;


    public Board(){
        populateCityList();
        for (String city:citylist){
            System.out.println(city);
        }
        int numCubes = citylist.size()/2;
        petriDish = new int[]{numCubes, numCubes, numCubes, numCubes};
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

    public ArrayList<String> getCityList(){ return this.citylist; }

    public int getDifficulty(){ return this.difficulty; }

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

    public void infectionRateUp(){
        if(this.infectionRateIndex < this.infectionRateArray.length){
            this.infectionRateIndex++;
        }
    }

    public void infectCities(){
        String cityToInfect;
        for(int i = 0; i<this.infectionRateArray[infectionRateIndex]; i++){
            cityToInfect = this.infectionDeck.drawCard();
            this.numEpidemic += this.gameMap.infectCity(citylist.indexOf(cityToInfect)); //TODO: review combining these lines. clearer while seperate?
            if(this.numEpidemic > this.epidemicsToLose){
                //TODO: trigger loss here
            }
        }
    }

    public static void main(String[] args){
        Board newBoard = new Board();
    }
}
