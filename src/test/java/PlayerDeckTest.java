import GamePackage.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Player Deck Test Class")
public class PlayerDeckTest {
    private PlayerDeck PlayerDeck;
    private Game game;

    @BeforeAll
    static void setUp() {
        game = new Game();
        PlayerDeck =  new PlayerDeck(game,CardType.PLAYERCARD,4);

    }

    @Test
    void testDeckLength() {
        assertEquals(PlayerDeck.cardsRemaining(),57);
    }





}