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
public class BookSeriesTest {
    private BookSeries bookseries1;
    private Book book1;
    private Book book2;

    /**
     * Default constructor for test class BookTest
     */

    public BookSeriesTest()
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
        bookseries1 = new BookSeries("Harry Potter", "J.K. Rowling", "Fantasy");
        book1 = new Book("De vises stein", "J.K. Rowling", "Fantasy");
        book2 = new Book("Mysteriekammeret", "J.K. Rowling", "Fantasy");
        
        bookseries1.addBook(book1);
        bookseries1.addBook(book2);
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
        assertEquals("Harry Potter", bookseries1.getTitle());
    }
    
    @Test
    public void getAuthorTest() 
    {
        assertEquals("J.K. Rowling", bookseries1.getAuthor());
    }

    @Test
    public void getGenreTest()
    {
        assertEquals("Fantasy", bookseries1.getGenre());
    }
    
    @Test
    public void createMagazineWithNullTitle()
    {
        assertNotNull(bookseries1.getTitle());
        assertNotNull(bookseries1.getAuthor());
        assertNotNull(bookseries1.getGenre());
    }

    @Test
    public void listAllBooksTest()
    {
        assertEquals("De vises stein\nMysteriekammeret\n", bookseries1.listAllBooks());
    }

    @Test
    public void removeBookFromSeriesTest()
    {
        assertTrue(bookseries1.removeBookFromSeries("Mysteriekammeret"));
    }
    
    @Test
    public void removeBookFromSeriesFailTest()
    {
        assertFalse(bookseries1.removeBookFromSeries("mysteriumkmaret"));
    }
    
    @Test
    public void removeLiteratureTest()
    {      
        assertEquals(true, bookseries1.removeBookFromSeries("De vises stein"));
    }
    
    @Test
    public void testTitleNull()
    {
        try {
            BookSeries bookseries2 = new BookSeries(null, "Catman:)", "Horror");
        }
        catch(IllegalArgumentException e) {
            assertEquals("Title or author was set to null" ,e.getMessage());
        }
    }
    
    @Test
    public void testAuthorNull()
    {
        try {
            BookSeries bookseries2 = new BookSeries("BookSeriestitleMan!", null, "Horror");
        }
        catch(IllegalArgumentException e) {
            assertEquals("Title or author was set to null" ,e.getMessage());
        }
    }
    
    @Test
    public void testGenreNull()
    {
        try {
            BookSeries bookseries2 = new BookSeries("BookSeriestitleMan!", "Catman", null);
        }
        catch(IllegalArgumentException e) {
            assertEquals("Book series genre was set to null" ,e.getMessage());
        }
    }
}








