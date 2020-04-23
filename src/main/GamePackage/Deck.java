package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

//TODO: split into child classes. infection and player. better practice.
public class Deck {
    private ArrayList<Card> deck = new ArrayList<>(); //bottom of deck is 0, end of arraylist is top of deck! //TODO: review this?
    private ArrayList<Card> discard = new ArrayList<>(); //bottom of discard is 0, end of arraylist is top of discard! //TODO: review this?
    private ArrayList<String> cityList; //String list of city names //TODO:refactor into game object. remove here?
    private final CardType deckType;

    public Deck(Board game, CardType type){ //TODO: add difficulty to passed variables?
        this.cityList = game.getCityList();
        this.deckType = type;

        for(String city: cityList){
            Card cityCard = new Card(city, type, game.colourAssign(cityList.indexOf(city)));
            deck.add(cityCard);
        }
        if(type==CardType.PLAYERCARD){
            //deck.addAll(); //TODO: Add event cards here. event card list built elsewhere?
            for(int i=0; i<game.getDifficulty(); i++){
                deck.add(new Card("Epidemic", CardType.PLAYERCARD, Colour.GREEN));
            }
        }
        Collections.shuffle(deck);
    }

    public CardType getDeckType() {
        return this.deckType;
    }

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
    }

    private void intensify(){
        this.deck.addAll(this.discard);
    }

    public String drawCard(){
        Card drawnCard = deck.get(deck.size()-1); // TODO: better way to get last indexed card?
        String drawnCityName = drawnCard.getName();
        this.discard.add(drawnCard);
        return drawnCityName;
    }
}
