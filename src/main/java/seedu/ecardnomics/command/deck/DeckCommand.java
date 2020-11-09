package seedu.ecardnomics.command.deck;

import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.deck.Deck;

public abstract class DeckCommand extends Command {
    protected Deck currentDeck;

    public DeckCommand() {
        currentDeck = null;
    }

    public DeckCommand(Deck currentDeck) {
        assert currentDeck != null : "Command must operate on a deck.";
        this.currentDeck = currentDeck;
    }

    public abstract void execute();
}
