package seedu.ecardnomics.exceptions;

public class NumberTooBigException extends Exception {
    public static final String NUMBER_TOO_BIG_LINE = "Index is too large.";

    @Override
    public String getMessage() {
        return NUMBER_TOO_BIG_LINE;
    }
}
