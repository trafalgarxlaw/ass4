package go.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import go.game.rule.CanPlay;
import go.game.rule.Capture;
import go.game.controller.Turn;

public class Board {

	private Turn gameTurn;
	private Dimensions boardDimensions;

	private List<Intersection> boardTable;

	private List <Board> historyBoard = new ArrayList<>();
	private List <Board> historyIntersectionCaptured = new ArrayList<>();

	private int pointsCapturedPlayer1;
	private int pointsCapturedPlayer2;
	private int teritoryPlayer1;
	private int teritoryPlayer2;
	

	public Board getBoard (Board board) {
		return board;

	}

	public List<Intersection> getBoardTable() {
		return boardTable;
	}


	public void setBoardTable(List<Intersection> boardTable) {
		this.boardTable = boardTable;
	}

	public void addHistoryBoard (Board board) {
		historyBoard.add(board);
	}

	public List <Board> getHistoryBoard () {
		return historyBoard;
	}

	public List <Board> getHistoryIntersectionCaptured () {
		return historyIntersectionCaptured;
	}

	public Dimensions getBoardDimensions() {
		return boardDimensions;
	}

	public Board(Dimensions dimension) {
		this.boardDimensions = dimension;
		initializeBoardTable();
	}

	private void initializeBoardTable() {
		boardTable =  new ArrayList<>(boardDimensions.getRows() * boardDimensions.getColumns());
		for (int i =0; i < boardDimensions.getRows(); i++) {
			for (int j =0; j < boardDimensions.getColumns(); j++) {
				boardTable.add(new Intersection(i, j));
			}
		}
	}

	public boolean isInBoard(Intersection intersection) {
		int x = intersection.getX();
		int y = intersection.getY();
		return (x >= 0 && x < boardDimensions.getRows() && y >= 0 && y < boardDimensions.getColumns());
	}

	public Intersection getIntersectionByXY(int x, int y) {

		return  boardTable.stream()
				.filter(i -> i.getX() == x)
				.filter(i -> i.getY() == y)
				.findFirst()
				.get();
	}


	public List<Intersection> placeToken(Player player, Intersection intersection) {

		go.game.rule.IsCapture isCaptured = new go.game.rule.IsCapture();
		if (player.getId()==1)
			intersection.setStatus(IntersectionStatus.PLAYER1);
		else
			intersection.setStatus(IntersectionStatus.PLAYER2);
		if (new CanPlay().isPlayable(this, intersection)) {

			if (player.getId()==1){
				updateIntersectionStatus(getIntersectionByXY(intersection.getX(),intersection.getY()),IntersectionStatus.PLAYER1);
				Capture capture = new Capture();
				Board board1 = capture.executeAction(this, intersection);
				pointsCapturedPlayer1 += capture.getPoints();
			

		
				return board1.getBoardTable();

			} else if (player.getId()==2){
				updateIntersectionStatus(getIntersectionByXY(intersection.getX(),intersection.getY()),IntersectionStatus.PLAYER2);
				Capture capture = new Capture();
				Board board1 = capture.executeAction(this, intersection);
				pointsCapturedPlayer2 += capture.getPoints();


				return board1.getBoardTable();

			}
		}
		return this.boardTable;
	}


	public void updateIntersectionStatus(Intersection intersection, IntersectionStatus status) {
		for (int i = 0; i < boardTable.size(); i++) {
			if (boardTable.get(i).getX() == intersection.getX()
					&& boardTable.get(i).getY() == intersection.getY()) {
				boardTable.get(i).setStatus(status);
			}
		}
	}

	public List<Intersection> tokensFreeAroundToken ( Intersection intersection) {
		List<Intersection> tokensFree = new ArrayList<>();
		List <Intersection> intersectionDown = boardTable.stream()
				.filter(i -> intersection.getX() == i.getX())
				.filter(i -> intersection.getY()-1 == i.getY())
				.filter(i -> i.getStatus() == IntersectionStatus.FREE)
				.collect(Collectors.toList());


		List <Intersection> intersectionUp = boardTable.stream()
				.filter(i -> intersection.getX() == i.getX())
				.filter(i -> intersection.getY()+1 == i.getY())
				.filter(i -> i.getStatus() == IntersectionStatus.FREE)
				.collect(Collectors.toList());



		List <Intersection> intersectionRight = boardTable.stream()
				.filter(i -> intersection.getX()+1 == i.getX())
				.filter(i -> intersection.getY() == i.getY())
				.filter(i -> i.getStatus() == IntersectionStatus.FREE)
				.collect(Collectors.toList());


		List <Intersection> intersectionLeft = boardTable.stream()
				.filter(i -> intersection.getX()-1 == i.getX())
				.filter(i -> intersection.getY() == i.getY())
				.filter(i -> i.getStatus() == IntersectionStatus.FREE)
				.collect(Collectors.toList());


		tokensFree.addAll(intersectionDown);
		tokensFree.addAll(intersectionUp);
		tokensFree.addAll(intersectionRight);
		tokensFree.addAll( intersectionLeft);

		return tokensFree;
	}

