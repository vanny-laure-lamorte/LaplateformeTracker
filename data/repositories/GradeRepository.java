import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GradeRepository {
    private Database database = new Database();

    public List<Grade> getGradesByStudentId(int studentID) {
        List<Grade> grades = new ArrayList<>();
        String selectSql = "SELECT * FROM grade WHERE studentId = ?";

        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grade grade = new Grade(
                        resultSet.getInt("studentId"),
                        resultSet.getString("subjectName"),
                        resultSet.getDouble("grade")
                );
                grades.add(grade);
            }
        } catch (SQLException exception) {
            System.err.println("Error connecting to database or executing query: " + exception.getMessage());
        }
        return grades;
    }
}
