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


    public void getStudentsOrderedByAge() {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM student ORDER BY age")) {
    
            ResultSet resultSet = statement.executeQuery();
    
            // 
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");
    
                // 
                FilterDisplay.filterStudentsByAge( id, firstName, lastName, field, age);
            }
    
        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }


    
    public void getStudentsOrderedField(String field) {
        String fieldToFilter = field; // Le domaine d'étude à filtrer
    
        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement(
                 "SELECT * FROM student WHERE field = ? ORDER BY age")) {
    
            // Définir le paramètre de la requête
            statement.setString(1, fieldToFilter);
            
            ResultSet resultSet = statement.executeQuery();
    
            // Parcourir les résultats
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
    
                // Filtrer et afficher les étudiants par âge
                FilterDisplay.filterStudentsByField(id, firstName, lastName, age);
            }
    
        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }
    

}