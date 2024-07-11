import java.util.Scanner;

public class FilterDisplay {
        private static final Scanner input = new Scanner(System.in);

    // --- FILTER ---//

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
                            filterStudentsByFirstName(); // sort by first name
                            break;
                        case 2: 
                            filterStudentsByLastName();  // sort by last name
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
                        "[4] Sort by field \n" +
                        "> Please your filter option: ");
        int filterSortingStudents = input.nextInt();
        input.nextLine();

        return filterSortingStudents;
    }

    // Method to filter by first name
    public static void filterStudentsByFirstName() {

        System.out.print(
                "╔═══════════════════════════════════════════════════════╗\n" +
                "║                 FILTER STUDENTS BY FIRST NAME         ║\n" +
                "╚═══════════════════════════════════════════════════════╝\n" +
                "> Enter the first name to filter students:");

        String filterName = input.nextLine();

        System.out.println("Students with the first name: " + filterName);

    }

    // Method to filter by last name
    public static void filterStudentsByLastName() {
          System.out.print(
            "╔═══════════════════════════════════════════════════════╗\n" +
            "║                 FILTER STUDENTS BY LAST NAME          ║\n" +
            "╚═══════════════════════════════════════════════════════╝\n" +
            "> Enter the last name to filter students: ");

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
        System.out.println("Your choice is: " + choiceAscDesc );
    }

  // Method to filter by field
  public static void filterStudentsByField() {

    // Display header for filtering by last name
    System.out.print(
      "╔═══════════════════════════════════════════════════════╗\n" +
      "║                FILTER STUDENTS BY FIELD               ║\n" +
      "╚═══════════════════════════════════════════════════════╝\n" +      
      "> Which specialty's student list do you want ? \n"+
      "[1] Software \n"+
      "[2] Cyber \n"+
      "[3] IA \n"+
      "[4] Web \n"+
      "[5] DPO \n");

      int choiceField = input.nextInt(); 
      input.nextLine(); 

    // ! debug 
    System.out.println("Your choice is: " + choiceField );

    }

}

