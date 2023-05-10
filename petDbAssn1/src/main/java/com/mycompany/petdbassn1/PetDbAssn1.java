/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.petdbassn1;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author veltk
 */
public class PetDbAssn1 {

    public static void main(String[] args) {
        ArrayList<Pet> pets = new ArrayList<>();
        
        int userChoice = -1;
        
        while (userChoice != 3) {
            Boolean doneEntering = false;
            Scanner input = new Scanner(System.in);
            
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            
            System.out.println("3) Exit program");
            
            System.out.print("Enter a choice: ");
            userChoice = input.nextInt();
            
            if (userChoice == 1) {
                displayPetTable(pets);
            }
            else if (userChoice == 2) {
                System.out.println("Enter \"done\" when finished entering new pets.");
                while (doneEntering == false) {
                    Scanner petEntry = new Scanner(System.in);
                    System.out.print("Add pet (\"name age\" ): ");
                    String strEntry = petEntry.nextLine();
                    System.out.println("DEBUG: user entered " + strEntry);
                    
                    if (strEntry.equals("done")) {
                        doneEntering = true;
                    }
                    else {
                        String[] arrEntry = strEntry.split(" ");
                        pets.add(new Pet(arrEntry[0], Integer.parseInt(arrEntry[1])));
                    }
                }
            }
        }
    }
    
    public static void displayPetTable(ArrayList<Pet> pets) {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
        
        for(int i = 0; i < pets.size(); i++) {
            System.out.printf("|%3d | %-9s |%4d |%n", i, pets.get(i).getName(), pets.get(i).getAge());
        }
        
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.");
    }
}
