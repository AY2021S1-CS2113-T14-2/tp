package seedu.ecardnomics.exceptions;

public class DuplicateDeckException extends Exception {
    public static final String DUPLICATE_DECK_LINE =
            "This deck name has already existed, please choose another name.";

    @Override
    public String getMessage() {
        return DUPLICATE_DECK_LINE;
    }
}
