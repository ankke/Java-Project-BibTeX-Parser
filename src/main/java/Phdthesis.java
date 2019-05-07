import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Phdthesis extends Item {

    public Phdthesis() {
        //items.add(this);
        this.types.put("category", "phdthesis");
        obligatory = new HashSet<>(Arrays.asList(new String[]{"author", "title", "school","year"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"type", "address", "month", "note", "key"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Phdthesis)) return false;
        Phdthesis item = (Phdthesis) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Phdthesis{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }
}

