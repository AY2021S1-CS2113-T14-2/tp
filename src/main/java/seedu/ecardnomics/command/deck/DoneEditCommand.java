package seedu.ecardnomics.command.deck;

import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.deck.Deck;

public class DoneEditCommand extends DeckCommand {

    /** Constructor. */
    public DoneEditCommand(Deck deck) {
        super(deck);
    }

    @Override
    public void execute() {
    }

    /** Returns if command given is an instance of <code>DoneEditCommand</code>.  */
    public static boolean isDoneEdit(Command command) {
        return command instanceof DoneEditCommand;
    }
}
