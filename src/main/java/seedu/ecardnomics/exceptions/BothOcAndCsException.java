package seedu.ecardnomics.exceptions;

public class BothOcAndCsException extends Exception {
    public static final String BOTH_OC_AND_CS_LINE =
            "Input should not have both -oc and -cs option.";

    @Override
    public String getMessage() {
        return BOTH_OC_AND_CS_LINE;
    }
}
