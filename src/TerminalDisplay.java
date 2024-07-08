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
}
