package BoardPackage;

import GamePackage.Colour;

import java.util.ArrayList;

public class City {
    private final String cityName;
    private final Colour cityColour; //inherent disease
    private ArrayList<City> nextTo = new ArrayList<>(); //Links to every city that is touching this city.
    private boolean canInfect = true; //can be blocked by quarantine specialist
    private int[] diseaseCounts = {0, 0, 0, 0}; //blue, red, yellow, black
    private boolean hasResearchStation = false;

    public City(String name, Colour colour){
        this.cityName = name;
        this.cityColour = colour;
    }

    public void addNext(City next){
        this.nextTo.add(next);
    }

    public String getName(){
        return this.cityName;
    }

    public Colour getColour(){
        return this.cityColour;
    }

    public void blockInfect(){ this.canInfect=false; } //Caused by Quarantine Specialist and Resilient Population

    public void allowInfect(){ this.canInfect=true; } //Caused by Quarantine Specialist leaving

    public boolean isHasResearchStation(){ return hasResearchStation; }

    public void placeResearchStation(){ this.hasResearchStation = true; }

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

    public int infectCity(Colour disease){
        int outbreakCount = 0; //how many epidemics were recursively caused here.
        if (this.canInfect){
            int index = colourToIndex(disease);
            if(this.diseaseCounts[index]==3){
                outbreakCount+=outBreak(disease);
            }else{
                this.diseaseCounts[index]++;
            }
        }
        return outbreakCount;
    }

    private int outBreak(Colour disease){
        int outbreakCount = 0;
        for(City city:nextTo){
            outbreakCount+=city.infectCity(disease);
        }
        return outbreakCount+1;
    }

    public void treatDisease(Colour treat, boolean isCured){
        if(isCured){
            this.diseaseCounts[colourToIndex(treat)]=0;
        }else{
            this.diseaseCounts[colourToIndex(treat)]--;
        }
        for(int i=0; i<diseaseCounts.length; i++){
            if(diseaseCounts[i]<0){
                diseaseCounts[i]=0;
            }
        }
    }
}