import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilterRepository {

    private Database database = new Database();

    public void getStudentsOrderedByLastName() {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM student ORDER BY lastName")) {
    
            ResultSet resultSet = statement.executeQuery();
    
            // Parcourir les résultats et afficher les étudiants
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
    
                // Utilisation de la méthode d'affichage de FilterDisplay
                FilterDisplay.filterStudentsByLastName(id, firstName, lastName);
            }
    
        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }
}