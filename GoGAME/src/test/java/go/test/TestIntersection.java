package go.test;

import go.game.model.*;
import go.game.model.*;
import go.game.model.*;
import go.game.model.Board;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;

import  go.game.rule.Capture;
import go.game.rule.IsKO;
import go.game.rule.IsCapture;
import go.game.rule.IsSuicide;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class TestIntersection {
    private Board board;
    private Intersection intersection;
    private Player player2;
    private Action  action;
    private GameMock gameMock;

    @BeforeEach
    public void setUp() {
        board = new Board(new Dimensions(9,9));
        gameMock= new GameMock();
        player2 = new Player(2, go.game.model.Color.WHITE, 0);
    }
    @Test
    void coordinatesInsideBoard() {
        // Arrange
        intersection = new Intersection(2,3);
        // Act
        boolean inBoard = board.isInBoard(new Intersection(2,3));
        // Assert
        assertTrue(inBoard);
    }
    @Test
    void coordinatesOutsideBoard() {
        // Act
        boolean notInBoard = board.isInBoard(new Intersection(-1,3));
        boolean notInBoard1 = board.isInBoard(new Intersection(3,15));
        // Assert
        assertFalse(notInBoard);
        assertFalse(notInBoard1);
    }
    @Test
    void intersectionAlreadyUsed() {
        // Arrange
        action = new Action(0,4);
        board.updateIntersectionStatus(new Intersection(0,6), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        board.updateIntersectionStatus(new Intersection(0,5), IntersectionStatus.PLAYER2);
        board.addHistoryBoard(board);

        board.updateIntersectionStatus(new Intersection(0, 4), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);
        // Act
        boolean intersectionUsed = gameMock.setIntersection(board,action);
        // Assert
        assertTrue(intersectionUsed);
    }
    @Test
    void intersectionNotUsed() {
        // Arrange
        action = new Action(1,5);
        board.updateIntersectionStatus(new Intersection(0,6), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);

        board.updateIntersectionStatus(new Intersection(0,5), IntersectionStatus.PLAYER2);
        board.addHistoryBoard(board);

        board.updateIntersectionStatus(new Intersection(0, 4), IntersectionStatus.PLAYER1);
        board.addHistoryBoard(board);
        gameMock.setIntersection(board,action);
        // Act
        boolean intersectionUsed = gameMock.setIntersection(board,action);;
        // Assert
        assertFalse(intersectionUsed);
    }
    @Test
    void endOfGame() {
        List<Action> actionList = new ArrayList();
        actionList.add(new Action(1, 5));
        actionList.add(new Action(1, 6));
        actionList.add(new Action(1, 7));
        actionList.add(new Action(1, 8));
        actionList.add(new Action(true));
        actionList.add(new Action(true));
        Action currentAction = this.gameMock.getCurrentaction(actionList);
        Action previousaction = this.gameMock.getPreviousCurrentaction(actionList);
        this.gameMock.setConsecutivePass(currentAction, previousaction);
        // Act
        boolean isEndOfGame = this.gameMock.isConsecutivePass();
        // Assert
        Assert.assertTrue(isEndOfGame);
    }
    @Test
    void BoardFull () {
        // Arrange
        for (Intersection i : 	board.getBoardTable()) {
            IntersectionStatus status = i.getStatus();
            status = IntersectionStatus.PLAYER1;
        }
        // Act
        boolean isFull = board.isBoardFull();
        // Assert
        assertTrue(isFull);
    }
  

}
