import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Singleon
 */
public class StringBT extends Item {

    private static StringBT instance = new StringBT();

    /**
     * Mapa wprowadzonych skrótów za pomocą deklaracji @String
     */
    private Map<String, String> abbreviations;

    /**
     * Uzupełnia defaultowe skróty miesięcy
     */
    private StringBT() {
        abbreviations = new HashMap<>();
        abbreviations.put("jan", "January");
        abbreviations.put("feb", "February");
        abbreviations.put("mar", "March");
        abbreviations.put("apr", "April");
        abbreviations.put("may", "May");
        abbreviations.put("jun", "June");
        abbreviations.put("jul", "July");
        abbreviations.put("aug", "August");
        abbreviations.put("sep", "September");
        abbreviations.put("oct", "October");
        abbreviations.put("nov", "November");
        abbreviations.put("dec", "December");
    }

    public static StringBT getInstance(){
        return instance;
    }

    public void setAbbreviations(String key, String value) {
        this.abbreviations.put(key,value);
    }

    public String getAbbreviations(String key) {
        return this.abbreviations.get(key);
    }

    /**
     * @param abbrev znalziony skrót w polu rekordu
     * @return zwraca rozwinięcie skrótu o ile istnieje
     * @throws UnknownAbbreviation gdy szukane rozwinięcie nie istnieje
     */
    public static String extract(String abbrev) throws UnknownAbbreviation {
        Scanner scan = new Scanner(abbrev);
        scan.useDelimiter(",");
        abbrev = scan.next().trim();
        if (instance.abbreviations.get(abbrev) == null) throw new UnknownAbbreviation("Unknown abbreviation: " + abbrev);
        return instance.abbreviations.get(abbrev);
    }
}








