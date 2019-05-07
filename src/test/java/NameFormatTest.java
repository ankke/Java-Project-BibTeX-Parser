import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Testy jednostkowe formatu Imienia i Nazwiska (metoda extractName)
 */
public class NameFormatTest {
    @Test
     public void shouldReturnListOfNames() throws WrongNameFormat{
        String names = "Alfred V. Oaho and von Ullman| Jr| Jeffrey and Mihalis Yannakakis";
        List<String> namesList = new LinkedList<>();

        namesList = Names.splitNames(names);

        Assert.assertEquals(namesList.size(), 3);
    }

    @Test
    public void shouldReturnPatternOne() throws WrongNameFormat{
        String name = "Alfred V. Oaho";
        String result;

        result = Names.extractName(name);

        Assert.assertEquals(result, "Alfred V. Oaho");
    }

    @Test
    public void shouldReturnPattern1() throws WrongNameFormat{
        String name = "Alfred von Oaho";
        String result;

        result = Names.extractName(name);

        Assert.assertEquals(result, "Alfred von Oaho");
    }

    @Test
    public void shouldReturnPatternOne1() throws WrongNameFormat{
        String name = "Alfred Oaho";
        String result;

        result = Names.extractName(name);

        Assert.assertEquals(result, "Alfred Oaho");
    }

    @Test
    public void shouldReturnPatternTwo() throws WrongNameFormat{
        String name = "von Oaho| Alfred";
        String result;

        result = Names.extractName(name);

        Assert.assertEquals(result, "Alfred von Oaho");
    }

    @Test
    public void shouldReturnPatternTwo2() throws WrongNameFormat{
        String name = "Oaho| Alfred";
        String result;

        result = Names.extractName(name);

        Assert.assertEquals(result, "Alfred Oaho");
    }

    @Test
    public void shouldReturnPatternThree() throws WrongNameFormat{
        String name = "von Oaho| Jr| Alfred";
        String result;

        result = Names.extractName(name);

        Assert.assertEquals(result, "Alfred von Oaho  Jr");
    }

    @Test
    public void shouldReturnPattern3() throws WrongNameFormat{
        String name = "Oaho| Jr| Alfred";
        String result;

        result = Names.extractName(name);

        Assert.assertEquals(result, "Alfred Oaho  Jr");
    }

}
