import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class StudentDisplay {
    private static final StudentRepository tracker = new StudentRepository();
    private static final Scanner input = new Scanner(System.in);

    public static void displayTitleStudentInfo() {
        Frame.displayInFrame("STUDENT INFO");
    }

    public static void displayAllStudents() {
        List<Student> students = tracker.getAllStudents();
        StringBuilder studentDisplay = new StringBuilder();

        for (Student student : students) {
            studentDisplay.append("Name: ").append(student.getFirstName()).append(" ").append(student.getLastName())
                    .append(" | Field: ").append(student.getField())
                    .append(" | Age : ").append(student.getAge())
                    .append("\n");
        }

        Frame.displayInFrame(studentDisplay.toString());
    }

    public static void displayAddStudent() {
        Frame.displayInFrame("ADD A NEW STUDENT");

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
                Frame.displayInFrame(
                        "First Name: " + newFirstName +
                                " | Last Name: " + newLastName +
                                " | Field: " + newField +
                                " | Age : " + newAge +
                                " | Average Grade: " + newAverageGrade);
            } else {
                Frame.displayInFrame("ERROR: No student added.");
            }
        } catch (Exception e) {
            Frame.displayInFrame("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void displayModifyStudent() {
        Frame.displayInFrame("UPDATE STUDENT INFO");

        // Display options the user can modify
        displayAllStudents();

        // Ask the user to select a student by their Id
        System.out.print("> Please choose the student Id: ");
        int studentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String studentSelected = tracker.getStudentNameById(studentId);
        Frame.displayInFrame("Selected student: " + studentSelected);

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
                        Frame.displayInFrame("Invalid input! Please enter 1, 2, 3, or 4");
                    }
                }

                // Update the database based on the user choice
                switch (infoToModify) {
                    case 1:
                        System.out.print("> Enter new first name: ");
                        String updateFirstName = input.nextLine();
                        tracker.updateFirstName(studentId, updateFirstName);
                        Frame.displayInFrame("First name updated successfully!");
                        break;
                    case 2:
                        System.out.print("> Enter new last name: ");
                        String updateLastName = input.nextLine();
                        tracker.updateLastName(studentId, updateLastName);
                        Frame.displayInFrame("Last name updated successfully!");
                        break;
                    case 3:
                        System.out.print("> Enter new age: ");
                        int updateAge = input.nextInt();
                        tracker.updateAge(studentId, updateAge);
                        Frame.displayInFrame("Age updated successfully!");
                        input.nextLine();
                        break;
                    case 4:
                        System.out.print("> Enter new field: ");
                        String updateField = input.nextLine();
                        tracker.updateField(studentId, updateField);
                        Frame.displayInFrame("Field updated successfully!");
                        break;
                    default:
                        Frame.displayInFrame("Invalid choice. Please try again.");
                        break;
                }
            }
        } while (inputStudentUpdate.equalsIgnoreCase("Y"));
    }

    public static void displayDeleteStudent() {
        Frame.displayInFrame("DELETE STUDENT");

        // Display all students the user can delete
        displayAllStudents();

        // Ask the user to select a student to delete by their Id
        System.out.print("> Please choose the student Id: ");
        int deleteStudentId = input.nextInt();
        input.nextLine();

        // Display the user choice
        String deleteStudentSelected = tracker.getStudentNameById(deleteStudentId);
        Frame.displayInFrame("Selected student: " + deleteStudentSelected);

        // Ask confirmation before deleting the student
        System.out.print("> Delete " + deleteStudentSelected + "'s information permanently (Y/N)? \n");
        String inputStudentDelete = input.nextLine();

        if (inputStudentDelete.equalsIgnoreCase("Y")) {
            tracker.deleteStudent(deleteStudentId);
            Frame.displayInFrame("Student deleted successfully!");
        }
    }

    public static void displaySearchStudent() {
        Frame.displayInFrame("SEARCH A STUDENT BY THEIR ID");

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
            Frame.displayInFrame(
                    " | First Name: " + student.getFirstName() +
                            " | Last Name: " + student.getLastName() +
                            " | Age: " + student.getAge() +
                            " | Field: " + student.getField());
        } else {
            Frame.displayInFrame("No student found with ID: " + studentId);
        }
    }
}
