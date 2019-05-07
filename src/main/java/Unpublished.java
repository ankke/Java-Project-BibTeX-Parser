import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Unpublished extends Item {

    public Unpublished() {
        //items.add(this);
        this.types.put("category", "book");
        obligatory = new HashSet<>(Arrays.asList(new String[]{"author", "title", "note"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"month", "year", "key"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Unpublished)) return false;
        Unpublished item = (Unpublished) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Unpublished{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }


}

