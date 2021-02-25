package avions;

public class Plane {
	
	private String licensePlate;	// common features in all types of planes
	private String brand;
	private String model;
	private int capacity;
	private boolean engine;
	private int height;
	private int speed;
	private boolean landingGear;
	private int heading;
	private int crew;
	private String origin;
	private String destination;
	private Coordinate coordinates;
	int passengers;
	int missiles;
	private boolean enemy;
	private boolean encrypted;
	
	// initialize essential features for the new plane
	public Plane(String licensePlate, String brand, String model, int capacity){
		this.licensePlate = licensePlate;
		this.brand = brand;
		this.model = model;
		this.capacity = capacity;
		this.engine = false;	
		this.height = 0;
		this.coordinates = new Coordinate(900, 900);
		this.speed = 0;
		this.landingGear = false;
		this.encrypted = false;
		this.enemy = true;
	}
	
	// all of their getters and setters
	public String getLicensePlate() {return licensePlate;}

	public void setLicensePlate(String licensePlate) {this.licensePlate = licensePlate;}

	public String getBrand() {return brand;}

	public void setBrand(String brand) {this.brand = brand;}

	public String getModel() {return model;}

	public void setModel(String model) {this.model = model;}

	public int getHeight() {return height;}

	public void setHeight(int height) {this.height = height;}
	
	public int getSpeed() {return speed;}

	public void setSpeed(int speed) {this.speed = speed;}

	public int getHeading() {return heading;}

	public void setHeading(int heading) {this.heading = heading;}

	public int getCapacity() {return capacity;}

	public void setCapacity(int capacity) {this.capacity = capacity;}
	
	public boolean isEngine() {return engine;}

	public void setEngine(boolean engine) {this.engine = engine;}

	public int getCrew() {return crew;}

	public void setCrew(int crew) {this.crew = crew;}

	public String getOrigin() {return origin;}

	public void setOrigin(String origin) {this.origin = origin;}

	public String getDestination() {return destination;}

	public void setDestination(String destination) {this.destination = destination;}
	
	public Coordinate getCoordinates(){return coordinates;}
	
	public void setCoordinates(Coordinate coordinates){this.coordinates=coordinates;}
	
	public boolean isLandingGear() {return landingGear;}

	public void setLandingGear(boolean landingGear) {this.landingGear = landingGear;}
	// end setters i getters
	
	// specific setters i getters
	public void setPassengers(int passengers) {}
	public int getPassengers() {return passengers;}
	public void setMissiles(int missils) {}
	public int getMissiles() {return missiles;}
	public boolean isEnemy() {return enemy;}
	public void setEnemy(boolean enemy) {this.enemy = enemy;}
	public void setEncrypted(boolean encrypted) {}
	public boolean isEncrypted() {return encrypted;}
}
