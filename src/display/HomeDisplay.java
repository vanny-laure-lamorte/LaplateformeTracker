import java.util.Scanner;

public class HomeDisplay {
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
                input.nextLine();

                switch (choice) {
                    case 1:
                        StudentDisplay.displayAllStudents();
                        break;
                    case 2:
                        StudentDisplay.displayModifyStudent();
                        break;
                    case 3:
                        StudentDisplay.displayAddStudent();
                        break;
                    case 4:
                        StudentDisplay.displayDeleteStudent();
                        break;
                    case 7:
                        StudentRepository.updateAverageGrades();
                        break;
                    case 8:
                        StudentDisplay.displayStudentById();
                        break;
                    case 9:
                        pageNumber = 2;
                        break;
                    case 0:
                        System.out.println("Thanks for using La Plateforme Tracker. Goodbye !");
                        break;
                    default:
                        System.out.println("ERROR. Option not available.");
                        break;
                }
            } else if (pageNumber == 2) {
                // Frame.clearScreen();
                displayPageTwoMenu();
                choice = Frame.getUserChoice(input, 9);
                input.nextLine();

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

}
