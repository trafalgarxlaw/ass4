package go.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import go.game.model.Board;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;
import go.game.rule.Capture;
import go.game.rule.IsCapture;
import go.game.rule.IsKO;
import go.game.rule.IsSuicide;



public class TestRules {


	//// Tests for suicide 
	@Test
	public void  testSucideRule1() {

		Board board = new Board(new Dimensions(9, 9));
		IsSuicide suicide = new IsSuicide ();


		Intersection tokenAtester = new Intersection(4, 0);
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(4, 0), IntersectionStatus.PLAYER1);


		Intersection token0 = new Intersection(3, 0);
		token0.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 0), IntersectionStatus.PLAYER2);

		Intersection token1 = new Intersection(3, 1);
		token1.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 1), IntersectionStatus.PLAYER2);

		Intersection token2 = new Intersection(4, 1);
		token2.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(4, 1), IntersectionStatus.PLAYER2);


		Intersection token3 = new Intersection(5, 1);
		token3.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 1), IntersectionStatus.PLAYER2);

		Intersection token4 = new Intersection(5, 0);
		token4.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 0), IntersectionStatus.PLAYER2);


		assertEquals(0, board.numberOfLiberties(tokenAtester));	
		assertTrue(suicide.isPlayable(board,tokenAtester));
	}

	@Test
	public void  testSucideRule2() {

		Board board = new Board(new Dimensions(9, 9));
		IsSuicide suicide = new IsSuicide ();

		Intersection tokenAtester = new Intersection(4, 0);
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(4, 0), IntersectionStatus.PLAYER1);

		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(4, 1), IntersectionStatus.PLAYER1);


		Intersection token0 = new Intersection(3, 0);
		token0.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 0), IntersectionStatus.PLAYER2);

		Intersection token1 = new Intersection(3, 1);
		token1.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 1), IntersectionStatus.PLAYER2);

		Intersection token2 = new Intersection(3, 2);
		token2.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 2), IntersectionStatus.PLAYER2);


		Intersection token3 = new Intersection(4, 2);
		token3.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(4, 2), IntersectionStatus.PLAYER2);

		Intersection token4 = new Intersection(5, 2);
		token4.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 2), IntersectionStatus.PLAYER2);

		Intersection token5 = new Intersection(5, 1);
		token5.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 1), IntersectionStatus.PLAYER2);

		Intersection token6 = new Intersection(5, 0);
		token6.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 0), IntersectionStatus.PLAYER2);


		assertEquals(0, board.numberOfLiberties(tokenAtester));	
		assertTrue(suicide.isPlayable(board,tokenAtester));
	}
	
	@Test
	public void  testSucideRule3() {

		Board board = new Board(new Dimensions(9, 9));
		IsSuicide suicide = new IsSuicide ();

		Intersection tokenAtester = new Intersection(4, 0);
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(4, 0), IntersectionStatus.PLAYER1);

	
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(4, 1), IntersectionStatus.PLAYER1);


		Intersection token0 = new Intersection(3, 0);
		token0.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 0), IntersectionStatus.PLAYER2);

		Intersection token1 = new Intersection(3, 1);
		token1.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 1), IntersectionStatus.PLAYER2);

		Intersection token2 = new Intersection(3, 2);
		token2.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(3, 2), IntersectionStatus.PLAYER2);


		Intersection token3 = new Intersection(4, 2);
		token3.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(4, 2), IntersectionStatus.PLAYER2);

		Intersection token4 = new Intersection(5, 2);
		token4.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 2), IntersectionStatus.PLAYER2);

		Intersection token5 = new Intersection(5, 1);
		token5.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 1), IntersectionStatus.PLAYER2);

		Intersection token6 = new Intersection(5, 0);
		token6.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(5, 0), IntersectionStatus.PLAYER2);


		assertEquals(0, board.numberOfLiberties(tokenAtester));	
		assertTrue(suicide.isPlayable(board,tokenAtester));
	}


	//// Tests for class IsCapture
	@Test
	public void  testCaptureRuleIsCapture() {

		Board board = new Board(new Dimensions(9, 9));
		IsCapture  iscapture = new IsCapture ();

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

		assertTrue(iscapture.isPlayable(board,tokenAtester));

	}


	//// Tests for class Capture
	@Test
	public void  testCaptureRuleCapture() {


		Board board = new Board(new Dimensions(9, 9));
		Capture capture = new Capture ();




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

	

		for (Intersection i : board.getBoardTable()) {

			if( i.getX() == 2 && i.getY() == 1 ) {
				System.out.println(" Before Apply Capture " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");
			}

			if( i.getX() == 2 && i.getY() == 0 ) {
				System.out.println(" Before Apply Capture " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");

			}	  	
		}


		board = capture.executeAction(board,tokenAtester);


		for (Intersection i : board.getBoardTable()) {

			if( i.getX() == 2 && i.getY() == 1 ) {
				System.out.println(" After Apply Capture  " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");

			}	  

			if( i.getX() == 2 && i.getY() == 0 ) {
				System.out.println(" After Apply Capture  " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");

			}	
		}


	}
/*
	@Test
	public void  testKORule2() {
		Board board = new Board(new Dimensions(9, 9));
		Capture capture = new Capture ();
		IsKO ko = new IsKO();


		Intersection tokenAtester = new Intersection(6,8);
		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(6,8), IntersectionStatus.PLAYER1);
		board.addHistoryBoard(board);

		tokenAtester.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(5,7), IntersectionStatus.PLAYER1);
		board.addHistoryBoard(board);



		Intersection token0 = new Intersection(6, 6);
		token0.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(6,6), IntersectionStatus.PLAYER1);
		board.addHistoryBoard(board);


		Intersection token1 = new Intersection(7, 7);
		token1.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(7,7), IntersectionStatus.PLAYER1);
		board.addHistoryBoard(board);


		Intersection tokenForCapture = new Intersection(7,8);
		tokenForCapture.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(7,8), IntersectionStatus.PLAYER2);
		board.addHistoryBoard(board);


		Intersection tokenForCapture1 = new Intersection(8,7);
		tokenForCapture1.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(8, 7), IntersectionStatus.PLAYER2);
		board.addHistoryBoard(board);


		Intersection tokenForCapture2 = new Intersection(7,6);
		tokenForCapture2.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(7, 6), IntersectionStatus.PLAYER2);
		board.addHistoryBoard(board);


		Intersection tokenForCapture3 = new Intersection(6,7);
		tokenForCapture3.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(6, 7), IntersectionStatus.PLAYER2);
		board.addHistoryBoard(board);


		board = capture.executeAction(board,tokenForCapture3);
		board.addHistoryBoard(board);


		assertTrue(ko.isPlayable(board,token1));


	}

 */

	// Test 2 of teacher 
	@Test
	public void  testCaptureRuleCaptureProf() {


		Board board = new Board(new Dimensions(9, 9));
		Capture capture = new Capture ();



		Intersection tokenAtester1 = new Intersection(6, 6);
		tokenAtester1.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(6, 6), IntersectionStatus.PLAYER1);

		Intersection tokenAtester2 = new Intersection(7, 6);
		tokenAtester2.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(7, 6), IntersectionStatus.PLAYER2);

		Intersection tokenAtester3 = new Intersection(8, 6);
		tokenAtester3.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(8, 6), IntersectionStatus.PLAYER1);

		Intersection tokenAtester4 = new Intersection(7, 5);
		tokenAtester4.setStatus(IntersectionStatus.PLAYER2);
		board.updateIntersectionStatus(new Intersection(7, 5), IntersectionStatus.PLAYER2);

		Intersection tokenAtester5 = new Intersection(7, 7);
		tokenAtester5.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(7, 7), IntersectionStatus.PLAYER1);

		Intersection tokenAtester6 = new Intersection(6, 5);
		tokenAtester6.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(6, 5), IntersectionStatus.PLAYER1);

		Intersection tokenAtester7 = new Intersection(8, 5);
		tokenAtester7.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(8, 5), IntersectionStatus.PLAYER1);

		Intersection tokenAtester8 = new Intersection(7, 4);
		tokenAtester8.setStatus(IntersectionStatus.PLAYER1);
		board.updateIntersectionStatus(new Intersection(7, 4), IntersectionStatus.PLAYER1);

		for (Intersection i : board.getBoardTable()) {

	
				System.out.println(" Before Apply Capture " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");
	

				System.out.println(" Before Apply Capture " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");


		}


		board = capture.executeAction(board,tokenAtester8);


		for (Intersection i : board.getBoardTable()) {

				System.out.println(" After Apply Capture  " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");

				System.out.println(" After Apply Capture  " + i.getX() + " " + i.getY() + " " + i.getStatus()+ " ");

		}
	}
	
	@Test 
	public void testIsBoardFull () {
		
		Board board = new Board(new Dimensions(9, 9));

	    for (Intersection i : 	board.getBoardTable()) {
	    	IntersectionStatus status = i.getStatus();
	    	status = IntersectionStatus.PLAYER1;
	    }
     
	    assertTrue(board.isBoardFull());
	
		
	}
}
