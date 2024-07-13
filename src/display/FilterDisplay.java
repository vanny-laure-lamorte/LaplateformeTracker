import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FilterDisplay {
    private static final Scanner input = new Scanner(System.in);
    private static FilterRepository filterRepository = new FilterRepository();


    public static void displayFilters() {
        int choice = -2;
        do {
            System.out.print("\n" +
                    "       ╔═════════════════════ FILTER SECTION ═══════════════════╗\n" +
                    "       ║                                                        ║\n" +
                    "       ║ [1] Sorting students       ║  [2] Advanced search      ║\n" +
                    "       ║ [3] Statistics             ║  [4] Data import/export   ║\n" +
                    "       ║ [5] Pagination             ║  [6]  Export results      ║\n" +
                    "       ║                                                        ║\n" +
                    "       ║                                                        ║\n" +
                    "       ║ [0]  Quit                                              ║\n" +
                    "       ╚════════════════════════════════════════════════════════╝\n");

            do {
                System.out.print("> Chose a filter option : ");
                String inputString = input.next();
                if (inputString.matches("[0-6]")) {
                    choice = Integer.parseInt(inputString);
                    input.nextLine();
                } else {
                    choice = -1;
                    System.out.print("Please enter a number between 0 et 6 \n");
                }

            } while (choice < 0 || choice > 6);

            switch (choice) {

                case 1:
                    int choiceSortingStudent = filterSortingStudents();
                    switch (choiceSortingStudent) {
                        case 1:
                        filterRepository.getStudentsOrderedByFirstName(); // sort by first name
                            break;
                        case 2:
                            filterRepository.getStudentsOrderedByLastName(); // sort by last name
                            break;
                        case 3:
                            filterStudentsByAge(); // sort by age
                        case 4:
                            filterStudentsByField(); // sort by field
                    }

                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Thanks for using La Plateforme Tracker. Goodbye !");
                    break;
                default:
                    System.out.println("ERROR. Filter option not available.");
                    break;
            }
        } while (choice != 0);
    }

    public static int filterSortingStudents() {

        // Display header for filtering by first name
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                      SORTING STUDENTS                 ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "\n[1] Sort by first Name\n" +
                        "[2] Sort by last Name \n" +
                        "[3] Sort by age \n" +
                        "[4] Sort by field \n \n" +
                        "> Please your filter option: ");
        int filterSortingStudents = input.nextInt();
        input.nextLine();
        System.out.println();

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
        System.out.println( "Prénom: " + firstName + " | " + "Nom: " + lastName +  " | " + "Field: " + field + " | " + "Age: " + age + " | " + " ID: " + id   );    
    }
                        
    // Method to filter students by last name
    public static void filterStudentsByLastName(int id, String firstName, String lastName, String field, int age) {    
        System.out.println("Nom: " + lastName + " | " + "Prénom: " + firstName + " | " + "Field: " + field + " | " + "Age: " + age + " | " + " ID: " + id   );    
    }

    // Method to filter by age
    public static void filterStudentsByAge() {

        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                 FILTER STUDENTS BY AGE                ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "> Do you want to sort the students by ascending [1] or descending age [2] ?");
        int choiceAscDesc = input.nextInt();
        input.nextLine();

        // ! debug
        System.out.println("Your choice is: " + choiceAscDesc);
    }

    // Method to filter by field
    public static void filterStudentsByField() {

        // Display header for filtering by last name
        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                        "║                FILTER STUDENTS BY FIELD               ║\n" +
                        "╚═══════════════════════════════════════════════════════╝\n" +
                        "> Which specialty's student list do you want ? \n" +
                        "[1] Software \n" +
                        "[2] Cyber \n" +
                        "[3] IA \n" +
                        "[4] Web \n" +
                        "[5] DPO \n");

        int choiceField = input.nextInt();
        input.nextLine();

        // ! debug
        System.out.println("Your choice is: " + choiceField);

    }

}
