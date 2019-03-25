import java.util.Scanner;

/**
 * This class has to job to print out all texts to make it easier to change where the text will be shown.
 * Also it will take care of terminal reading.
 *
 * @author TrymV
 * @version 0.1
 */
public class TextInterface {
    private Scanner reader;

    /**
     * Takes in a text as a parameter to then print it out to the location you want it.
     * Makes it easier to change out print location.
     * @param outPrintText the text to be out printed.
     */
    public void textOutPrintln(String outPrintText) {
        System.out.println(outPrintText);
    }

    /**
     * Read the next line of text the user input into the terminal.
     * @return the line of text from the user.
     */
    public String readNextLine() {
        this.reader = new Scanner(System.in);
        return reader.nextLine();
    }

    /**
     * Read the next integer from the user.
     * @return the next integer from the user
     */
    public int readNextInt() {
        this.reader = new Scanner(System.in);
        return reader.nextInt();
    }
}
