package Player;
import GamePackage.Card;

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

abstract class Player {
    private int actions; //Number of actions the player has
    private String location;
    private ArrayList<Card> heldCards;

    public Player(){
        this.location = "Atlanta";
        //held cards will be updated from Game, and held in the player object
        this.heldCards = new ArrayList<Card>();
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
    }

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
                return;
            }else{ //if the user chooses a card to discard from their held deck, discard it and add new
                discardFromHand(discarded);
                heldCards.add(card);
            }
        }else{
            heldCards.add(card);
        }


    }


}
