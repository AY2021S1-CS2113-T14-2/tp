package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;

/**
 * Creates a deck adds it to the existing deck list.
 */
public class CreateCommand extends NormalCommand {
    Deck newDeck;

    /**
     * Constructor.
     *
     * @param deckList reference to the existing list of decks
     * @param deck the new deck created
     */
    public CreateCommand(DeckList deckList, Deck deck) {
        super(deckList);
        assert deck != null : "Do not add null objects to the list.";
        newDeck = deck;
    }

    @Override
    public void execute() {
        deckList.addDeck(newDeck);
        Ui.printNewDeck(newDeck);
    }
}
