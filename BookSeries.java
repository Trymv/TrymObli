import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class has the job to hold on book series.
 * In this class you should be available to choose how many books there is in
 * a book series, set the author of the book and set the title of every book.
 */
public class BookSeries extends Literature {
    private String genre;
    private ArrayList<Literature> booksInSeries;

    public BookSeries(String title, String author, String genre) {
        super(title, author);
        this.genre = genre;
        this.booksInSeries = new ArrayList<>();
    }

    /**
     * Add book into a array list of books of a series.
     * @param bookTitle the name of the book to add into the series
     */
    public void addBook(Literature bookTitle) {
        this.booksInSeries.add(bookTitle);
    }

    /**
     * Return the genre of the book series.
     * @return the genre of the book series
     */
    public String getGenre(){
        return this.genre;
    }

    /**
     * List all books in the book register on the terminal.
     * @return bookList list of the titles of all the books
     */
    public String listAllBooks() {
        Iterator<Literature> it = booksInSeries.iterator();
        String literatureList = "";

        while(it.hasNext()) {
            Literature literature = it.next();
            literatureList += literature.getTitle() + "\n";
        }
        return literatureList;
    }
}
