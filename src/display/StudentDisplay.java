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

    public static void displayAllStudents() {
        int currentPage = 1;
        int pageSize = 5;
        String quit = "";

        do {
            Frame.clearScreen();
            StringBuilder displayText = new StringBuilder();
            displayText.append("╔═══════════════════════════════════════════════════════╗\n")
                    .append("║                    ALL STUDENTS                       ║\n")
                    .append("╚═══════════════════════════════════════════════════════╝\n\n");

            List<Student> students = tracker.getAllStudents();
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, students.size());

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
                    .append("    [N] Next Page  [P] Previous Page  [0] Return");

            Frame.displayInFrame(displayText.toString());

            quit = input.nextLine();

            if (quit.equalsIgnoreCase("N")) {
                currentPage++;
            } else if (quit.equalsIgnoreCase("P")) {
                currentPage = Math.max(1, currentPage - 1);
            }

        } while (!quit.equalsIgnoreCase("0"));
    }

    public static void displayAddStudent() {

        String newAgeStr; 

        String title = "                    ADD A NEW STUDENT                  ";
        Frame.displayInFrame(title);

        System.out.print("> Enter student's first name: ");
        String newFirstName = input.nextLine();
        System.out.print("> Enter student's last name: ");
        String newLastName = input.nextLine();
        System.out.print("> Enter student's field: ");
        String newField = input.nextLine();
        double newAverageGrade = 0;

        // Verify the user input if it's a digit and display an error message if it's not
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
                        .append("\n");
            } else {
                displayText.append("ERROR: No student added.");
            }
            Frame.displayInFrame(displayText.toString());
        } catch (Exception e) {
            Frame.displayInFrame("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void displayModifyStudent() {
        String choice;
        do {
            Frame.clearScreen();
            String title = "                   UPDATE STUDENT INFO                 \n\n" +
                    "   Do you want to display students before updating ? \n\n" +
                    "[Y] Yes    [N] No   [Q] Quit";
            System.out.print(" --> ");

            Frame.displayInFrame(title);
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                displayAllStudents();
            } else if (choice.equalsIgnoreCase("Q")) {
                break; 
            } else if (!choice.equalsIgnoreCase("N")) {
                Frame.displayInFrame("Invalid choice. Please enter Y, N, or Q.");
                continue; 
            }


            // Ask the user to select a student by their Id
            System.out.print("> Please choose the student Id: ");
            int studentId = input.nextInt();
            input.nextLine();

            // Display the user choice
            String studentSelected = tracker.getStudentNameById(studentId);
            Frame.clearScreen();
            Frame.displayInFrame("Selected student: " + studentSelected + "\n");

            String inputStudentUpdate;
            do {
                System.out.print("> Do you want to modify " + studentSelected + "'s information (Y/N) ? ");
                inputStudentUpdate = input.nextLine();

                if (inputStudentUpdate.equalsIgnoreCase("Y")) {
                    int infoToModify = 0;

                    // Check if the user input is valid
                    while (!InputValidator.isValidUpdateStudentInfo(String.valueOf(infoToModify))) {
                        Frame.clearScreen();
                        Frame.displayInFrame(
                                "Selected student: " + studentSelected + "\n\n" +
                                        "\n[1] First Name\n" +
                                        "[2] Last Name \n" +
                                        "[3] Age \n" +
                                        "[4] Field \n\n");
                        System.out.print("> Please choose the information you wish to modify: ");
                        infoToModify = input.nextInt();
                        input.nextLine();

                        if (infoToModify < 1 || infoToModify > 4) {
                            Frame.displayInFrame("Invalid input! Please enter 1, 2, 3, or 4");
                        }
                    }

                    // Update the database based on the user choice
                    switch (infoToModify) {
                        case 1:
                            System.out.print("> Enter new first name: ");
                            String updateFirstName = input.nextLine();
                            tracker.updateFirstName(studentId, updateFirstName);
                            Frame.displayInFrame("First name updated successfully! \n");
                            break;
                        case 2:
                            System.out.print("> Enter new last name: ");
                            String updateLastName = input.nextLine();
                            tracker.updateLastName(studentId, updateLastName);
                            Frame.displayInFrame("Last name updated successfully! \n");
                            break;
                        case 3:
                            System.out.print("> Enter new age: ");
                            int updateAge = input.nextInt();
                            tracker.updateAge(studentId, updateAge);
                            Frame.displayInFrame("Age updated successfully! \n");
                            input.nextLine();
                            break;
                        case 4:
                            System.out.print("> Enter new field: ");
                            String updateField = input.nextLine();
                            tracker.updateField(studentId, updateField);
                            Frame.displayInFrame("Field updated successfully! \n");
                            break;
                        default:
                            Frame.displayInFrame("Invalid choice. Please try again.");
                            break;
                    }
                }
            } while (inputStudentUpdate.equalsIgnoreCase("Y"));

        } while (!choice.equalsIgnoreCase("Q"));
    }

    public static void displayDeleteStudent() {
        // Display title
        String title = "╔═══════════════════════════════════════════════════════╗\n" +
                "║ DELETE STUDENT ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n";
        Frame.displayInFrame(title);

        // Display all students the user can delete
        displayAllStudents();

        // Ask the user to select a student to delete by their Id
        System.out.print("> Please choose the student Id: ");
        int deleteStudentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String deleteStudentSelected = tracker.getStudentNameById(deleteStudentId);
        Frame.displayInFrame("Selected student: " + deleteStudentSelected + "\n");

        // Ask confirmation before deleting the student
        System.out.print("> Delete " + deleteStudentSelected + "'s information permanently (Y/N)? ");
        String inputStudentDelete = input.nextLine();

        if (inputStudentDelete.equalsIgnoreCase("Y")) {
            tracker.deleteStudent(deleteStudentId);
            Frame.displayInFrame("Student deleted successfully!");
        }
    }

    public static void displaySearchStudent() {
        String title = "╔═══════════════════════════════════════════════════════╗\n" +
                "║               SEARCH A STUDENT BY THEIR ID            ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n";
        Frame.displayInFrame(title);

        // Ask the user to choose the ID of a student
        System.out.print("> Please enter the student's ID: ");
        int searchStudentID = input.nextInt();
        input.nextLine();

        // Get the student with the given ID
        String studentFound = tracker.getStudentNameById(searchStudentID);
        Frame.displayInFrame("Found student: " + studentFound);
    }

    public static void displayStudentById() {
        Frame.displayInFrame("SEARCH STUDENT");

        System.out.print("> Enter the student ID: ");
        int studentId = input.nextInt();
        input.nextLine();

        // Retrieve the student with the given ID
        Student student = new Student(studentId);

        if (student != null) {
            String displayText = "Student found:\n" +
                    "Name: " + student.getFirstName() + " " + student.getLastName() +
                    " | Age: " + student.getAge() +
                    " | Field: " + student.getField() + "\n";
            Frame.displayInFrame(displayText);
        }
    }
}
