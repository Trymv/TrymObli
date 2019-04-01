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
        for(Literature literature:literatureRegister) {
            if (literature.getTitle().equals(seriesTitle) && literature instanceof BookSeries) {
               ((BookSeries) literature).addBook(newLiterature);
            }
        }
    }

    /**
     *
     * @param seriesTitle title of the series you want to remove book from.
     * @param literatureToBeRemoved title of the literature you want to remove.
     */
    public void removeLiteratureFromSeries(String seriesTitle, String literatureToBeRemoved) {
        for(Literature literature:literatureRegister) {
            if (literature.getTitle().equals(seriesTitle) && literature instanceof BookSeries) {
                ((BookSeries) literature).removeBookFromSeries(literatureToBeRemoved);
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
    public String listAllLiterature() {
        StringBuilder literatureList = new StringBuilder();

        for(Literature literature:literatureRegister) {
            literatureList.append(literature.getTitle()).append("\n");
        }
        return literatureList.toString();
    }

    /**
     * For testing
     * @return size of list.
     */
    public int getArrayLength() {
        return literatureRegister.size();
    }

    /**
     * Search on a literature if the list has a match that contains a word from the parameter.
     * @param search put in the string of the literature you want to search for
     * in the array list "bookRegister".
     * @return searchResult will return the found literature. If not found it will return null.
     */
    public Literature searchByName(String search) {
        Literature searchResult = null;

        for (Literature literature : literatureRegister) {
            if (literature.getTitle().contains(search) || literature.getAuthor().contains(search)) {
                searchResult = literature;
            }
        }
        return searchResult;
    }
}
