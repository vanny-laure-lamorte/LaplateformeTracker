import java.sql.*;
import java.util.Vector;

public class PlateformeTracker {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/PlateformeTracker";
    private static final String DB_USER = "root";

    public Connection connect() {
        Connection connection = null;
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

                } catch (Exception exception) {
                }
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return connection;
    }

    public ResultSet displayStudent() {
        ResultSet resultSet = null;

        try (Connection connection = connect()) {
            Statement statement;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student");

            System.out.println(
                "╔═══════════════════════════════════════════════════════╗\n" +
                "║                     STUDENTS INFO                     ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n");


            while (resultSet.next()) {

                System.out.println(       
                " First Name: " + resultSet.getString("firstName")  + 
                " | Last Name: " + resultSet.getString("lastName") + 
                " | Field: " +  resultSet.getString("field") + 
                " | Average Grade : " +  resultSet.getInt("age") );
            }
            System.out.println();
            statement.close();
            connection.close();
            resultSet.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return resultSet;
    }




}