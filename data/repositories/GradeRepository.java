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
        String selectSql = "SELECT * FROM grade WHERE studentId = ?";

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grade grade = new Grade(
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

    public static int setAverageGrades(int studentID) throws SQLException {
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

}