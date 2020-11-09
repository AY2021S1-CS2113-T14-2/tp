package seedu.ecardnomics.command.game;

import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.deck.Deck;

public abstract class GameCommand extends Command {
    protected Deck currentDeck;

    public GameCommand() {
        currentDeck = null;
    }

    public GameCommand(Deck currentDeck) {
        assert currentDeck != null : "Command must operate on a deck.";
        this.currentDeck = currentDeck;
    }

    public abstract void execute();
}
