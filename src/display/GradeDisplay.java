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

    public static void displayAllGrades() {
        int currentPage = 1;
        int pageSize = 10; // Number of grades per page
        String choice = "";
    
        List<Grade> grades = gradeRepository.getAllGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades found in the database.");
            return;
        }
    
        do {
            Frame.clearScreen();
            StringBuilder displayText = new StringBuilder();
            displayText.append("╔═══════════════════════════════════════════════════════╗\n")
                       .append("║                    ALL GRADES                         ║\n")
                       .append("╚═══════════════════════════════════════════════════════╝\n\n");
    
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, grades.size());
            int totalPages = (int) Math.ceil((double) grades.size() / pageSize);
    
            for (int i = startIndex; i < endIndex; i++) {
                Grade grade = grades.get(i);
                displayText.append("Grade ID: ").append(grade.getId())
                           .append(" | Student ID: ").append(grade.getStudentId())
                           .append(" | Course: ").append(grade.getCourseName())
                           .append(" | Grade: ").append(grade.getGrade())
                           .append("\n");
            }
    
            displayText.append("\n[N] Next Page  [P] Previous Page  [R] Return\n")
                       .append("Page ").append(currentPage).append(" / ").append(totalPages);
    
            Frame.displayInFrame(displayText.toString());
            System.out.print("> Your selection: ");
            choice = input.nextLine().trim();
    
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
        String quit = "";
        do {
            Frame.clearScreen();
            Frame.displayInFrame("Grade :" + deleteStudentId + " has correctly been deleted." + "\n [R] return");
            System.out.print(" > Press R: ");
            quit = input.nextLine();
        } while (!quit.equalsIgnoreCase("R"));

    }

    public static void modifyGrade() {

        // Display all students the user can delete
        displayAllGrades();

        String studentIdStr;
        while (true) {
            System.out.print("> Enter grade ID: ");
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
        String recap = "";
        String choice = "";
        do {
            Frame.clearScreen();
            String title = "                    ADD GRADE                    \n\n" +
                    "   Do you want to display students before ? \n\n" +
                    "   [Y] Yes    [N] No  ";

            Frame.displayInFrame(title);
            if (!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")
                    && !choice.equalsIgnoreCase("")) {
                System.out.println("Invalid choice. Please enter Y or N");
            }
            System.out.print("Your selection: ");
            choice = input.nextLine();

        } while (!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y"));
        // Ask the user to select a student by their Id and verify the input user
        if (choice.equalsIgnoreCase("Y")) {
            StudentDisplay.displayAllStudents();
        }

        String inputIdStr;
        String inputSubjectName;
        String inputGradeStr;

        while (true) {
            System.out.print("> Enter a student id : ");
            inputIdStr = input.nextLine();
            if (InputValidator.isValidDigit(inputIdStr)) {
                Frame.clearScreen();
                recap += "Student Id: " + inputIdStr;
                break;
            } else {
                System.out.println("Invalid input. Please enter only digit.");
            }
        }

        while (true) {
            Frame.displayInFrame(recap);
            System.out.print("> Enter a subject name: ");
            inputSubjectName = input.nextLine();
            if (InputValidator.isValidAlphabetic(inputSubjectName)) {
                Frame.clearScreen();
                recap += "\nSubject name: " + inputSubjectName;
                break;
            } else {
                System.out.println("Invalid input. Please enter only letters.");
            }
        }

        while (true) {
            Frame.displayInFrame(recap);
            System.out.print("> Enter a grade: ");
            inputGradeStr = input.nextLine();
            if (InputValidator.isValidDigitDouble(inputGradeStr)) {
                Frame.clearScreen();
                recap += "\nGrade: " + inputGradeStr;
                break;
            } else {
                System.out.println("Invalid input. Please enter only a digit.");
            }
        }

        int inputId = Integer.parseInt(inputIdStr);
        double inputGrade = Double.parseDouble(inputGradeStr);
        try {
            gradeRepository.addGrade(inputId, inputSubjectName, inputGrade);

            gradeRepository.setAverageGrades(inputId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String quit = "";
        do {
            Frame.clearScreen();
            Frame.displayInFrame("Grade added successfully! \n");
            recap += "\n[R] Return: ";
            Frame.displayInFrame(recap);
            System.out.print(" > Press R: ");
            quit = input.nextLine();
        } while (!quit.equalsIgnoreCase("R"));

    }

}
