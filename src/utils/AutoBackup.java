import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AutoBackup {

    private final StudentRepository studentRepo;
    private Timer timer;

    public AutoBackup(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void startBackupSchedule(int interval) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                backupData();
            }
        }, 0, interval * 1000L);
    }

    private void backupData() {
        String dateTime = getCurrentDateTimeString();
        List<Student> students = studentRepo.getAllStudents();
        String backupFilePath = "files\\backup\\backup.csv";
        try {
            ExportResults.exportToCSV(students, backupFilePath);
            insertLineAtTop(backupFilePath, dateTime);
        } catch (Exception e) {
            System.err.println("Error backing up data: " + e.getMessage());
        }
    }
        private String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Backup Date and Time: " + now.format(formatter);
    }

    private void insertLineAtTop(String filePath, String lineToInsert) throws IOException {
        // Read existing file contents
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        // Insert new line at the top
        lines.add(0, lineToInsert);
        // Write updated contents back to the file
        Files.write(Paths.get(filePath), lines);
    }
}