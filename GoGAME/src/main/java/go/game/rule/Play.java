package go.game.rule;

import go.game.model.Board;
import go.game.model.Intersection;

public interface Play {
	public Board executeAction(Board board, Intersection intersection);
	public int getPoints();

}
