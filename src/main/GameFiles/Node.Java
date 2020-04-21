import java.util.List;

public class Node{
    private String cityName;
    private Colour cityColour;
    private List<Node> nextTo;

    public Node(String name){
        this.cityName = name;
    }

    public void addNext(Node next){
        this.nextTo.add(next);
    }

    public void addColour(Colour colour){
        this.cityColour = colour;
    }
}