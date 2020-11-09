package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.deck.DeckList;

public class DeleteDeckCommand extends NormalCommand {
    private int index;
    private boolean isDeckDeleted;

    public DeleteDeckCommand(DeckList decks, int index, boolean isDeckDeleted) {
        super(decks);
        assert (index >= 0 && index < decks.size()) : "Index must be within range.";
        this.index = index;
        this.isDeckDeleted = isDeckDeleted;
    }

    @Override
    public void execute() {
        if (isDeckDeleted) {
            deckList.removeDeck(index);
        }
    }
}
