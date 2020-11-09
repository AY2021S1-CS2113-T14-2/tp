package seedu.ecardnomics.exceptions;

public class CsIndexFormatException extends Exception {
    public static final String CS_INDEX_FORMAT_LINE =
            "-cs needs to be followed by a integer between 1 and 10 (inclusive)";

    @Override
    public String getMessage() {
        return CS_INDEX_FORMAT_LINE;

    }
}
