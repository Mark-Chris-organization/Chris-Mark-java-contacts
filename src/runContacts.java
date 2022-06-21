import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.nio.file.Files.createFile;

public class runContacts {

    public static void main(String[] args) {

        createFileSystem();                         // create directory and contacts file
        menuSystem();                               // operates CLI menu system

    }

    // TODO: Menu System
    public static void menuSystem() {
        // menu system for contacts - a do while loop

        boolean loopAgain = true;

        do {

            System.out.print("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search for a contact.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5): ");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.next();       // Getting input as a String

            switch (userInput) {
                case "1" ->                                       // View contacts
                    displayContacts();

                case "2" -> {                                     // Add new contact
                    addContact();
                    System.out.println();
                }
                case "3" -> {                                     // Search for contact
                    searchContacts();
                    System.out.println();
                }
                case "4" ->                                       // Delete contact
                    deleteContacts();

                case "5" -> {                                     // Exit
                    System.out.println("Thanks");
                    loopAgain = false;
                }
                default -> System.out.println("Sorry, invalid input, please try again");
            }
        } while (loopAgain);
    }

    // TODO: add contacts
    public static void addContact() {

        // string names for path and filename
        String dirName = "data";
        String filename = "contacts.txt";

        try {

            // Using static method to get filepath for directory.
            Path dataDirectory = Paths.get(dirName);
            // Using static method to get filepath within directory stated above.
            Path dataFile = Paths.get(dirName, filename);

            System.out.print("Please enter First Name: ");              // Ask first name
            Scanner scannerFirstName = new Scanner(System.in);
            String firstName = scannerFirstName.next();

            System.out.print("Please enter Last Name: ");               // Ask last name
            Scanner scannerLastName = new Scanner(System.in);
            String lastName = scannerLastName.next();

            System.out.print("Please enter Phone Number: ");            // Ask phone number
            Scanner scannerPhoneNumber = new Scanner(System.in);
            String phoneNumber = scannerPhoneNumber.next();

            String fullName = firstName + " " + lastName;

            // format the contact being placed into contacts.txt
            String contactLine = addSpaces(fullName) + " | " + phoneNumber;

            if(Files.exists(dataFile)) {
                // Files.write(Path path, List<> data)
                System.out.println("Writing to contacts.txt file!");
                // Add a new name to the file.
                Files.write(dataFile, Arrays.asList(contactLine), StandardOpenOption.APPEND);

            }
        } catch(IOException iox) {
            iox.printStackTrace();
        }
    }

    // Method to add spaces to full name for output formatting
    public static String addSpaces (String fullName) {

        int gap = 21 - fullName.length();
        String gapString = "";

        switch (gap) {
            case 14 ->
                    gapString = "              ";
            case 13 ->
                    gapString = "             ";
            case 12 ->
                    gapString = "            ";
            case 11 ->
                    gapString = "           ";
            case 10 ->
                    gapString = "          ";
            case 9 ->
                    gapString = "         ";
            case 8 ->
                    gapString = "        ";
            case 7 ->
                    gapString = "       ";
            case 6 ->
                    gapString = "      ";
            case 5 ->
                    gapString = "     ";
            case 4 ->
                    gapString = "    ";
            case 3 ->
                    gapString = "   ";
            case 2 ->
                    gapString = "  ";
            case 1 ->
                    gapString =" ";
        }
        return fullName + gapString;
    }

    // TODO: display contacts
    public static void displayContacts() {

        // string names for path and filename
        String dirName = "data";
        String filename = "contacts.txt";

        try {

            // Create a list of strings that represent the file data.
            List<String> fileData = Files.readAllLines(Paths.get(dirName, filename));

            // Print header
            System.out.println("         Name         |       Number");
            System.out.println("----------------------|-------------------");

            //Print out each line in the contacts.txt file
            for (String line: fileData) {
                System.out.println(line);
            }
            System.out.println();

        } catch( IOException iox) {
            iox.printStackTrace();
        }
    }

    public static void createFileSystem() {

        String dirName = "data";
        String filename = "contacts.txt";

        try {

            // Using static method to get filepath for directory.
            Path dataDirectory = Paths.get(dirName);
            // Using static method to get filepath within directory stated above.
            Path dataFile = Paths.get(dirName, filename);

            // Files class - contains static methods to manipulate files.
            // Files.exists() - returns boolean
            // Files.notExists() - returns boolean

            // Create a directory if it does not exist.
            if(Files.notExists(dataDirectory)) {
                System.out.println("Creating new directory named: " + dataDirectory);
//                 Files.createDirectories(Path path) - returns a Path
                Files.createDirectories(dataDirectory); // accepts a Path object
            }
            // Create a file based on the path of the file.
            if(!Files.exists(dataFile)) {
                System.out.println("Your new file has been created!");
                // Files.createFile(Path path) - returns a Path
                createFile(dataFile);
            }

        } catch(IOException iox) {
            iox.printStackTrace();
        }
    }

    // TODO: search contacts
    public static void searchContacts() {

        // string names for path and filename
        String dirName = "data";
        String filename = "contacts.txt";

        try {

            // Prompt for search item
            System.out.print("Please enter search item: ");
            Scanner scannerSearch = new Scanner(System.in);
            String searchItem = scannerSearch.next();

            // Create a list of strings that represent the file data.
            List<String> fileData = Files.readAllLines(Paths.get(dirName, filename));

            //Print out each line in the contacts.txt file
            boolean searchItemFound = false;
            for (String line: fileData) {
                if (line.toLowerCase().contains(searchItem)) {
                    System.out.println(line);
                    searchItemFound = true;
                }
            }
            if (!searchItemFound) {
                System.out.println("Sorry, search item not found.");
            }

        } catch( IOException iox) {
            iox.printStackTrace();
        }
    }

    // TODO: delete contacts
    public static void deleteContacts() {

        // string names for path and filename
        String dirName = "data";
        String filename = "contacts.txt";

        try {

            // Prompt for search item
            System.out.print("To delete, please enter search item: ");
            Scanner scannerSearchDelete = new Scanner(System.in);
            String searchDeleteItem = scannerSearchDelete.next();

            // Create a list of strings that represent the file data.
            List<String> fileData = Files.readAllLines(Paths.get(dirName, filename));

            // New list to dump appropriate data.
            List<String> newList = new ArrayList<>();

            // Loop thru each line in the contacts.txt file searching for searchDeleteItem
            boolean searchItemFound = false;
            for (String line: fileData) {

                // Ask if found item/line is the info to delete
                if (line.toLowerCase().contains(searchDeleteItem)) {
                    System.out.printf("Found search info: %s  -  ", line);
                    System.out.print("Is this the information you want to delete?(Y/n):");
                    Scanner askDelete = new Scanner(System.in);
                    String deleteYorN = askDelete.next().toLowerCase();

                    // If item found is the one to delete, skip over adding to newList
                    if (deleteYorN.equals("y")) {
                        continue;
                    }
                    searchItemFound = true;
                }
                // otherwise add line to newList
                newList.add(line);
            }

            if (!searchItemFound) {
                System.out.println("Sorry, search item not found.");
            } else {
                // Overwrite file with updated contents
                Files.write(Paths.get(dirName, filename), newList);
                // Re-assign list to updated file contents.
//                fileData = Files.readAllLines(Paths.get(dirName, filename));
            }

        } catch( IOException iox) {
            iox.printStackTrace();
        }
    }
}
