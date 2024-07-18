import java.util.HashSet;
import java.util.Set;

public class InputValidator {   

        //--- MENU ---//

        // Validate only numbers between 1 and 5
        public static boolean isValidMenu(String input) {
            return input.matches("[1-5]");
        } 

        //--- UPDATE STUDENT INFO ---//

        // Validate only digits
        public static boolean isValidDigit(String input) {
            return input.matches("\\d+");
        }

        // Validate only digits
           public static boolean isValidDigitDouble(String input) {
            return input.matches("\\d+(\\.\\d+)?");
        }
        
        
        // Validate only letters
        public static boolean isValidAlphabetic(String input) {
            return input.matches("[a-zA-Z]+"); 
        }
        
        // Validate only Yes, yes, Y, No, no or N
        public static boolean isValidYesNo(String input) {
            Set<String> validInputs = new HashSet<>();
            validInputs.add("Y");
            validInputs.add("y");
            validInputs.add("N");
            validInputs.add("n");
            return validInputs.contains(input);
        }

        // Validate only numbers between 1 and 5
        public static boolean isValidUpdateStudentInfo(String input) {
        return input.matches("[1-4]");
        }       
    
}
