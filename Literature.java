/**
 * This class stores information about literatures.
 * Literatures can be sored and displayed.
 * This class serves as a superclass for different types if literatures.
 */
public class Literature {
    private String title;
    private String author;

    /**
     * Constructor for objects of the class Literature.
     *
     * @param title The title of the literature.
     */
    public Literature(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Return the title
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Return the author
     * @return the author
     */
    public String getAuthor() {
        return this.author;
    }
}
