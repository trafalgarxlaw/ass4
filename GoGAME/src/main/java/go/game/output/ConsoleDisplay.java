package go.game.output;
import java.util.ArrayList;
import java.util.List;

import go.game.model.Board;
import go.game.model.IntersectionStatus;

public class ConsoleDisplay implements Displayable {


	public void display( Board boardTable,int boardSize) {
		List <Character> alphabets = new ArrayList<>(List.of('A','B','C','D', 'E','F','G','H','J', 'K','L','M','N','O','P','Q','R'));
		String firstLigne = " ";
	

		if ( boardSize > 9) {
			firstLigne = "  "; 
		}

		/// Display First ligne
		for (int i = 0; i < boardSize; i++) {			
			firstLigne = firstLigne + " " + alphabets.get(i);
		}

		System.out.println(firstLigne);


		for (int k= boardSize; k > 0; --k ) {
			System.out.print(k + " ");
			for (int i=0; i < boardSize; i++ ) {

				String symbol = displaySymbol(boardTable.getIntersectionByXY(i,k -1 ).getStatus());
				System.out.print(symbol);

			}

			System.out.print("\n");
		}

	}

	public String displaySymbol(IntersectionStatus status) {
		String  free  = ". ";
		String  player1 = "o ";
		String  player2  = "x ";

		if (status ==IntersectionStatus.PLAYER1)  {
			return player1;
		}

		if (status ==IntersectionStatus.PLAYER2)  {
			return player2;
		}

		return free;

	}

}

