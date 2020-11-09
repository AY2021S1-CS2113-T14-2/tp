package seedu.ecardnomics.exceptions;

public class ColorsNotAvailException extends Exception {
    public static final String COLORS_NOT_AVAIL_LINE =
            "Colors option is not available";

    @Override
    public String getMessage() {
        return COLORS_NOT_AVAIL_LINE;
    }
}
