import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilterRepository {

    // Initialize the database object to manage connections
    private Database database = new Database();

    // --- SORTING STUDENTS ---//

    public void getStudentsOrdered(String filter) {
        String query = "SELECT * FROM student ORDER BY " + filter;

        try (Connection connection = database.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("averageGrade");

                // Display students
                FilterDisplay.filterStudents(filter, id, firstName, lastName, field, age, grade);
            }

        } catch (SQLException exception) {
            System.err.println("Erreur lors de la récupération des étudiants : " + exception.getMessage());
        }
    }

    // Method to retrieve and display students filtered by a specific field and
    // ordered by last name
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
                int grade = resultSet.getInt("averageGrade");

                // Display Students
                FilterDisplay.filterStudentsByField(id, firstName, lastName, age, grade);
            }

        } catch (SQLException exception) {
            System.err.println("No students found for the field: " + fieldToFilter);
        }
    }

    // --- ADVANCED SEARCH ---//

    // Method to retrieve and display students by first name
    public void getAdvancedSearchByFirstName(String firstName) {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM student WHERE firstName = ?")) {

            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("averageGrade");

                // Display students
                FilterDisplay.filterStudents("lastName",id, firstName, lastName, field, age, grade);
            }

        } catch (SQLException exception) {
            System.err.println("ERROR " + exception.getMessage());
        }
    }

    public void getAdvancedSearchByLastName(String lastName) {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM student WHERE lastName = ?")) {

            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String field = resultSet.getString("field");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("averageGrade");

                // Display students
                FilterDisplay.filterStudents("lastName", id, firstName, lastName, field, age, grade);
            }
        } catch (SQLException exception) {
            System.err.println("ERROR " + exception.getMessage());
        }
    }

    public void getAdvancedSearchByAge(int age) {
        try (Connection connection = database.connect();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM student WHERE age = ?")) {

            statement.setInt(1, age);
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and display each student
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String field = resultSet.getString("field");
                double grade = resultSet.getDouble("averageGrade");

                // Display students
                FilterDisplay.filterStudents("lastName",id, firstName, lastName, field, age, grade);
            }
        } catch (SQLException exception) {
            System.err.println("ERROR " + exception.getMessage());
        }
    }

    // --- STATISTICS ---//

    // Method to sort student by age gap
    public void getStatisticsByAge(int ageStart, int ageEnd) throws SQLException {
        try (Connection connection = database.connect()) {
            String insertSql = "SELECT id, firstName, lastName, field, age, averageGrade FROM student WHERE age between ? AND ? ORDER BY age";
            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setInt(1, ageStart);
                statement.setInt(2, ageEnd);

                ResultSet resultSet = statement.executeQuery();

                // Iterate through the result set and display each student
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String field = resultSet.getString("field");
                    int age = resultSet.getInt("age");
                    double grade = resultSet.getDouble("averageGrade");

                    // Display students
                    FilterDisplay.filterStudents("lastName", id, firstName, lastName, field, age, grade);
                }
            }
        }
    }

    // Method to sort student by field
    public void getStatisticsByField(String inputField) throws SQLException {
        try (Connection connection = database.connect()) {
            // First query to get the count of students
            String countSql = "SELECT COUNT(*) AS studentCount FROM student WHERE field = ?";
            try (PreparedStatement countStatement = connection.prepareStatement(countSql)) {
                countStatement.setString(1, inputField);
                ResultSet countResultSet = countStatement.executeQuery();

                if (countResultSet.next()) {
                    int studentCount = countResultSet.getInt("studentCount");

                    // Display the count of students
                    System.out.println("Number of students in " + inputField + ": " + studentCount);
                }
            }

            // Second query to get the student details
            String detailsSql = "SELECT * FROM student WHERE field = ? ORDER BY lastName";
            try (PreparedStatement detailsStatement = connection.prepareStatement(detailsSql)) {
                detailsStatement.setString(1, inputField);
                ResultSet detailsResultSet = detailsStatement.executeQuery();

                // Iterate through the result set and display each student
                while (detailsResultSet.next()) {
                    int id = detailsResultSet.getInt("id");
                    String firstName = detailsResultSet.getString("firstName");
                    String lastName = detailsResultSet.getString("lastName");
                    int age = detailsResultSet.getInt("age");
                    double grade = detailsResultSet.getDouble("averageGrade");

                    // Display students
                    FilterDisplay.filterStudentsByField(id, firstName, lastName, age, grade);
                }
            }
        }
    }

    // Method to sort student by grade
    public void getStatisticsByGrade(int grade1, int grade2) throws SQLException {
        try (Connection connection = database.connect()) {
            String insertSql = "SELECT * FROM student WHERE averageGrade between ? AND ? ORDER BY averageGrade";
            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setInt(1, grade1);
                statement.setInt(2, grade2);

                ResultSet resultSet = statement.executeQuery();

                // Iterate through the result set and display each student
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String field = resultSet.getString("field");
                    int age = resultSet.getInt("age");
                    double grade = resultSet.getDouble("averageGrade");

                    // Display students
                    FilterDisplay.filterStudents("averageGrade", id, firstName, lastName, field, age, grade);
                }
            }
        }
    }

    // Method to get the average grade
    public double getStatisticsAverageGrade() {
        double averageGrade = 0.0;
        try (Connection connection = database.connect()) {
            String selectSql = "SELECT averageGrade FROM student";
            try (PreparedStatement statement = connection.prepareStatement(selectSql)) {

                ResultSet resultSet = statement.executeQuery();

                double totalGrade = 0;
                int count = 0;

                while (resultSet.next()) {
                    double grade = resultSet.getDouble("averageGrade");
                    totalGrade += grade;
                    count++;
                }

                if (count > 0) {
                    averageGrade = Math.round(count == 0 ? 0.0 : totalGrade / count * 100.0) / 100.0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageGrade;
    }

}
