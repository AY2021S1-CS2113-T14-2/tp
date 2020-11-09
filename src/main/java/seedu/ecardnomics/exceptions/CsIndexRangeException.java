package seedu.ecardnomics.exceptions;

public class CsIndexRangeException extends Exception {
    public static final String CS_INDEX_RANGE_LINE =
            "-cs index not within the correct range, [1,10]";

    @Override
    public String getMessage() {
        return CS_INDEX_RANGE_LINE;
    }
}
