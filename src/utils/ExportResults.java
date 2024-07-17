import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportResults {

    public void exportToCSV(List<Student> students, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("ID,First Name,Last Name,Age,Field,Average Grade\n");
            for (Student student : students) {
                writer.append(String.valueOf(student.getId()))
                      .append(",")
                      .append(student.getFirstName())
                      .append(",")
                      .append(student.getLastName())
                      .append(",")
                      .append(String.valueOf(student.getAge()))
                      .append(",")
                      .append(student.getField())
                      .append(",")
                      .append(student.getAverageGrade())
                      .append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public void exportToPDF(List<Student> students, String filePath) {
        // Placeholder for PDF export logic
        // Implement using a library like iText or Apache PDFBox
    }

    public void exportToHTML(List<Student> students, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("<html><body><table border='1'>")
                  .append("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Age</th><th>Field</th><th>Average Grade</th></tr>");
            for (Student student : students) {
                writer.append("<tr>")
                      .append("<td>").append(String.valueOf(student.getId())).append("</td>")
                      .append("<td>").append(student.getFirstName()).append("</td>")
                      .append("<td>").append(student.getLastName()).append("</td>")
                      .append("<td>").append(String.valueOf(student.getAge())).append("</td>")
                      .append("<td>").append(student.getField()).append("</td>")
                      .append("<td>").append(student.getAverageGrade()).append("</td>")
                      .append("</tr>");
            }
            writer.append("</table></body></html>");
        } catch (IOException e) {
            System.err.println("Error writing HTML file: " + e.getMessage());
        }
    }
}
