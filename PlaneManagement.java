package avions;

import java.util.Scanner;

public class PlaneManagement {
	
	Scanner entry = new Scanner(System.in);
	
	public void managementMenu(){	// method with the plane management options
		
		int option = 1;             
		String licensePlate;
		Plane selection=null;
		
		// search the plane selected by the license plate
		System.out.println("Write the plane's license plate");	
		licensePlate = entry.next();
		
		try{
			for(int i=0; i<Airspace.planes.size(); i++){ 
				if(Airspace.planes.get(i).getLicensePlate().equals(licensePlate)){ 
					selection = Airspace.planes.get(i);
					break;
				}
			}
		}catch(Exception e){
			System.out.println("There isn't a license plate saved that match with the inserted");
		}
		
		while(option!=0){ 	// loop to continue doing operations until 0 is pressed
			System.out.println("You are in the plane management menu");
			System.out.println("Write the corresponding number to the action you want to perform");
			System.out.println("1: Turn on/off engine");
			System.out.println("2: Speed (increase or decrease)");
			System.out.println("3: Height (up or down)");
			System.out.println("4: Fold/unfold landing gear");
			System.out.println("5: Place coordinates X/Y");
			System.out.println("6: Shoot");
			System.out.println("0: Exit management menu");
			
			option = entry.nextInt();
			
			switch(option){		// switch with a method for each operation
				case 1: engine(selection);	break;
				case 2: speed(selection);	break;
				case 3: height(selection);	break;
				case 4: landingGear(selection);	break;
				case 5: position(selection);	break;
			}
		}
		
	}
	
	public void engine(Plane selection){	// method turn on or off the engine
		
		int option = 0;
		System.out.println("Press 1 to turn on the engine, or 2, to turn it off");
		option=entry.nextInt();
		
		if(option==1){	// turn it on
			
			if(selection.isEngine()==true){	// checking if it's already opened
				System.out.println("The engine's plane was already on");
			}else{
				selection.setEngine(true);	// modify value with the setter
				System.out.println("Engine on");
			}
			
		}else if(option==2){ 	// turn it off
			
			if(selection.isEngine()==false){
				System.out.println("The engine's plane was already off");
			}else{
				selection.setEngine(false);
				System.out.println("Engine off");
			}
			
		}else{ 	// check if another number is pressed
			System.out.println("The only options are 1 or 2. You have pressed another number.");
		}
	}
	
	public void speed(Plane selection){	// method to modify the plane's speed
		int speed = 0;
		
		if(selection.isEngine()==true){	// first of all, check if the engine is on
			
			System.out.println("Write the speed at which you want the plane to attain in km/h");
			speed=entry.nextInt();
			
			try{	// try/catch to assure that the speed is modified correctly
				selection.setSpeed(speed);
			}catch(Exception e){
				System.out.println("There has been a problem, try again");
			}
			
		}else{
			System.out.println("El motor està apagat, encen-lo primer");
		}	
	}
	
	public void height(Plane selection){	// method to change height
		
		int height = 0;
		LandingTrack track = new LandingTrack();	// landing track
		
		if(selection.isEngine()==true){	// only if the engine is on
			
			// if height = 0, needs to take off. Speed has to be more than 180km/h
			if(selection.getHeight()==0 && selection.getSpeed()<180){
				System.out.println("To take off, the speed has to be 180km/h or more");
				
			}else{
				
				System.out.println("Write the height at which you want the plane to attain");
				height=entry.nextInt();
				
				// when the height is more than 500m, the landing gear has to hide
				if(selection.isLandingGear()==true && height>=500){	
					System.out.println("To fly over 500m, fold the landing gear");
					
				}else if(selection.getHeight()!= 0 && height==0){	// if the user wants the height at 0, it's a landing
					
					if(selection.getCoordinates().getX()!=track.getStart().getX()){	// check if the plane is at the landing track
						if(selection.getCoordinates().getY()<track.getStart().getY() && selection.getCoordinates().getY()>track.getEnd().getY()){
							if(selection.getSpeed()<200){ 	// check that the speed is less than 200km/h
								selection.setHeight(height);
								track.setOccupied(true);	// when has all the requirements, lands and set landing track to occupied
							}else{ // if not, the plane crashes and it's deleted
								System.out.println("The speed has to be less than 200km/h, the plane is going to crash!");
								deletePlane(selection);
								System.out.println("Oh no! Plane crash!");
							}
							
						}else{
							System.out.println("You can't land out of the landing track, the plane is going to crash!");
							deletePlane(selection);
							System.out.println("Oh no! Plane crash!");
						}
						
					}else{
						System.out.println("You can't land out of the landing track, the plane is going to crash!");
						deletePlane(selection);
						System.out.println("Oh no! Plane crash!");
					}
					
				}else{	// to change the height when it's not a take of or a landing, try/catch and assign it
					
					try{
						selection.setHeight(height);
					}catch(Exception e){
						System.out.println("There has been a problem when trying to assign the height, try again");
					}
				}
			}
		}else{
			System.out.println("Engine is off, turn it on first");
		}
		
	}
	
	public void landingGear(Plane selection){	// method to un/fold the landing gear
		
		int option = 0;
		System.out.println("Write 1 to unfold the landing gear, or 2, to fold it");
		option=entry.nextInt();
		
		if(option==1){	// unfold
			
			if(selection.getHeight()>500 || selection.getSpeed()>300){	// requirements
				System.out.println("You can't unfold the landing gear over 500m of height or 300km/h of speed");
			}else{	// requirements achieved
				selection.setLandingGear(true);
				System.out.println("Landing gear unfolded succesfully");
			}
			
		}else if(option==2){	// fold
			selection.setLandingGear(false);
			
		}else{
			System.out.println("The only options are number 1 or 2");
		}
	}
	
	public void position(Plane selection){	// method to indicate the plane's coordinates
		
		int x=0, y=0;
		Coordinate coordinates;
		
		System.out.println("Indicate the coordinates. Have in mind that the airspace's measures are 1000x1000.");
		System.out.println("With higher coordinates, the plane will get out of the airspace and will be deleted");
		
		System.out.println("Coordinate X: ");
		x = entry.nextInt();
		
		System.out.println("Coordinate Y: ");
		y = entry.nextInt();
		
		if(x>1000 && y>1000){ 	// getting out of the airspace and deleting the plane
			System.out.println("The plane is no more in the airspace");
			deletePlane(selection);
		}else{
			coordinates = new Coordinate(x, y);
			selection.setCoordinates(coordinates);
		}
	}
	
	public void deletePlane(Plane selection){ // search the plane by it's license plate and delete it
		
		for(int i=0; i<Airspace.planes.size(); i++){ 
			if(Airspace.planes.get(i).getLicensePlate().equals(selection.getLicensePlate())){ 
				Airspace.planes.remove(i);	// the array's position of the deleted plane turns to null
			}
		}
	}
}
