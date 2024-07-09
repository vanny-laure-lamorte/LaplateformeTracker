import java.util.Scanner;

public class TerminalDisplay {

    private static PlateformeTracker tracker = new PlateformeTracker();
    private static Scanner input = new Scanner(System.in);

    public void homeDisplay() {
        int choice = -1;
        do {
            System.out.print("\n" +
                    "       ╔════════════════ Gestion Commerciale ═════════════════╗\n" +
                    "       ║                                                      ║\n" +
                    "       ║ [1] Display Students     ║  [2] Add a new student    ║\n" +
                    "       ║ [2] Modify student       ║  [3]                      ║\n" +
                    "       ║ [4]                      ║  [5]                      ║\n" +
                    "       ║                                                      ║\n" +
                    "       ║                                                      ║\n" +
                    "       ║ [0]  Quit                                            ║\n" +
                    "       ╚══════════════════════════════════════════════════════╝\n");
            do {
                System.out.print("Enter a choice : ");
                String inputString = input.next();
                if (inputString.matches("[0-6]")) {
                    choice = Integer.parseInt(inputString);
                } else {
                    choice = -1;
                    System.out.print("Please enter a number between 0 et 6 \n");
                }

            } while (choice < 0 || choice > 6);

            switch (choice) {
                case 1:
                    tracker.displayStudent();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choice not yet implemanted.");
                    break;
            }
        } while (choice != 0);
        input.close();

    };

    public void displayAddStudent() {
        // --- ADD NEW USER ---//
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                    ADD A NEW STUDENT                  ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");
        System.out.print("> Enter student's first name: ");
        String newFirstName = input.nextLine();
        System.out.print("> Enter student's last name: ");
        String newLastName = input.nextLine();
        System.out.print("> Enter student's age: ");
        int newAge = input.nextInt();
        input.nextLine();
        System.out.print("> Enter student's field: ");
        String newField = input.nextLine();
        double newAverageGrade = 0;

        try {

            int result = tracker.addStudent(newFirstName, newLastName, newAge, newField, newAverageGrade);

            if (result != 0) {

                System.out.println(
                        "First Name: " + newFirstName +
                                " | Last Name: " + newLastName +
                                " | Field: " + newField +
                                " | Age : " + newAge + "\n");
            } else {

                System.out.println("ERROR. no student added.");
            }
        }

        catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }

    }

    public void displayModifyStudent() {
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                   UPDATE STUDENT INFO                 ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");

        // Display options the user can modify
        tracker.displayStudent();

        // Ask the user to select a student by his Id
        System.out.print("> Please choose the student Id: ");
        int studentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String studentSelected = tracker.getStudentById(studentId);
        System.out.println("Selected student: " + studentSelected + "\n");

        System.out.print("> Do you want to modify " + studentSelected + "'s information (Y/N) ? ");
        String inputStudentUpdate = input.nextLine();

        // Loop to ensure a valid input is provided
        if (inputStudentUpdate.equalsIgnoreCase("Y")) {

            int infoToModify = 0;
            boolean validInput = false;
            while (!validInput) {

                System.out.print(
                        "\n[1] First Name\n" +
                                "[2] Last Name \n" +
                                "[3] Age \n" +
                                "[4] Field \n" +
                                "> Please choose the information you wish to modify: ");
                infoToModify = input.nextInt();
                input.nextLine();

                // Check is the user input is valid
                if (infoToModify >= 1 && infoToModify <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
                }
            }

            // Update the database based on the user choice
            switch (infoToModify) {
                case 1:
                    System.out.print("> Enter new first name: ");
                    String updateFirstName = input.nextLine();
                    tracker.updateFirstName(studentId, updateFirstName);
                    System.out.println("First name updated successfully! \n");
                    break;
                case 2:
                    System.out.print("> Enter new last name: ");
                    String updateLastName = input.nextLine();
                    tracker.updateLastName(studentId, updateLastName);
                    System.out.println("Last name updated successfully! \n");
                    break;
                case 3:
                    System.out.print("> Enter new age: ");
                    int updateAge = input.nextInt();
                    tracker.updateAge(studentId, updateAge);
                    System.out.println("Age updated successfully! \n");
                    break;
                case 4:
                    System.out.print("> Enter new field: ");
                    String updateField = input.nextLine();
                    tracker.updateField(studentId, updateField);
                    System.out.println("Field updated successfully! \n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } else {
            System.out.println("ERROR. No update performed.");
        }

    }
}
