import java.util.Scanner;

public class HomeDisplay {
    public static final FilterRepository filter = new FilterRepository();
    protected static Scanner input;

    public HomeDisplay(Scanner input) {
        HomeDisplay.input = input;
    }

    public void homeDisplay() {
        Frame.clearScreen();
        int pageNumber = 1;
        int choice = -1;

        do {
            if (pageNumber == 1) {
                Frame.clearScreen();
                displayPageOneMenu();
                choice = Frame.getUserChoice(input, 9);

                switch (choice) {
                    case 1:
                       // StudentDisplay.displayTitleStudentInfo();
                        StudentDisplay.displayAllStudents();
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
                        StudentRepository.updateAverageGrades();
                        // LoginDisplay.userAccount();
                        break;
                    case 7:

                    case 9:
                        pageNumber = 2;
                        Frame.clearScreen();
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
                choice = Frame.getUserChoice(input, 9);

                switch (choice) {
                    case 1:
                        FilterDisplay.displayFilters();
                        break;

                    case 2:
                        int choiceAdvancedSearch = FilterDisplay.AdvancedSearchOptions();
                        switch (choiceAdvancedSearch) {
                            case 1:
                                FilterDisplay.getAdvancedSearchFirstName();
                                break;
                            case 2:
                                FilterDisplay.getAdvancedSearchLastName();
                                break;
                            case 3:
                                FilterDisplay.getAdvancedSearchAge();
                                break;
                        }
                        break;
                    case 3:
                        int choiceStatistic = FilterDisplay.statisticsMenu();
                        switch (choiceStatistic) {
                            case 1:
                                FilterDisplay.staticsAge();
                                break;
                            case 2:
                                FilterDisplay.staticsField();
                                break;
                            case 3:
                                FilterDisplay.staticsGrade();
                                break;
                        }

                        break;

                    case 6:
                        break;
                    case 9:
                        pageNumber = 1;
                        Frame.clearScreen();
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
    }

    private void displayPageOneMenu() {
        System.out.print(
                "╔═══════════════ LA PLATEFORME TRACKER ══════════════════╗\n" +
                "║                                                        ║\n" +
                "║  [1] Display Student       ║  [5] Add grade            ║\n" +
                "║  [2] Update Student Info   ║  [6] Delete grade         ║\n" +
                "║  [3] Add a New Student     ║  [7] Update grade         ║\n" +
                "║  [4] Delete a new student  ║  [8] Search Student by ID ║\n" +
                "║                                                        ║\n" +
                "║  [0] Quit                   [9] Next page      1/2     ║\n" +
                "╚════════════════════════════════════════════════════════╝\n");
    }

    private void displayPageTwoMenu() {
        System.out.print("\n" +
                "╔═══════════════ LA PLATEFORME TRACKER ══════════════════╗\n" +
                "║                                                        ║\n" +
                "║ [1] Sorting students      ║  [4] Export results        ║\n" +
                "║ [2] Advanced search       ║  [5] Data import/export    ║\n" +
                "║ [3] Statistics            ║  [6] Log Out               ║\n" +
                "║                                                        ║\n" +
                "║                                                        ║\n" +
                "║ [0] Quit                  [9] Previous page      2/2   ║\n" +
                "╚════════════════════════════════════════════════════════╝\n");
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
