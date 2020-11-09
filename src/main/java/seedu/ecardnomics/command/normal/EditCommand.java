package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;

public class EditCommand extends NormalCommand {
    Deck deck;

    /** Constructor. */
    public EditCommand(DeckList deckList, Deck deck) {
        super(deckList);
        assert deck != null : "Do not operate on a null reference.";
        this.deck = deck;
    }

    @Override
    public void execute() {
        Ui.printDeckWelcome(deckList.getIndexOf(deck) + 1, deck);
    }

    /** Returns Deck which is to be edited. */
    public Deck getDeck() {
        return deck;
    }

    /** Returns if command given is an instance of <code>EditCommand</code>. */
    public static boolean isEdit(Command command) {
        return command instanceof EditCommand;
    }
}
