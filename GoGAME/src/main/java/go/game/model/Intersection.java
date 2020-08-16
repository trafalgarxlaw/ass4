package go.game.model;

public class Intersection {
	private int x;
	private int y;
	private IntersectionStatus status = IntersectionStatus.FREE;
		
	
	public Intersection (int horizontal, int vertical) {
		this.x = horizontal;
		this.y = vertical;
	}
	
	public IntersectionStatus getStatus() {
		return status;
	}

	public void setStatus(IntersectionStatus status) {
		this.status = status;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	@Override
	public String toString() {
		return "Intersection [X=" + x + ", Y=" + y + ", status=" + status + "]";
	}


}
