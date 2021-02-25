package avions;

public class MilitaryPlane extends Plane{

	private int missiles;
	private int shotRange;
	private boolean enemy;
	private boolean encrypted;
	
	// constructor with the father class' variables
	public MilitaryPlane(String licensePlate, String brand, String model, int capacity){
		super(licensePlate, brand, model, capacity);
	}
	
	// setters & getters
	public int getMissiles() {return missiles;}

	public void setMissiles(int missiles) {this.missiles = missiles;}

	public int getShotRange() {return shotRange;}

	public void setShotRange(int shotRange) {this.shotRange = shotRange;}

	public boolean isEnemy() {return enemy;}

	public void setEnemy(boolean enemy) {this.enemy = enemy;}
	
	public void setEncrypted(boolean encrypted) {this.encrypted = encrypted;}
	
	public boolean isEncrypted() {return encrypted;}
	
	
	
}
