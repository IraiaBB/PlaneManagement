package avions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class Airspace {
	
	
	static ArrayList<Plane> planes = new ArrayList<Plane>();
	Scanner entry = new Scanner(System.in);
	
	public void controllerMenu(){	// options the air controller can perform
		int option=1;
		
		while(option!=0){ 
			System.out.println("You are in the air controller menu");
			System.out.println("Write the number corresponding to the action you quant to perform:");
			System.out.println("1. Add plane (basic info)");
			System.out.println("2. Add detailed information of a plane");
			System.out.println("3. Show airspace");
			System.out.println("4. Encrypt plane");
			System.out.println("5. Decrypt plane");
			System.out.println("Press 0 to exit");
			
			option = entry.nextInt();
			
			switch(option){	// switch with a method for each operation
				case 1: 
					addPlane();
					break;
				case 2: 
					afegirInfo();
					break;
				case 3: 
					displayInfo();
					break;
				case 4: 
					encrypt();
					break;
				case 5:
					desencriptar();
					break;
			}
		}
		
	}
	
	public void addPlane(){
		
		String licensePlate;
		String brand;
		String model;
		boolean goOn= true;
		int tryIt=1;
		int capacity;
		int planeType = 0;
		int planeMeter = 0;
		Plane newPlane = null;
		
		// count the planes available in the airspace
		for(int i=0; i<planes.size(); i++){
			if(planes.get(i)!=null){
				planeMeter++;
			}
		}
		// if there are more than 10, warn the user and ask if they want to add it anyway
		if(planeMeter>=10){
			System.out.println("Only can be 10 planes on the airspace, but there are " +
		planeMeter + "...");
			System.out.println("It could cause a crash! Do you wish to continue?(write the number) 1:yes / 2:no");
			int response = entry.nextInt(); 
			if (response == 1){goOn = true;}
			else if(response == 2){goOn = false;}
			else{System.out.println("Incorrect numer pressed. Try again");}
		}
		
		if(goOn == true){ 	// add it
			
			// main data insertion: license plate, brand, model i capacity
			System.out.println("Introdueix les dades de l'avió");
			System.out.println("Matrícula: ");
			licensePlate = entry.next();
			System.out.println("Marca: ");
			brand = entry.next();
			System.out.println("Model: ");
			model = entry.next();
			System.out.println("Capacitat: ");
			capacity = entry.nextInt();
			
			// ask plane type
			System.out.println("Escriu 1 si és un avió comercial, o 2 si és militar");
			planeType = entry.nextInt();
			
			while(tryIt==1){ // if they want to try again
				
				if(planeType==1 || planeType==2){	// if the type is correct
					try{
						switch (planeType) {
						case 1:
							newPlane = new CommercialPlane(licensePlate, brand, model, capacity);
							break;
						case 2:
							newPlane = new MilitaryPlane(licensePlate, brand, model, capacity);
							break;
						}
					
						// add the plane in a blank space of the array	
						planes.add(newPlane);
						System.out.println(planes.get(0).getLicensePlate());
						System.out.println("The plane has been added succesfully");
						break;
					}catch(Exception e){ // if error, ask for another try
						System.out.println("There has been an error adding the game... Write 1 to try again, 2 if not");
						tryIt = entry.nextInt();
					}
					
				}else{
					// another chance to write it
					System.out.println("The answer must be 1 or 2. Do you want to try again?");
					System.out.println("For a YES, write 1, for a NO, write 2!");
					tryIt = entry.nextInt();
				}
			}
		}	
	}
	
	public void afegirInfo(){	// method to add details of the plane
		int goOn =1;
		
		// ask for the license plate for identification
		System.out.println("Insert the license plate");
		String licensePlate = entry.next();
		
		while(goOn==1){ // loop to continue adding information
			
			// features' menu
			System.out.println("Write the number corresponding to the data you want to add:");
			System.out.println("1.Origin / 2.Destination / 3.Crew members / 4.Passengers / 5.Missiles");
			int number = entry.nextInt();
			
			for(int i=0; i<planes.size();){ // planes loop
				
				if(planes.get(i).getLicensePlate().equals(licensePlate)){ // find the plane
					switch(number){ // search the feature to modify
					
					case 1:
						System.out.println("Insert origin");
						String origin = entry.next();
						planes.get(i).setOrigin(origin);
						break;
						
					case 2:
						System.out.println("Insert destination");
						String destination = entry.next();
						planes.get(i).setDestination(destination);
						break;
						
					case 3:
						System.out.println("Insert the number of crew members");
						int crew = entry.nextInt();
						planes.get(i).setCrew(crew);
						break;
						
					case 4:
						if(planes.get(i) instanceof CommercialPlane){
							System.out.println("Insert the number of passengers");
							int passengers = entry.nextInt();
							planes.get(i).setPassengers(passengers);
						}else{
							System.out.println("A military plane has not passengers");
						}
						break;
					
					case 5:
						if(planes.get(i) instanceof MilitaryPlane){
							System.out.println("Insert the number of missiles");
							int missiles = entry.nextInt();
							planes.get(i).setMissiles(missiles);
						}else{
							System.out.println("A commercial plane has not missiles");
						}
						break;
					}
				}
				break; // if it's changed, exit
			}
			// ask if the user want to continue doing changes
			System.out.println("Do you want continue adding or changing information? YES: 1 / NO: 2");
			goOn = entry.nextInt(); // if it's a 2, get out of the while
		}		
	}
	
	public void displayInfo(){	// method that creates an information board
		int planeNum=0;
		
		for(int i=0; i<planes.size(); i++){	// loop to know the number of planes
			if(planes.get(i)!=null){
				planeNum++;
			}
		}
		
		Plane[] infoBoard = new Plane[planeNum];	// create the array that will collect all the planes
		
		int j=0;
		for(int i=0; i<planes.size(); i++){		// put the planes in the array
			if(planes.get(i)!=null){
				infoBoard[j] = planes.get(i);
				j++;
			}
		}
		String[][] grid = new String[17][infoBoard.length + 1];	// new matrix
		
		//característiques a mostrar fixes
		grid[0][0] = "Id..................";
		grid[1][0] = "License plate.......";
		grid[2][0] = "Brand...............";
		grid[3][0] = "Model...............";
		grid[4][0] = "Capacity............";
		grid[5][0] = "Type................";
		grid[6][0] = "X...................";
		grid[7][0] = "Y...................";
		grid[8][0] = "Height..............";
		grid[9][0] = "Speed...............";
		grid[10][0] = "Landing gear........";
		grid[11][0] = "Engine..............";
		grid[12][0] = "Crew................";
		grid[13][0] = "Passengers..........";
		grid[14][0] = "Missiles............";
		grid[15][0] = "Origin..............";
		grid[16][0] = "Destination.........";
		
		// loop to fill the plane's data
		for(int i=0; i<infoBoard.length; i++){
			Plane selection = infoBoard[i]; 	// each turn will fill the data of one plane
			
			int id= i + 1;
			// with the getters, the information is collected
			grid[0][id] = String.valueOf(id);
			
			if(selection.isEncrypted()==false){
				
				grid[1][id] = selection.getLicensePlate();
				grid[2][id] = selection.getBrand();
				grid[3][id] = selection.getModel();
				grid[4][id] = String.valueOf(selection.getCapacity());
				
				if(selection.getClass().equals("CommercialPlane")){
					grid[5][id] = "Commercial";
				}else{
					grid[5][id] = "Military";
				}
				
				grid[6][id]=String.valueOf(selection.getCoordinates().getX());
				grid[7][id]=String.valueOf(selection.getCoordinates().getY());
				grid[8][id]=String.valueOf(selection.getHeight());
				grid[9][id]=String.valueOf(selection.getSpeed());
				
				if(selection.isLandingGear()==true){
					grid[10][id] = "On";
				}else{
					grid[10][id] = "Off";
				}
				if(selection.isEngine()==true){
					grid[11][id] = "On";
				}else{
					grid[11][id] = "Off";
				}
				
				grid[12][id] = String.valueOf(selection.getSpeed());
				
				if(selection instanceof CommercialPlane){
					grid[13][id] = String.valueOf(selection.getPassengers());
				}else{
					grid[13][id] = "No";
				}
				if(selection instanceof CommercialPlane){
					grid[14][id] = "No";
				}else{
					grid[14][id] = String.valueOf(selection.getMissiles());
				}
				
				grid[15][id]=String.valueOf(selection.getOrigin());
				grid[16][id]=String.valueOf(selection.getDestination());
				
			}else{
				grid[1][id] = "ENCRYPTED";
				grid[2][id] = "";
				grid[3][id] = "";
				grid[4][id] = "";
				grid[5][id] = "";
				grid[6][id] = "";
				grid[7][id] = "";
				grid[8][id] = "";
				grid[9][id] = "";
				grid[10][id] = "";
				grid[11][id] = "";
				grid[12][id] = "";
				grid[13][id] = "";
				grid[14][id] = "";
				grid[15][id] = "";
				grid[16][id] = "";
				
			}
		}
		
		// each piece of data has to be in a 10 character string
		String unit = " ";
		int stringLength = 11;
		
		for(int a=0; a<grid.length; a++){
			for(int b=1; b<grid[a].length; b++){
				int realLength = grid[a][b].length();   // current length of the string
				int spaces = stringLength - realLength;	// spaces to fill until the 10th character
				String pack = "";
				
				for(int c=0; c<=spaces; c++){
					pack = pack + unit;
				}
				grid[a][b] = grid[a][b]+ pack;	// add spaces
				
			}
		}
		
		// show information
		for(int x=0; x<grid.length; x++){
			for(int y=0; y<grid[x].length; y++){
				System.out.print(grid[x][y]);
			}
			System.out.println("");
		}
		
	}
	
	public void encrypt() {
        
        int option = 0;
        
       
        System.out.print("Insert the license plate of the plane to encrypt it: ");
        String licensePlate = entry.next();
   
        for (int x = 0; x < planes.size(); x++) {
            if(planes.get(x).getLicensePlate().contentEquals(licensePlate)) {
                if(planes.get(x).isEnemy() == false) {
                	
                	int value = (int) Math.floor(Math.random()*9+1);
            		
                    // create salt
                    String licenseAux = planes.get(x).getLicensePlate() + value;
      
                    
                    String hashMat = Base64.getEncoder().encodeToString(licenseAux.getBytes(StandardCharsets.UTF_16));
                   
                    //nom de l'arxiu
                    String doc = planes.get(x).getLicensePlate() + ".gpg";
               
                    //creem el document
                    try (FileWriter write = new FileWriter(doc, true);
                            BufferedWriter lector = new BufferedWriter(write);
                            PrintWriter print = new PrintWriter(lector)) {
 
                        print.write("");
                        print.print(hashMat);
                       
                        lector.close();
                       
                        planes.get(x).setEncrypted(true);
                        option = 3;
                        
                       
                    } catch (Exception e) {
                    	option=4;
                        System.out.print("\n" + e);
                    }
                   
                } else {
                    option = 1;
                }
            } else {
                option = 2;
            }
        }
       
        if (option == 1) {
            System.out.println("This plane is not an ally");
        } else if (option == 2) {
            System.out.print("This license plate doesn't exist");
        } else if (option == 3) {
            System.out.print("Succesful encrytation");
        }else if (option == 4) {
            System.out.print("Error");
        }
    }

	
	public void desencriptar() {
      
        String auxLicense, licensePlate, hashLicense, readDoc = null;
       
        // ask for the license plate
        System.out.println("Write the plane's license plate decrypt:");
        licensePlate = entry.next();
       
        try {
            String docName = licensePlate + ".gpg";
            readDoc = new String(Files.readAllBytes(Paths.get(docName)));
               
            // loop to find the key
            do {
            	int value = (int) Math.floor(Math.random()*9+1);
            	
                auxLicense = licensePlate + value;
               
                hashLicense = Base64.getEncoder().encodeToString(auxLicense.getBytes(StandardCharsets.UTF_16));
            } while(!readDoc.equals(hashLicense));
           
           
            if (readDoc.equals(hashLicense)) {
                for (int x = 0; x < planes.size(); x++) {
                    if(planes.get(x).getLicensePlate().contentEquals(licensePlate)) {
                        if(planes.get(x).isEncrypted() == true) {
                        	planes.get(x).setEncrypted(false);
                            System.out.println("The plane " + licensePlate + " has been decrypted succesfully.");
                           
                          
                            File doc = new File(docName);
                            doc.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("The document can't be found");
        }
    }
   
}
