/**
 * This class stores information about literatures.
 * Literatures can be sored and displayed.
 * This class serves as a superclass for different types if literatures.
 */
public abstract class Literature {
    private String title;
    private String author;

    /**
     * Constructor for objects of the class Literature.
     * @param title The title of the literature.
     */
    public Literature(String title, String author) {
        if (title != null || author != null) {
            this.title = title;
            this.author = author;
        }
        else {
            throw new IllegalArgumentException("Title or author was set to null");
        }
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
