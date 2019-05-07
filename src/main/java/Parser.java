import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Klasa parsująca
 *
 */
public class Parser {

    public List<Item> items = new LinkedList<>();

    /**
     * @throws IOException przy błedzie we wczytaniu pliku
     * parsuje plik BiTex'u używając metod z klasy Tokenizer: itemFactory oraz collectLine
     * najpierw szukany jest regex @(\w+) i przekazywany do medoty collectItem
     * Stringi są rozpatrywane osobno e klasie SringBT za pomoca metody collectString z klasy Tokenizer
     * dla itemu nie będącego Stringiem zapisywany jest klucz cross-referencji - crossrefKey w types
     * za pomocą pomocniczej metocy parseItem parsowane jest wnętrze rekordu
     * następnie wykonywana jest cross-referencja z użyciem metody z klasy Search - crossrefSearch
     * dla każdego sparsowaego regordu jest sprawdzana poprawność uzupełnienia wszystkich pól obowiązkowych w checkIfFilled
     * rekordy, które nie spełniają tego waruku sa usuwane z listy Item.items
     *
     * działa w oparciu o wzorzec projektowy Factory -> deleguje tworzenie obiektów odpowiednich klas do metody Tokenizer.itemFactory
     */
     public static List<Item> parse(String path) throws IOException{
         try (BufferedReader in = new BufferedReader(new FileReader(path))) {
             Scanner scan = new Scanner(in);
             Pattern pattern = Pattern.compile("@(\\w+)");
             while (scan.findWithinHorizon(pattern, 0) != null) {
                 String content = scan.match().group(1);
                 Item item = null;
                 try {
                     item = Tokenizer.itemFactory(content);
                 }catch (UnknownItemType e){
                     System.out.println(e.getMessage());
                 }
                 if (item instanceof StringBT) {
                     scan.useDelimiter("\\}");
                     String inside = scan.next();
                     Tokenizer.collectString(inside);
                 } else if (item != null) {
                     String rest = scan.nextLine();
                     item.types.put("crossrefKey", rest.substring(1, rest.length() - 1));
                     parseItem(scan, item);
                 }
             }

             for(Item item : Item.crossrefItems){
                 Search.crossrefSearch(item.types.get("crossref").toLowerCase(), item);
             }

             List<Item> toRemove = new LinkedList<>();

             for (Item item : Item.items) {
                 try{
                     item.checkIfFilled();
                 }catch (NotFilledException e){
                     System.out.println(e.getMessage());
                     System.out.println(item.types.get("author"));
                     toRemove.add(item);
                 }
             }

             for(Item item : toRemove) Item.items.remove(item);
             return Item.items;
         }
    }

    /**
     * pomocnicza metoda parsująca
     * wywołuje metodę collectLine z Tokenizer dla kolejnych linii rekordu
     * @param scan aktualny Scanner
     * @param item aktualnie analizowany rekord
     */
    public static void parseItem(Scanner scan, Item item){
        scan.useDelimiter("\\}");
        String inside = scan.next();
        int i=1;
        try {
            Tokenizer.collectLine(inside, item);
            i++;
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage() + " in " + item.toString());
        }catch (WrongNameFormat e){
            System.out.println(e.getMessage() + " in " + item.toString());
        }
        if( i==2 ) Item.items.add(item); // dodaje tylko te które spełniają wszystkie wymagania (poza checkiffilled)
    }
}

