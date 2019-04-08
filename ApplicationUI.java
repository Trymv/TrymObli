import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 * 
 * @author asty
 * @version 1.1
 */
public class ApplicationUI {

    private Register literatureRegister;
    private Scanner reader;
    private TextInterface textInterface;

    // The menu tha will be displayed. Please edit/alter the menu
    // to fit your application (i.e. replace "product" with "literature"
    // etc.
    private String[] menuItems = {
            "1. List all products",
            "2. Add new product",
            "3. Remove product",
            "4. Find a product by name",
        };

    private String[] literatureTypes = {
            "1. Book",
            "2. Book series",
            "3. New's paper",
            "4. Magazine",
    };

    /**
     * Creates an instance of the ApplicationUI User interface. 
     */
    public ApplicationUI() {
        this.literatureRegister = new Register();
        this.reader = new Scanner(System.in);
        this.textInterface = new TextInterface();
    }

    /**
     * Starts the application by showing the menu and retrieving input from the
     * user.
     */
    public void start() {

        boolean quit = false;

        while (!quit) {
            try {
                int menuSelection = this.showMenu("mainMenu");
                switch (menuSelection) {
                    case 1:
                    this.listAllProducts();
                    break;

                    case 2:
                    this.addNewProduct();
                    break;

                    case 3:
                    this.removeProduct();
                    break;

                    case 4:
                    this.findProductByName();
                    break;

                    case 5:
                    textInterface.textOutPrintln("\nThank you for using Application v0.1. Bye!\n");
                    quit = true;
                    break;

                    default:
                }
            } 
            catch (InputMismatchException ime) {
                textInterface.textOutPrintln("\nERROR: Please provide a number between 1 and " + this.menuItems.length + "..\n");
            }
        }
    }

    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items. 
     * If the user inputs anything else, an InputMismatchException is thrown. 
     * The method returns the valid input from the user.
     *
     * @param menuType input what menu that you want to be shown (mainMenu, productMenu).
     * @return the menu number (between 1 and max menu item number) provided by the user.
     * @throws InputMismatchException if user enters an invalid number/menu choice
     */
    private int showMenu(String menuType) throws InputMismatchException {
        int maxMenuItemNumber = 0;

        switch (menuType) {

            case "mainMenu":
                textInterface.textOutPrintln("\n**** Application v0.1 ****\n");
                // Display the menu

            for (String menuItem : menuItems) {
                textInterface.textOutPrintln(menuItem);
            }
            maxMenuItemNumber = menuItems.length + 1;
                // Add the "Exit"-choice to the menu
                textInterface.textOutPrintln(maxMenuItemNumber + ". Exit\n");
                textInterface.textOutPrintln("Please choose menu item (1-" + maxMenuItemNumber + "): ");
                // Read input from user
                break;

            case "productMenu":
                for (String literatureType : literatureTypes) {
                    textInterface.textOutPrintln(literatureType);
                }
                maxMenuItemNumber = literatureTypes.length;
                textInterface.textOutPrintln("Please choose menu item (1-" + maxMenuItemNumber + "): ");
                break;

                default:
        }

        int menuSelection = reader.nextInt();
        if ((menuSelection < 1) || (menuSelection > maxMenuItemNumber)) {
            throw new InputMismatchException();
        }
        //reader.nextLine(); // Consume /n
        return menuSelection;
    }

    // ------ The methods below this line are "helper"-methods, used from the menu ----
    // ------ All these methods are made private, since they are only used by the menu ---

    /**
     * Lists all the products/literature in the register
     */
    private void listAllProducts() {
        if(literatureRegister.getBookRegister().isEmpty()) {
            textInterface.textOutPrintln("You don't have any products.");
        }
        else {
            textInterface.textOutPrintln("The products you have is: ");
            textInterface.textOutPrintln(literatureRegister.listAllLiteratures());
        }
    }

    /**
     * Add a new product/literature to the register.
     * In this method you have to add code to ask the
     * user for the necessary information you need to 
     * create an instance of the product, which you
     * then send as a parameter to the addNewspaper()-
     * method of the register.
     * Remember to also handle invalid input from the
     * user!!
     */
    private void addNewProductOld() {
        textInterface.textOutPrintln("addNewProduct() was called");

        textInterface.textOutPrintln("Enter name: ");
        String literatureName = reader.next();

        textInterface.textOutPrintln("Enter genre: ");
        String literatureGenre = reader.next();

        textInterface.textOutPrintln("Enter author: ");
        String literatureAuthor = reader.next();

        literatureRegister.add(new Book(literatureName, literatureAuthor, literatureGenre));
        textInterface.textOutPrintln("Book register successful");
    }

