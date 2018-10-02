import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String inputPesel;
        Scanner scanner = new Scanner(System.in);
        int PESELcoeffs[] = {9,7,3,1};

        while(true) {
            System.out.print("Podaj PESEL: ");
            inputPesel = scanner.nextLine();
            try {
                Pattern peselRegex = Pattern.compile("(\\d{2})(\\d{2})(\\d{2})(\\d{3})(\\d)(\\d)");
                Matcher peselMatcher = peselRegex.matcher(inputPesel);
                if (Pattern.matches(, inputPesel)) {
                    
                } else {
                    throw new NumberFormatException("Nieprawidłowy format PESELu lub podany ciąg nie jest PESELem.");
                }
            } catch (Exception e) {
                System.err.println("Wystąpił błąd: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}
