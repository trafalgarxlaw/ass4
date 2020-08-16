package go.test;

import go.game.model.Color;
import org.junit.Before;
import org.junit.Test;
import go.game.model.Player;
import go.game.model.Board;
import go.game.model.Dimensions;

import static org.junit.Assert.*;


public class TestPlayer {
    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player(1, Color.BLACK, 0);
    }

    @Test
    public void testAddCaptureStone() {
        // Arrange
        player.addCapturedStones(5);
        player.addCapturedStones(5);

        // Act
        int result = player.getCapturedStones();
        // Assert
        assertEquals(10,result);
    }
    @Test
    public void testRemoveCaptureStone(){
        // Arrange
        player.addCapturedStones(10);
        player.removeCapturedStones(3);
        player.removeCapturedStones(3);

        // Act
        int result = player.getCapturedStones();
        // Assert
        assertEquals(4,result);
    }


}
