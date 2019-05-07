import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CrossRefTest {
    @Test
    public void shouldReturnTrue(){
        List<Item> result = new LinkedList<>();

        try{
            result = Parser.parse("C:\\Users\\Aneczka\\IdeaProjects\\pars\\src\\test\\java\\TEST2.txt");
        }catch(IOException e){

        }

        Assert.assertEquals(result.size(), 2);
    }
}
