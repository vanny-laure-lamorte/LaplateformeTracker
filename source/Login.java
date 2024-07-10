import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    
    public static boolean checkLoginCredentials(String userLogin, String userPassword) {

        PlateformeTracker PTracker = new PlateformeTracker();
        
        // Hash the user password
        String hashedPassword = hashPassword(userPassword);

        // Get the stored hashed password from the database
        String storedHashedPassword = PTracker.authenticateUser(userLogin, hashedPassword);

        // Check if the stored hashed password matches the input hashed password
        return storedHashedPassword != null && storedHashedPassword.equals(hashedPassword);
    }
}


