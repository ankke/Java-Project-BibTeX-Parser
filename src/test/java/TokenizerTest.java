import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class TokenizerTest {
    @Test
    public void shouldReturnItem() throws UnknownItemType{
        String line = "book";
        Item item = new Book();

        Assert.assertEquals(Tokenizer.itemFactory(line), item);
    }

    @Test
    public void shouldReturnNull() throws UnknownItemType{
        String line = "preamble";

        Assert.assertEquals(Tokenizer.itemFactory(line), null);
    }

    @Test (expected = UnknownItemType.class)
    public void shouldThrowUnknownItemType() throws UnknownItemType{
        String line = "book1";

        Tokenizer.itemFactory(line);
    }

    @Test (expected = WrongNameFormat.class)
    public void shouldThrowWrongNameFormat() throws WrongNameFormat{
        String in = " author = \" von, First, Last, Jr\"" ;
        Item item = new Book();

        Tokenizer.collectLine(in, item);
    }

    @Test
    public void shouldReturnString() throws UnknownAbbreviation{
        String line = "publisher = \"Orion\"";

        Assert.assertEquals(Tokenizer.extractType(line), "Orion");
    }

    @Test
    public void shouldReturnString2() throws UnknownAbbreviation{
        String line = "number = 7";

        Assert.assertEquals(Tokenizer.extractType(line), "7");
    }

    @Test
    public void shouldReturnString3() throws UnknownAbbreviation{
        String line = "month = apr";

        Assert.assertEquals(Tokenizer.extractType(line), "April");
    }

    @Test (expected = UnknownAbbreviation.class)
    public void shouldThrowUnknownAbbreviation() throws UnknownAbbreviation{
        String line = "month = april";

        Tokenizer.extractType(line);
    }

    @Test
    public void shouldReturnString4(){
        String line = "month = apr";

        Assert.assertEquals(Tokenizer.collectType(line), "month");
    }

    @Test
    public void shouldPutAbbreviation(){
        String content = "{ACM = \"The OX Association for Computing Machinery\"";

        Tokenizer.collectString(content);

        Assert.assertEquals(StringBT.getInstance().getAbbreviations("acm"), "The OX Association for Computing Machinery");
    }

    @Test
    public void shouldReturnStringAbbrev() throws UnknownAbbreviation{
        String content = "{ACM = \"The OX Association for Computing Machinery\"";
        String line = "organization = ACM";

        Tokenizer.collectString(content);

        Assert.assertEquals(Tokenizer.extractType(line), "The OX Association for Computing Machinery");
    }

    @Test (expected = UnknownItemType.class)
    public void shouldThrowYnknownItemType() throws UnknownItemType{
        String line = "book1";

        Tokenizer.itemFactory(line);
    }

}
