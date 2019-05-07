import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Search {

    /**
     * @param names Nazwiska to wyszukania
     * @return listę rekordów z autorami o szukanych nazwiskach
     * @throws NoMatchingBibTeXItemFound gdy rekord taki nei istnieje
     * @throws WrongNameFormat gdy format imienia i nazwiska w rekordzie nie spełnia szablonu
     */
    public static List<Item> namesSearch (String[] names) throws NoMatchingBibTeXItemFound, WrongNameFormat{
        PrintVisitor print = new PrintVisitor();
        List<Item> result = new LinkedList<>();
        StringBuilder s = new StringBuilder();
        for(String name : names){
            for(Item item : Item.items) {
                if (Names.equalsLastName(item.types.get("author"), name)) {
                    result.add(item);
                }
            }
            s.append(name + " ");
        }
        for(Item result1 : result){
            result1.accept(print);
        }
        if(result.isEmpty()) throw new NoMatchingBibTeXItemFound("Cannot find item with author/authors: " + s);
        return result;
    }

    /**
     * @param categories tablica szukanych kategorii
     * @return zwraca listę cnalezionych rekordów
     * @throws NoMatchingBibTeXItemFound gdy szukany rekord nie istnieje
     */
    public static List<Item> categorySearch (String[] categories) throws NoMatchingBibTeXItemFound {
        PrintVisitor print = new PrintVisitor();
        List<Item> result = new LinkedList<>();
        StringBuilder s = new StringBuilder();
        for(String category : categories) {
            for (Item item : Item.items) {
                if (item.types.get("category").equals(category.toLowerCase()))
                    result.add(item);
            }
            s.append(category + " ");
        }
        for(Item result1 : result){
            result1.accept(print);
        }
        if(result.isEmpty()) throw new NoMatchingBibTeXItemFound("Cannot find item from category: " + s);
        return result;
    }

    /**
     * @param key klucz rekordu nadrzędnego w croessreferencji
     * @param crossItem rekord nadrzędny
     * metoda obsługująca crossreferencję uzupełnia odpowiednie pola w rekordzie nadrzędnym
     */
    public static void crossrefSearch (String key, Item crossItem) {
        for (Item item : Item.items) {
            if (item.types.get("crossrefKey").equals(key.toLowerCase())) {
                for (String type : crossItem.obligatory) {
                    if ((crossItem.types.get(type) == null)) {
                        crossItem.types.put(type, item.types.get(type));
                    }
                }
                for (String type : crossItem.optional) {
                    if ((crossItem.types.get(type) == null)) {
                        crossItem.types.put(type, item.types.get(type));
                    }

                }
            }
        }

    }
}
