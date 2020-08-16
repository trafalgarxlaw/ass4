package go.test;

import org.junit.jupiter.api.Test;
import  go.game.model.Board;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.rule.CanPlay;

import static org.junit.jupiter.api.Assertions.*;

class CanPlayTest {
    private Board board;
    private CanPlay canPlay;

    @Test
    void isPlayable() {
        // Arrange
        board = new Board(new Dimensions(9, 9));
        canPlay = new CanPlay();
        Intersection testIntersection = new Intersection(5, 5);
        // Act
        boolean playable = canPlay.isPlayable(board , testIntersection);
        // Assert
        assertTrue(playable);
    }
    @Test
    void isNotPlayableoutofBoard() {
        // Arrange
        board = new Board(new Dimensions(9, 9));
        canPlay = new CanPlay();
        Intersection testIntersection = new Intersection(19, 5);
        // Act
        boolean playable = canPlay.isPlayable(board , testIntersection);
        // Assert
        assertFalse(playable);
    }
    @Test
    void isNotPlayableoutofBoard2() {
        // Arrange
        board = new Board(new go.game.model.Dimensions(9, 9));
        canPlay = new CanPlay();
        Intersection testIntersection = new Intersection(6, 15);
        // Act
        boolean playable = canPlay.isPlayable(board , testIntersection);
        // Assert
        assertFalse(playable);
    }

}