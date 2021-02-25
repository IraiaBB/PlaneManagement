package avions;

public class LandingTrack {
	
	private Coordinate start;
	private Coordinate end;
	private boolean occupied;
	
	// initialize landing track with it's position in the space
	public LandingTrack (){
		this.start = new Coordinate(100, 100);
		this.end = new Coordinate(100, 120);
		this.occupied = false;
	}
	
	// setter & getter
	public boolean isOccupied() {return occupied;}

	public void setOccupied(boolean occupied) {this.occupied = occupied;}
	
	public Coordinate getStart() {return start;}

	public Coordinate getEnd() {return end;}
}
