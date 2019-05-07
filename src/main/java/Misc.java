import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Misc extends Item {

    public Misc() {
        //items.add(this);
        this.types.put("category", "misc");
        obligatory = new HashSet<>(Arrays.asList(new String[]{}));
        optional = new HashSet<>(Arrays.asList(new String[]{"howpublished", "author", "title", "month", "year", "note", "key"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Misc)) return false;
        Misc item = (Misc) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Misc{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

