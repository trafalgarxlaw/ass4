package go.game.controller;

import go.game.model.Color;
import go.game.model.Player;

public class Turn {
    private int currentTurn;
    private Player player1;
    private Player player2;
    private int currentPlayer;
    private Color currentPlayerColor;

    public Turn(Player p1,Player p2){
        this.currentPlayerColor=Color.BLACK;
        this.player1=p1;
        this.player2=p2;
        this.currentPlayer=this.player1.getId();

    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setCurrentPlayerColor(Color currentPlayerColor) {
        this.currentPlayerColor = currentPlayerColor;
    }

    public void nextTurn(){
        this.currentTurn++;
    }

    public void nextPlayer(){
        if (currentPlayer==player1.getId()){
            this.currentPlayer=player2.getId();
        }else if (currentPlayer==player2.getId()){
            this.currentPlayer=player1.getId();
        }
    }

    public Player getCurrentPlayer(){
        Player p =player1;

        if (currentPlayer==player1.getId()){
            p= player1;
        }else if (currentPlayer==player2.getId()){
            p= player2;
        }
        return p;
    }
}
