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

    public int addStudent(String newFirstName, String newLastName, int newAge, String newField, double newAverageGrade) {
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


    public int updateStudent(int updateStudentId, String updateFirstName, String updateLastName, int updateAge, String updateField, double updateAverageGrade) throws SQLException {
        
        try (Connection connection = database.connect()) { 

        String insertSql = "UPDATE students SET firstName = ?, lastName = ?, age = ?, field = ?, averageGrade = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(insertSql))  {
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
        String query = "SELECT firstName, lastName FROM student WHERE id = ?";

        try (Connection connection = database.connect(); PreparedStatement statement = connection.prepareStatement(query)) {
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
        String updateSql = "UPDATE student SET firstName = ? WHERE id = ?";
        try (Connection connection = database.connect(); PreparedStatement statement = connection.prepareStatement(updateSql)) {
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
        try (Connection connection = database.connect(); PreparedStatement statement = connection.prepareStatement(updateSql)) {
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
        try (Connection connection = database.connect(); PreparedStatement statement = connection.prepareStatement(updateSql)) {
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
        try (Connection connection = database.connect(); PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, newField);
            statement.setInt(2, studentId);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error updating field: " + exception.getMessage());
            return 0;
        }
    }

}
