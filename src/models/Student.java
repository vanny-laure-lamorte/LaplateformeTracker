import java.sql.*;
import java.util.List;


public class Student {
    private StudentRepository studentRepo = new StudentRepository();
    private String firstName;
    private String lastName;
    private int age;
    private String field;
    private String grade;
    private List<Grade> grades;

    public Student(int studentID) {
        try {
            ResultSet resultSet = studentRepo.getStudentById(studentID);

            if (resultSet.next()) {
                this.firstName = resultSet.getString("firstName");
                this.lastName = resultSet.getString("lastName");
                this.field = resultSet.getString("field");
                this.age = resultSet.getInt("age");
                this.grade = resultSet.getString("averageGrade");
            }
            System.out.println(age);
        } catch (SQLException e) {
            System.err.println("Error fetching student data: " + e.getMessage());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getField() {
        return field;
    }

    public String getGrade() {
        return grade;
    }

    public List<Grade> getGrades() {
        return grades;
    }
}
