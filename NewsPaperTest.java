import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BookTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NewsPaperTest {
    private NewsPaper newspaper1;

    /**
     * Default constructor for test class BookTest
     */

    public NewsPaperTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        newspaper1 = new NewsPaper("NTNU artikkel", "Mr. Proffesor", "VG");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void getTitleTest()
    {
        assertEquals("NTNU artikkel", newspaper1.getTitle());
    }
    
    @Test
    public void getAuthorTest() {
        assertEquals("Mr. Proffesor", newspaper1.getAuthor());
    }

    @Test
    public void getBrandTest()
    {
        assertEquals("VG", newspaper1.getBrand());
    }
}




