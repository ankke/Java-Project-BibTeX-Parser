import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Book extends Item {
    /**
     * Inicjalizacja setów atrybutów obowiązkowych i opcjonalnych
     */
    public Book() {
        //items.add(this);
        this.types.put("category", "book"); //TODO
        obligatory = new HashSet<>(Arrays.asList(new String[]{"author", "title", "publisher","year", "editor"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"volume", "series", "address", "edition", "month", "note", "key"}));
    }

    /**
     * Override metody z klasy Item, zwraca logiczną zależność wypełnienia dla Book
     * @return flase gdy
     */
    @Override
    public boolean checkIfFilled() throws NotFilledException{
        if(!((types.get("author") != null || types.get("editor") !=null) &&
                types.get("title") != null && types.get("publisher") != null && types.get("year") != null))
            throw new NotFilledException("Not every obligatory is filled in " + this.toString());
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Book)) return false;
        Book item = (Book) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Book{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

