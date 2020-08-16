package go.game.model;

public class PlayerBuilder {

	public Color color;
	private int id;
	private int capturedStones=0;
	private char symbol;
	
	//public PlayerBuilder (int id, Color color, int capturedStones){
	//	this.id=id;
//		this.color=color;
//		this.capturedStones =0;
//
//	}
	

	public PlayerBuilder setId(int id) {
		this.id = id;
		return this;
	}
	
	public PlayerBuilder setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public PlayerBuilder setCapturedStones(int capturedStones) {
		this.capturedStones = capturedStones;
		return this;
	} 
	
	public PlayerBuilder setSymbol(char symbol) {
		this.symbol = symbol;
		return this;
	} 

	public Player build() {
		return new Player(id, color, capturedStones);

	}
}