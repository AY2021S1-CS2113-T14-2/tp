package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.DeckList;

/**
 * Lists all the existing deck.
 */
public class DecksCommand extends NormalCommand {

    /** Constructor. */
    public DecksCommand(DeckList deckList) {
        super(deckList);
    }

    @Override
    public void execute() {
        if (deckList.size() == 0) {
            Ui.printMessage("There are no decks to list.");
        } else {
            Ui.printDeckList(deckList);
        }
    }
}
