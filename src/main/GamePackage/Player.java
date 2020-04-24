package GamePackage;

/*
Each player is given 4 actions when their turn comes around. (Can do less if they desire)
Kinds of actions:
    -Move(Drive/Direct Flight/Charter Flight/Shuttle Flight
    -Treat Disease
    -Share knowledge
    -Discover Cure
    -Share knowledge
 */

abstract class Player {
    private int actions; //Number of actions the player has

    public Player(){

    }

    public abstract void Drive();

    public abstract void directFlight();

    public abstract void charterFlight();

    public abstract void shuttleFlight();

    public abstract void treat();

    public abstract void shareKnowledge();

    public abstract void discoverCure();

}
