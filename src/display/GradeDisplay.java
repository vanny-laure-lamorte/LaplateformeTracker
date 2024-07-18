import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GradeDisplay extends HomeDisplay {

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
                System.out.println("Grade ID: " + grade.getId() + " | Student ID: " + grade.getStudentId()
                        + " | Course: " + grade.getCourseName() + " | Grade: " + grade.getGrade());
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

    public static void modifyGrade() {

        // Display all students the user can delete
        displayAllGrades();

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

        int idStudent = Integer.parseInt(studentIdStr);

        String inputUser;
        while (true) {
            System.out.print("> Please confirm you wish to modify the grade (Y/N)? ");
            inputUser = input.nextLine();
            if (InputValidator.isValidYesNo(inputUser)) {
                break;

            } else {
                System.out.println("Invalid input. Please enter only Y or N.");
            }
        }

        if (inputUser.equalsIgnoreCase("Y")) {
            System.out.print("New grade: ");
            int newGrade = input.nextInt();
            input.nextLine();
            try {

                gradeRepository.updateGrade(idStudent, newGrade);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        
    }


    // Method to add a new grade 
    public static void addGrade() {

        String inputIdStr;
        String inputSubjectName;
        String inputGradeStr; 

        while (true) {
            System.out.print("> Enter a student id : ");
            inputIdStr = input.nextLine(); 
            if (InputValidator.isValidDigit(inputIdStr)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only digit.");
            }            
        }

        while (true) {
            System.out.print("> Enter a subject name: ");
            inputSubjectName = input.nextLine();
            if (InputValidator.isValidAlphabetic( inputSubjectName)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only letters.");
            }
        }

        
        while (true) {
            System.out.print("> Enter a grade: ");
            inputGradeStr= input.nextLine();
            if (InputValidator.isValidDigitDouble(inputGradeStr)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only a digit.");
            }
        } 

        int inputId = Integer.parseInt(inputIdStr); 
        double inputGrade = Double.parseDouble(inputGradeStr); 
        try {
            gradeRepository.addGrade(inputId, inputSubjectName, inputGrade);
            Frame.displayInFrame("\n Grade added successfully! \n");
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    


}
