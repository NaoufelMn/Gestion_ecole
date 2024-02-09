import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean continueShowingMenu = true;

        do {
            displayMainMenu();
            if (scanner.hasNextInt()) { //We check if the next input token is an integer using scanner.hasNextInt()
                choice = scanner.nextInt();//If it's an integer, we read the integer choice using scanner.nextInt()
                switch (choice) {
                    case 1:
                        continueShowingMenu = login(scanner); // Update continueShowingMenu based on login result
                        break;
                    case 2:
                        System.out.println("Exiting program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer choice.");
                scanner.next(); // Clear the invalid input from the buffer
                choice = 0; //  Then, we set choice to a default value (0 in this case) to avoid an infinite loop.
            }
        } while (choice != 2 && continueShowingMenu);

        // Close the Scanner object after the entire application has finished executing
        scanner.close();
    }

    public static boolean login(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            System.out.println("Welcome, " + email + "!");
            Administrateur.display();
            return false; // Stop showing the menu after successfully logging in
        } else {
            System.out.println("Incorrect email or password. Please try again.");
            return true; // Continue showing the menu
        }
    }

    public static void displayMainMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }
}
