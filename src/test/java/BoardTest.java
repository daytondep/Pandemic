import BoardPackage.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Boad Test Class")
public class BoardTest {
    private Board board;
    private Game game;
    private ArrayList<String> cityList;

    @BeforeAll
    static void setUp() {
        cityList = new ArrayList<String>(Arrays.asList("Atlanta",
                "Chicago",
                "Washington",
                "Madrid" ,
                "Paris" ,
                "Milan" ,
                "London",
                "Essen" ,
                "San Francisco" ,
                "St. Petersburg" ,
                "Montreal" ,
                "New York" ,
                "Seoul" ,
                "Shanghai" ,
                "Manila" ,
                "Osaka" ,
                "Bejing" ,
                "Taipei" ,
                "Hong Kong" ,
                "Sydney" ,
                "Ho Chi Minh city" ,
                "Jakarta" ,
                "Bangkok" ,
                "Tokyo" ,
                "Miami" ,
                "Lima" ,
                "Bogota" ,
                "Kinshasa" ,
                "Mexico City" ,
                "Johannesburg" ,
                "Santiago" ,
                "Khartoum" ,
                "Buenos Aires" ,
                "Los Angeles" ,
                "Lagos" ,
                "Sao Paulo" ,
                "Algiers" ,
                "Kolkata" ,
                "Cairo" ,
                "Delhi" ,
                "Moscow" ,
                "Mumbai" ,
                "Riyadh" ,
                "Baghdad" ,
                "Tehran" ,
                "Chennai" ,
                "Karachi" ,
                "Istanbul"); );
        board = new Board();
        game = new Game();
    }

    @Test
    void testCityList() {
        assertEquals(game.cityList,cityList);
    }

    @Test
    void testBoardCreation(){
        //assertEquals(board 2d array, text file)
    }


}