package seedu.ecardnomics.exceptions;

public class InvalidOptionsException extends Exception {
    public static final String INVALID_OPTIONS_LINE =
            "Invalid options in command";

    @Override
    public String getMessage() {
        return INVALID_OPTIONS_LINE;
    }
}
