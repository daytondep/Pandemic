package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

//TODO: split into child classes. infection and player. better practice.
public abstract class Deck {
    private Game game;
    private ArrayList<Card> deck = new ArrayList<>(); //bottom of deck is 0, end of arraylist is top of deck! //TODO: review this?
    private ArrayList<Card> discard = new ArrayList<>(); //bottom of discard is 0, end of arraylist is top of discard! //TODO: review this?
    private ArrayList<String> cityList; //String list of city names //TODO:refactor into game object. remove here?
    private final CardType deckType;

    public Deck(Game game, CardType type){
        this.game = game;
        this.cityList = game.getCityList();
        this.deckType = type;

        for(String city: cityList){
            Card cityCard = new Card(city, type, game.colourAssign(cityList.indexOf(city)));
            deck.add(cityCard);
        }
        Collections.shuffle(deck);
    }

    public CardType getDeckType() {
        return this.deckType;
    }


    protected ArrayList<Card> getDeck(){ return this.deck; }


    protected ArrayList<Card> getDiscard(){ return this.discard; }

    public Card drawCard(){
        if(!this.deck.isEmpty()) {
            return deck.remove(deck.size() - 1);
        }else{
            return null;
            //TODO: exception handling
        }
    }

    public void discardCard(Card discarded){ this.discard.add(discarded); }

    public Card getDiscardedCard(String cardName){
        for(Card c: this.getDiscard()){
            if(c.getName().equals(cardName)){
                this.getDiscard().remove(c);
                return c;
            }
        }
        return new Card("Bad Card", this.deckType, Colour.GOLD);
        //TODO: exception handling. no card found.
    }

}
