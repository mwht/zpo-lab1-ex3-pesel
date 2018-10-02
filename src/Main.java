import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String inputPesel;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Podaj PESEL: ");
            inputPesel = scanner.nextLine();
            try {
                System.err.println(Pesel.fromString(inputPesel));
            } catch (Exception e) {
                System.err.println("Wystąpił błąd: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}
