import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is to make a register for readable's such as books with "ArrayList".
 *
 * @author Group 18
 * @version 1.1
 */
public class Register
{
    // instance variables - replace the example below with your own
    private ArrayList<Literature> literatureRegister;

    /**
     * Constructor for objects of class Register
     */
    public Register() {

        this.literatureRegister = new ArrayList<>();
    }
    
    /**
     * Return the array list literatureRegister.
     * @return literatureRegister as array list.
     */
    public ArrayList<Literature> getBookRegister() {

        return literatureRegister;
    }

    /**
     * Add a book to the literature register.
     * @param literature add the book to the literature register (ArrayList).
     */
    public void add(Literature literature) {

        literatureRegister.add(literature);
    }

    /**
     * Add a book to an array list of a book series.
     * @param literature add a book to a book in series.
     *
    public BookSeries makeBookSeries(String title, String author, String genre) {
        BookSeries nameOfBookSeires = new BookSeries(title, author, genre);
        return nameOfBookSeires;
    }
    */

    /**
     * Use iterator to search though literatureRegister with a parameter.
     * If an exact match is found the object will be removed.
     * @param objectToBeRemoved remove the book from the book register (ArrayList).
     * @return true if object was successfully removed.
     */
    public boolean removeLiterature(String objectToBeRemoved) {
        boolean isLiteratureRemoved = false;
        Iterator<Literature> it = literatureRegister.iterator();
        
        while(it.hasNext()) {
            if(it.next().getTitle().equals(objectToBeRemoved)) {
                it.remove();
                isLiteratureRemoved = true;
            }
        }
        return isLiteratureRemoved;
    }

    /**
     * Uses a iterator to search for a literature, if the iterator find a literature
     * with a title witch equals seriesTitle and is an instance of BookSeries a new Book
     * will be added to the series.
     * @param seriesTitle title of the series you want to add book to.
     * @param newLiterature title of the literature you want to add.
     */
    public void addLiteratureToSeries(String seriesTitle, Book newLiterature) {
        Iterator<Literature> it = literatureRegister.iterator();

        while(it.hasNext()) {
            Literature literature = it.next();

            if (literature.getTitle().equals(seriesTitle) && literature instanceof BookSeries) {
               ((BookSeries) literature).addBook(newLiterature);
            }
        }
    }

    /**
     * Checks if a literature with input name already exists in the register.
     * If it already exist it will return true.
     * @param literatureTitle name of register to check.
     * @return true if literature already exist in the register.
     */
    public boolean doLiteratureExist(String literatureTitle) {
        Iterator<Literature> it = literatureRegister.iterator();
        boolean doItExist = false;

        while(it.hasNext()) {
            Literature literature = it.next();

            if(literature.getTitle().equals(literatureTitle)) {
                doItExist = true;
            }
        }
        return doItExist;
    }
    
    /**
     * List all books in the book register on the terminal.
     * @return bookList list of the titles of all the books
     */
    public String listAllLiteratures() {
        String literatureList = "";
        
        for(Literature literature:literatureRegister) {
            literatureList += literature.getTitle() + "\n";
        }
        return literatureList;
    }
    
    /**
     * Search on a literature if the list has a match that contains a word from the parameter.
     * @param searchString put in the string of the book you want to search for 
     * from the array list "bookRegister".
     * List all books that contains the name you sent in.
     * @return bookFound
     */
    public String searchByName(String searchString) {
        boolean foundBook = false;
        String searchResult = "";
        
        for(Literature literature:literatureRegister) {
            if(literature.getTitle().contains(searchString) || literature.getAuthor().contains(searchString)) {
                searchResult += "The literature title is: " + literature.getTitle() + ".\n"
                + "The literature author is: " + literature.getAuthor() + ".\n";
                if(literature instanceof Book) {
                    searchResult += "The book genre is: " + ((Book) literature).getGenre() + ".\n";
                }
                if(literature instanceof Magazine) {
                    searchResult += "The magazine brand is: " + ((Magazine) literature).getBrand() + ".\n";
                }
                if(literature instanceof NewsPaper) {
                    searchResult += "The new's paper brand is: " + ((NewsPaper) literature).getBrand() + ".\n";
                }
                if(literature instanceof BookSeries) {
                    searchResult += "The literature brand is: " + ((BookSeries) literature).getGenre() + ".\n" + "The books in this series is: " + "\n" + (((BookSeries) literature).listAllBooks());
                }
                foundBook = true;
            }
        }
        
        if(!foundBook){
                searchResult = ("No literature with the name: " + searchString + " found.");
            }
        return searchResult;
    }
    
    public int getArrayLength() {
        return literatureRegister.size();
    }
}
