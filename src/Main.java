import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static DisplayState[] VIEW_STATE = new DisplayState[] {
        DisplayState.HOME,
        DisplayState.STUDENT,
        DisplayState.GRADE
    };

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            HomeDisplay display = new HomeDisplay();
            display.homeDisplay();
        } finally {
            input.close();
        }
    }
}
