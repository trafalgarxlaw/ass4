package go.game.model;



public class Player {


	public Color color;
	private int id;
	private int capturedStones;
	private char symbole;

	
	public Player (int id, Color color, int capturedStones){
		this.id=id;
		this.color=color;
		this.capturedStones =0;

	}

	public int  getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public  int getCapturedStones(){
		return capturedStones;	
	}
	
	public void addCapturedStones(int nb) {
		capturedStones += nb;
	}
	public void removeCapturedStones(int nb) {
		capturedStones -= nb;
	}
	public void setCapturedStones(int capturedStones) {
		this.capturedStones = capturedStones;
	} 
	

}