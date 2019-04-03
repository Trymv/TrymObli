

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RegisterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RegisterTest
{
    private Register register1;
    private BookSeries bookseries1;
    private Book book1;
    private Book book2;

    /**
     * Default constructor for test class RegisterTest
     */
    
    public RegisterTest()
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
        register1 = new Register();
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
    public void RemoveToFailTest()
    {
        register1.add(book1);
        register1.add(book2);
        assertFalse(register1.removeLiterature("Cats"));
    }    
   
    @Test
    public void removeLiteratureTest()
    {
        register1.add(book1);
        register1.add(book2);
        assertTrue(register1.removeLiterature("Mysteriekammeret"));
    }
    
    @Test
    public void listAllLiteratureTest() 
    {
        register1.add(book1);
        register1.add(book2);
        register1.add(bookseries1);
        assertEquals("De vises stein\n" + 
                      "Mysteriekammeret\n" +
                      "Harry Potter\n", register1.listAllLiterature());
    }
    
    @Test
    public void addLiteratureToSeriesTest()
    {
        register1.add(bookseries1);
        register1.add(book1);
        //register1.addLiteratureToSeries
    }

    @Test
    public void testtest()
    {
        register1.add(bookseries1);
        register1.add(book1);
        register1.add(book2);
        register1.addLiteratureToSeries("Harry Potter", book1);
    }
}










