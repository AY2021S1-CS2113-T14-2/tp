package seedu.ecardnomics.exceptions;

public class EmptyQnAException extends Exception {
    public static final String EMPTY_QNA_LINE =
            "A flash card must have a question and answer.";

    @Override
    public String getMessage() {
        return EMPTY_QNA_LINE;
    }
}
