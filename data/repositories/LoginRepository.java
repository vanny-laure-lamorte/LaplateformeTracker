import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    private Database database = new Database();

    // Method to authenticate a user based on login credentials
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
}
