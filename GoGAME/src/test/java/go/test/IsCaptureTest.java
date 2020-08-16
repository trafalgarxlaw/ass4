package go.test;

import org.junit.jupiter.api.Test;

import go.game.model.Board;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;
import go.game.rule.IsCapture;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class IsCaptureTest {
    private Board board;

    @Test
    void isPlayableIsCaptured() {
        // Arrange
        board = new Board(new Dimensions(9, 9));
        IsCapture iscapture = new IsCapture();

        Intersection tokenAtester = new Intersection(2, 0);
        tokenAtester.setStatus(IntersectionStatus.FREE);
        board.updateIntersectionStatus(new Intersection(2, 0), IntersectionStatus.FREE);

        tokenAtester.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(3, 1), IntersectionStatus.PLAYER2);


        Intersection token0 = new Intersection(1, 1);
        token0.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(1, 1), IntersectionStatus.PLAYER2);

        Intersection token1 = new Intersection(2, 2);
        token1.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(2, 2), IntersectionStatus.PLAYER2);

        Intersection tokenForCapture = new Intersection(2, 1);
        tokenForCapture.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(new Intersection(2, 1), IntersectionStatus.PLAYER1);

        // Act
        boolean isCaptured = iscapture.isPlayable(board,tokenAtester);
        // Assert
        assertTrue(isCaptured);

    }
    @Test
    void isPlayableNotCaptured() {
        // Arrange
        board = new Board(new Dimensions(9, 9));
        go.game.rule.IsCapture iscapture = new go.game.rule.IsCapture();

        Intersection tokenAtester = new Intersection(2, 0);
        tokenAtester.setStatus(IntersectionStatus.FREE);
        board.updateIntersectionStatus(new Intersection(2, 0), IntersectionStatus.FREE);

        tokenAtester.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(3, 1), IntersectionStatus.PLAYER2);


        Intersection token1 = new Intersection(2, 2);
        token1.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(2, 2), IntersectionStatus.PLAYER2);

        Intersection tokenForCapture = new Intersection(2, 1);
        tokenForCapture.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(new Intersection(2, 1), IntersectionStatus.PLAYER1);

        // Act
        boolean isCaptured = iscapture.isPlayable(board,tokenAtester);
        // Assert
        assertFalse(isCaptured);

    }
}