	public List<Intersection> tokensSameColorAroundToken (Intersection intersection) {
		List<Intersection> tokensSameColor = new ArrayList<>();
		List <Intersection> intersectionDown = boardTable.stream()
				.filter(i -> intersection.getX() == i.getX())
				.filter(i -> intersection.getY()-1 == i.getY())
				.filter(i -> i.getStatus() == intersection.getStatus())
				.collect(Collectors.toList());


		List <Intersection> intersectionUp = boardTable.stream()
				.filter(i -> intersection.getX() == i.getX())
				.filter(i -> intersection.getY()+1 == i.getY())
				.filter(i -> i.getStatus() == intersection.getStatus())
				.collect(Collectors.toList());


		List <Intersection> intersectionRight = boardTable.stream()
				.filter(i -> intersection.getX()+1 == i.getX())
				.filter(i -> intersection.getY() == i.getY())
				.filter(i -> i.getStatus() == intersection.getStatus())
				.collect(Collectors.toList());


		List <Intersection> intersectionLeft = boardTable.stream()
				.filter(i -> intersection.getX()-1 == i.getX())
				.filter(i -> intersection.getY() == i.getY())
				.filter(i -> i.getStatus() == intersection.getStatus())
				.collect(Collectors.toList());


		tokensSameColor.addAll(intersectionDown);
		tokensSameColor.addAll(intersectionUp);
		tokensSameColor.addAll(intersectionRight);
		tokensSameColor.addAll( intersectionLeft);

		return tokensSameColor;
	}

	public int numberOfLiberties(Intersection intersection) {
		Set<Intersection> freesintersections = new HashSet<Intersection>(tokensFreeAroundToken(intersection));

		List<Intersection> sameColorTokens = new ArrayList<Intersection>(tokensSameColorAroundToken(intersection)) ;

		List<Intersection> verifiedIntersection = new ArrayList<Intersection>();
		verifiedIntersection.add(intersection);

		List<Intersection> nextIntersectionsToVerify = new ArrayList<Intersection>();
		nextIntersectionsToVerify.addAll(sameColorTokens);


		while (! nextIntersectionsToVerify.isEmpty()) {

			Intersection nextIntersection =  nextIntersectionsToVerify.get(0);
			freesintersections.addAll(tokensFreeAroundToken(nextIntersection));

			List<Intersection> sameColorTokensNextInstruction = new ArrayList<Intersection>(tokensSameColorAroundToken(nextIntersection));

			for (int i = 0; i < sameColorTokensNextInstruction.size(); i++) {

				if (!verifiedIntersection.contains(sameColorTokensNextInstruction.get(i)))
					nextIntersectionsToVerify.add(sameColorTokensNextInstruction.get(i));
			}

			verifiedIntersection.add(nextIntersection);
			nextIntersectionsToVerify.remove(nextIntersection);

		}

		return 	 freesintersections.size();
	}



	public boolean isBoardFull() {

		List <Intersection> list = this.getBoardTable();
		long numberIntersectionFree = list.stream().filter(i -> i.getStatus() != IntersectionStatus.FREE).count();

		return numberIntersectionFree == 0;
	}
	

	
	public List<Intersection> allTokensFreeAroundAToken(Intersection intersection) {

		Set<Intersection> finalFreesintersections = new HashSet<Intersection>();
		Set<Intersection> verifiedIntersections =  new HashSet<Intersection>();
		List<Intersection> tokensFreeInFirstLevel =  new ArrayList<>(tokensFreeAroundToken(intersection));
		

		verifiedIntersections.add(intersection);


		while(!tokensFreeInFirstLevel.isEmpty()) {
	
			List<Intersection> tokensFreeInSecondLevel =  new ArrayList<>(tokensFreeAroundToken(tokensFreeInFirstLevel.get(0)));
			verifiedIntersections.add(tokensFreeInFirstLevel.get(0));


			finalFreesintersections.addAll(tokensFreeInSecondLevel);

		
			for (int i=0; i<tokensFreeInSecondLevel.size(); i++) {
				if(!verifiedIntersections.contains(tokensFreeInSecondLevel.get(i)))
					tokensFreeInFirstLevel.add(tokensFreeInSecondLevel.get(i));
			}

			tokensFreeInFirstLevel.remove(tokensFreeInFirstLevel.get(0));
	
		}

		List<Intersection> targetList = new ArrayList<Intersection>(finalFreesintersections);
		
		if(intersection.getStatus() == IntersectionStatus.FREE && !(targetList.contains(intersection))) {

			targetList.add(intersection);
		}

		return 	targetList;
	}


