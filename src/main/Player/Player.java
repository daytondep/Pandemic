package Player;
import GamePackage.Card;
import GamePackage.Game;

import java.util.ArrayList;
/*
Each player is given 4 actions when their turn comes around. (Can do less if they desire)
Kinds of actions:
    -Move(Drive/Direct Flight/Charter Flight/Shuttle Flight
    -Treat Disease
    -Share knowledge
    -Discover Cure
    -Share knowledge
 */

public abstract class Player {

    private Game thisGame;

    private int actionCount; //Number of actions the player has
    private String location;
    private ArrayList<Card> heldCards;

    public Player(){
        this.location = "Atlanta"; //TODO: change to be cityList[0]
        //this.thisGame = game; //TODO: change to a setter method?
        //held cards will be updated from Game, and held in the player object
        this.heldCards = new ArrayList<>();
    }

    public Game getThisGame() {
        return thisGame;
    }

    public abstract void Drive();

    public abstract void directFlight();

    public abstract void charterFlight();

    public abstract void shuttleFlight();

    public abstract void treat();

    public abstract void shareKnowledge();

    public abstract void discoverCure();

    public abstract void buildResearchStation();

    //Look at the hand that the player holds
    private void seeHand(){
        for(Card c: heldCards){
            System.out.println(c);
        }

    }

    //Remove card from player's held card pile
    private void discardFromHand(Card card){
        heldCards.remove(card);
    } //TODO: review changing to string?

    /*
    This method will be used to allow a player to go through their deck of cards, and choose a card to discard
     */
    private Card chooseDiscard(){
        Card discarded = null;
        //TODO: Choose a card to discard
        return discarded;
    }
    private void addToHand(Card card){
        if(heldCards.size() == 7){
            Card discarded = null;
            //TODO: must choose a card to discard (User input) *from their held pile*
            discarded = chooseDiscard(); //**
            //if the user decides to keep their original deck, nothing happens
            if(discarded == null) {
                return; //TODO: compiler is yelling that this is redundant. just this way for readability?
            }else{ //if the user chooses a card to discard from their held deck, discard it and add new
                discardFromHand(discarded);
                heldCards.add(card);
            }
        }else{
            heldCards.add(card);
        }


    }


}
