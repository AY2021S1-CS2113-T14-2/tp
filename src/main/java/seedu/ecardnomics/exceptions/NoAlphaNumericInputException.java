package seedu.ecardnomics.exceptions;

public class NoAlphaNumericInputException extends Exception {
    public static final String NO_ALPHANUMERIC_LINE =
            "Input should contain at least one alphanumeric character! Returning...";

    @Override
    public String getMessage() {
        return NO_ALPHANUMERIC_LINE;
    }
}
