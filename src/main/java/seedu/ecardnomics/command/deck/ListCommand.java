package seedu.ecardnomics.command.deck;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.Deck;

public class ListCommand extends DeckCommand {
    private final String arguments;

    /** Constructor. */
    public ListCommand(Deck deck, String arguments) {
        super(deck);
        assert arguments != null : "Arguments cannot be a null String.";
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        Ui.printDeck(currentDeck, !arguments.contains("/ans"));
    }
}
