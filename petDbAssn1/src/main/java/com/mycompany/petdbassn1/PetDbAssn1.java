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
        
        pets.add(new Pet("bobb", 9));
        pets.add(new Pet("joe", 8));
        pets.add(new Pet("bobb", 7));
        pets.add(new Pet("alice", 9));
        
        
        
        int userChoice = -1;
        
        while (userChoice != 4) {
            Boolean doneEntering = false;
            Scanner input = new Scanner(System.in);
            
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Search for pets");
            
            System.out.println("4) Exit program");
            
            System.out.print("Enter a choice: ");
            userChoice = input.nextInt();
            
            if (userChoice == 1) {
                displayPetHeader();
                    for (int k = 0; k < pets.size(); k++) {
                        displayPetRow(k, pets);
                    }
                    displayPetFooter(pets);
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
            else if (userChoice == 3) {
                Scanner petSearch = new Scanner(System.in);
                System.out.print("Would you like to search by name or by age? (Enter \"name\" or \"age\") ");
                String choice = petSearch.nextLine();
                if (choice.equals("name")) {
                    System.out.print("Enter the name you would like to search for: ");
                    String nameSearched = petSearch.nextLine();
                    displayPetHeader();
                    for (int k = 0; k < pets.size(); k++) {
                        if (pets.get(k).getName().equals(nameSearched)) {
                            displayPetRow(k, pets);
                        }
                    }
                    displayPetFooter(pets);
                    
                }
                else if(choice.equals("age")) {
                    System.out.print("Enter the age you would like to search for: ");
                    int ageSearched = petSearch.nextInt();
                    displayPetHeader();
                    for (int k = 0; k < pets.size(); k++) {
                        if (pets.get(k).getAge() == (ageSearched)) {
                            displayPetRow(k, pets);
                        }
                    }
                    displayPetFooter(pets);
                }
                else {
                    System.out.println("That is not a valid search option\n");
                }
            }
        }
    }

    public static void displayPetHeader() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
    }
    public static void displayPetRow(int index, ArrayList<Pet> pets) {
        System.out.printf("|%3d | %-9s |%4d |%n", index, pets.get(index).getName(), pets.get(index).getAge());
    }
    public static void displayPetFooter(ArrayList<Pet> pets) {
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.");
    }
}
