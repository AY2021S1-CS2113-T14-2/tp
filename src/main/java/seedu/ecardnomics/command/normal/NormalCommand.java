package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.deck.DeckList;

/**
 * Parent Command in Normal Mode.
 */
public abstract class NormalCommand extends Command {
    protected DeckList deckList;

    public NormalCommand() {
        deckList = null;
    }

    public NormalCommand(DeckList deckList) {
        assert deckList != null : "Command must operate on a deck list";
        this.deckList = deckList;
    }

    public abstract void execute();
}
