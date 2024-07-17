import java.util.Scanner;

public class LoginDisplay extends HomeDisplay {
    public LoginDisplay(Scanner input) {
        super(input);
    }

    private final LoginRepository tracker = new LoginRepository();

    public boolean userAccount() {
        String userPassword = "";
        String userLogin = "";

        // Ask the user if he has an account
        String inputAccount;
        Frame.clearScreen();
        Frame.displayInFrame("\n          WELCOME TO LA PLATEFORME TRACKER          \n ");
        while (true) {
            System.out.print("\n> Do you have an account with us (Y/N)? ");
            inputAccount = input.nextLine();
            if (InputValidator.isValidYesNo(inputAccount)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter only Y or N.");
            }
        }

        // Get the user Password and Email
        if (inputAccount.equalsIgnoreCase("Y")) {
            System.out.print("> Please enter your email: ");
            userLogin = input.nextLine();
            System.out.print("> Please enter your password: ");
            userPassword = input.nextLine();

            // Check login credentials
            boolean loginSuccessful = Login.checkLoginCredentials(userLogin, userPassword);

            if (loginSuccessful) {
                String choice;
                do {
                    Frame.displayInFrame("Login successful!" + "\n\nPress Enter to continue");
                    choice = input.nextLine();
                } while (!choice.equalsIgnoreCase(""));
                return true;
            } else {
                String choice;
                while (true) {
                    Frame.displayInFrame(
                            "Invalid email or password." + "\n\n Do you wish to retry ?" + "\n [Y] Yes   [N] No");
                    choice = input.nextLine();
                    if (InputValidator.isValidYesNo(choice)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter only Y or N.");
                    }
                }
                if (inputAccount.equalsIgnoreCase("Y")) {
                    userAccount();
                } else if (inputAccount.equalsIgnoreCase("N")) {
                    return false;
                }
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
                String choice;
                do {
                    Frame.displayInFrame("Registration successful!" + "\n\nPress Enter to continue");
                    choice = input.nextLine();
                } while (!choice.equalsIgnoreCase(""));
                return true;
            } else {
                System.out.println("Registration failed. Try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
        }
        return false;
    }
}
