import java.util.Scanner;

public class LoginDisplay extends HomeDisplay {
    public LoginDisplay(Scanner input) {
        super(input);
    }

    private final LoginRepository tracker = new LoginRepository();

    public boolean userAccount() {
        boolean registrationSuccessful = false;
        String userPassword="";
        String userLogin;

        while (true) {
            // Ask the user if he has an account
            Frame.clearScreen();
            Frame.displayInFrame("\n          WELCOME TO LA PLATEFORME TRACKER          \n ");
            String inputAccount;
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
                    Frame.displayInFrame("Login successful!" + "\n\nPress Enter to continue");
                    input.nextLine(); // Wait for user to press enter
                    return true;
                } else {
                    String choiceF;
                    while (true) {
                        Frame.displayInFrame(
                                "Invalid email or password." + "\n\n Do you wish to retry ?" + "\n [Y] Yes   [N] No");
                        choiceF = input.nextLine();
                        if (InputValidator.isValidYesNo(choiceF)) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter only Y or N.");
                        }
                    }
                    if (choiceF.equalsIgnoreCase("N")) {
                        return false;
                    }
                    // If choiceF is "Y", loop will restart and ask for credentials again
                }
            } else if (inputAccount.equalsIgnoreCase("N")) {

                // Ask the user to enter his account details
                System.out.print("> Please enter your email: ");
                while(true) {
                    System.out.print("> Please enter your email: ");
                    userLogin = input.nextLine();
                    if (InputValidator.isValidEmail(userLogin)) {
                        System.out.print("> Please enter your password: ");
                        userPassword = input.nextLine();
                        if (InputValidator.isValidPassword(userPassword)) {
                            break;
                        } else {
                        System.out.println("Invalid password. Please enter a valid password.");
                    }
                    } else {
                        System.out.println("Invalid email. Please enter a valid email.");
                    }
                }
                int studentID = 0;

                // Hash the user's password
                String hashedNewPassword = Login.hashPassword(userPassword);

                // Register the new user using the method in Login class
                registrationSuccessful = tracker.registerUser(studentID, userLogin, hashedNewPassword);

                if (registrationSuccessful) {
                    Frame.displayInFrame("Registration successful!" + "\n\nPress Enter to continue");
                    input.nextLine(); // Wait for user to press enter
                    return true;
                } else {
                    System.out.println("Registration failed. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
    }
}
