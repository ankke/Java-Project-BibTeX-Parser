import java.util.Arrays;
import java.util.HashSet;

public class Inbook extends Item {
    /**
     * Inicjalizacja setów atrybutów obowiązkowych i opcjonalnych
     */
    public Inbook() {
        this.types.put("category", "inbook");
        obligatory = new HashSet<>(Arrays.asList(new String[]{"title", "author", "editor", "chapter", "pages", "publisher", "year"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"volume", "number", "series", "type", "address", "edition", "month", "note", "key"}));
    }
    /**
     * Override metody z klasy Item, zwraca logiczną zależność wypełnienia dla Book
     * @return flase gdy
     */
    @Override
    public boolean checkIfFilled() throws NotFilledException {
        if(!((types.get("author")!= null || types.get("editor")!=null) &&(types.get("chapter")!= null || types.get("pages")!=null)
                && types.get("publisher") != null && types.get("year") != null)) throw new NotFilledException("Not every obligatory is filled in " + this.toString());
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Inbook)) return false;
        Inbook item = (Inbook) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Inbook{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

