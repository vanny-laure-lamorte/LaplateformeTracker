import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GradeDisplay {

    private static final Scanner gradeInput = new Scanner(System.in);
    private GradeRepository gradeRepository = new GradeRepository();

    public void displayGrades() {
        System.out.print("> Enter student ID: ");
        int studentID = gradeInput.nextInt();
        gradeInput.nextLine(); // Consume newline character
        List<Grade> grades = gradeRepository.getGradesByStudentId(studentID);
        if (grades.isEmpty()) {
            System.out.println("No grades found for student ID: " + studentID);
        } else {
            System.out.println("Grades for student ID " + studentID + ":");
            for (Grade grade : grades) {
                System.out.println("Course: " + grade.getCourseName() + ", Grade: " + grade.getGrade());
            }
        }
    }

    public void sortAndDisplayGrades() {
        System.out.print("> Enter student ID: ");
        int studentID = gradeInput.nextInt();
        gradeInput.nextLine(); // Consume newline character
        List<Grade> grades = gradeRepository.getGradesByStudentId(studentID);

        // Sort grades by subject name
        Collections.sort(grades, (g1, g2) -> g1.getCourseName().compareTo(g2.getCourseName()));

        // Display sorted grades
        System.out.println("Sorted Grades for student ID " + studentID + ":");
        for (Grade grade : grades) {
            System.out.println("Course: " + grade.getCourseName() + ", Grade: " + grade.getGrade());
        }
    }



   

}
