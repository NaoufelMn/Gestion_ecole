import java.util.Scanner;

public class Administrateur {
    public static void displayMenutwo() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAdministrateur Menu:");
            System.out.println("1. Apprenant");
            System.out.println("2. Classes");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Redirecting to Apprenant Menu...");
                        Apprenant.displayMenuthree();
                        break;
                    case 2:
                        System.out.println("Redirecting to Classe Menu...");
                        Classe.displayMenufour();
                        break;
                    case 3:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer choice.");
                scanner.next(); // Clear the invalid input from the buffer
                choice = 0; // Reset choice to force re-prompting
            }
        } while (choice != 3);

        scanner.close();
    }

    public static void display() {
        System.out.println("\nWelcome to the Administrateur area!");
        displayMenutwo();
    }
}
