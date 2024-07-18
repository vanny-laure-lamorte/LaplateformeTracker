import java.util.Scanner;

public class Main {
    public static Scanner input;

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            HomeDisplay display = new HomeDisplay(input);
            StudentRepository studentRepo = new StudentRepository();
            // Start the AutoBackup in a separate thread
            AutoBackup autoBackup = new AutoBackup(studentRepo);
            Thread backupThread = new Thread(() -> autoBackup.startBackupSchedule(60)); // Backup every 60 seconds
            backupThread.setDaemon(true); // Ensure it doesn't block JVM shutdown
            backupThread.start();
            display.homeDisplay();
        }
    }
}