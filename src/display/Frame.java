import java.util.Scanner;

public class Frame {
    public static void displayInFrame(String text) {
        String[] lines = text.split("\n");
        int maxLength = 0;
        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }

        String border = "╔" + "═".repeat(maxLength + 2) + "╗";
        String frame = border + "\n";
        for (String line : lines) {
            frame += "║ " + line + " ".repeat(maxLength - line.length()) + " ║\n";
        }
        frame += border.replace('╔', '╚').replace('╗', '╝');

        System.out.println(frame);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int getUserChoice(Scanner input, int maxOption) {
        int choice;
        do {
            System.out.print("> Choose a menu option: ");
            String inputString = input.next();
            if (inputString.matches("[0-" + maxOption + "]")) {
                choice = Integer.parseInt(inputString);
            } else {
                choice = -1;
                System.out.println("Please enter a number between 0 and " + maxOption + ".");
            }
        } while (choice < 0 || choice > maxOption);
        return choice;
    }
}
