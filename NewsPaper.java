/**
 * This class has the job to hold on a object of NewsPaper.
 * In this class you should be available to set news paper title, author and brand
 *
 * @author TrymV
 * @version 0.1
 */
public class NewsPaper extends Literature {
    private String brand;

    /**
     * Constructor for object of class NewsPaper.
     * Will set title, author and brand.
     */
    public NewsPaper(String title, String author, String brand) {
        super(title, author);
        if(brand != null) {
            this.brand = brand;
        }
        else {
            throw new IllegalArgumentException("News paper brand was set to null");
        }
    }

    /**
     * Return the brand of the magazine.
     * @return the brand of the magazine
     */
    public String getBrand() {
        return this.brand;
    }
}
