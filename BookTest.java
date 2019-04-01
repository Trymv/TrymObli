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
public class BookTest {
    private Book book1;

    /**
     * Default constructor for test class BookTest
     */

    public BookTest()
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
        book1 = new Book("Cat", "Catman", "Horror");
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
        assertEquals("Cat", book1.getTitle());
    }
    
    @Test
    public void getAuthorTest() {
        assertEquals("Catman", book1.getAuthor());
    }

    @Test
    public void getGenreTest()
    {
        assertEquals("Horror", book1.getGenre());
    }
    
    @Test
    public void createMagazineWithNullTitle()
    {
        assertNotNull(book1.getTitle());
        assertNotNull(book1.getAuthor());
        assertNotNull(book1.getGenre());
    }
    
    @Test
    public void testTitleNull()
    {
        Book book2 = new Book(null, "Catman", "Horror");
        assertNotNull(book2.getTitle());
    }
}




