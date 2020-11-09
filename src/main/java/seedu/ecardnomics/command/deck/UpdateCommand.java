package seedu.ecardnomics.command.deck;

import seedu.ecardnomics.deck.Deck;

public class UpdateCommand extends DeckCommand {
    private int flashCardID;
    private String newQuestion;
    private String newAnswer;

    public UpdateCommand(Deck deck, int flashCardID, String question, String answer) {
        super(deck);
        this.flashCardID = flashCardID;
        newQuestion = question;
        newAnswer = answer;
    }

    @Override
    public void execute() {
        // Update question
        currentDeck.get(flashCardID).setQuestion(newQuestion);
        // Update answer
        currentDeck.get(flashCardID).setAnswer(newAnswer);
    }
}
