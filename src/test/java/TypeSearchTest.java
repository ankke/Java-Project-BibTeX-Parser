import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TypeSearchTest {
    @Test
    public void shouldReturnListOfItems() throws NotFilledException{
        Item article = new Article();
        Set<String> obligatory = new HashSet<>();
        obligatory.add("test1");
        Map<String, String> types = new HashMap<>();
        types.put("test1", "1");

        article.obligatory = obligatory;
        article.types = types;

        Assert.assertTrue(article.checkIfFilled());
    }

    @Test (expected = NotFilledException.class)
    public void shouldThrowNotFilledException() throws NotFilledException{
        Item article = new Article();
        Set<String> obligatory = new HashSet<>();
        obligatory.add("test1");
        Map<String, String> types = new HashMap<>();

        article.obligatory = obligatory;
        article.types = types;

        article.checkIfFilled();
    }
}