	public List<Intersection> tokensNonFreeAroundAteritoryFreeTokens(List<Intersection> intersectionsFree) {

		List <Intersection> intersectionDown = new ArrayList<>();
		List <Intersection> intersectionUp = new ArrayList<>();
		List <Intersection> intersectionRight = new ArrayList<>();
		List <Intersection> intersectionLeft = new ArrayList<>();
		List <Intersection> intersectionsNonFree = new ArrayList<>();



		for(Intersection intersection : intersectionsFree ) {
	
			intersectionDown = boardTable.stream()
					.filter(i -> intersection.getX() == i.getX())
					.filter(i -> intersection.getY()+1 == i.getY())
					.filter(i -> i.getStatus() != IntersectionStatus.FREE)
					.collect(Collectors.toList());
		

			intersectionUp = boardTable.stream()
					.filter(i -> intersection.getX() == i.getX())
					.filter(i -> intersection.getY()-1 == i.getY())
					.filter(i -> i.getStatus() != IntersectionStatus.FREE)
					.collect(Collectors.toList());

			intersectionRight = boardTable.stream()
					.filter(i -> intersection.getX()+1 == i.getX())
					.filter(i -> intersection.getY() == i.getY())
					.filter(i -> i.getStatus() != IntersectionStatus.FREE)
					.collect(Collectors.toList());
		

			intersectionLeft = boardTable.stream()
					.filter(i -> intersection.getX()-1 == i.getX())
					.filter(i -> intersection.getY() == i.getY())
					.filter(i -> i.getStatus() != IntersectionStatus.FREE)
					.collect(Collectors.toList());


			intersectionsNonFree.addAll(intersectionDown);
			intersectionsNonFree.addAll(intersectionUp);
			intersectionsNonFree.addAll(intersectionRight);
			intersectionsNonFree.addAll(intersectionLeft);
		}

		return intersectionsNonFree;
	}



	public boolean pointsForplayerWonTerrity (List<Intersection> listTokensOccupied,Player player) {

		Set<Intersection> setTokensOccupied = new HashSet<Intersection>(listTokensOccupied);



		if(player.getId() == 1) {

			List<Intersection> intersP1 = listTokensOccupied.stream()
					.filter(i -> i.getStatus() == IntersectionStatus.PLAYER1)
					.collect(Collectors.toList());
			
			return intersP1.size() == setTokensOccupied.size();
		}
		
		
		if(player.getId() == 2) {
		
			List<Intersection> intersP2 = listTokensOccupied.stream()
					.filter(i -> i.getStatus() == IntersectionStatus.PLAYER2)
					.collect(Collectors.toList());
			
	
			return intersP2.size() == setTokensOccupied.size();
		}

		return false;
	}
	

	public int calculatePoints(Player player) {

		List <Intersection> listIntersectionFree = boardTable.stream()
				.filter(i -> i.getStatus() == IntersectionStatus.FREE)
				.collect(Collectors.toList());

		while (!listIntersectionFree.isEmpty()) {
			
			List<Intersection> territoryTokenFree =	allTokensFreeAroundAToken(listIntersectionFree.get(0));
			List <Intersection> intersectionsOccupide = tokensNonFreeAroundAteritoryFreeTokens(territoryTokenFree);
			Set<Intersection> setIntersectionsOccupide = new HashSet <Intersection>(intersectionsOccupide); 
			List<Intersection> listIntersectionsOccupide = new ArrayList<>(setIntersectionsOccupide);


			if (pointsForplayerWonTerrity(listIntersectionsOccupide,player)) {
		
				player.addCapturedStones(territoryTokenFree.size());	

			}
			
			listIntersectionFree.removeAll(territoryTokenFree);

		} 
		
		if (player.getId() == 1) {
			player.addCapturedStones(pointsCapturedPlayer1);
		}
		
		if (player.getId() == 2) {
			player.addCapturedStones(pointsCapturedPlayer2);
		}

		return player.getCapturedStones();

	}

	
}





