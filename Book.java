/**
 * This class has the job to hold on a object book.
 * In this class you should be available to set book title, genre and author.
 *
 * @author Group 18
 * @version 0.1
 */
public class Book extends Literature
{
    private String genre;

    /**
     * Constructor for objects of class Book
     * Will set book title, genre and author.
     */
    public Book(String title, String author, String genre) {
        super(title, author);
        this.genre = genre;
    }

    /**
     * Return the genre of the book.
     * @return the genre of the book.
     */
    public String getGenre() {

        return this.genre;
    }
}
