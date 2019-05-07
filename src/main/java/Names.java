import javax.sound.midi.Soundbank;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SocketHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Names {

    /**
     * szablony Imion i Nazwisk
     */
    private static Pattern pattern1 = Pattern.compile("(\\s*[A-Z][a-z]*\\s*[A-Z]*\\.*\\s*)([a-z]*\\s*)([A-Z][a-z]*\\s*)"); // First von Last
    private static Pattern pattern2 = Pattern.compile("(\\s*[a-z]*\\s*[A-Z][a-z]*\\s*)(,)(\\s*[A-Z][a-z]*\\s*)"); // von Last| First
    private static Pattern pattern3 = Pattern.compile("(\\s*[a-z]*\\s*[A-Z][a-z]*\\s*,\\s*[A-Z][a-z]*\\s*,)(\\s*[A-Z][a-z]*\\s*)");
    // von Last| Jr| First

    /**
     *
     * @param names oryginalne nazwy ( oddzielone 'and')
     * @return zwraca listę rozdzielonych nazw osób
     * korzysta z metody pomocniczej extractName
     * @throws WrongNameFormat rzucane przez extractName gdy Nazwisko nie spełnia żadnego z szablonów
     */
    public static List<String> splitNames(String names) throws WrongNameFormat {
        if(names==null) return null;
        List<String> result = new LinkedList<>();
        Scanner scan = new Scanner(names);
        scan.useDelimiter("and");
        if(!scan.hasNext()){
            result.add(extractName(names));
        }
        else {
            do {
                result.add(extractName(scan.next()));
            } while (scan.hasNext());
        }
        return result;
    }

    /**
     * @param name pojedyncza nazwa osoby
     * @return zwraca nazwe osoby w formacie: Imię Nazwisko
     * @throws WrongNameFormat gdy gdy nazwa osoby nie spełnia żadnego z szablonów
     */
    public static String extractName(String name) throws WrongNameFormat {
        if(name==null) return null;
        String firstName;
        String lastName;

        Matcher matcher1 = pattern1.matcher(name);
        Matcher matcher2 = pattern2.matcher(name);
        Matcher matcher3 = pattern3.matcher(name);
        if (matcher1.matches()) {
            return name.trim();
        }
        else if (matcher2.matches()) {
            firstName = matcher2.group(3).replace('|',' ').trim();
            lastName = matcher2.group(1).replace('|',' ').trim();
        }

        else if (matcher3.matches()) {
            firstName = matcher3.group(2).replace('|',' ').trim();
            lastName = matcher3.group(1).replace('|',' ').trim();

        }
        else {
            throw new WrongNameFormat("Wrong name format in " + name);

        }


        StringBuilder result = new StringBuilder();
        result.append(firstName);
        result.append(" ");
        result.append(lastName);
        return result.toString();
    }

    /**
     * @param name nazwa osoby do sprawdzenia
     * @param lastName nazwisko testujące
     * @return true gdy testujące jest równe sprawdzanemu
     * @throws WrongNameFormat gdy splitNames rzuci wyjątkiem (partz wyżej)
     */
    public static boolean equalsLastName(String name, String lastName) throws WrongNameFormat {
        List<String> names = splitNames(name);
        if(names!=null) {
            for (String s : names) {
                if (s.substring(s.lastIndexOf(" ") + 1).toLowerCase().equals(lastName.toLowerCase().trim()) ||
                        s.toLowerCase().contains(lastName.toLowerCase().trim())) {
                    return true;
                }
            }
        }
        return false;
    }

}
