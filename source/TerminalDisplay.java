import java.util.Scanner;

public class TerminalDisplay {

    private static final PlateformeTracker tracker = new PlateformeTracker();
    private static final Scanner input = new Scanner(System.in);

    public void userAccount() {

        // Ask the user if he has an account
        System.out.print("Do you have an account with us (Y/N)? ");
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

            // Check login credentials
            boolean loginSuccessful = Login.checkLoginCredentials(userLogin, userPassword);

            if (loginSuccessful) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid email or password.");
            }
        } else if (inputAccount.equalsIgnoreCase("N")) {

            // Ask the user to enter his account details
            System.out.print("Please enter your email: ");
            userLogin = input.nextLine();
            System.out.print("Please enter your password: ");
            userPassword = input.nextLine();
            int studentID = 0;

            // Hash the user's password
            String hashedNewPassword = Login.hashPassword(userPassword);

            // Register the new user using the method in Login class
            boolean registrationSuccessful = tracker.registerUser(studentID, userLogin, hashedNewPassword);

            if (registrationSuccessful) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed. Try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
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
                    "       ║ [5] Search student by ID ║  [6] Choose a filter      ║\n" +
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
                    displayFilters(); // Section to test methods
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

    // --- FILTER ---//

    public void displayFilters() {
        int choice = -2;
        do {
            System.out.print("\n" +
                    "       ╔═════════════════════ FILTER SECTION ═══════════════════╗\n" +
                    "       ║                                                        ║\n" +
                    "       ║ [1] Sorting students       ║  [2] Advanced search      ║\n" +
                    "       ║ [3] Statistics             ║  [4] Data import/export   ║\n" +
                    "       ║ [5] Pagination             ║  [6]  Export results      ║\n" +
                    "       ║                                                        ║\n" +
                    "       ║                                                        ║\n" +
                    "       ║ [0]  Quit                                              ║\n" +
                    "       ╚════════════════════════════════════════════════════════╝\n");

            do {
                System.out.print("> Chose a filter option : ");
                String inputString = input.next();
                if (inputString.matches("[0-6]")) {
                    choice = Integer.parseInt(inputString);
                    input.nextLine();
                } else {
                    choice = -1;
                    System.out.print("Please enter a number between 0 et 6 \n");
                }

            } while (choice < 0 || choice > 6);

            switch (choice) {

                case 1:
                    int choiceSortingStudent = filterSortingStudents();
                    switch (choiceSortingStudent) {
                        case 1:
                            filterStudentsByFirstName(); // sort by first name
                            break;
                        case 2: 
                            filterStudentsByLastName();  // sort by last name
                            break;
                        case 3: 
                            filterStudentsByAge(); // sort by age
                        case 4: 
                            filterStudentsByField(); // sort by field
                        }

                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Thanks for using La Plateforme Tracker. Goodbye !");
                    break;
                default:
                    System.out.println("ERROR. Filter option not available.");
                    break;
            }
        } while (choice != 0);
    }

    public int filterSortingStudents() {

        // Display header for filtering by first name
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                      SORTING STUDENTS                 ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "\n[1] Sort by first Name\n" +
                        "[2] Sort by last Name \n" +
                        "[3] Sort by age \n" +
                        "[4] Sort by field \n" +
                        "> Please your filter option: ");
        int filterSortingStudents = input.nextInt();
        input.nextLine();

        return filterSortingStudents;
    }

    // Method to filter by first name
    public void filterStudentsByFirstName() {

        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                "║                 FILTER STUDENTS BY FIRST NAME         ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n" +
                "> Enter the first name to filter students:");

        String filterName = input.nextLine();

        System.out.println("Students with the first name: " + filterName);

    }

    // Method to filter by last name
    public void filterStudentsByLastName() {
          System.out.print(
            "╔═══════════════════════════════════════════════════════╗\n" +
            "║                 FILTER STUDENTS BY LAST NAME          ║\n" +
            "╚═══════════════════════════════════════════════════════╝\n" +
            "> Enter the last name to filter students: ");

    }


    // Method to filter by age
    public void filterStudentsByAge() {

        System.out.print(
          "╔═══════════════════════════════════════════════════════╗\n" +
          "║                 FILTER STUDENTS BY AGE                ║\n" +
          "╚═══════════════════════════════════════════════════════╝\n" +
          "> Do you want to sort the students by ascending [1] or descending age [2] ?");
          int choiceAscDesc = input.nextInt(); 
          input.nextLine(); 

        // ! debug
        System.out.println("Your choice is: " + choiceAscDesc );
    }

  // Method to filter by field
  public void filterStudentsByField() {

    // Display header for filtering by last name
    System.out.print(
      "╔═══════════════════════════════════════════════════════╗\n" +
      "║                FILTER STUDENTS BY FIELD               ║\n" +
      "╚═══════════════════════════════════════════════════════╝\n" +      
      "> Which specialty's student list do you want ? \n"+
      "[1] Software \n"+
      "[2] Cyber \n"+
      "[3] IA \n"+
      "[4] Web \n"+
      "[5] DPO \n");

      int choiceField = input.nextInt(); 
      input.nextLine(); 

    // ! debug 
    System.out.println("Your choice is: " + choiceField );

    }

}
