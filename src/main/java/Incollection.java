import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Incollection extends Item {
    /**
     * Inicjalizacja setów atrybutów obowiązkowych i opcjonalnych
     */
    public Incollection() {
        //items.add(this);
        this.types.put("category", "incollection");
        obligatory = new HashSet<>(Arrays.asList( new String[]{"author", "title", "booktitle","year", "publisher"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"volume", "number", "series", "type", "address", "editor", "month", "note", "key", "chapter",
                "pages", "edition"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Incollection)) return false;
        Incollection item = (Incollection) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Incollection{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

