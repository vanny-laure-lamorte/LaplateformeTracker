import java.sql.*;
import java.util.Vector;

public class PlateformeTracker {

    public void initDataBase() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Vector<String> passwords = new Vector<>();
            passwords.add("VannyLamorte25!"); // --- Vanny
            passwords.add("root"); // --- Thanh
            passwords.add("$~Bc4gB9"); // --- Lucas

            for (String password : passwords) {
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/PlateformeTracker",
                            "root", password);
                            break;

                } catch (Exception exception) {
                }
            }

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from student");
            String firstName;
            int age;
            while (resultSet.next()) {
                firstName = resultSet.getString("firstName");
                age = resultSet.getInt("age");
                System.out.println("First name : " + firstName
                        + " | Age : " + age);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}