import java.util.Scanner;

public class TerminalDisplay {

    private static final PlateformeTracker tracker = new PlateformeTracker();
    private static final Scanner input = new Scanner(System.in);

    public void userAccount() {

        // Ask the user if he has an account
        System.out.print("Do you have an account with us (Y/N) ? ");
        input.nextLine();
        String inputAccount = input.nextLine();

        // Get the user Password and Email
        String userPassword = "";
        String userLogin = "";

        if (inputAccount.equals("Y")) {
            System.out.print("Please enter your email: ");
            userLogin = input.nextLine();
            System.out.print("Please enter your password: ");
            userPassword = input.nextLine();
        }

        // Check login credentials
        boolean loginSuccessful = Login.checkLoginCredentials(userLogin, userPassword);

        if (loginSuccessful) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public void homeDisplay() {
        int choice = -1;
        do {
            System.out.print("\n" +
                    "       ╔══════════════ LA PLATEFORME TRACKER ═════════════════╗\n" +
                    "       ║                                                      ║\n" +
                    "       ║ [1] Display Student      ║  [2] Add a new student    ║\n" +
                    "       ║ [3] Update student info  ║  [4] Delete student       ║\n" +
                    "       ║ [5] Search student by ID ║  [6]                      ║\n" +
                    "       ║                                                      ║\n" +
                    "       ║                                                      ║\n" +
                    "       ║ [0]  Quit                                            ║\n" +
                    "       ╚══════════════════════════════════════════════════════╝\n");

            do {
                System.out.print("> Chose a menu option : ");
                String inputString = input.next();
                if (inputString.matches("[0-7]")) {
                    choice = Integer.parseInt(inputString);
                } else {
                    choice = -1;
                    System.out.print("Please enter a number between 0 et 6 \n");
                }

            } while (choice < 0 || choice > 7);

            switch (choice) {
                case 1:
                    displayTitleStudentInfo();
                    tracker.displayStudent();
                    break;
                case 2:
                    displayAddStudent();
                    break;
                case 3:
                    displayModifyStudent();
                    break;

                case 4:
                    displayDeleteStudent();
                    break;
                case 5:
                    displaySearchStudent();
                    break;
                case 6:
                    userAccount();
                    break;
                case 0:
                    System.out.println("Thanks for using La Plateforme Tracker. Goodbye !");
                    break;
                default:
                    System.out.println("ERROR. Option not available.");
                    break;
            }
        } while (choice != 0);
        input.close();
    }

    public void displayTitleStudentInfo() {
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                       STUDENT INFO                    ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");
    }

    public void displayAddStudent() {
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                    ADD A NEW STUDENT                  ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");
        input.nextLine(); // Consume the newline left-over
        System.out.print("> Enter student's first name: ");
        String newFirstName = input.nextLine();
        System.out.print("> Enter student's last name: ");
        String newLastName = input.nextLine();
        System.out.print("> Enter student's age: ");
        int newAge = input.nextInt();
        input.nextLine(); // Consume the newline
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
                                " | Age : " + newAge +
                                " | Average Grade: " + newAverageGrade + "\n");
            } else {
                System.out.println("ERROR: No student added.");
            }
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void displayModifyStudent() {
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

        String inputStudentUpdate;
        do {
            System.out.print("> Do you want to modify " + studentSelected + "'s information (Y/N) ? ");
            // Loop to ensure a valid input is provided
            inputStudentUpdate = input.nextLine();

            if (inputStudentUpdate.equalsIgnoreCase("Y")) {

                int infoToModify = 0;

                // Check is the user input is valid
                while (!InputValidator.isValidUpdateStudentInfo(String.valueOf(infoToModify))) {
                    System.out.print(
                            "\n[1] First Name\n" +
                                    "[2] Last Name \n" +
                                    "[3] Age \n" +
                                    "[4] Field \n" +
                                    "> Please choose the information you wish to modify: ");
                    infoToModify = input.nextInt();
                    input.nextLine();

                    if (infoToModify < 1 || infoToModify > 4) {
                        System.out.print("Invalid input! Please enter 1, 2, 3, or 4 \n");
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
            }
        } while (inputStudentUpdate.equalsIgnoreCase("Y"));

    }

    public static void displayDeleteStudent() {

        // Display title
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                      DELETE STUDENT                   ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");

        // Display all students the user can delete
        tracker.displayStudent();

        // Ask the user to select a student to delete by his Id
        System.out.print("> Please choose the student Id: ");
        int deleteStudentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String deleteStudentSelected = tracker.getStudentById(deleteStudentId);
        System.out.println("Selected student: " + deleteStudentSelected + "\n");

        // Ask confirmation before deleting the student
        String inputStudentDelete;
        System.out.print("> Delete " + deleteStudentSelected + "'s information permanently (Y/N)? \n");
        inputStudentDelete = input.nextLine();

        if (inputStudentDelete.equalsIgnoreCase("Y")) {
            tracker.deleteStudent(deleteStudentId);
            System.out.println("Student deleted successfully! ");
        }

    }

    public void displaySearchStudent() {
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║               SEARCH A STUDENT BY THEIR ID            ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");

        // Ask the user to choose the id of a student
        System.out.print("> Please enter the student's ID: ");
        int searchStudentID = input.nextInt();
        input.nextLine();

        // Display the student's information according to the id given
        System.out.println("\nDetails of the student with ID: " + searchStudentID);

    }
}
