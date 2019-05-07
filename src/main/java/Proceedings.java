import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Proceedings extends Item {

    public Proceedings() {
        //items.add(this);
        this.types.put("category", "proceedings");
        obligatory = new HashSet<>(Arrays.asList(new String[]{"author", "title", "booktitle","year"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"editor", "volume", "number", "series", "pages", "address", "month",
                "organization", "publisher", "note", " key"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Proceedings)) return false;
        Proceedings item = (Proceedings) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Proceedings{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }

}

