import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GradeRepository {
    private static Database database = new Database();

    public List<Grade> getGradesByStudentId(int studentID) {
        List<Grade> grades = new ArrayList<>();
        String selectSql = "SELECT * FROM grades WHERE studentId = ?";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grade grade = new Grade(
                        resultSet.getInt("id"),
                        resultSet.getInt("studentId"),
                        resultSet.getString("subjectName"),
                        resultSet.getDouble("grade"));
                grades.add(grade);
            }
        } catch (SQLException exception) {
            System.err.println("Error connecting to database or executing query: " + exception.getMessage());
        }
        return grades;
    }

    public int setAverageGrades(int studentID) throws SQLException {
        String selectSql = "SELECT grade FROM grades WHERE studentId = ?";
        String updateSql = "UPDATE student SET averageGrade = ? WHERE id = ?";

        double totalGrades = 0.0;
        int gradeCount = 0;

        try (Connection connection = database.connect();
                PreparedStatement selectStatement = connection.prepareStatement(selectSql);
                PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            selectStatement.setInt(1, studentID);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    totalGrades += resultSet.getDouble("grade");
                    gradeCount++;
                }
            }

            double averageGrade = Math.round(gradeCount == 0 ? 0.0 : totalGrades / gradeCount * 100.0) / 100.0;
            ;

            updateStatement.setDouble(1, averageGrade);
            updateStatement.setInt(2, studentID);
            return updateStatement.executeUpdate();
        }
    }

    // Get all grades in the database
    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        String selectSql = "SELECT * FROM grades";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(selectSql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Grade grade = new Grade(
                        resultSet.getInt("Id"),
                        resultSet.getInt("studentId"),
                        resultSet.getString("subjectName"),
                        resultSet.getDouble("grade"));
                grades.add(grade);
            }
        } catch (SQLException exception) {
            System.err.println("Error connecting to database or executing query: " + exception.getMessage());
        }
        return grades;
    }

    // Method to delete a grade
    public boolean deleteGradeById(int id) {
        String deleteSql = "DELETE FROM grades WHERE Id = ?";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            // Vérifier si une ligne a été supprimée
            return rowsAffected > 0;
        } catch (SQLException exception) {
            System.err.println("Error connecting to database or executing query: " + exception.getMessage());
            return false;
        }
    }

    // Method to update a grade info
    public int updateGrade(int gradeId, int newGrade) throws SQLException {
        String updateSql = "UPDATE grades SET grade = ? WHERE id = ?";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(updateSql)) {

            statement.setDouble(1, newGrade);
            statement.setInt(2, gradeId);

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Error updating grade: " + exception.getMessage());
            throw exception;
        }
    }

    // Method to add a grade

    public int addGrade(int studentId, String subjectName, double grade) throws SQLException {
        String insertSql = "INSERT INTO grades (studentId, subjectName, grade) VALUES (?, ?, ?)";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(insertSql)) {

            // Définir les paramètres de la requête
            statement.setInt(1, studentId);
            statement.setString(2, subjectName);
            statement.setDouble(3, grade);

            // Exécuter la requête d'insertion
            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Error adding grade: " + exception.getMessage());
            throw exception;
        }
    }

    public Grade getGrade(int id) {
        String selectSql = "SELECT * FROM grades WHERE id = ?";
        Grade grade = null;

        try (Connection connection = database.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int gradeId = resultSet.getInt("id");
                int studentId = resultSet.getInt("studentId");
                String courseName = resultSet.getString("subjectName");
                double gradeValue = resultSet.getDouble("grade");

                grade = new Grade(gradeId, studentId, courseName, gradeValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

}
