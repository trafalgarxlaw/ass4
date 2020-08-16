package go.test;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import go.game.model.Board;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;
import go.game.rule.Capture;
import go.game.rule.IsKO;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class IsKOTest {

    private static Board board;
    private static Capture capture;
    private static IsKO isko;



    /// Test pour les nouveaux fonctionalites 
    
    @Test
    void isPlayableIsKO() {
        // Arrange
        board = new Board(new Dimensions(9, 9));
        capture = new Capture ();
        isko = new IsKO();

        Intersection tokenAtester = new Intersection(6,6);
        tokenAtester.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(tokenAtester,IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        Intersection tokenAtester1 = new Intersection(5,2);
        tokenAtester1.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(tokenAtester1, IntersectionStatus.PLAYER2);
        board.addHistoryBoard(board);

        Intersection token0 = new Intersection(5, 3);
        token0.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(token0, IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        Intersection token1 = new Intersection(7, 6);
        token1.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(7,6), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        Intersection tokenForCapture = new Intersection(7,5);
        tokenForCapture.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(new Intersection(7,5), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        Intersection tokenForCapture1 = new Intersection(8,5);
        tokenForCapture1.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(8, 5), IntersectionStatus.PLAYER2);
        board.addHistoryBoard(board);

        Intersection tokenForCapture2 = new Intersection(6,4);
        tokenForCapture2.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(new Intersection(6, 4), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        Intersection tokenForCapture3 = new Intersection(7,4);
        tokenForCapture3.setStatus(IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new Intersection(7, 4), IntersectionStatus.PLAYER2);
        board.addHistoryBoard(board);

        Intersection tokenForCapture4 = new Intersection(5,5);
        tokenForCapture3.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(new Intersection(5, 5), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        board = capture.executeAction(board,tokenForCapture4);
        board.addHistoryBoard(board);

        // Act
        boolean Ko = isko.isPlayable(board, tokenForCapture4);
        // Assert
        assertTrue(Ko);
    }

    @Test
    void isPlayableNotKO() {
        // Arrange
        board = new Board(new Dimensions(9, 9));
        capture = new Capture ();
        isko = new IsKO();

        Intersection token1 = new Intersection(7, 7);
        token1.setStatus(IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(new Intersection(7,7), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);
        // Act
        boolean Ko = isko.isPlayable(board,token1);
        // Assert
        assertFalse(Ko);

    }
}