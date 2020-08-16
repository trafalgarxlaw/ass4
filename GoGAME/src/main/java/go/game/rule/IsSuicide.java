package go.game.rule;

import go.game.model.Board;
import go.game.model.Intersection;

public class IsSuicide implements Playable{

	@Override
	public boolean isPlayable(Board board, Intersection intersection) {
	
		return board.numberOfLiberties(intersection) == 0;
	}
}
