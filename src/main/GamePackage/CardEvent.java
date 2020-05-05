package GamePackage;

public class CardEvent extends Card {

    private int eventIndex; //TODO: review having this field

    public CardEvent(String name, CardType type, Colour colour, int index) {
        super(name, type, colour);
        this.eventIndex = index;
    }
}
