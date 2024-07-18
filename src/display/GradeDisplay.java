import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GradeDisplay extends HomeDisplay{

    public GradeDisplay(Scanner input) {
        super(input);
    }

    private static GradeRepository gradeRepository = new GradeRepository();

    public static void displayGrades() {
        String studentID;
        while (true) {
            System.out.print("> Enter student ID: ");
            studentID = input.nextLine();
            if (InputValidator.isValidDigit(studentID)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only digits.");
            }
        }

        int studentIDInt = Integer.parseInt(studentID);
        List<Grade> grades = gradeRepository.getGradesByStudentId(studentIDInt);
        if (grades.isEmpty()) {
            System.out.println("No grades found for student ID: " + studentIDInt);
        } else {
            System.out.println("Grades for student ID " + studentIDInt + ":");
            for (Grade grade : grades) {
                System.out.println("Course: " + grade.getCourseName() + ", Grade: " + grade.getGrade());
            }
        }
    }
    public void sortAndDisplayGrades() {
        System.out.print("> Enter student ID: ");
        int studentID = input.nextInt();
        input.nextLine(); // Consume newline character
        List<Grade> grades = gradeRepository.getGradesByStudentId(studentID);

        // Sort grades by subject name
        Collections.sort(grades, (g1, g2) -> g1.getCourseName().compareTo(g2.getCourseName()));

        // Display sorted grades
        System.out.println("Sorted Grades for student ID " + studentID + ":");
        for (Grade grade : grades) {
            System.out.println("Course: " + grade.getCourseName() + ", Grade: " + grade.getGrade());
        }
    }

    // Method to display all grades
    public static void displayAllGrades() {
        List<Grade> grades = gradeRepository.getAllGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades found in the database.");
        } else {
            System.out.println("\n----------\n" +
                                "ALL GRADES \n" +
                                "-----------\n");
            for (Grade grade : grades) {
                System.out.println("Grade ID: " + grade.getId() + " | Student ID: " + grade.getStudentId() + " | Course: " + grade.getCourseName() + " | Grade: " + grade.getGrade());
            }
        }
    }

    public static void displaydeleteGrades() {

        // Display title
        String title = "╔═══════════════════════════════════════════════════════╗\n" +
                "║ DELETE GRADE ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n";
        Frame.displayInFrame(title);

        // Display all students the user can delete
        displayAllGrades();

        // Ask the user to select a student by id and verify the user input
        String inputUserStr;
        while (true) {
            System.out.print("> Please choose the grade Id: ");
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
         
         // Ask confirmation before deleting the student and verify if the input user is
         // correct
         
         while (true) {
             System.out.print("> Do you wish to delete the grade permanently (Y/N)? ");
             String inputDelete = input.nextLine();
             if (InputValidator.isValidYesNo(inputDelete)) {

                gradeRepository.deleteGradeById(deleteStudentId);
                
                 break;
             } else {
                 System.out.println("Invalid input. Please enter only Y or N.");
             }
         }

        }






   

}
