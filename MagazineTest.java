

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
public class MagazineTest {
    private Magazine magazine1;

    /**
     * Default constructor for test class BookTest
     */

    public MagazineTest()
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
        magazine1 = new Magazine("Kjent kjendis på forsiden!", "Sladderdamen", "Kjent Magasin Navn");
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
        assertEquals("Kjent kjendis på forsiden!", magazine1.getTitle());
    }
    
    @Test
    public void getAuthorTest() {
        assertEquals("Sladderdamen", magazine1.getAuthor());
    }

    @Test
    public void getBrandTest()
    {
        assertEquals("Kjent Magasin Navn", magazine1.getBrand());
    }

    @Test
    public void getTitleTestNotNull()    {
        assertNotNull(magazine1.getTitle());
    }
}





