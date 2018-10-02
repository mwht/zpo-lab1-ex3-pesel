import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pesel {
    private String peselString;
    private Date birthDate;
    private static final long[] PESEL_COEFFS = {9,7,3,1};

    public enum PeselPersonType {
        PESEL_MALE,
        PESEL_FEMALE
    };

    private PeselPersonType peselPersonType;

    public static Date calculateBirthday(String pesel) {
        Pattern peselRegex = Pattern.compile("^(\\d{2})(\\d{2})(\\d{2})(\\d{3})(\\d)(\\d)$");
        Matcher peselMatcher = peselRegex.matcher(pesel);
        if(peselMatcher.matches()) {
            int peselYear = Integer.parseInt(peselMatcher.group(1));
            int peselCenturyAndMonth = Integer.parseInt(peselMatcher.group(2));
            int peselDay = Integer.parseInt(peselMatcher.group(3));
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

            GregorianCalendar newBirthDate = new GregorianCalendar(birthYear, birthMonth - 1, peselDay, 0 ,0);
            return newBirthDate.getTime();

        } else {
            throw new NumberFormatException("Nieprawidłowy zapis PESEL!");
        }
    }

    public static Pesel fromString(String pesel) throws InvalidChecksumException {
        Pesel resultPesel = new Pesel();
        resultPesel.setBirthDate(calculateBirthday(pesel));
        resultPesel.setPeselString(pesel);
        resultPesel.setPeselPersonType(getPersonTypeFromString(pesel));
        if(isChecksumValid(pesel)) {
            return resultPesel;
        } else {
            throw new InvalidChecksumException("Nieprawidłowa suma kontrolna!");
        }
    }

    public static boolean isChecksumValid(String pesel) {
        Pattern peselRegex = Pattern.compile("^(\\d{2})(\\d{2})(\\d{2})(\\d{3})(\\d)(\\d)$");
        Matcher peselMatcher = peselRegex.matcher(pesel);
        long sum = 0;
        if(peselMatcher.matches()) {
            for(int i=0;i<pesel.length()-1;i++) {
                sum += PESEL_COEFFS[i%4]*((long)(pesel.charAt(i)-'0'));
            }
            return ((char)((sum%10)+'0') == pesel.charAt(pesel.length()-1));
        } else {
            throw new NumberFormatException("Nieprawidłowy zapis PESEL!");
        }
    }

    public static PeselPersonType getPersonTypeFromString(String pesel) {
        Pattern peselRegex = Pattern.compile("^(\\d{2})(\\d{2})(\\d{2})(\\d{3})(\\d)(\\d)$");
        Matcher peselMatcher = peselRegex.matcher(pesel);
        long sum = 0;
        if(peselMatcher.matches()) {
            return ((Integer.parseInt(peselMatcher.group(5)) % 2) == 1 ? PeselPersonType.PESEL_MALE : PeselPersonType.PESEL_FEMALE);
        } else {
            throw new NumberFormatException("Nieprawidłowy zapis PESEL!");
        }
    }

    public void setPeselPersonType(PeselPersonType peselPersonType) {
        this.peselPersonType = peselPersonType;
    }

    @Override
    public String toString() {
        return peselString + " - "
                + ((peselPersonType == PeselPersonType.PESEL_MALE) ? "mężczyzna" : "kobieta")
                + ", " + getBirthDate();
    }

    public void setPeselString(String peselString) {
        this.peselString = peselString;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
