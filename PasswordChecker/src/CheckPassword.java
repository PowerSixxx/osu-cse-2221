import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Baowen Liu
 *
 */
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate, SimpleWriter out) {
        int validCount = 0;
        if (passwordCandidate.length() < 8) {
            out.println("Please enter a password AT LEAST 8 characters long.");
        }
        if (containsUpperCaseLetter(passwordCandidate)) {
            validCount++;
        } else {
            out.println("Please enter a password contains uppercase.");
        }
        if (containsLowerCaseLetter(passwordCandidate)) {
            validCount++;
        } else {
            out.println("Please enter a password contains lowercase.");
        }
        if (containsDigits(passwordCandidate)) {
            validCount++;
        } else {
            out.println("Please enter a password contains digit.");
        }

        if (validCount < 2) {
            out.println("Invalid password!");
        } else {
            out.println("Valid password!");
        }
        if (passwordCandidate.length() < 8) {
            out.println("Please enter a password AT LEAST 8 characters long.");
            if (containsUpperCaseLetter(passwordCandidate)) {

            }
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        boolean returnVal = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                returnVal = true;
            }
        }
        return returnVal;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.println("Enter your passwords: ");
        String passwordCandidate = in.nextLine();
        checkPassword(passwordCandidate, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
