import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilterRepository {

    // Initialize the database object to manage connections
    private Database database = new Database();

    // Method to retrieve and display students ordered by first name
    public void getStudentsOrderedByFirstName() {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM student ORDER BY firstName")) {

            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");

                // Display students
                FilterDisplay.filterStudentsByFirstName(id, firstName, lastName, field, age);
            }

        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }

    // Method to retrieve and display students ordered by last name
    public void getStudentsOrderedByLastName() {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM student ORDER BY lastName")) {

            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");

                // Display students
                FilterDisplay.filterStudentsByLastName(id, firstName, lastName, field, age);
            }

        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }

    // Method to retrieve and display students ordered by age
    public void getStudentsOrderedByAge() {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM student ORDER BY age")) {

            ResultSet resultSet = statement.executeQuery();
     
            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");

                // Display students
                FilterDisplay.filterStudentsByAge(id, firstName, lastName, field, age);
            }

        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }

     // Method to retrieve and display students filtered by a specific field and ordered by last name
    public void getStudentsOrderedField(String field) {
        String fieldToFilter = field; // Student Field

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM student WHERE field = ? ORDER BY lastName")) {            
            statement.setString(1, fieldToFilter);

            ResultSet resultSet = statement.executeQuery();

            // Check if any results are returned
            if (!resultSet.isBeforeFirst()) {
                System.err.println("No students found for the following field: " + fieldToFilter);
                return;
            }

            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");

                // Display Students
                FilterDisplay.filterStudentsByField(id, firstName, lastName, age);
            }

        } catch (SQLException exception) {
            System.err.println("No students found for the field: " + fieldToFilter);
        }
    }

}