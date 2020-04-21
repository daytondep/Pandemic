package GameFiles;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();
    private String[] cityList; //String list of city names //TODO:refactor into game object. remove here?
    private final CardType deckType;

    public Deck(Board game, CardType type){
        this.cityList = game.getCityList();
        this.deckType = type;
        for(int i=0; i<cityList.length; i++){
            Card cityCard = new Card(cityList[i], type, game.colourAssign(i));
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

}