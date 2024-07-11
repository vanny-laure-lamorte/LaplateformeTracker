import java.util.Scanner;

public class HomeDisplay {
    private static final StudentRepository tracker = new StudentRepository();
    private static final GradeDisplay gradeDisplay = new GradeDisplay();
    private static final Scanner input = new Scanner(System.in);

    public void homeDisplay() {
        clearScreen();
        int pageNumber = 1;
        int choice = -1;

        do {
            if (pageNumber == 1) {
                displayPageOneMenu();
                choice = getUserChoice();

                switch (choice) {
                    case 1:
                        StudentDisplay.displayTitleStudentInfo();
                        tracker.displayStudent();
                        break;
                    case 2:
                        StudentDisplay.displayAddStudent();
                        break;
                    case 3:
                        StudentDisplay.displayModifyStudent();
                        break;
                    case 4:
                        StudentDisplay.displayDeleteStudent();
                        break;
                    case 5:
                        StudentDisplay.displaySearchStudent();
                        break;
                    case 6:
                    LoginDisplay.userAccount();
                    case 7:
                    FilterDisplay.displayFilters();
                    case 9:
                        pageNumber = 2;
                        clearScreen();
                        break;
                    case 0:
                        System.out.println("Thanks for using La Plateforme Tracker. Goodbye !");
                        break;
                    default:
                        System.out.println("ERROR. Option not available.");
                        break;
                }
            } else if (pageNumber == 2) {
                displayPageTwoMenu();
                choice = getUserChoice();

                switch (choice) {
                    case 1:
                        gradeDisplay.displayGrades();
                        break;
                    case 9:
                        pageNumber = 1;
                        clearScreen();
                        break;
                    case 0:
                        System.out.println("Thanks for using La Plateforme Tracker. Goodbye !");
                        break;
                    default:
                        System.out.println("ERROR. Option not available.");
                        break;
                }
            }
        } while (choice != 0);
        input.close();
    }

    private void displayPageOneMenu() {
        System.out.print(
                "╔═══════════════ LA PLATEFORME TRACKER ═══════════════╗\n" +
                        "║                                                     ║\n" +
                        "║ [1] Display Student       ║  [2] Add a new student  ║\n" +
                        "║ [3] Update student info   ║  [4] Delete student     ║\n" +
                        "║ [5] Search student by ID  ║  [6] Test               ║\n" +
                        "║                                                     ║\n" +
                        "║                                                     ║\n" +
                        "║ [0] Quit                  [9] Next page      1/2    ║\n" +
                        "╚═════════════════════════════════════════════════════╝\n");
    }

    private void displayPageTwoMenu() {
        System.out.print(
                "╔═══════════════ LA PLATEFORME TRACKER ═══════════════╗\n" +
                        "║                                                     ║\n" +
                        "║ [1] Display Student Grade  ║  [2]                   ║\n" +
                        "║ [3]                        ║  [4]                   ║\n" +
                        "║ [5]                        ║  [6]                   ║\n" +
                        "║                                                     ║\n" +
                        "║                                                     ║\n" +
                        "║ [0] Quit                  [9] Next page      2/2    ║\n" +
                        "╚═════════════════════════════════════════════════════╝\n");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private int getUserChoice() {
        int choice;
        do {
            System.out.print("> Choose a menu option: ");
            String inputString = input.next();
            if (inputString.matches("[0-9]")) {
                choice = Integer.parseInt(inputString);
            } else {
                choice = -1;
                System.out.println("Please enter a number between 0 and 9.");
            }
        } while (choice < 0 || choice > 9);
        return choice;
    }
}
