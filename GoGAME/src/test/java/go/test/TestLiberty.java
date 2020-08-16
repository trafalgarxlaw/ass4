package go.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import go.game.model.Board;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;

public class TestLiberty {
	
	// Test for creation Board
	@Test
	public void testBoardCreation() {
		Board board = new Board(new Dimensions(9,9));
		assertEquals(81,board.getBoardTable().size());
	}
	// Test for find an intersection from Board
	@Test
	public void  testGettIntersectionFromBoard() {
		
		Board board = new Board(new Dimensions(9, 9));
		
		// We take an intersection1
		board.updateIntersectionStatus(new Intersection(2, 3), IntersectionStatus.PLAYER1);
		assertEquals(2, board.getIntersectionByXY(2,3).getX());
		assertEquals(3, board.getIntersectionByXY(2, 3).getY());
		assertEquals(IntersectionStatus.PLAYER1, board.getIntersectionByXY(2, 3).getStatus());
	}
	///////Tests for One Taken
	
	@Test
	public void  testTokensFreeAroundOneToken() {
		
		Board board = new Board(new Dimensions(9, 9));
		// We take an intersection1
	
		Intersection corner = new Intersection(0, 8);
		corner.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 8), IntersectionStatus.PLAYER1);

		assertEquals(2, board.numberOfLiberties(corner));
		
	}
	
	@Test
	public void  testTokensFreeAroundOneToken2() {
		
		Board board = new Board(new Dimensions(9, 9));
		// We take an intersection1
	
		Intersection corner = new Intersection(2, 3);
		corner.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(2, 3), IntersectionStatus.PLAYER1);


		assertEquals(4, board.numberOfLiberties(corner));
		
	}

	
	@Test 
	public void testNumberOfLibertiesForCorners() {
		
		// We need a board
		Board board = new Board(new Dimensions(9, 9));
	
		//we need a intersection in corner with statut black
		Intersection corner = new Intersection(0, 0);
		corner.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(0, 8), IntersectionStatus.PLAYER2);
		
	
		Intersection uprightCorner = new Intersection(8, 8);
		uprightCorner.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(8, 8), IntersectionStatus.PLAYER2);
	

		assertEquals(2, board.numberOfLiberties(corner));
		assertEquals(2, board.numberOfLiberties(uprightCorner));	
	}
	
	
	@Test 
	public void testNumberOfLibertiesForBorder() {
		// We need a board
		Board board = new Board(new Dimensions(9, 9));

		// We take an intersection
		Intersection testIntersection1 = new Intersection(8, 3);
		testIntersection1.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(8, 3), IntersectionStatus.PLAYER2);
		
		Intersection testIntersection4 = new Intersection(8, 4);
		testIntersection4.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(8, 4), IntersectionStatus.PLAYER1);

		assertEquals(2, board.numberOfLiberties(testIntersection1));
		assertEquals(2, board.numberOfLiberties(testIntersection4));

	}
	
	
	@Test 
	public void testNumberOfLibertiesWithOneTokenPlacedCenter() {
		Board board = new Board(new Dimensions(9, 9));
		
		Intersection testIntersection1 = new Intersection(8, 3);
		testIntersection1.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 1), IntersectionStatus.PLAYER2);
		
		assertEquals(3, board.numberOfLiberties(testIntersection1));			
	}
	@Test
	public void testNumberOfLibertiesWithAchainOfToken() {
		Board board = new Board(new Dimensions(9, 9));
		
		Intersection corner = new Intersection(0, 8);
		corner.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 8), IntersectionStatus.PLAYER1);

		Intersection upOfcorner = new Intersection(0, 7);
		upOfcorner.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 7), IntersectionStatus.PLAYER1);
	
		
		assertEquals(3, board.numberOfLiberties(corner));
			
	}
	@Test
	public void  tokensSameColorAroundTokenOnCorner() {
		
		Board board = new Board(new Dimensions(9, 9));
		// We take an intersection1
	
		Intersection corner = new Intersection(0, 8);
		corner.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 8), IntersectionStatus.PLAYER1);

		Intersection upOfcorner = new Intersection(0, 7);
		upOfcorner.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 7), IntersectionStatus.PLAYER1);
		
		assertEquals(3, board.numberOfLiberties(corner));	
	}
	
	/////// Test for Chain of Token  /////////
	
	@Test
	public void  testTokensFreeAroundTokenOnCenter() {
		
		Board board = new Board(new Dimensions(9, 9));
		// We take an intersection1
		

		Intersection tokenAtester = new Intersection(2, 2);
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(2, 2), IntersectionStatus.PLAYER1);
	
		Intersection token1 = new Intersection(1, 3);
		token1.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(1, 3), IntersectionStatus.PLAYER1);

		Intersection token2 = new Intersection(1, 2);
		token2.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(1, 2), IntersectionStatus.PLAYER1);
		

		Intersection token3 = new Intersection(1, 1);
		token2.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(1, 1), IntersectionStatus.PLAYER1);
		
		//System.out.println(board.tokensSameColorAroundToken(tokenAtester));
		
		assertEquals(8, board.numberOfLiberties(tokenAtester));	
	}
	@Test
	public void  testTokensFreeAroundTokenOnCenter2() {
		
		Board board = new Board(new Dimensions(9, 9));
		// We take an intersection1
		

		Intersection tokenAtester = new Intersection(2, 2);
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(2, 2), IntersectionStatus.PLAYER1);
	
		Intersection token1 = new Intersection(1, 3);
		token1.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(1, 3), IntersectionStatus.PLAYER1);

		Intersection token2 = new Intersection(1, 2);
		token2.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(1, 2), IntersectionStatus.PLAYER1);
		

		Intersection token3 = new Intersection(1, 1);
		token3.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(1, 1), IntersectionStatus.PLAYER1);
		

		Intersection token4 = new Intersection(2, 1);
		token4.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(2, 1), IntersectionStatus.PLAYER1);
		
		assertEquals(9, board.numberOfLiberties(tokenAtester));	
	}
	@Test
	public void  testTokensFreeAroundTokenOnBoard() {
		
		Board board = new Board(new Dimensions(9, 9));
		// We take an intersection1
		

		Intersection tokenAtester = new Intersection(0, 5);
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 5), IntersectionStatus.PLAYER1);
	
		Intersection token1 = new Intersection(0, 4);
		token1.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 4), IntersectionStatus.PLAYER1);

		Intersection token2 = new Intersection(0, 3);
		token2.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 3), IntersectionStatus.PLAYER1);
		

		Intersection token3 = new Intersection(0, 2);
		token3.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(0, 2), IntersectionStatus.PLAYER1);
		
		
		assertEquals(6, board.numberOfLiberties(tokenAtester));	
	}
	
}

