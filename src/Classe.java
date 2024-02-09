import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Classe {
    private static List<ClasseDetails> classes = new ArrayList<>();
    private static int nextID = 1;

    public static void displayMenufour() {
        Scanner scanner = new Scanner(System.in);
        boolean continueShowingMenu = true;

        while (continueShowingMenu) {
            System.out.println("\nClasse Menu:");
            System.out.println("1. Add Class");
            System.out.println("2. Modify Class");
            System.out.println("3. Delete Class");
            System.out.println("4. Display Class List");
            System.out.println("5. Search Class by ID");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addClass(scanner);
                    break;
                case 2:
                    modifyClass(scanner);
                    break;
                case 3:
                    deleteClass(scanner);
                    break;
                case 4:
                    displayClassList();
                    break;
                case 5:
                    searchClassByID(scanner);
                    break;
                case 6:
                    returnToMainMenu();
                    continueShowingMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        scanner.close();
    }

    private static void addClass(Scanner scanner) {
        System.out.println("Adding Class...");
        scanner.nextLine(); // Consume newline character left after previous nextInt() call
        System.out.print("Enter Class Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Numero de Classe: ");
        String numeroclass = scanner.nextLine();
        System.out.print("Enter Effectif: "); // Prompt for the effectif
        String effectif = scanner.nextLine(); // Read effectif input

        // Assign the next available ID
        int id = nextID++;

        ClasseDetails newClass = new ClasseDetails(id, name, numeroclass, effectif);
        classes.add(newClass);
        System.out.println("Class added successfully! ID: " + id);
    }


    private static void displayClassList() {
        if (classes.isEmpty()) {
            System.out.println("There are no classes in the list.");
        } else {
            System.out.println("\nClass List:");
            System.out.println("+----+--------------+------------------------+-----------+");
            System.out.printf("| %-2s | %-12s | %-22s | %-9s |\n", "ID", "Name", "Numero de Classe", "Effectif");
            System.out.println("+----+--------------+------------------------+-----------+");

            for (ClasseDetails classe : classes) {
                System.out.printf("| %-2d | %-12s | %-22s | %-9s |\n",
                        classe.id,
                        classe.name,
                        classe.numeroclass,
                        classe.effectif);
            }
            System.out.println("+----+--------------+------------------------+-----------+");
        }
    }


    private static void modifyClass(Scanner scanner) {
        if (classes.isEmpty()) {
            System.out.println("There are no classes in the list.");
            return;
        }

        System.out.print("Enter the ID of the Class you want to modify: ");
        int id = scanner.nextInt();

        ClasseDetails selectedClass = null;
        for (ClasseDetails classe : classes) {
            if (classe.id == id) {
                selectedClass = classe;
                break;
            }
        }

        if (selectedClass == null) {
            System.out.println("Class with ID " + id + " not found.");
            return;
        }

        System.out.println("Selected Class: ");
        System.out.println("ID: " + selectedClass.id);
        System.out.println("Name: " + selectedClass.name);
        System.out.println("Numero de Classe: " + selectedClass.numeroclass);
        System.out.println("Effectif De Classe"+selectedClass.effectif);

        System.out.println("\nChoose an action:");
        System.out.println("1. Modify Name");
        System.out.println("2. Modify Numero de classe");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.print("Enter new Name: ");
                selectedClass.name = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter new Numero de classe: ");
                selectedClass.numeroclass = scanner.nextLine();
                break;
            case 3:
                System.out.print("Entrer Numero deffectif : ");
                selectedClass.effectif = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("Class details modified successfully!");
    }

    private static void deleteClass(Scanner scanner) {
        if (classes.isEmpty()) {
            System.out.println("There are no classes in the list.");
            return;
        }

        System.out.print("Enter the ID of the Class you want to delete: ");
        int id = scanner.nextInt();

        boolean found = false;
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).id == id) {
                classes.remove(i);
                found = true;
                System.out.println("Class with ID " + id + " deleted successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Class with ID " + id + " not found.");
        }
    }

    private static void searchClassByID(Scanner scanner) {
        if (classes.isEmpty()) {
            System.out.println("There are no classes in the list.");
            return;
        }

        System.out.print("Enter the ID of the Class you want to search for: ");
        int id = scanner.nextInt();

        boolean found = false;
        for (ClasseDetails classe : classes) {
            if (classe.id == id) {
                displayClassDetails(classe);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Class with ID " + id + " not found.");
        }
    }

    private static void returnToMainMenu() {
        Main.main(new String[]{});
    }

    private static void displayClassDetails(ClasseDetails classe) {
        System.out.println("Class Details:");
        System.out.println("ID: " + classe.id);
        System.out.println("Name: " + classe.name);
        System.out.println("Numero de Classe: " + classe.numeroclass);
        System.out.println("Numero deffectif: " + classe.effectif);
    }

    static class ClasseDetails {
        int id;
        String name;
        String numeroclass;
        String effectif;

        public ClasseDetails(int id, String name, String numeroclass,String effectif) {
            this.id = id;
            this.name = name;
            this.numeroclass = numeroclass;
            this.effectif = effectif;
        }
    }
}
