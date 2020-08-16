package go.game.rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import go.game.model.Board;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;

public class IsCapture implements Playable {

	public IsCapture() {
	}

	public boolean isPlayable(Board board, Intersection intersection) {
		int numberLiberties = 0;
		IntersectionStatus currentPlayer = intersection.getStatus();
		board.updateIntersectionStatus(intersection, intersection.getStatus());
		List<Intersection> tokensOpponent = (List)board.getBoardTable().stream().filter((ix) -> {
			return ix.getStatus() != currentPlayer;
		}).filter((ix) -> {
			return ix.getStatus() != IntersectionStatus.FREE;
		}).collect(Collectors.toList());
		List<Intersection> listIntersectionZeroLiberties = new ArrayList();

		Iterator var7 = tokensOpponent.iterator();

		while(var7.hasNext()) {
			Intersection i = (Intersection)var7.next();
			numberLiberties = board.numberOfLiberties(i);
			if (numberLiberties == 0) {
				listIntersectionZeroLiberties.add(i);
			}
		}

		board.updateIntersectionStatus(intersection, IntersectionStatus.FREE);
		return !listIntersectionZeroLiberties.isEmpty();
	}
	
	
}
