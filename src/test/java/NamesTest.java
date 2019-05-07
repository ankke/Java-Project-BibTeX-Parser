import org.junit.Assert;
import org.junit.Test;
import java.util.List;

/**
 * Testy metod z klasy Names
 */
public class NamesTest {

    /**
     * Test integracyjny equalsLastName i splitNames
     */
    @Test
    public void shouldReturnTrue() throws WrongNameFormat{
        String name = "Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis and von Daniels| Jr | Jan";
        String lastName = "Oaho";

        Assert.assertEquals(Names.equalsLastName(name, lastName), true);
    }

    /**
     * Test integracyjny equalsLastName i splitNames
     */
    @Test
    public void shouldReturnFalse() throws WrongNameFormat{
        String name = "Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis and von Daniels| Jr | Jan";
        String lastName = "Kowalski";

        Assert.assertEquals(Names.equalsLastName(name, lastName), false);
    }

    @Test (expected = WrongNameFormat.class)
    public void shouldThrowWrongNameFormat() throws WrongNameFormat{
        String name = "Alfred V. von| Jr| Oaho and Jeffrey D. Ullman and Mihalis Yannakakis and von Daniels| Jr | Jan";
        String lastName = "Kowalski";

        Names.equalsLastName(name, lastName);
    }

    @Test
    public void shouldReturnListOfNames() throws WrongNameFormat{
        String names = "Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis and von Daniels| Jr | Jan";
        List<String> result;

        result = Names.splitNames(names);

        Assert.assertEquals(result.size(), 4);
    }

    @Test (expected = WrongNameFormat.class)
    public void shouldThrowWrongNameFormat1() throws WrongNameFormat{
        String names = "Alfred V. von| Jr| Oaho and Jeffrey D. Ullman and Mihalis Yannakakis and von Daniels| Jr | Jan";

        Names.splitNames(names);

    }

}
