import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Manual extends Item {

    public Manual() {
        //items.add(this);
        this.types.put("category", "manual");
        obligatory = new HashSet<>(Arrays.asList( new String[]{"title"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"organization", "author", "address", "month", "year", "note", " key", "edition"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Manual)) return false;
        Manual item = (Manual) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Manual{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }

}

