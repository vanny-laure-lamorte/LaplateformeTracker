import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilterRepository {

    private Database database = new Database();

    public void getStudentsOrderedByFirstName() {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM student ORDER BY firstName")) {
    
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");
    
                // Display students
                FilterDisplay.filterStudentsByFirstName(id, firstName, lastName, field,age);
            }
    
        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }

    public void getStudentsOrderedByLastName() {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM student ORDER BY lastName")) {
    
            ResultSet resultSet = statement.executeQuery();
    
            // 
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");
    
                // 
                FilterDisplay.filterStudentsByLastName(id, firstName, lastName, field,age);
            }
    
        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }



}