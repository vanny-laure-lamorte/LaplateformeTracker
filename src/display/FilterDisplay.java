import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

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
            int filterSortingStudents = input.nextInt();
            input.nextLine();
            
            String selectedChoice = array.get(filterSortingStudents - 1);
        // To modify
        System.out.print("\n═════ SORTING BY " + selectedChoice + "  ══════" + "\n\n");

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
    public static void filterStudentsByAge(int id, String firstName, String lastName, String field, int age) {
        System.out.println("Age: " + age + " | " + "Nom: " + lastName + " | " + "Prénom: " + firstName + " | "
                + "Field: " + field + " | " + " ID: " + id);
    }

    // Method to allow the user to sort by field
    public static int filterFieldOptions() {
        // Display header for filtering by last name
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                FILTER STUDENTS BY FIELD               ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "[1] Software \n" +
                        "[2] Cyber \n" +
                        "[3] IA \n" +
                        "[4] Web \n" +
                        "[5] DPO \n" +
                        "[6] Immersive Systems \n" +
                        "> Which specialty's student list do you want ? ");

        try {
            int choiceField = input.nextInt();
            input.nextLine(); // Consume newline

            if (choiceField < 1 || choiceField > 6) {
                System.out.println("Invalid choice. Please select a number between 1 and 6.");
                return -1;
            }

            System.out.println();
            return choiceField;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 6.");
            input.nextLine(); // Consume invalid input
            return -1;
        }
    }

    // Display students according to their field
    public static void filterStudentsByField(int id, String firstName, String lastName, int age) {
        System.out.println(
                "Prénom: " + firstName + " | " + "Nom: " + lastName + " | " + "Age: " + age + " | " + " ID: " + id);
        System.out.println("Banane");
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

        // Display header for filtering by last name
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
        System.out.print("Please add a first name: ");
        String filterFirstName = input.nextLine();
        filterRepository.getAdvancedSearchByFirstName(filterFirstName);
        System.out.println();
    }

    public static void getAdvancedSearchLastName() {
        System.out.print("Please add a last name: ");
        String filterLastName = input.nextLine();
        filterRepository.getAdvancedSearchByLastName(filterLastName);
        System.out.println();
    }

    public static void getAdvancedSearchAge() {
        System.out.print("Please add an age: ");
        int filterAge = input.nextInt();
        input.nextLine();
        filterRepository.getAdvancedSearchByAge(filterAge);
        System.out.println();
    }

    // --- STATISTICS ---//
    public static void statisticsMenu() {

        // Display a menu to select the type of statistics
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                          STATISTICS                   ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "[1] Student age range \n" +
                        "[2] Number of students per specialty \n" +
                        "[3] Students passing or failing \n \n" +
                        "> Please choose an option: ");

        int choiceStatisticSearch = input.nextInt();
        input.nextLine();
        System.out.println();

    }

}
