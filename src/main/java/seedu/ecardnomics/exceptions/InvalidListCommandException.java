package seedu.ecardnomics.exceptions;

public class InvalidListCommandException extends Exception {
    public static final String INVALID_LIST_COMMAND_LINE =
            "Invalid argument for list command";

    @Override
    public String getMessage() {
        return INVALID_LIST_COMMAND_LINE;
    }
}
