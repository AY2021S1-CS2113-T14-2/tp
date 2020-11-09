package seedu.ecardnomics.exceptions;

public class InvalidPptxArgumentException extends Exception {
    public static final String INVALID_PPTX_ARGUMENT_LINE =
            "Invalid arguments for PPTX command";

    @Override
    public String getMessage() {
        return INVALID_PPTX_ARGUMENT_LINE;
    }
}
