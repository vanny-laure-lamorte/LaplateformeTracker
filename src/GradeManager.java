import java.util.Collections;
import java.util.List;

public class GradeManager {
    public void sortGradesBySubject(List<Grade> grades) {
        Collections.sort(grades);
    }

    public void displayGrades(List<Grade> grades) {
        for (Grade grade : grades) {
            System.out.println("Course: " + grade.getCourseName() + ", Grade: " + grade.getGrade());
        }
    }
}
