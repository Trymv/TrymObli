/**
 * This class has the job to hold on a object of magazine.
 * In this class you should be available to set title, author and brand.
 *
 * @author TrymV
 * @version 0.1
 */
public class Magazine extends Literature {
    private String brand;

    /**
     * Constructor for objects of class Magazine.
     * Will set title, author and brand.
     */
    public Magazine(String title, String author, String brand) {
        super(title, author);
        if(brand != null) {
            this.brand = brand;
        }
        else {
            throw new IllegalArgumentException("Magazine brand was set to null");
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
