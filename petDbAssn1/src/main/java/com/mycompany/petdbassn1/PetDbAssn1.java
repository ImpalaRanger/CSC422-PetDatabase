/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.petdbassn1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
/**
 *
 * @author veltk
 */
public class PetDbAssn1 {

    public static void main(String[] args) {
        ArrayList<Pet> pets = new ArrayList<>();
        String filename = "petData.txt";
        // default pets for testing purposes
        /* 
        pets.add(new Pet("bobb", 9));
        pets.add(new Pet("joe", 8));
        pets.add(new Pet("bobb", 7));
        pets.add(new Pet("alice", 9));
        */
        try { // file creation code block from https://www.w3schools.com/java/java_files_create.asp
            File petFile = new File(filename);
            if (petFile.createNewFile()) {
                System.out.println("File created: " + filename);
            } 
            else {
                System.out.println("File already exists.");
            }
            pets = loadFromFile(filename);
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        int userChoice = -1;
        
        while (userChoice != 6) {
            Boolean doneEntering = false;
            Scanner input = new Scanner(System.in);
            
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Search for pets");
            System.out.println("4) Update a pet");
            System.out.println("5) Remove a pet");
            System.out.println("6) Exit program");
            
            System.out.print("Enter a choice: ");
            userChoice = input.nextInt();
            
            if (userChoice == 1) {
                displayFullPetTable(pets);
            }
            else if (userChoice == 2) {
                System.out.println("Enter \"done\" when finished entering new pets.");
                while (doneEntering == false) {
                    Scanner petEntry = new Scanner(System.in);
                    System.out.print("Add pet (\"name age\" ): ");
                    String strEntry = petEntry.nextLine();
                    //System.out.println("DEBUG: user entered " + strEntry);
                    
                    if (strEntry.equals("done")) {
                        doneEntering = true;
                    }
                    else if (pets.size() >= 5) {
                        System.out.println("Database only supports 5 entries.\n");
                        doneEntering = true;
                    }
                    else {
                        try {
                            String[] arrEntry = strEntry.split(" ");
                            String name = arrEntry[0];
                            try {
                                int age = Integer.parseInt(arrEntry[1]);
                                if (1 > age || 20 < age) {
                                    System.out.println("Valid age range is 1-20");
                                }
                                else {
                                    pets.add(new Pet(name, age));
                                }
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Enter the age as an integer");
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Not a valid input.");
                        } 
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
            else if (userChoice == 4) {
                displayFullPetTable(pets);
                
                Scanner updateId = new Scanner(System.in);
                
                System.out.println("");
                System.out.print("Enter the ID of the pet you would like to update: ");
                int petToUpdate = updateId.nextInt();
                if ((!(petToUpdate == (int)petToUpdate)) || petToUpdate < 0 || petToUpdate > pets.size() - 1) {
                    System.out.println("That is not a valid ID.");
                }
                else {
                    Scanner updatePet = new Scanner(System.in);
                    System.out.print("Enter the updated name and age for this pet: ");
                    String strEntry = updatePet.nextLine();
                    String[] arrEntry = strEntry.split(" ");
                    pets.get(petToUpdate).setName(arrEntry[0]);
                    pets.get(petToUpdate).setAge(Integer.parseInt(arrEntry[1]));
                
                    System.out.println("Here is the updated table");
                    displayFullPetTable(pets);
                }
                
            }
            else if (userChoice == 5) {
                displayFullPetTable(pets);
                
                Scanner removePet = new Scanner(System.in);
                System.out.println("");
                System.out.print("Enter the ID of the pet you would like to remove: ");
                int petToRemove = removePet.nextInt();
                if ((!(petToRemove == (int)petToRemove)) || petToRemove < 0 || petToRemove > pets.size() - 1) {
                    System.out.println("That is not a valid ID.");
                }
                else {
                    pets.remove(petToRemove);
                    System.out.println("Here is the updated table");
                    displayFullPetTable(pets);
                }
                
                
            }
        }
        
        saveToFile(filename, pets);
    }

    public static void displayFullPetTable(ArrayList<Pet> pets) {
        displayPetHeader();
        for (int k = 0; k < pets.size(); k++) {
            displayPetRow(k, pets);
        }
        displayPetFooter(pets);
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
    
    public static void saveToFile(String filename, ArrayList<Pet> pets) {
        try {
            FileWriter petWriter = new FileWriter(filename);
            for (Pet p : pets) {
                petWriter.write(p.getName() + " " + p.getAge() + "\n");
            }
            petWriter.close();
            System.out.println("Saved pets to file successfully");
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Pet> loadFromFile(String filename) {
        ArrayList<Pet> pets = new ArrayList<>();
        try {
            File petFile = new File(filename);
            Scanner petFileScanner = new Scanner(petFile);
            while (petFileScanner.hasNextLine()) {
                String pet = petFileScanner.nextLine();
                String[] arrEntry = pet.split(" ");
                pets.add(new Pet(arrEntry[0], Integer.parseInt(arrEntry[1])));
            }
            petFileScanner.close();
            return pets;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return pets;
    }
}
