import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Masterthesis extends Item {

    public Masterthesis() {
        //items.add(this);
        this.types.put("category", "masterthesis");
        obligatory = new HashSet<>(Arrays.asList( new String[]{"author", "title", "school","year"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"type", "address", "month", "note", "key"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Masterthesis)) return false;
        Masterthesis item = (Masterthesis) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Masterthesis{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

