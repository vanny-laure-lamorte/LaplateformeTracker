import java.sql.*;

public class GradeRepository {
    private Database database = new Database();

    public Grade getGrade(int studentID) {
        String selectSql = "SELECT * FROM grade WHERE studentId = ?";

        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, studentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Assuming the Grade class has appropriate constructor and fields
                    return new Grade(
                        resultSet.getInt("studentId"),
                        resultSet.getString("courseName"),
                        resultSet.getDouble("grade")
                    );
                } else {
                    System.err.println("No grade found for student ID: " + studentID);
                }
            } catch (SQLException exception) {
                System.err.println("Error executing query: " + exception.getMessage());
            }
        } catch (SQLException exception) {
            System.err.println("Error connecting to database: " + exception.getMessage());
        }
        return null;
    }
}