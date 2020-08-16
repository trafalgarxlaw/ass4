package go.game.rule;

import go.game.model.Board;
import go.game.model.Intersection;

public class CanPlay implements Playable {

	@Override
	public boolean isPlayable(Board board, Intersection intersection) {

		boolean goPlay = false;

		if (new IsCapture().isPlayable(board, intersection)) {
		
			if (new IsKO().isPlayable(board, intersection)) {
			
				goPlay = false;
			} else {
				
				goPlay = true;

			}

		} else 	{
		
			if (new IsSuicide().isPlayable(board, intersection)) {
		
				goPlay = false;

			} else {
				goPlay = true;
			}
		}
		return goPlay;

	}
}


