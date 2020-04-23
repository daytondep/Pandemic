package GamePackage;

import java.util.ArrayList;

public class City {
    private final String cityName;
    private final Colour cityColour; //inherent disease
    private ArrayList<City> nextTo = new ArrayList<>(); //Links to every city that is touching this city.
    private boolean canInfect = true; //can be blocked by quarantine specialist
    //TODO: review. change to array?
    private int blue = 0;
    private int red = 0;
    private int yellow = 0;
    private int black = 0;

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

    public int infectCity(Colour disease){
        int outbreakCount = 0; //how many epidemics were recursively caused here.
        if (this.canInfect){
            switch(disease){
                case BLUE:
                    if(this.blue==3){
                        outbreakCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.blue++;
                    }
                    break;
                case RED:
                    if(this.red==3){
                        outbreakCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.red++;
                    }
                    break;
                case YELLOW:
                    if(this.yellow==3){
                        outbreakCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.yellow++;
                    }
                    break;
                case BLACK:
                    if(this.black==3){
                        outbreakCount+=outBreak(disease); //TODO: fix passing problem
                    }else{
                        this.black++;
                    }
                    break;
                default:
                    //TODO: error handling
                    break;
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
            curedTreat(treat);
        }else{
            regTreat(treat);
        }
        //TODO: check if any colour is below 0;
    }

    private void curedTreat(Colour treat){
        switch (treat){
            case BLUE:
                this.blue=0;
            case RED:
                this.red=0;
            case YELLOW:
                this.yellow=0;
            case BLACK:
                this.black=0;
            default:
                //TODO: error handling
        }
    }

    private void regTreat(Colour treat){
        switch (treat){
            case BLUE:
                    this.blue--;
            case RED:
                    this.red--;
            case YELLOW:
                    this.yellow--;
            case BLACK:
                    this.black--;
            default:
                //TODO: error handling
        }
    }
}