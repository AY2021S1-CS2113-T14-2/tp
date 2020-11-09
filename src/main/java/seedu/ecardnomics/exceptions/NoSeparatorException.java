package seedu.ecardnomics.exceptions;

public class NoSeparatorException extends Exception {
    public static final String NO_SEPARATOR_LINE =
            "</tag> separator should be added before tags!";

    @Override
    public String getMessage() {
        return NO_SEPARATOR_LINE;
    }
}
