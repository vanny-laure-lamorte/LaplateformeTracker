import java.sql.*;

public class Student {
    private StudentRepository studentRepo = new StudentRepository();
    private int studentId;
    private String firstName;
    private String lastName;
    private int age;
    private String field;
    private String grade;

    public Student(int studentID) {
        try {
            ResultSet resultSet = studentRepo.getStudentById(studentID);

            if (resultSet.next()) {
                this.studentId = studentID;
                this.firstName = resultSet.getString("firstName");
                this.lastName = resultSet.getString("lastName");
                this.age = resultSet.getInt("age");
                this.field = resultSet.getString("field");
                this.grade = resultSet.getString("averageGrade");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching student data: " + e.getMessage());
        }
    }

}