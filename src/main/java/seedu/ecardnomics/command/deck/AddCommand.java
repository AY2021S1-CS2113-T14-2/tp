package seedu.ecardnomics.command.deck;

import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.FlashCard;

public class AddCommand extends DeckCommand {
    private final String question;
    private final String answer;

    /** Constructor. */
    public AddCommand(Deck deck, String question, String answer) {
        super(deck);
        assert (question != null && !question.isEmpty()) : "Flashcard must have one question.";
        this.question = question;
        assert (answer != null && !answer.isEmpty()) : "Flashcard must have one question.";
        this.answer = answer;
    }

    @Override
    public void execute() {
        this.currentDeck.add(new FlashCard(this.question, this.answer));
    }
}
