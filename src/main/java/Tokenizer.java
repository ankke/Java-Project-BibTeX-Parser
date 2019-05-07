import java.util.Scanner;
import java.util.regex.Pattern;


/**
 *Klasa ze statycznymi medotami składowymi do parsowania pliku BibTeX
 */
public class Tokenizer {

    /**
     * @param line
     * przyjmuje wyszukanego regex'a z metody Parse.parser na podstawie któego tworzy obiekt porządanej klasy
     * jeśli regex jest ignorowany zwraca null
     * @return
     */
    public static Item itemFactory (String line) throws UnknownItemType{
        Item item;
        String s = line.toLowerCase();

        switch (s) {
            case ("book"): {
                item = new Book();
                break;
            }
            case ("article"): {
                item = new Article();
                break;
            }
            case ("booklet"): {
                item = new Booklet();
                break;
            }
            case ("inbook"): {
                item = new Inbook();
                break;
            }
            case ("incollection"): {
                item = new Incollection();
                break;
            }
            case ("inproceedings"): {
                item = new Inproceedings();
                break;
            }
            case ("manual"): {
                item = new Manual();
                break;
            }
            case ("masterthesis"): {
                item = new Masterthesis();
                break;
            }
            case ("misc"): {
                item = new Misc();
                break;
            }
            case ("phdthesis"): {
                item = new Phdthesis();
                break;
            }
            case ("techreport"): {
                item = new Techreport();
                break;
            }
            case ("unpublished"): {
                item = new Unpublished();
                break;
            }
            case ("proceedings"): {
                item = new Proceedings();
                break;
            }
            case ("string"): {
                item = StringBT.getInstance();
                break;
            }
            case ("preamble"): {
                return null;
            }
            default:
                throw new UnknownItemType("Unknown item type: " + s);
        }
        return item;
    }


    /**
     * @param in
     * @param item
     * przyjmuje wnętrze rekordu z metody Parse.parser, które za pomocą metod collectType oraz extractType rozdziela na pola
     * i zapisuje w mapie obiektu item, gdzie kluczem jest nazwa pola a wartością wartość tego pola
     * działa także dla przecinków w tytułach, ponieważ skanuje względem linii a nie przecinków
     *
     * @throws WrongNameFormat gdy format zapisu Imienia i Nazwiska jest niepoprawny
     *
     */
    public static void collectLine (String in, Item item) throws WrongNameFormat {
        Scanner scan = new Scanner(in);
        String line;
        while(scan.hasNextLine()) {
            line = scan.nextLine();
            String type = collectType(line);
            if(item.isNotIgnored(type)){
                try{
                    if(collectType(line).equals("author") || collectType(line).equals("editor")){
                        Names.splitNames(extractType(line));
                        item.types.put(collectType(line), extractType(line));
                    }
                    else if(collectType(line).trim().equals("crossref")){
                        item.types.put(collectType(line), extractType(line));
                        Item.crossrefItems.add(item);
                    }
                    else {
                        item.types.put(collectType(line), extractType(line));
                    }
                }catch (UnknownAbbreviation e){
                    System.out.println(e.getMessage() + " in " + item.toString());
                }
            }
        }
    }

    /**
     * @param line
     * przyjmuje linię z metody collectLine
     * z podziałem na rodzaje wartości pola rozdziela na przypadki
     * pierwszy gdy pole jest stringiem
     * drugie gdy pole jest cyfrą
     * trzecie gdy jest zmienną @string -> wywołuje metodę collectString operującą na tym typie zmiennej
     * zwraca wartość pola
     * @return
     */
     public static String extractType (String line) throws UnknownAbbreviation {
        Scanner scan = new Scanner(line);
        if(scan.findWithinHorizon("\".*\"",0)!=null){
            return scan.match().group().substring(1,(scan.match().group().length())-1);
        }
        Pattern pattern = Pattern.compile("\\d+");
        if(scan.findWithinHorizon(pattern,0)!=null){
            return scan.match().group();
        }
        else{
            scan.useDelimiter("=");
            scan.next();
            String s = scan.next().toLowerCase();
            return  StringBT.extract(s);
        }
     }

    /**
     * @param line
     * przyjmuje linię z medoty collectLine
     * zwraca nazwę pola (author, title, itp.)
     * @return
     */

     public static String collectType (String line){
         Scanner scan = new Scanner(line);
         scan.useDelimiter("=");
         return scan.next().trim().toLowerCase();
     }

     public static void collectString(String content){
         Scanner scan = new Scanner(content);
         String line;
         while(scan.hasNextLine()) {
             line = scan.nextLine();
             try{
                 StringBT.getInstance().setAbbreviations(collectType(line).substring(1), extractType(line));
             }catch (UnknownAbbreviation e){
                 System.out.println(e.getMessage());
             }
         }
     }
}
