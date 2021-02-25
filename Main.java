package avions;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int option;
		
		Scanner entry = new Scanner(System.in);
		
		System.out.println("Welcome!");
		System.out.println("Write the number of the menu you want to open:");
		System.out.println("1. Controller menu");
		System.out.println("2. Plane management menu");
		System.out.println("0. Finish");
		
		Airspace controller = new Airspace();
		PlaneManagement pilot = new PlaneManagement();
		
		option = entry.nextInt();
		
		while(option!=0){
			if(option==1){
				controller.controllerMenu();
			}else if(option==2){
				pilot.managementMenu();
			}else{
				System.out.println("Error, you have to insert a number between 0 and 2.");
			}
			
			System.out.println("You have exited both menus, what you want to do next?");
			System.out.println("1. Controller menu");
			System.out.println("2. Plane management menu");
			System.out.println("0. Finish");
			option = entry.nextInt();
		}
		entry.close();
		
		

	}
	
	
	
	

}
