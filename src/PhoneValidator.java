import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PhoneValidator {
    public static void main(String[] args) {
        String fileName = "file.txt";
        validatePhoneNumbers(fileName);
    }

    public static void validatePhoneNumbers(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidPhoneNumber(line)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");
    }
}

