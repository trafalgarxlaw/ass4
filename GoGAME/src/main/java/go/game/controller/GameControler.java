package go.game.controller;


import javax.swing.JFileChooser;

import go.game.controller.GameInterface;
import go.game.Interface.InterfaceTerminal;
import go.game.input.FileInputReader;
import go.game.model.Action;
import go.game.model.ActionsCollection;
import go.game.model.Board;
import go.game.model.Color;
import go.game.output.ConsoleDisplay;
import go.game.rule.CanPlay;
import go.game.rule.IsKO;
import go.game.rule.IsSuicide;
import go.game.state.AutomaticGameState;
import go.game.state.InteractiveGameState;
import go.game.state.State;
import go.game.model.Dimensions;
import go.game.model.Player;
import go.game.controller.Turn;
import go.game.model.Intersection;
import go.game.model.IntersectionStatus;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameControler implements GameInterface {

	private ActionsCollection playersActions;
	private ConsoleDisplay displayer;
	private Turn gameTurn;
	private Board gameBoard;
	private Dimensions boardDimensions;
	private boolean isIntrsection;
	private boolean isConsecutivePass=false;
	private boolean endOfGameFinal = false; 
	private int playerGagnant;
	private go.game.model.Player player1;
	private Player player2;
	private State gameState;


	public GameControler(Player player1,Player player2){
		this.player1 = player1;
		this.player2 = player2;
		this.gameState = new AutomaticGameState(this);
		this.boardDimensions= new Dimensions(9,9);
	}

	public State GameState() {
		return gameState;
	}
	public void initialiseInteractiveState(){
		this.gameState=new InteractiveGameState(this);
	}
	private String loadPathFile(){
		String path="";
		String userDir = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(userDir +"/Desktop");
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			path=  chooser.getSelectedFile().getAbsolutePath();
		}
		return path;
	}
	public void InitialiseGameControler(){
		FileInputReader reader=  ReadInputFromFile(loadPathFile());
		this.playersActions=new ActionsCollection(reader.GenerateInputActions());
	}
	public FileInputReader ReadInputFromFile(String pathname){
		FileInputReader reader = new FileInputReader();
		reader.ReadInput(pathname);
		return reader;
	}
	public void startGame( ) throws IOException {
		gameBoard = new Board(boardDimensions);
		this.gameTurn= new Turn(player1,player2);
		this.gameTurn.setCurrentPlayerColor(Color.BLACK);
		this.gameTurn.setCurrentTurn(0);
		boolean endOfGame = false;

		while (gameTurn.getCurrentTurn() < playersActions.getHowManyActions() && !endOfGame){
			if (gameTurn.getCurrentTurn()!=0){
				endOfGame = isConsecutivePass(playersActions.getActionList().get(gameTurn.getCurrentTurn()),
						playersActions.getActionList().get(gameTurn.getCurrentTurn()-1));
				endOfGameFinal = endOfGame;
				if (!endOfGame)
					endOfGame = gameBoard.isBoardFull();
			}
			startRound(gameTurn.getCurrentPlayer(),gameBoard,
					playersActions.getActionList().get(gameTurn.getCurrentTurn()));
			nextRound(gameTurn);
		}
	}
	public boolean continueGame(){
		return (gameTurn.getCurrentTurn() == playersActions.getHowManyActions()) && !endOfGameFinal ;
	}
	public void activateInterface() throws IOException {
		InterfaceTerminal GameInterface = new InterfaceTerminal();
		IsKO isKO = new IsKO();
		boolean endOfGame = false;
		boolean firstPass = false;
		boolean secondPass = false;

		while (!endOfGame) {
			boolean isko = false;
			System.out.println("Player " + gameTurn.getCurrentPlayer().getId() + " Turn");
			GameInterface.printMessage();
			String input = GameInterface.readUserInput();
			Action userAction = GameInterface.createAction(input);
			if (userAction.isPass() || gameBoard.isInBoard(userAction.getActionIntersection())) {
				isIntrsection = isIntersectionUsed(gameBoard, userAction);
				if (userAction.isPass() || isIntrsection) {
					if (gameTurn.getCurrentPlayer().getId() == 1) {
						firstPass = userAction.isPass();
					} else if (gameTurn.getCurrentPlayer().getId() == 2) {
						secondPass = userAction.isPass();
					}
					if(firstPass == true && secondPass== true)
						break;
					isko = isKO.isPlayable(gameBoard, userAction.getActionIntersection());
					if(userAction.isPass() || !isko ) {
						startRound(gameTurn.getCurrentPlayer(), gameBoard, userAction);
						gameBoard.addHistoryBoard(gameBoard);
						nextRound(gameTurn);
						System.out.println("Displaying current board.");
						displayBoard();
					} else {
						GameInterface.isKOMSG();
					}
				} else {
					System.out.println("\n\u26A0 Warning !");
					System.out.println("The entred position is already used, please try again");
					System.out.println("------------------------------------------------------\n");
				}
				} else {
					GameInterface.outofBoardErrorMSG();
				}
			endOfGame = (firstPass && secondPass) || gameBoard.isBoardFull();
		}

		System.out.println("\n \u2663 \u2663 \u2663 GAME OVER \u2663 \u2663 \u2663");
		System.out.print("-----------------------\n");
		displayPlayersScore();
	}
	public void displayPlayersScore(){
		int totalPointPlayer1 = gameBoard.calculatePoints(player1);
		int totalPointPlayer2 = gameBoard.calculatePoints(player2);

		if (totalPointPlayer1 > totalPointPlayer2) {
			playerGagnant = player1.getId();
		} else {	
			playerGagnant = player2.getId();	
		}
		System.out.println("\n" );
		System.out.println("-----------Score----------" );
		System.out.println("Player 1 points | " + totalPointPlayer1+" points");
		System.out.println("Player 2 points | " + totalPointPlayer2+" points");
		System.out.println("--------------------------");
		System.out.println("\n***Player " + playerGagnant + " is the winner !***");
	}
	public boolean isConsecutivePass(Action currentaction, Action previousaction){
		if (currentaction.isPass() && previousaction.isPass()){
			isConsecutivePass=true;
		}
		return isConsecutivePass;
	}
	public void initialiseDisplayer(){
		displayer = new ConsoleDisplay();
	}
	public void displayBoard(){
		displayer.display(gameBoard,9);
	}
	public void startRound(Player p,Board gameBoard,Action playerAction) {

		if (!playerAction.isPass()) {
			this.gameBoard.setBoardTable(gameBoard.placeToken(p, playerAction.getActionIntersection()));
		}else if(playerAction.isPass()) {

		}
	}
	public Turn nextRound(Turn actualturn) {

		if (actualturn.getCurrentPlayerColor()==Color.BLACK){
			actualturn.setCurrentPlayerColor(Color.WHITE);
			actualturn.nextTurn();
		}else if (actualturn.getCurrentPlayerColor()==Color.WHITE){
			actualturn.setCurrentPlayerColor(Color.BLACK);
			actualturn.nextTurn();
		}
		actualturn.nextPlayer();
		return actualturn;
	}
	public boolean intersectionUsed(Board board, Action userAction){
		List<Board> listBoards = board.getHistoryBoard();
		if (listBoards.size() ==0) {
			return false;
		} else {
			if (!userAction.isPass()) {
				Board firstListOfBoard = listBoards.get(listBoards.size() - 1);
				Intersection third = firstListOfBoard.getIntersectionByXY(userAction.getActionIntersection().getX(),
						userAction.getActionIntersection().getY());
				if (third.getStatus() != go.game.model.IntersectionStatus.FREE) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean isIntersectionUsed(Board board, Action userAction) {
		if (!userAction.isPass()) {
			boolean isFree = false;
			Intersection intersection = userAction.getActionIntersection();
			long countIntersectionFree = board.getBoardTable().stream().filter(i-> i.getX() == intersection.getX())
					.filter(i-> i.getY() == intersection.getY())
					.filter(i-> i.getStatus() == IntersectionStatus.FREE).count();
			return countIntersectionFree == 1;
		} else {
			return false;
		}
	}
	public boolean isConsecutivePass() {
		return isConsecutivePass;
	}

}
