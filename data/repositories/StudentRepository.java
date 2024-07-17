import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private static Database database = new Database() {

    };

    // Method to display all the students in the database
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection connection = database.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"));
                students.add(student);
            }
        } catch (SQLException exception) {
            System.err.println("Error fetching students: " + exception.getMessage());
        }
        return students;
    }

    // Method to add a new student to the database
    public int addStudent(String newFirstName, String newLastName, int newAge, String newField,
            double newAverageGrade) {
        String insertSql = "INSERT INTO student (firstName, lastName, age, field, averageGrade) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(insertSql)) {

            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setInt(3, newAge);
            statement.setString(4, newField);
            statement.setDouble(5, newAverageGrade);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\nStudent added successfully!");
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException exception) {
            System.err.println("Error adding student: " + exception.getMessage());
            return 0;
        }
    }

    // Method to update an existing student's info
    public int updateStudent(int updateStudentId, String updateFirstName, String updateLastName, int updateAge,
            String updateField, double updateAverageGrade) throws SQLException {

        try (Connection connection = database.connect()) {

            String insertSql = "UPDATE students SET firstName = ?, lastName = ?, age = ?, field = ?, averageGrade = ? WHERE id = ?";

            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setString(1, updateFirstName);
                statement.setString(2, updateLastName);
                statement.setInt(3, updateAge);
                statement.setString(4, updateField);
                statement.setDouble(5, updateAverageGrade);
                statement.setInt(6, updateStudentId);
                return statement.executeUpdate();
            }

        }
    }

    // Method to get a student's name by their ID
    public String getStudentNameById(int studentId) {
        String studentInfo = "";
        String query = "SELECT firstName, lastName FROM student WHERE id = ?";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    studentInfo = resultSet.getString("firstName") + " " + resultSet.getString("lastName");
                } else {
                    studentInfo = "Student not found";
                }
            }
        } catch (SQLException exception) {
            System.out.println("Error fetching student: " + exception.getMessage());
        }
        return studentInfo;
    }

    public ResultSet getStudentById(int studentId) {
        String query = "SELECT * FROM student WHERE id = ?";
        ResultSet resultSet = null;

        try {
            Connection connection = database.connect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();
        } catch (SQLException exception) {
            System.out.println("Error fetching student: " + exception.getMessage());
        }

        return resultSet;
    }

    // Method to update the first name of a student
    public int updateFirstName(int studentId, String newFirstName) {
        String updateSql = "UPDATE student SET firstName = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, newFirstName);
            statement.setInt(2, studentId);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error updating first name: " + exception.getMessage());
            return 0;
        }
    }

    // Method to update the last name of a student
    public int updateLastName(int studentId, String newLastName) {
        String updateSql = "UPDATE student SET lastName = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, newLastName);
            statement.setInt(2, studentId);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error updating last name: " + exception.getMessage());
            return 0;
        }
    }

    // Method to update the age of a student
    public int updateAge(int studentId, int newAge) {
        String updateSql = "UPDATE student SET age = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setInt(1, newAge);
            statement.setInt(2, studentId);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error updating age: " + exception.getMessage());
            return 0;
        }
    }

    // Method to update the field of study of a student
    public int updateField(int studentId, String newField) {
        String updateSql = "UPDATE student SET field = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, newField);
            statement.setInt(2, studentId);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error updating field: " + exception.getMessage());
            return 0;
        }
    }

    // Method to delete a student
    public int deleteStudent(int studentId) {
        String deleteSql = "DELETE FROM student WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setInt(1, studentId);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error deleting student: " + exception.getMessage());
            return 0;
        }

    }

    public static void updateAverageGrades() {
        String query = "SELECT id FROM student";

        try (Connection connection = database.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                GradeRepository.setAverageGrades(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching IDs", e);
        }
    }

    public List<Integer> getAllStudentIds() {
        List<Integer> studentIds = new ArrayList<>();
        String query = "SELECT id FROM student";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                studentIds.add(id);
            }

        } catch (SQLException exception) {
            System.err.println("Error fetching student IDs: " + exception.getMessage());
        }

        return studentIds;
    }

}
