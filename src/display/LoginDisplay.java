import java.util.Scanner;

public class LoginDisplay extends HomeDisplay{
    public LoginDisplay(Scanner input) {
        super(input);
    }

    private static final LoginRepository tracker = new LoginRepository();

    public static void userAccount() {
        String userPassword = "";
        String userLogin = "";
        
        // Ask the user if he has an account
        System.out.print("> Do you have an account with us (Y/N)? ");
        String inputAccount = input.nextLine();
        
        // System.out.print("Hello: ");
        
        // Get the user Password and Email
        if (inputAccount.equals("Y")) {
            System.out.print("> Please enter your email: ");
            userLogin = input.nextLine();
            System.out.print("> Please enter your password: ");
            userPassword = input.nextLine();

            // Check login credentials
            boolean loginSuccessful = Login.checkLoginCredentials(userLogin, userPassword);

            if (loginSuccessful) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid email or password.");
            }
        } else if (inputAccount.equalsIgnoreCase("N")) {

            // Ask the user to enter his account details
            System.out.print("> Please enter your email: ");
            userLogin = input.nextLine();
            System.out.print("> Please enter your password: ");
            userPassword = input.nextLine();
            int studentID = 0;

            // Hash the user's password
            String hashedNewPassword = Login.hashPassword(userPassword);

            // Register the new user using the method in Login class
            boolean registrationSuccessful = tracker.registerUser(studentID, userLogin, hashedNewPassword);

            if (registrationSuccessful) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed. Try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
        }
    }
}
