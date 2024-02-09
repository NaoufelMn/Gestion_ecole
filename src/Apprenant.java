import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Apprenant {
    // List to store details of apprenants
    private static List<ApprenantDetails> apprenants = new ArrayList<>();
    // ID counter for assigning unique IDs to apprenants
    private static int nextID = 1;
    // Constants for minimum and maximum age
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 30;

    // Method to display the main menu
    public static void displayMenuthree() {
        Scanner scanner = new Scanner(System.in);
        boolean continueShowingMenu = true;

        // Displaying menu options and handling user input
        do {
            System.out.println("\nApprenant Menu:");
            System.out.println("1. Add Apprenant");
            System.out.println("2. Modify Apprenant");
            System.out.println("3. Delete Apprenant");
            System.out.println("4. View the List");
            System.out.println("5. Search for an Apprenant");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addApprenant(scanner);
                        break;
                    case 2:
                        modifyApprenant(scanner);
                        break;
                    case 3:
                        deleteApprenant(scanner);
                        break;
                    case 4:
                        displayApprenantList();
                        break;
                    case 5:
                        searchApprenant(scanner);
                        break;
                    case 6:
                        returnToMainMenu();
                        continueShowingMenu = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer choice.");
                scanner.next(); // Clear the invalid input from the buffer
            }
        } while (continueShowingMenu);

        scanner.close();
    }

    // Method to add a new apprenant
    private static void addApprenant(Scanner scanner) {
        System.out.println("Adding Apprenant...");
        scanner.nextLine(); // Consume newline character left after previous nextInt() call
        System.out.print("Enter Apprenant Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Apprenant FamilyName: ");
        String familyName = scanner.nextLine();

        // Prompt for Date of Birth until a valid one is entered
        String dob;
        boolean validDOB;
        do {
            System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
            dob = scanner.nextLine(); // Read entire line for date of birth
            validDOB = isValidDateOfBirth(dob);
            if (!validDOB) {
                System.out.println("Invalid Date of Birth. Please enter a valid date in the format DD/MM/YYYY.");
            }
        } while (!validDOB);

        System.out.print("Enter Adresse: ");
        String adresse = scanner.nextLine();

        // Prompt for Phone Number until a valid one is entered
        String phoneNumber;
        boolean validPhoneNumber;
        do {
            System.out.print("Enter Phone Number: ");
            phoneNumber = scanner.nextLine();
            validPhoneNumber = isValidPhoneNumber(phoneNumber);
            if (!validPhoneNumber) {
                System.out.println("Invalid Phone Number. Please enter a valid number starting with 07, 06, or 05 and containing 10 digits.");
            }
        } while (!validPhoneNumber);

        System.out.print("Enter Classe Number: ");
        int classeNumber = scanner.nextInt();

        // Assign the next available ID
        int id = nextID++;

        // Create and add the new apprenant to the list
        ApprenantDetails newApprenant = new ApprenantDetails(id, name, familyName, dob, adresse, phoneNumber, classeNumber);
        apprenants.add(newApprenant);
        System.out.println("Apprenant added successfully! ID: " + id);
    }

    // Method to validate phone number format
    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("(07|06|05)\\d{8}"); // Check if the phone number starts with 07, 06, or 05 and has 10 digits in total
    }

    // Method to validate date of birth format and age
    private static boolean isValidDateOfBirth(String dob) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Disable leniency in parsing (strict parsing)
        try {
            Date parsedDate = dateFormat.parse(dob);
            // Calculate minimum and maximum birth years
            Calendar minCal = Calendar.getInstance();
            minCal.add(Calendar.YEAR, -MAX_AGE); // Maximum age from current year
            Calendar maxCal = Calendar.getInstance();
            maxCal.add(Calendar.YEAR, -MIN_AGE); // Minimum age from current year
            // Set the minimum date to January 1st of the minimum birth year
            minCal.set(Calendar.MONTH, Calendar.JANUARY);
            minCal.set(Calendar.DAY_OF_MONTH, 1);
            // Set the maximum date to December 31st of the maximum birth year
            maxCal.set(Calendar.MONTH, Calendar.DECEMBER);
            maxCal.set(Calendar.DAY_OF_MONTH, 31);
            // Check if the parsed date falls between the calculated minimum and maximum dates
            return parsedDate.after(minCal.getTime()) && parsedDate.before(maxCal.getTime());
        } catch (ParseException e) {
            return false;
        }
    }

    // Method to display the list of apprenants
    private static void displayApprenantList() {
        if (apprenants.isEmpty()) {
            System.out.println("There are no apprenants in the list.");
        } else {
            System.out.println("\nApprenant List:");
            System.out.println("+----+--------------+--------------+--------------+--------------+--------------+--------------+");
            System.out.printf("| %-2s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n", "ID", "Name", "Family Name", "Date of Birth", "Adresse", "Phone Number", "Classe Number");
            System.out.println("+----+--------------+--------------+--------------+--------------+--------------+--------------+");

            // Display details of each apprenant in the list
            for (ApprenantDetails apprenant : apprenants) {
                System.out.printf("| %-2d | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |\n",
                        apprenant.id,
                        apprenant.name,
                        apprenant.familyName,
                        apprenant.dob,
                        apprenant.adresse,
                        apprenant.phoneNumber,
                        apprenant.classeNumber);
            }
            System.out.println("+----+--------------+--------------+--------------+--------------+--------------+--------------+");
        }
    }

    // Method to modify details of an existing apprenant
    private static void modifyApprenant(Scanner scanner) {
        // Check if there are apprenants in the list
        if (apprenants.isEmpty()) {
            System.out.println("There are no apprenants in the list.");
            return;
        }

        System.out.print("Enter the ID of the Apprenant you want to modify: ");
        int id = scanner.nextInt();

        ApprenantDetails selectedApprenant = null;
        for (ApprenantDetails apprenant : apprenants) {
            if (apprenant.id == id) {
                selectedApprenant = apprenant;
                break;
            }
        }

        if (selectedApprenant == null) {
            System.out.println("Apprenant with ID " + id + " not found.");
            return;
        }

        // Display details of the selected apprenant
        System.out.println("Selected Apprenant: ");
        displayApprenantDetails(selectedApprenant);

        // Prompt for modification choice
        System.out.println("\nChoose an action:");
        System.out.println("1. Modify individual attribute");
        System.out.println("2. Modify all attributes");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                // Modify individual attribute
                modifyIndividualAttribute(scanner, selectedApprenant);
                break;
            case 2:
                // Modify all attributes
                modifyAllAttributes(scanner, selectedApprenant);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("Apprenant details modified successfully!");
    }

    // Method to modify individual attribute of an apprenant
    private static void modifyIndividualAttribute(Scanner scanner, ApprenantDetails selectedApprenant) {
        // Prompt for attribute choice
        System.out.println("Choose an attribute to modify:");
        System.out.println("1. Name");
        System.out.println("2. Family Name");
        System.out.println("3. Date of Birth");
        System.out.println("4. Adresse");
        System.out.println("5. Phone Number");
        System.out.println("6. Classe Number");
        System.out.print("Enter your choice: ");
        int attributeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (attributeChoice) {
            case 1:
                System.out.print("Enter new Name: ");
                selectedApprenant.name = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter new Family Name: ");
                selectedApprenant.familyName = scanner.nextLine();
                break;
            case 3:
                System.out.print("Enter new Date of Birth (DD/MM/YYYY): ");
                selectedApprenant.dob = scanner.nextLine();
                break;
            case 4:
                System.out.print("Enter new Adresse: ");
                selectedApprenant.adresse = scanner.nextLine();
                break;
            case 5:
                System.out.print("Enter new Phone Number: ");
                selectedApprenant.phoneNumber = scanner.nextLine();
                break;
            case 6:
                System.out.print("Enter new Classe Number: ");
                selectedApprenant.classeNumber = scanner.nextInt();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Method to modify all attributes of an apprenant
    private static void modifyAllAttributes(Scanner scanner, ApprenantDetails selectedApprenant) {
        System.out.print("Enter new Name: ");
        selectedApprenant.name = scanner.nextLine();
        System.out.print("Enter new Family Name: ");
        selectedApprenant.familyName = scanner.nextLine();
        System.out.print("Enter new Date of Birth (DD/MM/YYYY): ");
        selectedApprenant.dob = scanner.nextLine();
        System.out.print("Enter new Adresse: ");
        selectedApprenant.adresse = scanner.nextLine();
        System.out.print("Enter new Phone Number: ");
        selectedApprenant.phoneNumber = scanner.nextLine();
        System.out.print("Enter new Classe Number: ");
        selectedApprenant.classeNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
    }

    // Method to delete an apprenant
    private static void deleteApprenant(Scanner scanner) {
        if (apprenants.isEmpty()) {
            System.out.println("There are no apprenants in the list.");
            return;
        }

        System.out.print("Enter the ID of the Apprenant you want to delete: ");
        int id = scanner.nextInt();

        boolean found = false;
        for (int i = 0; i < apprenants.size(); i++) {
            if (apprenants.get(i).id == id) {
                apprenants.remove(i);
                found = true;
                System.out.println("Apprenant with ID " + id + " deleted successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Apprenant with ID " + id + " not found.");
        }
    }

    // Method to search for an apprenant
    private static void searchApprenant(Scanner scanner) {
        if (apprenants.isEmpty()) {
            System.out.println("There are no apprenants in the list.");
            return;
        }

        System.out.println("Search Apprenant:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name and Family Name");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();

        switch (searchChoice) {
            case 1:
                searchById(scanner);
                break;
            case 2:
                searchByNameAndFamilyName(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Method to search for an apprenant by ID
    private static void searchById(Scanner scanner) {
        System.out.print("Enter the ID of the Apprenant you want to search for: ");
        int id = scanner.nextInt();

        boolean found = false;
        for (ApprenantDetails apprenant : apprenants) {
            if (apprenant.id == id) {
                displayApprenantDetails(apprenant);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Apprenant with ID " + id + " not found.");
        }
    }

    // Method to search for an apprenant by Name and Family Name
    private static void searchByNameAndFamilyName(Scanner scanner) {
        System.out.print("Enter the Name of the Apprenant you want to search for: ");
        String name = scanner.next();
        System.out.print("Enter the Family Name of the Apprenant you want to search for: ");
        String familyName = scanner.next();

        boolean found = false;
        for (ApprenantDetails apprenant : apprenants) {
            if (apprenant.name.equalsIgnoreCase(name) && apprenant.familyName.equalsIgnoreCase(familyName)) {
                displayApprenantDetails(apprenant);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Apprenant with Name '" + name + "' and Family Name '" + familyName + "' not found.");
        }
    }

    // Method to return to the main menu
    private static void returnToMainMenu() {
        Main.main(new String[]{});
    }

    // Method to display details of an apprenant
    private static void displayApprenantDetails(ApprenantDetails apprenant) {
        System.out.println("Apprenant Details:");
        System.out.println("ID: " + apprenant.id);
        System.out.println("Name: " + apprenant.name);
        System.out.println("Family Name: " + apprenant.familyName);
        System.out.println("Date of Birth: " + apprenant.dob);
        System.out.println("Adresse: " + apprenant.adresse);
        System.out.println("Phone Number: " + apprenant.phoneNumber);
        System.out.println("Classe Number: " + apprenant.classeNumber);
    }

    // Inner class to represent details of an apprenant
    static class ApprenantDetails {
        int id;
        String name;
        String familyName;
        String dob;
        String adresse;
        String phoneNumber;
        int classeNumber;

        // Constructor to initialize apprenant details
        public ApprenantDetails(int id, String name, String familyName, String dob, String adresse, String phoneNumber, int classeNumber) {
            this.id = id;
            this.name = name;
            this.familyName = familyName;
            this.dob = dob;
            this.adresse = adresse;
            this.phoneNumber = phoneNumber;
            this.classeNumber = classeNumber;
        }
    }
}
