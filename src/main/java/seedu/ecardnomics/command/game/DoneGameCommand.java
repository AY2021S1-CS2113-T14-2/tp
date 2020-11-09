package seedu.ecardnomics.command.game;

import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.deck.Deck;

public class DoneGameCommand extends GameCommand {
    /** Constructor. */
    public DoneGameCommand(Deck deck) {
        super(deck);
    }

    @Override
    public void execute() {
    }

    /** Returns if command given is an instance of <code>DoneEditCommand</code>.  */
    public static boolean isDoneGame(Command command) {
        return command instanceof DoneGameCommand;
    }
}
