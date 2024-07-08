import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        PlateformeTracker tracker = new PlateformeTracker();

        //--- DISPLAY USER INFO ---//
        tracker.displayStudent();
        
        //--- ADD NEW USER ---//
        System.out.println(
            "╔═══════════════════════════════════════════════════════╗\n" +
            "║                    ADD A NEW STUDENT                  ║\n" +
            "╚═══════════════════════════════════════════════════════╝\n");    
        Scanner input = new Scanner(System.in);
        System.out.print("> Enter student's first name: ");
        String newFirstName = input.nextLine();
        System.out.print("> Enter student's last name: ");
        String newLastName = input.nextLine();
        System.out.print("> Enter student's age: ");
        int newAge = input.nextInt();
        input.nextLine();
        System.out.print("> Enter student's field: ");
        String newField= input.nextLine();
        double newAverageGrade = 0;

        try {
            
            int result = tracker.addStudent(newFirstName, newLastName, newAge, newField, newAverageGrade);
            
            if (result !=0) {

                System.out.println(   
                    "First Name: " + newFirstName+ 
                    " | Last Name: " + newLastName + 
                    " | Field: " +  newField + 
                    " | Age : " +  newAge +"\n");
            } else {

            System.out.println("ERROR. no student added.");
        }}
                
        catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
          
        


    // Close scanner
    input.close();

}
}
