import java.sql.*;
import java.util.Vector;

/**
 * Database
 */
public class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/PlateformeTracker";
    private static final String DB_USER = "root";

    private Connection connection = null;

    public Connection connect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Vector<String> passwords = new Vector<>();
            passwords.add("VannyLamorte25!"); // --- Vanny
            passwords.add("root"); // --- Thanh
            passwords.add("$~Bc4gB9"); // --- Lucas

            for (String password : passwords) {
                try {
                    connection = DriverManager.getConnection(DB_URL, DB_USER, password);
                    break;
                } catch (SQLException exception) {
                }
            }

        } catch (ClassNotFoundException exception) {
            System.err.println("MySQL JDBC Driver not found.");
            throw new SQLException("Driver not found.", exception);
        }
        
        if (connection == null) {
            throw new SQLException("Failed to connect to the database.");
        }
        
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
