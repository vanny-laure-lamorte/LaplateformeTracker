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
        StringBuilder displayText = new StringBuilder();
        displayText.append("╔═══════════════════════════════════════════════════════╗\n")
                   .append("║                    ALL STUDENTS                       ║\n")
                   .append("╚═══════════════════════════════════════════════════════╝\n\n");

        List<Student> students = tracker.getAllStudents();
        for (Student student : students) {
            displayText.append("Id: ").append(student.getId())
                       .append(" | First Name: ").append(student.getFirstName())
                       .append(" | Last Name: ").append(student.getLastName())
                       .append(" | Field: ").append(student.getField())
                       .append(" | Age: ").append(student.getAge())
                       .append(" | Average Grade: ").append(student.getAverageGrade())
                       .append("\n");
        }
        displayText.append("\n");

        Frame.displayInFrame(displayText.toString());
    }

    public static void displayAddStudent() {
        String title = "╔═══════════════════════════════════════════════════════╗\n" +
                       "║                    ADD A NEW STUDENT                  ║\n" +
                       "╚═══════════════════════════════════════════════════════╝\n";
        Frame.displayInFrame(title);

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
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void displayModifyStudent() {
        String title = "╔═══════════════════════════════════════════════════════╗\n" +
                       "║                   UPDATE STUDENT INFO                 ║\n" +
                       "╚═══════════════════════════════════════════════════════╝\n";
        Frame.displayInFrame(title);

        // Display options the user can modify
        displayAllStudents();

        // Ask the user to select a student by their Id
        System.out.print("> Please choose the student Id: ");
        int studentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String studentSelected = tracker.getStudentNameById(studentId);
        Frame.displayInFrame("Selected student: " + studentSelected + "\n");

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

        // Retrieve student information by ID
        Student student = new Student(searchStudentID);

        // Display the student's information if found
        if (student != null) {
            String displayText = "Student found:\n" +
                                 "Name: " + student.getFirstName() + " " + student.getLastName() +
                                 " | Age: " + student.getAge() +
                                 " | Field: " + student.getField() + "\n";
            Frame.displayInFrame(displayText);
        }
    }
}
