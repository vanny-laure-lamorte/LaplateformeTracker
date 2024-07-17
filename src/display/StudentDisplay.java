import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDisplay extends HomeDisplay {
    private static final StudentRepository tracker = new StudentRepository();

    public StudentDisplay(Scanner input) {
        super(input);
    }

    public static void displayTitleStudentInfo() {
        String title = "╔═══════════════════════════════════════════════════════╗\n" +
                "║                       STUDENT INFO                    ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n";
        Frame.displayInFrame(title);
    }

    // Method to display all students
    public static void displayAllStudents() {
        int currentPage = 1;
        int pageSize = 5;
        String choice = "";

        do {
            Frame.clearScreen();
            StringBuilder displayText = new StringBuilder();
            displayText.append("╔═══════════════════════════════════════════════════════╗\n")
                    .append("║                    ALL STUDENTS                       ║\n")
                    .append("╚═══════════════════════════════════════════════════════╝\n\n");

            List<Student> students = tracker.getAllStudents();
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, students.size());
            int totalPages = (int) Math.ceil((double) students.size() / pageSize);

            for (int i = startIndex; i < endIndex; i++) {
                Student student = students.get(i);
                displayText.append("Id: ").append(student.getId())
                        .append(" | First Name: ").append(student.getFirstName())
                        .append(" | Last Name: ").append(student.getLastName())
                        .append(" | Field: ").append(student.getField())
                        .append(" | Age: ").append(student.getAge())
                        .append(" | Average Grade: ").append(student.getAverageGrade())
                        .append("\n");
            }

            displayText.append("\n")
                    .append("    [N] Next Page  [P] Previous Page  [R] Return " +
                            "                                   page " + currentPage + " / " + totalPages);
            Frame.displayInFrame(displayText.toString());
            if (!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("R") && !choice.equalsIgnoreCase("Y")
                    && !choice.equalsIgnoreCase("")) {
                System.out.println("Invalid choice. Please enter Y, N, or R.");
            }
            System.out.print("> Your selection: ");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("N")) {
                if (currentPage < totalPages) {
                    currentPage++;
                }
            } else if (choice.equalsIgnoreCase("P")) {
                if (currentPage > 1) {
                    currentPage--;
                }
            }

        } while (!choice.equalsIgnoreCase("R"));
    }

    // Method to add a new student
    public static void displayAddStudent() {
        Frame.clearScreen();
        String title = "                    ADD A NEW STUDENT                  ";
        Frame.displayInFrame(title);

        String newFirstName;
        String newLastName;
        String newField;

        // Validate first name
        while (true) {
            System.out.print("> Enter student's first name: ");
            newFirstName = input.nextLine();
            if (InputValidator.isValidAlphabetic(newFirstName)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only letters.");
            }
        }

        // Validate last name
        while (true) {
            System.out.print("> Enter student's last name: ");
            newLastName = input.nextLine();
            if (InputValidator.isValidAlphabetic(newLastName)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only letters.");
            }
        }

        // Validate field
        while (true) {
            System.out.print("> Enter student's field: ");
            newField = input.nextLine();
            if (InputValidator.isValidAlphabetic(newField)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only letters.");
            }
        }

        double newAverageGrade = 0;

        // Verify the user input if it's a digit and display an error message if it's
        // not.
        String newAgeStr;
        while (true) {
            System.out.print("> Enter student's age: ");
            newAgeStr = input.nextLine();
            if (InputValidator.isValidDigit(newAgeStr)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only digits.");
            }
        }

        int newAge = Integer.parseInt(newAgeStr);
        try {
            int result = tracker.addStudent(newFirstName, newLastName, newAge, newField, newAverageGrade);
            StringBuilder displayText = new StringBuilder();
            if (result != 0) {
                displayText.append("First Name: ").append(newFirstName)
                        .append(" | Last Name: ").append(newLastName)
                        .append(" | Field: ").append(newField)
                        .append(" | Age: ").append(newAge)
                        .append(" | Average Grade: ").append(newAverageGrade)
                        .append("\n\n")
                        .append("   Student added successfully!");
            } else {
                displayText.append("ERROR: No student added.");
            }

            Frame.clearScreen();
            String choice;
            do {
                Frame.displayInFrame(displayText.toString() + "\n\n[R] Return");
                System.out.print("Press R to return: ");
                choice = input.nextLine();
            } while (!choice.equalsIgnoreCase("R"));
        } catch (Exception e) {
            Frame.displayInFrame("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to modify a student information
    public static void displayModifyStudent() {
        String choice = "";
        do {
            do {
                Frame.clearScreen();
                String title = "                   UPDATE STUDENT INFO                 \n\n" +
                        "   Do you want to display students before updating ? \n\n" +
                        "[Y] Yes    [N] No   [R] Return";

                Frame.displayInFrame(title);
                if (!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("R") && !choice.equalsIgnoreCase("Y")
                        && !choice.equalsIgnoreCase("")) {
                    System.out.println("Invalid choice. Please enter Y, N, or R.");
                }
                System.out.print("Your selection: ");
                choice = input.nextLine();

            } while (!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("R") && !choice.equalsIgnoreCase("Y"));
            // Ask the user to select a student by their Id and verify the input user
            if (choice.equalsIgnoreCase("Y")) {
                displayAllStudents();
            } else if (choice.equalsIgnoreCase("R")) {
                break;
            }

            String studentIdStr;
            while (true) {
                System.out.print("> Enter student ID: ");
                studentIdStr = input.nextLine();
                if (InputValidator.isValidDigit(studentIdStr)) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter only digits.");
                }
            }

            // Convert the input user from string to int
            int studentId = Integer.parseInt(studentIdStr);

            // Display the user choice
            Student student = new Student(studentId);
            Frame.clearScreen();

            String inputStudentUpdate;

            do {

                // Veryfy if the input user is correct
                while (true) {
                    System.out.print("> Do you want to modify " + student.getFirstName() + " " + student.getLastName()
                            + "'s information (Y/N) ? ");
                    inputStudentUpdate = input.nextLine();
                    if (InputValidator.isValidYesNo(inputStudentUpdate)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter only Y or N.");
                    }
                }

                if (inputStudentUpdate.equalsIgnoreCase("Y")) {
                    int infoToModify = 0;

                    // Check if the user input is valid
                    while (!InputValidator.isValidUpdateStudentInfo(String.valueOf(infoToModify))) {
                        Frame.clearScreen();
                        displayStudentInfo(student);
                        Frame.displayInFrame(
                                "Selected student: " + student.getFirstName() + " " + student.getLastName() + "\n\n" +
                                        "\n[1] First Name\n" +
                                        "[2] Last Name \n" +
                                        "[3] Age \n" +
                                        "[4] Field \n\n");
                        System.out.print("> Please choose the information you wish to modify: ");
                        infoToModify = input.nextInt();
                        input.nextLine();

                        if (infoToModify < 1 || infoToModify > 4) {
                            Frame.displayInFrame("Invalid input. Please enter 1, 2, 3, or 4");
                        }
                    }

                    // Update the database based on the user choice
                    switch (infoToModify) {

                        case 1: // Modify first name

                            System.out.print("> Enter new first name: ");
                            String updateFirstName = input.nextLine();
                            while (!InputValidator.isValidAlphabetic(updateFirstName)) {
                                System.out.println("Invalid first name input. Please enter only letters.");
                                System.out.print("> Enter new first name: ");
                                updateFirstName = input.nextLine();
                            }
                            tracker.updateFirstName(studentId, updateFirstName);
                            Frame.clearScreen();
                            student = new Student(studentId);
                            Frame.displayInFrame("First name updated successfully! \n");
                            break;

                        case 2: // Modify last name

                            System.out.print("> Enter new last name: ");
                            String updateLastName = input.nextLine();
                            while (!InputValidator.isValidAlphabetic(updateLastName)) {
                                System.out.println("Invalid last name input. Please enter only letters.");
                                System.out.print("> Enter new last name: ");
                                updateLastName = input.nextLine();
                            }
                            tracker.updateLastName(studentId, updateLastName);
                            Frame.clearScreen();
                            student = new Student(studentId);
                            Frame.displayInFrame("Last name updated successfully! \n");
                            break;

                        case 3:// Modify age
                            System.out.print("> Enter new age: ");
                            String updateAgeStr = input.nextLine();
                            while (!InputValidator.isValidDigit(updateAgeStr)) {
                                System.out.println("Invalid age input. Please enter only digits.");
                                System.out.print("> Enter new age: ");
                                updateAgeStr = input.nextLine();
                            }
                            // Convert a string age into int
                            int updateAge = Integer.parseInt(updateAgeStr);
                            tracker.updateAge(studentId, updateAge);
                            input.nextLine();
                            Frame.clearScreen();
                            student = new Student(studentId);
                            Frame.displayInFrame("Age updated successfully! \n");
                            break;

                        case 4: // Modify field
                            System.out.print("> Enter new field: ");
                            String updateField = input.nextLine();
                            while (!InputValidator.isValidAlphabetic(updateField)) {
                                System.out.println("Invalid field input. Please enter only letters.");
                                System.out.print("> Enter new field: ");
                                updateField = input.nextLine();
                            }
                            tracker.updateField(studentId, updateField);
                            Frame.clearScreen();
                            student = new Student(studentId);
                            Frame.displayInFrame("Field updated successfully! \n");
                            break;
                        default:
                            Frame.displayInFrame("Invalid choice. Please try again.");
                            break;
                    }
                }
            } while (inputStudentUpdate.equalsIgnoreCase("Y"));

        } while (!choice.equalsIgnoreCase("R"));
    }

    // Method to delete a student
    public static void displayDeleteStudent() {
        // Display title
        String title = "╔═══════════════════════════════════════════════════════╗\n" +
                "║ DELETE STUDENT ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n";
        Frame.displayInFrame(title);

        // Display all students the user can delete
        displayAllStudents();

        // Ask the user to select a student by id and verify the user input
        String inputUserStr;
        while (true) {
            System.out.print("> Please choose the student Id: ");
            inputUserStr = input.nextLine();
            if (InputValidator.isValidDigit(inputUserStr)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only digits.");
            }
        }

        // Convert the user input from string to int
        int deleteStudentId = Integer.parseInt(inputUserStr);

        // Display the user choice
        Frame.clearScreen();
        String deleteStudentSelected = tracker.getStudentNameById(deleteStudentId);
        Frame.displayInFrame("Selected student: " + deleteStudentSelected + "\n");

        // Ask confirmation before deleting the student and verify if the input user is
        // correct
        String inputStudentDelete;
        while (true) {
            System.out.print("> Delete " + deleteStudentSelected + "'s information permanently (Y/N)? ");
            inputStudentDelete = input.nextLine();
            if (InputValidator.isValidYesNo(inputStudentDelete)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only Y or N.");
            }
        }

        String choice = "";
        if (inputStudentDelete.equalsIgnoreCase("Y")) {
            tracker.deleteStudent(deleteStudentId);
            Frame.clearScreen();
            do {
                Frame.displayInFrame(
                        "Student " + deleteStudentSelected + " deleted successfully! \n\n " +
                                "[R] Return");
                System.out.print("Press R to return : ");
                choice = input.nextLine();
            } while (!choice.equalsIgnoreCase("R"));
        }
    }

    // Method to search student by ID
    public static void displaySearchStudent() {
        Frame.clearScreen();
        String title = "                SEARCH A STUDENT BY THEIR ID            \n";
        Frame.displayInFrame(title);

        // Ask the user to choose the ID of a student and verify if the input user is
        // only digit
        String studentIdStr;
        while (true) {
            System.out.print("> Enter student ID: ");
            studentIdStr = input.nextLine();
            if (InputValidator.isValidDigit(studentIdStr)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only digits.");
            }
        }

        // Convert the input user from sting to integer
        int searchStudentID = Integer.parseInt(studentIdStr);

        // Get the student with the given ID
        String studentFound = tracker.getStudentNameById(searchStudentID);
        Frame.displayInFrame("Found student: " + studentFound);

    }

    // Method to display student by ID
    public static void displayStudentById() {
        StringBuilder displayText = new StringBuilder();
        Student student = null;
        String studentIdStr = "";
        int studentId = 0;

        // Verify if the input user is only digit
        while (true) {
            // Title display
            Frame.clearScreen();
            String title = "                SEARCH STUDENT BY ID                ";
            Frame.displayInFrame(title);
            if (studentId == -1)
                System.out.println("Invalid input. Please enter only digits.");
            System.out.print("> Enter student ID: ");
            studentIdStr = input.nextLine();
            if (InputValidator.isValidDigit(studentIdStr)) {
                studentId = Integer.parseInt(studentIdStr);
                break;
            } else {
                studentId = -1;
            }
        }

        // Retrieve the student with the given ID
        List<Integer> studentIds = new ArrayList<>();
        studentIds = tracker.getAllStudentIds();
        if (studentIds.contains(studentId)) {
            student = new Student(studentId);
        }

        if (student != null) {
            displayText.append("Student found:\n")
                    .append("ID: ").append(student.getId()).append(" | Name: ").append(student.getFirstName())
                    .append(" ")
                    .append(student.getLastName())
                    .append(" | Age: ").append(student.getAge())
                    .append(" | Field: ").append(student.getField()).append("\n");
        } else {
            displayText.append("No student found with id " + studentIdStr);
        }

        String choice = "";
        do {
            Frame.clearScreen();
            Frame.displayInFrame(displayText.toString() + "\n\n[R] Return");
            if (choice != "" && choice != "R") {
                System.out.println("Error, only R can be selected");
            }
            System.out.print("Press R to return: ");
            choice = input.nextLine();
        } while (!choice.equalsIgnoreCase("R"));
    }

    public static void displayStudentInfo(Student student) {
        String displayText = "First name: " + student.getFirstName() +
                " | Last name: " + student.getLastName() +
                " | Age: " + student.getAge() +
                " | Field: " + student.getField() + "\n";
        Frame.displayInFrame(displayText);
    }
}
