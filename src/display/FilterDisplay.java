import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterDisplay extends HomeDisplay {
    public FilterDisplay(Scanner input) {
        super(input);
    }

    public static FilterRepository filterRepository = new FilterRepository();

    public static void displayFilters() {

        int choiceSortingStudent = filterSortingStudents();
        switch (choiceSortingStudent) {
            case 1:
                filterRepository.getStudentsOrderedByFirstName(); // sort by first name
                break;
            case 2:
                filterRepository.getStudentsOrderedByLastName(); // sort by last name
                break;
            case 3:
                filterRepository.getStudentsOrderedByAge(); // sort by age
                break;
            case 4:
                int choiceField = filterFieldOptions();
                if (choiceField != -1) {
                    ArrayList<String> array = new ArrayList<>(
                            Arrays.asList("Software", "Cyber", "IA", "Web", "DPO", "Immersive Systems"));
                    String selectedField = array.get(choiceField - 1);
                    filterRepository.getStudentsOrderedField(selectedField);
                }
                break;
            case 5:
                filterRepository.getStudentsOrderedGrade(); // sort by grade
                break;
        }
    }

    // --- SORTING STUDENTS ---//

    public static int filterSortingStudents() {
        ArrayList<String> array = new ArrayList<>(
                Arrays.asList("FIRST NAME", "LAST NAME", "AGE", "FIELD", "GRADE"));

        // Display header for filtering by first name
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                      SORTING STUDENTS                 ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "\n[1] Sort by first Name\n" +
                        "[2] Sort by last Name \n" +
                        "[3] Sort by age \n" +
                        "[4] Sort by field \n" +
                        "[5] Sort by grade \n \n" +
                        "> Please your filter option: ");

        int filterSortingStudents = -1;

        while (true) {
            String userInput = input.nextLine();
            if (InputValidator.isValidDigit(userInput)) {
                filterSortingStudents = Integer.parseInt(userInput);
                if (filterSortingStudents >= 1 && filterSortingStudents <= 5) {
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter a number between 1 and 5: ");
                }
            } else {
                System.out.print("Invalid input. Please enter digits only: ");
            }
        }

        String selectedChoice = array.get(filterSortingStudents - 1);

        System.out.print("\n---------------------------\n" +
                "SORTING BY " + selectedChoice + " \n" +
                "---------------------------\n \n");

        return filterSortingStudents;
    }

    // Method to display students from a ResultSet
    public static void displayStudent(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println(
                        "Id: " + resultSet.getString("id") +
                                " | First Name: " + resultSet.getString("firstName") +
                                " | Last Name: " + resultSet.getString("lastName") +
                                " | Field: " + resultSet.getString("field") +
                                " | Age: " + resultSet.getInt("age") +
                                " | Average Grade: " + resultSet.getDouble("averageGrade"));
            }
            System.out.println();
        } catch (SQLException exception) {
            System.err.println("Error displaying students: " + exception.getMessage());
        }
    }

    // Method to filter students by first name
    public static void filterStudentsByFirstName(int id, String firstName, String lastName, String field, int age) {
        System.out.println("Prénom: " + firstName + " | " + "Nom: " + lastName + " | " + "Field: " + field + " | "
                + "Age: " + age + " | " + " ID: " + id);
    }

    // Method to filter students by last name
    public static void filterStudentsByLastName(int id, String firstName, String lastName, String field, int age,
            double grade) {
        System.out.println("Nom: " + lastName + " | " + "Prénom: " + firstName + " | " + "Field: " + field + " | "
                + "Age: " + age + " | " + "Average Grade: " + grade + " | " + " ID: " + id);
    }

    // Method to filter by age
    public static void filterStudentsByAge(int id, String firstName, String lastName, String field, int age,
            double grade) {
        System.out.println("Age: " + age + " | " + "Nom: " + lastName + " | " + "Prénom: " + firstName + " | "
                + "Field: " + field + " | " + "Grade: " + grade + " | " + " ID: " + id);
    }

    // Method to allow the user to sort by field
    public static int filterFieldOptions() {

        // Display header for filtering by field
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                FILTER STUDENTS BY FIELD               ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "[1] Software \n" +
                        "[2] Cyber \n" +
                        "[3] IA \n" +
                        "[4] Web \n" +
                        "[5] DPO \n" +
                        "[6] Immersive Systems \n " +
                        "> Please choose a specialty: ");

        while (true) {

            String userInput = input.nextLine();
            if (InputValidator.isValidDigit(userInput)) {
                int choiceField = Integer.parseInt(userInput);
                if (choiceField >= 1 && choiceField <= 6) {
                    System.out.println();
                    return choiceField;
                } else {
                    System.out.print("Please enter a number between 1 and 6: ");
                }
            } else {
                System.out.print("Invalid input. Please enter a digit: ");
            }
        }
    }

    // Display students according to their field
    public static void filterStudentsByField(int id, String firstName, String lastName, int age, double grade) {
        System.out.println(
                "Prénom: " + firstName + " | " + "Nom: " + lastName + " | " + "Age: " + age + " | " + "Grade: " + grade
                        + " | " + " ID: " + id);
    }

    // Method to filter by average Grade
    public static void filterStudentsByGrade(int id, String firstName, String lastName, String field, int age,
            double grade) {
        System.out.println("Average Grade: " + grade + " | " + "Nom: " + lastName + " | " + "Prénom: " + firstName
                + " | " + "Field: " + field + " | "
                + "Age: " + age + " | " + " ID: " + id);
    }

    // --- ADVANCED SEARCH ---//

    public static int AdvancedSearchOptions() {

        // Display header for filtering by first name, last name or age
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                         ADVANCED SEARCH               ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +

                        "[1] First Name \n" +
                        "[2] Last Name \n" +
                        "[3] Age \n" +
                        "> Please choose the advanced search type ? ");

        int choiceAdvancedSearch = input.nextInt();
        input.nextLine();
        System.out.println();
        return choiceAdvancedSearch;
    }

    public static void getAdvancedSearchFirstName() {
        System.out.print("> Please add a first name: ");
        String filterFirstName = input.nextLine();
        filterRepository.getAdvancedSearchByFirstName(filterFirstName);
        System.out.println();
    }

    public static void getAdvancedSearchLastName() {
        System.out.print("> Please add a last name: ");
        String filterLastName = input.nextLine();
        filterRepository.getAdvancedSearchByLastName(filterLastName);
        System.out.println();
    }

    public static void getAdvancedSearchAge() {
        System.out.print("> Please add an age: ");
        int filterAge = input.nextInt();
        input.nextLine();
        filterRepository.getAdvancedSearchByAge(filterAge);
        System.out.println();
    }

    // --- STATISTICS ---//
    public static int statisticsMenu() {

        // Display a menu to select the type of statistics
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                          STATISTICS                   ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "[1] Student age range \n" +
                        "[2] Number of students per specialty \n" +
                        "[3] Students passing or failing \n \n" +
                        "> Please choose an option: ");

        int choiceStatistic = input.nextInt();
        input.nextLine();
        System.out.println();
        return choiceStatistic;
    }

    public static void staticsAge() {

        try {
            // Student under 18
            System.out.println("-----------------\n" +
                    "STUDENTS UNDER 18 \n" +
                    "-----------------");
            filterRepository.getStatisticsByAge(10, 17);
            System.out.println();

            // Student between 18 and 50
            System.out.println("--------------------------\n" +
                    "STUDENTS BETWEEN 18 AND 50  \n" +
                    "--------------------------");
            filterRepository.getStatisticsByAge(18, 50);
            System.out.println();

            // Student above 50
            System.out.println("-----------------\n" +
                    "STUDENTS ABOVE 50\n" +
                    "-----------------");
            filterRepository.getStatisticsByAge(51, 100);
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void staticsField() {

        try {
            // Students in Software
            System.out.println("---------------------\n" +
                    "STUDENTS IN SOFTWARE \n" +
                    "---------------------");
            filterRepository.getStatisticsByField("Software");
            System.out.println();

            // Students in Cyber
            System.out.println("-----------------\n" +
                    "STUDENTS IN CYBER  \n" +
                    "-----------------");
            filterRepository.getStatisticsByField("Cyber");
            System.out.println();

            // Students in IA
            System.out.println("----------------\n" +
                    "STUDENTS IN IA\n" +
                    "----------------");
            filterRepository.getStatisticsByField("IA");
            System.out.println();

            // Students in Web
            System.out.println("----------------\n" +
                    "STUDENTS IN WEB\n" +
                    "----------------");
            filterRepository.getStatisticsByField("Web");
            System.out.println();

            // Students in DPO
            System.out.println("----------------\n" +
                    "STUDENTS IN DPO\n" +
                    "----------------");
            filterRepository.getStatisticsByField("DPO");
            System.out.println();

            // Students in IA
            System.out.println("------------------------------\n" +
                    "STUDENTS IN IMMERSIVE SYSTEMS\n" +
                    "------------------------------");
            filterRepository.getStatisticsByField("Immersive Systems");
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void staticsGrade() {
        try {

            double overallAverageGrade = filterRepository.getStatisticsAverageGrade();

            System.out.println("------------------------------\n" +
                    "STUDENTS AVERAGE GRADE: " + overallAverageGrade + "\n" +
                    "------------------------------\n");

            // Students passing
            System.out.println("-----------------\n" +
                    "STUDENTS PASSING \n" +
                    "-----------------");
            filterRepository.getStatisticsByGrade(10, 20);
            System.out.println();

            // Students falling
            System.out.println("--------------------------\n" +
                    "STUDENTS FALLINGS \n" +
                    "--------------------------");
            filterRepository.getStatisticsByGrade(0, 9);
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
