package GamePackage;

public class PlayerDeck extends Deck {
    public PlayerDeck(Game game, CardType type, int difficulty) {
        super(game, type);
        if(type==CardType.PLAYERCARD){
            //deck.addAll(); //TODO: Add event cards here. event card list built elsewhere?
            for(int i=0; i<difficulty; i++){
                this.getDeck().add(new Card("Epidemic", CardType.PLAYERCARD, Colour.GREEN)); //TODO: change to method?
            }
        }
    }

    public int cardsRemaining(){
        return this.getDeck().size();
    }
}