    /**
     * In this method you will be available to choose what literature product you want
     * to add and then add it to the register.
     */
    private void addNewProduct() {
        int literatureType = this.showMenu("productMenu");
        reader.nextLine(); // Consume /n

        textInterface.textOutPrintln("Enter title: ");
        String literatureName = textInterface.readNextLine();

        textInterface.textOutPrintln("Enter author: ");
        String literatureAuthor = reader.nextLine();

        switch (literatureType) {

            case 1: //Book
                textInterface.textOutPrintln("Enter book genre: ");
                String literatureGenre = reader.nextLine();
                literatureRegister.add(new Book(literatureName, literatureAuthor, literatureGenre));
                textInterface.textOutPrintln("Book register successful");
                break;

            case 2: //Book series
                boolean addingBooks = true;
                textInterface.textOutPrintln("Enter genre of book's in the series: ");
                String seriesGenre = reader.nextLine();
                literatureRegister.add(new BookSeries(literatureName, literatureAuthor, seriesGenre));

                while(addingBooks) {
                    textInterface.textOutPrintln("Do you want to add a book to " + literatureName + "? (yes or no)");

                    if(textInterface.readNextLine().equals("yes")) {
                        textInterface.textOutPrintln("Enter title of book you want to add: ");
                        String literatureToBeAddedTitle = reader.nextLine();
                        checkForDuplicate(literatureToBeAddedTitle);
                        Book newBook = new Book(literatureToBeAddedTitle, literatureAuthor, seriesGenre);
                        literatureRegister.addLiteratureToSeries(literatureName, newBook);
                    }
                    if(textInterface.readNextLine().equals("no")) {
                        addingBooks = false;
                    }
                }

                break;

            case 3: //New's papers
                textInterface.textOutPrintln("Enter brand of new's paper: ");
                String newsPaperBrand = reader.nextLine();
                literatureRegister.add(new NewsPaper(literatureName, literatureAuthor, newsPaperBrand));
                textInterface.textOutPrintln("New's paper register successful");
                break;

            case 4: //Magazine
                textInterface.textOutPrintln("Enter brand of magazine: ");
                String literatureBrand = reader.nextLine();
                literatureRegister.add(new Magazine(literatureName, literatureAuthor, literatureBrand));
                textInterface.textOutPrintln("Magazine register successful");
                break;

                default:
                    textInterface.textOutPrintln("ERROR");
                    break;
        }

    }

    /**
     * Find and remove a product based on the input name (title).
     *
     */
    private void removeProduct() {
        textInterface.textOutPrintln("Enter name of product you want to remove: ");
        reader.nextLine(); // Consume /n
        String literatureName = reader.nextLine();

        if(literatureRegister.removeLiterature(literatureName)) {
            textInterface.textOutPrintln("The product with the name " + literatureName + " is removed.");
        }
        else{
            textInterface.textOutPrintln("No product with the name " + literatureName + " found.");
        }
    }

    /**
     * Find and display a product based om name (title).
     * As with the addNewProduct()-method, you have to
     * ask the user for the string (name/title/publisher)
     * to search for, and then use this string as input-
     * parameter to the method in the register-object.
     * Then, upon return from the register, you need
     * to print the details of the found item.
     */
    private void findProductByName() {
        textInterface.textOutPrintln("Enter search: ");
        reader.nextLine(); // Consume /n
        String search = reader.nextLine();
        textInterface.textOutPrintln(literatureRegister.searchByName(search));
    }

    /**
     * Check for duplicate's in register.
     * If one is found give the user the chose to remove it.
     * @param literatureToBeChecked name of the literature to be checked.
     */
    private void checkForDuplicate(String literatureToBeChecked) {
        boolean waitingForAnswer = true;
        if(literatureRegister.doLiteratureExist(literatureToBeChecked)) {
            while(waitingForAnswer) {
                textInterface.textOutPrintln("A literature with the name " + literatureToBeChecked + " already exist. Do you want it to be removed? (yes or no)");
                String userInput = reader.nextLine();
                if(userInput.equals("yes")) {
                    literatureRegister.removeLiterature(literatureToBeChecked);
                    textInterface.textOutPrintln("Literature successfully removed.");
                    waitingForAnswer = false;
                }
                if(userInput.equals("no")) {
                    textInterface.textOutPrintln("The literature was not removed.");
                    waitingForAnswer = false;
                }
            }
        }
    }
}
