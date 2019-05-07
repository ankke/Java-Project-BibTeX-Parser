import java.util.*;

/**
 *Nadklasa typów rekordów - klasa abstrakcyjna
 */
public class Item implements VisitableItem{

    /**
     * Tutaj trzymane pośrednie rekordy crossreferencji (nadrzędne)
     */
    protected static List<Item> crossrefItems = new LinkedList<>();

    /**
     * statyczna lista wszystkich prawidłowych rekordów zapisanych w używanym pliku
     */
    protected static List<Item> items = new LinkedList<>();
    /**
     * Mapa tworzona dla każdego obiektu podklasy przechowująca nazwy pól wraz z wartościami
     */
    protected Map<String, String> types = new HashMap<>();

    /**
     * Sety atrybutów obowiązkowych i opcjonalnych - inicjalizowane w konstruktorze podklasy
     */
    protected Set<String> obligatory;
    protected Set<String> optional;

    /**
     * metoda sprawdzająca uzupełnienie pól rekordu
     * @throws NotFilledException gdy rekord nie ma uzupełnionych wszystkich pól obowiązkowych
     */
    public boolean checkIfFilled() throws NotFilledException {
        for(String type : obligatory){
            if(types.get(type)==null) throw new NotFilledException("Not every obligatory is filled in " + this.toString());
        }
        return true;
    }
    /**
     * @param type
     * Metoda sprawdzająca czy dane pole nie jest ignorowane dla obiektu na którym została wywołana
     * @return
     */
    public boolean isNotIgnored(String type) {
        return (obligatory.contains(type) || optional.contains(type) || type.trim().equals("crossref"));
    }

    public void accept (Visitor visitor){
        visitor.visitItem(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(types, item.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(types);
    }

    /**
     * @param item
     * metoda pomocnicza przy sprawdzaniu równości rekordów
     */
    public boolean checkIfEquals(Item item){
        for (String s : obligatory) {
            if(this.types.get(s)!=null && item.types.get(s)==null) {
                return false;
            }
            else if((this.types.get(s)==null && item.types.get(s)!=null)){
                return false;
            }
            else if(this.types.get(s)!=null && item.types.get(s)!=null) {
                if (!(this.types.get(s).equals(item.types.get(s)))) {
                    return false;
                }
            }
        }
        for (String s : optional) {
            if(this.types.get(s)!=null && item.types.get(s)==null) {
                return false;
            }
            else if((this.types.get(s)==null && item.types.get(s)!=null)){
                return false;
            }
            else if(this.types.get(s)!=null && item.types.get(s)!=null) {
                if (!(this.types.get(s).equals(item.types.get(s)))) {
                    return false;
                }
            }
        }
        return true;
    }
}



