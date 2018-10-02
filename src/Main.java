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
                if(peselMatcher.matches()) {
                    int peselYear = Integer.parseInt(peselMatcher.group(1));
                    int peselCenturyAndMonth = Integer.parseInt(peselMatcher.group(2));
                    int peselDay = Integer.parseInt(peselMatcher.group(3));
                    int series = Integer.parseInt(peselMatcher.group(4));
                    int sex = Integer.parseInt(peselMatcher.group(5));
                    int checksum = Integer.parseInt(peselMatcher.group(6));
                    int birthYear = 0;
                    int birthMonth = 0;

                    if(peselCenturyAndMonth >= 21 && peselCenturyAndMonth <= 32) {
                        birthYear = 2000;
                        birthMonth = peselCenturyAndMonth - 20;
                    } else if(peselCenturyAndMonth >= 41 && peselCenturyAndMonth <= 52) {
                        birthYear = 2100;
                        birthMonth = peselCenturyAndMonth - 40;
                    } else if(peselCenturyAndMonth >= 61 && peselCenturyAndMonth <= 72) {
                        birthYear = 2100;
                        birthMonth = peselCenturyAndMonth - 60;
                    } else if(peselCenturyAndMonth >= 81 && peselCenturyAndMonth <= 92) {
                        birthYear = 1800;
                        birthMonth = peselCenturyAndMonth - 80;
                    } else {
                        birthYear = 1900;
                        birthMonth = peselCenturyAndMonth;
                    }

                    birthYear += peselYear;
                    System.out.print(peselDay+"-"+birthMonth+"-"+birthYear);
                } else {
                    throw new NumberFormatException("Nieprawidłowy zapis PESEL!");
                }
            } catch (Exception e) {
                System.err.println("Wystąpił błąd: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }
}
