# PlaneManagement

## Introduction

This project is a simulation of a plane management system. It's compounded by a serie of menus which perform actions to change the state and information about a plane. 
Planes can be commercial or military.      
There could be a ton of similar managent system projects, such as one for a retail warehouse or a coffe shop. This is just an example.   
The purpose of this project is to practice the most used java statements and encryption.   

## Installation

This is a Java project made in the [Eclipse IDE](https://www.eclipse.org/downloads/packages/). Any IDE supporting Java can be used, though.

## How it works

The main class is only to choose between two menus: the controller menu and the plane management menu.    

#### Controller menu    
The options in this menu are related to the ones that an air traffic controller does.    
- Add a plane to the system and it's most important information.     
- Add detailed information about a plane.      
- Display an information board about all the planes inside the airspace.      
- Encrypt plane. Only ally military planes have access to this option. This means that in the board information will appear the word "ENCRYPTED" without data.      
- Decrypt plane.

#### Plane management menu    
In this menu the operations are more related to a plane pilot's job.     
- Turn on or turn off the plane's engine.     
- Speed up or slow down. The engine has to be on.     
- Change height. First of all, the engine has to be on. Then, if the desired height is more than 500 meters, it's indispensable to hide the landing gear. If it's 0 meters, means that the user wants to land, so the plane must be inside the landing track coordinates and the speed has to be less than 200km/h.    
- Fold/unfold landing gear. This can't be unfolded over 500m or 300km/h.    
- Place the plane with X/Y coordinates. The plane must be inside the airspace (1000x1000), or the plane will be deleted.   

## Project's structure

As I said before, the main class, called with that same name `Main.java`, is for choosing between the two menus, each one with it's own class: `Airspace.java` (controller menu) and `PlaneManagement.java`. Those have their corresponding operations explained in the previous section, that are choosen by the menu number.This leads to a method using a `switch` statement.   
But the most important class in the project is `Plane.java`, the central object, each one with its own features, translated in attributes. As there are two types of the plane in this simulation, this class has two children: `CommercialPlane.java` and `MilitaryPlane.java`. They have similar features like the model name or the license plate number, but differ in others. For example, a military plane doesn't have passengers, but have missiles, unlike the commercial plane.      
Another important object is `Coordinate.java`, which gives the position of the plane or demarcates the landing track (`LandingTrack.java`). 
