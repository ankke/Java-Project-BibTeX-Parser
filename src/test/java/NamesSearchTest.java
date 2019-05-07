import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class NamesSearchTest {
    /**
     * Test integracyjny : wyszukiwanie ze sprawdzeniem nazwiska (klasa Names)
     */
    @Test
    public void shouldReturnListOfItems() throws NoMatchingBibTeXItemFound, WrongNameFormat{
        Item article = new Article();
        article.types.put("author", "Jan Kowalski");
        Item.items = List.of(article, new Book());
        String [] names = new String[]{"Kowalski", "Nowak"};
        List<Item> abstractItems = new LinkedList<>();

        abstractItems = Search.namesSearch(names);

        Assert.assertEquals(abstractItems.size(), 1);
    }

    @Test (expected = NoMatchingBibTeXItemFound.class)
    public void shouldThrowNoMatchingBibTeXItemFound() throws NoMatchingBibTeXItemFound, WrongNameFormat{
        Item article = new Article();
        article.types.put("author", "Jan Golab");
        Item.items = List.of(article, new Book());
        String [] names = new String[]{"Kowalski", "Nowak"};

        Search.namesSearch(names);
    }

    @Test (expected = WrongNameFormat.class)
    public void shouldThrowWrongNameFormat() throws NoMatchingBibTeXItemFound, WrongNameFormat{
        Item article = new Article();
        article.types.put("author", "von, Jan, Kowalski");
        Item.items = List.of(article, new Book());
        String [] names = new String[]{"Kowalski", "Nowak"};

        Search.namesSearch(names);
    }


}
