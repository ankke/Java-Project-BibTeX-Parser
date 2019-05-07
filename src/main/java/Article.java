import java.util.Arrays;
import java.util.HashSet;

public class Article extends Item {

    /**
     * Inicjalizacja setów atrybutów obowiązkowych i opcjonalnych
     */
    public Article() {
        this.types.put("category", "article");
        obligatory = new HashSet<>(Arrays.asList( new String[]{"author", "title", "publisher","year"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"volume", "number", "pages", "month", "note", "key"})); //TODO
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Article)) return false;
        Article item = (Article) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Article{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

