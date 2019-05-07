import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Inproceedings extends Item {

    public Inproceedings() {
        //items.add(this);
        this.types.put("category", "inproceedings");
        obligatory = new HashSet<>(Arrays.asList(new String[]{"author", "title", "booktitle","year"}));
        optional = new HashSet<>(Arrays.asList(new String[]{"editor", "volume", "number", "series", "pages", "address", "month",
                "organization", "publisher", "note", " key"}));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Inproceedings)) return false;
        Inproceedings item = (Inproceedings) o;
        return checkIfEquals(item);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("Inproceedings{");
        for(String type : obligatory){
            result.append(type + "=" + types.get(type) + ", ");
        }
        result.append("}\n");
        return result.toString();
    }

}

