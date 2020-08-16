package go.test;

import org.junit.jupiter.api.Test;

import go.game.model.Board;
import go.game.model.Dimensions;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;
import go.game.rule.IsSuicide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IsSuicideTest {
    private go.game.model.Board board;
    private go.game.rule.IsSuicide suicide;
    
    // Test pour les nouveaux fonctionnalite 

    @Test
    void isPlayable() {
        // Arrange

        board = new go.game.model.Board(new go.game.model.Dimensions(9, 9));
        suicide = new go.game.rule.IsSuicide();

        go.game.model.Intersection tokenAtester = new go.game.model.Intersection(4, 0);
        tokenAtester.setStatus(go.game.model.IntersectionStatus.PLAYER1);
        board.updateIntersectionStatus(new go.game.model.Intersection(4, 0),
				go.game.model.IntersectionStatus.PLAYER1);


        go.game.model.Intersection token0 = new go.game.model.Intersection(3, 0);
        token0.setStatus(go.game.model.IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new go.game.model.Intersection(3, 0),
				go.game.model.IntersectionStatus.PLAYER2);

        go.game.model.Intersection token1 = new go.game.model.Intersection(3, 1);
        token1.setStatus(go.game.model.IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new go.game.model.Intersection(3, 1),
				go.game.model.IntersectionStatus.PLAYER2);

        go.game.model.Intersection token2 = new go.game.model.Intersection(4, 1);
        token2.setStatus(go.game.model.IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new go.game.model.Intersection(4, 1),
				go.game.model.IntersectionStatus.PLAYER2);

        go.game.model.Intersection token3 = new go.game.model.Intersection(5, 1);
        token3.setStatus(go.game.model.IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new go.game.model.Intersection(5, 1),
				go.game.model.IntersectionStatus.PLAYER2);

        go.game.model.Intersection token4 = new go.game.model.Intersection(5, 0);
        token4.setStatus(go.game.model.IntersectionStatus.PLAYER2);
        board.updateIntersectionStatus(new go.game.model.Intersection(5, 0),
				go.game.model.IntersectionStatus.PLAYER2);
        // Act
        boolean isSuicide = suicide.isPlayable(board,tokenAtester);
        // Assert
        assertTrue(isSuicide);
    }
    
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

}
