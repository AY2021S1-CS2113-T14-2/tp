package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.command.game.GameStartCommand;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;

public class StartCommand extends NormalCommand {
    Deck deck;

    /** Constructor. */
    public StartCommand(DeckList deckList, Deck deck) {
        super(deckList);
        assert deck != null : "Do not operate on a null reference.";
        this.deck = deck;
    }

    @Override
    public void execute() {
        Ui.printGameWelcome(deckList.getIndexOf(deck) + 1, deck);
        new GameStartCommand(deck);
    }

    /** Returns Deck whose Game Mode is in progress. */
    public Deck getDeck() {
        return deck;
    }

    /** Returns if command given is an instance of <code>StartCommand</code>. */
    public static boolean isStart(Command command) {
        return command instanceof StartCommand;
    }
}
