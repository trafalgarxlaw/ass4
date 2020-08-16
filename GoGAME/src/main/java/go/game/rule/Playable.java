package go.game.rule;

import go.game.model.Board;
import go.game.model.Intersection;

public interface Playable {
	public boolean isPlayable (Board board, Intersection intersection);

}
