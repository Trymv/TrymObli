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

        int menuSelection = textInterface.readNextInt();
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
     * In this method you will be available to choose what literature product you want
     * to add and then add it to the register.
     */
    private void addNewProduct() {
        int literatureType = this.showMenu("productMenu");

        textInterface.textOutPrintln("Enter title: ");
        String literatureName = textInterface.readNextLine();
        checkForDuplicate(literatureName);

        textInterface.textOutPrintln("Enter author: ");
        String literatureAuthor = textInterface.readNextLine();

        switch (literatureType) {

            case 1: //Book
                textInterface.textOutPrintln("Enter book genre: ");
                String literatureGenre = textInterface.readNextLine();
                literatureRegister.add(new Book(literatureName, literatureAuthor, literatureGenre));
                textInterface.textOutPrintln("Book register successful");
                break;

            case 2: //Book series
                boolean addingBooks = true;
                textInterface.textOutPrintln("Enter genre of book's in the series: ");
                String seriesGenre = textInterface.readNextLine();
                literatureRegister.add(new BookSeries(literatureName, literatureAuthor, seriesGenre));

                while(addingBooks) {
                    textInterface.textOutPrintln("Do you want to add a book to " + literatureName + "? (yes or no)");

                    String yesOrNo = textInterface.readNextLine();
                    if(yesOrNo.equals("yes")) {
                        textInterface.textOutPrintln("Enter title of book you want to add: ");
                        String literatureToBeAddedTitle = textInterface.readNextLine();
                        checkForDuplicate(literatureToBeAddedTitle);
                        Book newBook = new Book(literatureToBeAddedTitle, literatureAuthor, seriesGenre);
                        literatureRegister.addLiteratureToSeries(literatureName, newBook);
                    }
                    if(yesOrNo.equals("no")) {
                        addingBooks = false;
                    }
                }

                break;

            case 3: //New's papers
                textInterface.textOutPrintln("Enter brand of new's paper: ");
                String newsPaperBrand = textInterface.readNextLine();
                literatureRegister.add(new NewsPaper(literatureName, literatureAuthor, newsPaperBrand));
                textInterface.textOutPrintln("New's paper register successful");
                break;

            case 4: //Magazine
                textInterface.textOutPrintln("Enter brand of magazine: ");
                String literatureBrand = textInterface.readNextLine();
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
        String literatureName = "";
        textInterface.textOutPrintln("Do you want to remove product from register or book series?");
        String answer = textInterface.readNextLine();

        if(answer.equals("register")) {
            textInterface.textOutPrintln("Enter name of product you want to remove: ");
            literatureName = textInterface.readNextLine();

            if (literatureRegister.removeLiterature(literatureName)) {
                textInterface.textOutPrintln("The product with the name " + literatureName + " is removed.");
            } else {
                textInterface.textOutPrintln("No product with the name " + literatureName + " found.");
            }
        }
        if(answer.equals("book series")) {
            textInterface.textOutPrintln("Enter name of book series title:");
            literatureName = textInterface.readNextLine();
            if(literatureRegister.doLiteratureExist(literatureName)) {
                textInterface.textOutPrintln("Enter name of the book you want to remove from " + literatureName + ":");
                String seriesTitle = textInterface.readNextLine();
                literatureRegister.removeLiteratureFromSeries(literatureName, seriesTitle);
            }
            else {
                textInterface.textOutPrintln("No book series with the name " + literatureName + " exist.");
            }
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
        String search = textInterface.readNextLine();

        if(literatureRegister.searchByName(search) != null) {
            Literature literature = literatureRegister.searchByName(search);
            String outPrintText = "The literature title is: " + literature.getTitle() + ".\n"
                    + "The literature author is: " + literature.getAuthor() + ".\n";
            if(literature instanceof Book) {
                outPrintText += "The book genre is: " + ((Book) literature).getGenre() + ".\n";
            }
            if(literature instanceof Magazine) {
                outPrintText += "The magazine brand is: " + ((Magazine) literature).getBrand() + ".\n";
            }
            if(literature instanceof NewsPaper) {
                outPrintText += "The new's paper brand is: " + ((NewsPaper) literature).getBrand() + ".\n";
            }
            if(literature instanceof BookSeries) {
                outPrintText += "The literature brand is: " + ((BookSeries) literature).getGenre() + ".\n" + "The books in this series is: " + "\n" + (((BookSeries) literature).listAllBooks());
            }
            textInterface.textOutPrintln(outPrintText);
        }
        else {
            textInterface.textOutPrintln("No literature with the name: " + search + " found.");
        }
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
                textInterface.textOutPrintln("A literature with the name " + literatureToBeChecked + " already exist. Do you want it to be removed and replaced? (yes or no)");
                String userInput = textInterface.readNextLine();
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
