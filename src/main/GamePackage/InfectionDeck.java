package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

public class InfectionDeck extends Deck {
    public InfectionDeck(Game game, CardType type) {
        super(game, type);
    }

    private void intensify(){
        Collections.shuffle(this.getDiscard());
        this.getDeck().addAll(this.getDiscard());
    }

    public Card getCard(String city){
        for(Card c: this.getDiscard()){
            if(c.getName().equals(city)){
                this.getDiscard().remove(c);
                return c;
            }
        }
        return new Card("Bad Card", CardType.INFECTIONCARD, Colour.GREEN);
        //TODO: exception handling. no card found.
    }

    public Card drawLast(){
        return this.getDeck().remove(0);
    }
}
