import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Techreport extends Item {

    public Techreport() {
        //items.add(this);
        this.types.put("category", "techreport");
        obligatory = new HashSet<>(Arrays.asList(new String[]{"author", "title", "institution", "year"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"volume", "number", "series", "type", "address", "month", "note", "key", "editor",
                "organization", "publisher"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Techreport)) return false;
        Techreport item = (Techreport) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Techreport{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }


}

