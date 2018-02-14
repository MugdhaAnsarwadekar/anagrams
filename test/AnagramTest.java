import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.LinkedList;

public class AnagramTest {
    private Anagram anagram;
    @Before
    public void before(){
        anagram = new Anagram();
        try{
            anagram.readFile("dictionary.txt");
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void emptyInputTest() {
        assertNull(anagram.getAnagrams(""));
    }

    @Test
    public void inputWithWhitespaceTest(){
        assertNull(anagram.getAnagrams("st op"));
    }

    @Test
    public void extraWhitespaceTest(){
        assertNotNull(anagram.getAnagrams("  stop  "));
    }

    @Test
    public void invalidInputTest(){
        assertNull(anagram.getAnagrams("thisWordDoesNotExist"));
    }

    @Test
    public void caseSensitiveInputTest(){
        LinkedList<String> result = new LinkedList<>();
        result.add("start");
        assertEquals(anagram.getAnagrams("START"), result);
    }

    @Test
    public void invalidFilenameTest(){
        try{
            anagram.readFile("wrongFile.txt");
            fail("IOException not thrown");
        }catch(IOException exception){
            assertNotNull(exception.getMessage());
        }
    }

}
