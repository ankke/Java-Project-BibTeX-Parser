import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Booklet extends Item {
    /**
     * Inicjalizacja setów atrybutów obowiązkowych i opcjonalnych
     */
    public Booklet() {
        this.types.put("category", "booklet");
        obligatory = new HashSet<>(Arrays.asList(new String[]{"title"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"howpublished", "author", "address", "month", "year", "note", "key"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Booklet)) return false;
        Booklet item = (Booklet) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Booklet{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

