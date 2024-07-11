import java.sql.*;

public class PlateformeTracker {

    private Database database = new Database() {

    };

    public ResultSet displayStudent() {
        ResultSet resultSet = null;

        try (Connection connection = database.connect()) {
            Statement statement;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()) {
                System.out.println(
                        "Id: " + resultSet.getString("id") +
                                " | First Name: " + resultSet.getString("firstName") +
                                " | Last Name: " + resultSet.getString("lastName") +
                                " | Field: " + resultSet.getString("field") +
                                " | Age : " + resultSet.getInt("age") +
                                " | Average Grade : " + resultSet.getDouble("averageGrade"));
            }
            System.out.println();

        } catch (SQLException exception) {
            System.err.println("Error displaying students: " + exception.getMessage());
        }
        return resultSet;
    }

    public int addStudent(String newFirstName, String newLastName, int newAge, String newField,
            double newAverageGrade) {
        String Sql = "INSERT INTO student (firstName, lastName, age, field, averageGrade) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(Sql)) {

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

    public int updateStudent(int updateStudentId, String updateFirstName, String updateLastName, int updateAge,
            String updateField, double updateAverageGrade) throws SQLException {

        try (Connection connection = database.connect()) {

            String Sql = "UPDATE students SET firstName = ?, lastName = ?, age = ?, field = ?, averageGrade = ? WHERE id = ?";

            try (PreparedStatement statement = connection.prepareStatement(Sql)) {
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

    public String getStudentById(int studentId) {
        String studentInfo = "";
        String Sql = "SELECT firstName, lastName FROM student WHERE id = ?";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(Sql)) {
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

    // Method to update the first name of a student
    public int updateFirstName(int studentId, String newFirstName) {
        String Sql = "UPDATE student SET firstName = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(Sql)) {
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
        String Sql = "UPDATE student SET lastName = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(Sql)) {
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
        String Sql = "UPDATE student SET age = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(Sql)) {
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
        String Sql = "UPDATE student SET field = ? WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(Sql)) {
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
        String Sql = "DELETE FROM student WHERE id = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(Sql)) {
            statement.setInt(1, studentId);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error deleting student: " + exception.getMessage());
            return 0;
        }
    }

    //--- ACCOUNT ---//

    public String authenticateUser(String userLogin, String userPassword) {
        String sql = "SELECT email, password FROM login WHERE email = ?";
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userLogin);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String passwordFromDB = resultSet.getString("password");
                    return passwordFromDB;
                } else {
                    return null;
                }
            }
        } catch (SQLException exception) {
            System.out.println("ERROR " + exception.getMessage());
            return null;
        }
    }

    // Method to register a new user
     public boolean registerUser(int studentID, String email, String hashedPassword) {
        String sql = "INSERT INTO login (studentID, email, password) VALUES (?, ?, ?)";
        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentID);
            statement.setString(2, email);
            statement.setString(3, hashedPassword);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException exception) {
            System.out.println("ERROR: " + exception.getMessage());
            return false;
        }
    }

    //--- Filter ---//

   // Method to get students by first name
   


}
