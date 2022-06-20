import java.util.Scanner;

public class runContacts {

    public static void main(String[] args) {

        menuSystem();

    }

    public static void menuSystem() {
        // menu system for contacts - a do while loop

        boolean invalid = true;

        do {

            System.out.println("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5):");
            System.out.println();
            Scanner scanner = new Scanner(System.in);

            String userInput = scanner.next().toString();

            switch (userInput) {
                case "1":                                       // View contacts
                    System.out.println("Case 1");
                    break;
                case "2":                                       // Add new contact
                    System.out.println("Case 2");
                    break;
                case "3":                                       // Search for contact
                    System.out.print("Please enter search term: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String nameSearch = scanner2.next().toLowerCase();
                    searchContact(nameSearch);
                    break;
                case "4":                                       // Delete contact
                    System.out.println("Case 4");
                    break;
                case "5":                                       // Exit
                    System.out.println("Thanks");
                    invalid = false;
                    break;
                default:
                    System.out.println("Sorry, invalid input, please try again");
                    break;
            }
        } while (invalid);
    }
    public static String searchContact(String searchTerm) {
        String maryString = "Mary had a little lamb";
        System.out.println(maryString.toLowerCase().indexOf(searchTerm));
        return searchTerm;
    }
}
