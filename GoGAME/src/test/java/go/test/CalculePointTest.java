package go.test;

import static org.junit.Assert.assertEquals;




import go.game.controller.GameControler;
import go.game.model.Color;
import go.game.model.Player;
import go.game.rule.Capture;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import go.game.controller.GameControler;
import go.game.model.Board;
import go.game.model.Color;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;
import go.game.model.Player;



// Test pour les nouveaux fonctionnalites 

public class CalculePointTest {
	private Board board;
	private Player player1;
	private Player player2;

	@Test 
	public void testCompteDesPointsTerritory () {
		// Arrange
		board = new Board(new Dimensions(5, 5));
		player1= new Player( 1,Color.BLACK,0);
		player2= new Player( 2,Color.WHITE,0);

		Intersection intersection1 = new Intersection(4, 2);
		intersection1.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(4, 2), IntersectionStatus.PLAYER2);

		Intersection intersection2 = new Intersection(3, 2);
		intersection2.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3,2), IntersectionStatus.PLAYER2);

		Intersection intersection3 = new Intersection(2, 3);
		intersection3.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(2, 3), IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(2, 4), IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 4), IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(3,3), IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(4, 3), IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(2,2), IntersectionStatus.FREE);
		List <Intersection> allFreeTokens = board.allTokensFreeAroundAToken(new Intersection(2,2));

		// Assert
		assertEquals(17,board.calculatePoints (player2));
		assertEquals(1, board.calculatePoints (player1));

	}
}
	
	