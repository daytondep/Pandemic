package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

public class InfectionDeck extends Deck {
    public InfectionDeck(Game game, CardType type) {
        super(game, type);
    }

    public void intensify(){
        if(!this.getDiscard().isEmpty()) {
            Collections.shuffle(this.getDiscard());
            this.getDeck().addAll(this.getDiscard());
        }
    }

    public Card drawLast(){
        return this.getDeck().remove(0);
    }
}
