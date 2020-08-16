package go.game;

import go.game.controller.GameControler;
import go.game.model.Color;
import go.game.model.Player;
import go.game.model.PlayerBuilder;


import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
	
		Player player1 = new PlayerBuilder()
							.setId(1)
							.setColor(Color.BLACK)
							.setCapturedStones(0)
							.build();
				
		Player player2 = new PlayerBuilder()
				.setId(2)
				.setColor(Color.WHITE)
				.setCapturedStones(0)
				.build();

	
		GameControler controler = new GameControler(player1,player2);
		controler.InitialiseGameControler(); //--> a l'initialisation, l'etat par defaut est automatic
		controler.initialiseDisplayer();

		//Le patron State (ou État) permet d'encapsuler les différents états d'un objet dans
		// d'autres objets et de les interchanger sans avoir à modifier les autres propriété
		// de l'objet. En ce sens, ce patron peut rappeler le patron Stratégie dans son
		// fonctionnement.

		controler.GameState().handle(); //---> etat initial
		controler.displayBoard();
	

		

		if (controler.continueGame()){ //--> une fois que toutes les actions ont ete lue et placé

			// creation of the interactive state once the file actions are completed

			controler.initialiseInteractiveState(); //----> on switch a l'etat interactif
			controler.GameState().handle();  //---->  handle en tant qu'etat interactif

		} else {
			
			controler.displayPlayersScore();
		}

	}


}

