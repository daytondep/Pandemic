package GamePackage;

public class Card {
    private final String cityName;
    private final Colour cityColour;
    private final CardType cardType;

    public Card(String name, CardType type, Colour colour){
        this.cityName = name;
        this.cardType = type;
        this.cityColour = colour;
    }

    public String getName(){
        return this.cityName;
    }

    public CardType getType(){
        return this.cardType;
    }

    public Colour getColour(){
        return this.cityColour;
    }
}
