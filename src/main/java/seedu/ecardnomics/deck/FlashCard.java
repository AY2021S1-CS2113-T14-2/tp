package seedu.ecardnomics.deck;

import seedu.ecardnomics.Ui;

/**
 * Flashcards that contain a question and asnwer.
 */
public class FlashCard {
    private String question;
    private String answer;

    private static final String QN_LABEL = "Question: ";
    private static final String ANS_LABEL = "Answer:   ";

    /** Constructor. */
    public FlashCard(String question, String answer) {
        assert (question != null && !question.isEmpty()) : "Flashcard must have one question.";
        this.question = question;
        assert (answer != null && !answer.isEmpty()) : "Flashcard must have one answer";
        this.answer = answer;
    }

    /**
     * Get the answer of the flashcard.
     *
     * @return String representing the answer on the flashcard
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Get the question of the flashcard.
     *
     * @return String representing the question on the flashcard
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Set the answer of the flashcard.
     *
     * @param answer new answer on the flashcard
     */
    public void setAnswer(String answer) {
        assert (answer != null && !answer.isEmpty()) : "Flashcard must have one answer";
        this.answer = answer;
    }

    /**
     * Set the question of the flashcard.
     *
     * @param question new answer on the flashcard
     */
    public void setQuestion(String question) {
        assert (question != null && !question.isEmpty()) : "Flashcard must have one question.";
        this.question = question;
    }

    /* Would not recommend using this method unless there is no offset to be printed before QN_LABEL */
    public String toString() {
        return QN_LABEL + Ui.prettyPrintFormatter(question, QN_LABEL.length()) + System.lineSeparator()
                + ANS_LABEL + Ui.prettyPrintFormatter(answer, ANS_LABEL.length());
    }

    /**
     * Returns a String that contains the requested details (question or answer)
     * for the flashcard for printing. The String is formatted such that the
     * details will fit between offset characters from the start of the line and
     * Ui.DASH_LINES.length().
     *
     * @param isQuestion indicates whether the required detail is question or answer
     * @param offset Number of characters from the start of the line
     * @return String for displaying question if isQuestion, answer otherwise
     */
    public String toString(boolean isQuestion, int offset) {
        if (isQuestion) {
            return QN_LABEL + Ui.prettyPrintFormatter(question, offset + QN_LABEL.length());
        } else {
            return " ".repeat(offset) + ANS_LABEL
                    + Ui.prettyPrintFormatter(answer, offset + ANS_LABEL.length());
        }
    }
}
