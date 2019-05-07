import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ParserTest {

    /**
     * Test integracyjny
     */
    @Test
    public void shouldReturnListOfItems() {
        List<Item> result = new LinkedList<>();

        try{
            result = Parser.parse("C:\\Users\\Aneczka\\IdeaProjects\\pars\\src\\test\\java\\TEST1.txt");
        }catch(IOException e){

        }
        Assert.assertEquals(result.size(), 2);
    }
}
