import java.util.Scanner;

public class StudentDisplay {
    private static final StudentRepository tracker = new StudentRepository();
    private static final Scanner input = new Scanner(System.in);

    public static void displayTitleStudentInfo() {
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                       STUDENT INFO                    ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");
    }

    public static void displayAddStudent() {
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
        double newAverageGrade = 0; // Assuming this is set later or not needed in addStudent
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

        // Ask the user to select a student by their Id
        System.out.print("> Please choose the student Id: ");
        int studentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String studentSelected = tracker.getStudentNameById(studentId);
        System.out.println("Selected student: " + studentSelected + "\n");

        String inputStudentUpdate;
        do {
            System.out.print("> Do you want to modify " + studentSelected + "'s information (Y/N) ? ");
            // Loop to ensure a valid input is provided
            inputStudentUpdate = input.nextLine();

            if (inputStudentUpdate.equalsIgnoreCase("Y")) {

                int infoToModify = 0;

                // Check if the user input is valid
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
                        input.nextLine();
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
                        "║ DELETE STUDENT ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");

        // Display all students the user can delete
        tracker.displayStudent();

        // Ask the user to select a student to delete by their Id
        System.out.print("> Please choose the student Id: ");
        int deleteStudentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String deleteStudentSelected = tracker.getStudentNameById(deleteStudentId);
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

    public static void displaySearchStudent() {
        System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║               SEARCH A STUDENT BY THEIR ID            ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n");
        // Ask the user to choose the ID of a student
        System.out.print("> Please enter the student's ID: ");
        int searchStudentID = input.nextInt();
        input.nextLine();

        // Retrieve student information by ID
        Student student = new Student(searchStudentID);

        // Display the student's information if found
        if (student != null) {
            System.out.println("Student found:\n" +
                    "Name: " + student.getFirstName() + " " + student.getLastName() +
                    " | Age: " + student.getAge() +
                    " | Field: " + student.getField() + "\n");
        } else {
            System.out.println("Student with ID " + searchStudentID + " not found.\n");
        }
    }
}