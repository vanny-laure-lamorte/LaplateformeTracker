import java.util.Scanner;

public class Main {
    public static Scanner input;

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            HomeDisplay display = new HomeDisplay(input);
            LoginDisplay login = new LoginDisplay(input);
            if (login.userAccount())
                display.homeDisplay();
        }
    }
}