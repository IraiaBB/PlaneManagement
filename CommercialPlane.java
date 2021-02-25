package avions;

public class CommercialPlane extends Plane {

	private int passengers;
	
	// constructor with the father class' variables
	public CommercialPlane(String licensePlate, String brand, String model, int capacity){
		super(licensePlate, brand, model, capacity);
	}
	
	// passengers' setter & getter
	public int getPassengers() {return passengers;}

	public void setPassengers(int passengers) {this.passengers = passengers;}
	
}
