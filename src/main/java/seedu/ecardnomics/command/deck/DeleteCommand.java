package seedu.ecardnomics.command.deck;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.Deck;

public class DeleteCommand extends DeckCommand {
    private int flashCardID;
    private boolean isFlashCardDeleted;

    public DeleteCommand(Deck deck, int flashCardID, boolean isFlashCardDeleted) {
        super(deck);
        assert (flashCardID >= 0 && flashCardID < deck.size()) : "Index must be within range.";
        this.flashCardID = flashCardID;
        this.isFlashCardDeleted = isFlashCardDeleted;
    }

    @Override
    public void execute() {
        if (isFlashCardDeleted) {
            currentDeck.delete(flashCardID);
        }
    }
}
