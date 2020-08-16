
package go.game.rule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import go.game.model.Board;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;

public class Capture implements Play {


	private int points = 0;
	private boolean cap= false;


	@Override
	public Board executeAction(Board board, Intersection intersection) {

		int countPoint = 0;
		int numberLiberties = 0;
		IntersectionStatus currentPlayer = intersection.getStatus();
		List<Intersection> boardToVerify = board.getBoardTable();
		board.updateIntersectionStatus(intersection, currentPlayer);
		List<Intersection> tokensOpponent = (List)boardToVerify.stream().filter((ix) -> {
			return ix.getStatus() != currentPlayer;
		}).filter((ix) -> {
			return ix.getStatus() != IntersectionStatus.FREE;
		}).collect(Collectors.toList());
		List<Intersection> listIntersectionZeroLiberties = new ArrayList<>();
		Iterator var9 = tokensOpponent.iterator();

		Intersection i;
		while(var9.hasNext()) {
			i = (Intersection)var9.next();
			   numberLiberties = board.numberOfLiberties(i);
			if (numberLiberties == 0) {
				listIntersectionZeroLiberties.add(i);
				cap = true;
			}
		}

		for(var9 = listIntersectionZeroLiberties.iterator(); var9.hasNext(); ++countPoint) {
			i = (Intersection)var9.next();
			board.updateIntersectionStatus(i, IntersectionStatus.FREE);
		}

		this.points = countPoint;
		return board;

	}
	public boolean getcap(){
		return cap;
	}

	@Override
	public int getPoints() {
		return points;
	}


}


