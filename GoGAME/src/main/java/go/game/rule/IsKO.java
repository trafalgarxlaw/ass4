package go.game.rule;

import java.util.List;

import go.game.model.Board;
import go.game.model.Intersection;



public class IsKO implements Playable {
	
	

	public boolean isPlayable(Board board, Intersection intersection) {
		List <Board> listBoards = board.getHistoryBoard();
		


		if (listBoards.size() < 3) {
			return false;

		} else if(intersection != null) {
			Board thirdListOfBoard = listBoards.get(listBoards.size()-3);

			for ( Intersection i : thirdListOfBoard.getBoardTable()) {
				

				Intersection third = thirdListOfBoard.getIntersectionByXY(i.getX(), i.getY());

				if (third.getStatus() == intersection.getStatus() &&
					third.getY() == intersection.getY() &&
					third.getX() == intersection.getX()) {

					return true;
				}

			}
		}
		return false;

	}
}
	

	


	





