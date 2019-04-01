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
    }

    /**
     * Starts the application by showing the menu and retrieving input from the
     * user.
     */
    public void start() {

        literatureRegister.add(new Book("title", null, null));
        
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
                    System.out.println("\nThank you for using Application v0.1. Bye!\n");
                    quit = true;
                    break;

                    default:
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.menuItems.length + "..\n");
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
                System.out.println("\n**** Application v0.1 ****\n");
                // Display the menu

            for (String menuItem : menuItems) {
                System.out.println(menuItem);
            }
            maxMenuItemNumber = menuItems.length + 1;
                // Add the "Exit"-choice to the menu
                System.out.println(maxMenuItemNumber + ". Exit\n");
                System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
                // Read input from user
                break;

            case "productMenu":
                for (String literatureType : literatureTypes) {
                    System.out.println(literatureType);
                }
                maxMenuItemNumber = literatureTypes.length;
                System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
                break;

                default:
        }

        int menuSelection = readNextInt();
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
            System.out.println("You don't have any products.");
        }
        else {
            System.out.println("The products you have is: ");
            System.out.println(literatureRegister.listAllLiterature());
        }
    }

    /**
     * In this method you will be available to choose what literature product you want
     * to add and then add it to the register.
     */
    private void addNewProduct() {
        int literatureType = this.showMenu("productMenu");

        try {
            System.out.println("Enter title: ");
            String literatureName = readNextLine();
            checkForDuplicate(literatureName);

            System.out.println("Enter author: ");
            String literatureAuthor = readNextLine();

            switch (literatureType) {

                case 1: //Book
                    System.out.println("Enter book genre: ");
                    String literatureGenre = readNextLine();
                    literatureRegister.add(new Book(literatureName, literatureAuthor, literatureGenre));
                    System.out.println("Book register successful");
                    break;

                case 2: //Book series
                    boolean addingBooks = true;
                    System.out.println("Enter genre of book's in the series: ");
                    String seriesGenre = readNextLine();
                    literatureRegister.add(new BookSeries(literatureName, literatureAuthor, seriesGenre));

                    while (addingBooks) {

                        System.out.println("Do you want to add a book to " + literatureName + "? (yes or no)");

                        String yesOrNo = readNextLine();
                        if (!testForMissMatch("YesOrNo", yesOrNo)) {
                            //To test for a miss match.
                            if (yesOrNo.equals("yes")) {
                                System.out.println("Enter title of book you want to add: ");
                                String literatureToBeAddedTitle = readNextLine();
                                checkForDuplicate(literatureToBeAddedTitle);
                                Book newBook = new Book(literatureToBeAddedTitle, literatureAuthor, seriesGenre);
                                literatureRegister.addLiteratureToSeries(literatureName, newBook);
                            }
                            else if(yesOrNo.equals("no")) {
                                addingBooks = false;
                            }
                        }
                    }

                    break;

                case 3: //New's papers
                    System.out.println("Enter brand of new's paper: ");
                    String newsPaperBrand = readNextLine();
                    literatureRegister.add(new NewsPaper(literatureName, literatureAuthor, newsPaperBrand));
                    System.out.println("New's paper register successful");
                    break;

                case 4: //Magazine
                    System.out.println("Enter brand of magazine: ");
                    String literatureBrand = readNextLine();
                    literatureRegister.add(new Magazine(literatureName, literatureAuthor, literatureBrand));
                    System.out.println("Magazine register successful");
                    break;

                default:
                    System.out.println("ERROR");
                    break;
            }

        }
        catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }

    /**
     * Find and remove a product based on the input name (title).
     */
    private void removeProduct() {
        String literatureName;
        System.out.println("Do you want to delete literature from register or from book series?");
        String answer = readNextLine();

        while(testForMissMatch("RegisterOrBookSeries", answer)) {
            answer = readNextLine();

            if (answer.equals("register")) {
                System.out.println("Enter name of product you want to remove: ");
                literatureName = readNextLine();

                if (literatureRegister.removeLiterature(literatureName)) {
                    System.out.println("The product with the name " + literatureName + " is removed.");
                } else {
                    System.out.println("No product with the name " + literatureName + " found.");
                }
            }
            if (answer.equals("book series")) {
                System.out.println("Enter name of book series title:");
                literatureName = readNextLine();
                if (literatureRegister.doLiteratureExist(literatureName)) {
                    System.out.println("Enter name of the book you want to remove from " + literatureName + ":");
                    String seriesTitle = readNextLine();
                    literatureRegister.removeLiteratureFromSeries(literatureName, seriesTitle);
                } else {
                    System.out.println("No book series with the name " + literatureName + " exist.");
                }
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
        System.out.println("Enter search: ");
        String search = readNextLine();

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
            System.out.println(outPrintText);
        }
        else {
            System.out.println("No literature with the name: " + search + " found.");
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
                System.out.println("A literature with the name " + literatureToBeChecked + " already exist. Do you want it to be removed and replaced? (yes or no)");
                String userInput = readNextLine();
                if(userInput.equals("yes")) {
                    literatureRegister.removeLiterature(literatureToBeChecked);
                    System.out.println("Literature successfully removed.");
                    waitingForAnswer = false;
                }
                if(userInput.equals("no")) {
                    System.out.println("The literature was not removed.");
                    waitingForAnswer = false;
                }
            }
        }
    }

    /**
     * Read the next line of text the user input into the terminal.
     * @return the line of text from the user.
     */
    private String readNextLine() {
        this.reader = new Scanner(System.in);
        return reader.nextLine();
    }

    /**
     * Read the next integer from the user.
     * @return the next integer from the user
     */
    private int readNextInt() {
        this.reader = new Scanner(System.in);
        return reader.nextInt();
    }

    /**
     * Takes in a String to choose what case of miss match and String of a word to test against.
     * If there is a miss match this method will return true and print an error message.
     * @param missMatchCase what case of miss match. (YesOrNo, RegisterOrBookSeries)
     * @param missMatchToTest text to test miss match against.
     * @return false if there is no miss match.
     * @throws InputMismatchException if user enters an invalid text.
     */
    private boolean testForMissMatch(String missMatchCase, String missMatchToTest) throws InputMismatchException {
        boolean missMatch = true;
        String errorText;

        switch (missMatchCase) {
            case "YesOrNo":
                if(missMatchToTest.equals("yes") || missMatchToTest.equals("no")) {
                    missMatch = false;
                }
                errorText = "yes or no";
                break;

            case "RegisterOrBookSeries":
                if(missMatchToTest.equals("register") || missMatchToTest.equals("book series")) {
                    missMatch = false;
                }
                errorText = "register or book series";
                break;

                default:
                    errorText = "???";
        }
        try {
            if(missMatch) {
                throw new InputMismatchException();
            }
        }
        catch (InputMismatchException ime) {
            System.out.println("\nERROR: Please provide either " + errorText + "..\n");
        }
        return missMatch;
    }
}
