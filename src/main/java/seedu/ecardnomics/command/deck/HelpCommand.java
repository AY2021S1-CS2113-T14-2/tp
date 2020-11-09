package seedu.ecardnomics.command.deck;

import seedu.ecardnomics.Ui;

public class HelpCommand extends DeckCommand {
    public static final String DECK_HELP = ""
            // --------------------------------------------------------------------------------
            + "eCardnomics.\n"
            + "Deck Mode.\n"
            + "\n"
            + "Usage:\n"
            + "  add         [<qn> /ans <ans>]           Adds a new flash card to the current  \n"
            + "                                            deck.                               \n"
            + "  list        [/ans]                      Lists all flash cards in the current  \n"
            + "                                            deck, optionally with answers.      \n"
            + "  delete <ix> [-y]                        Deletes the flash card at list index  \n"
            + "  update <ix>                             Updates the flash card at list index  \n"
            + "                                            <ix> from the current deck.         \n"
            + "  pptx        [-y] [-cs <index> | -oc     Creates a PowerPoint slides based on  \n"
            + "               <bg color> <txt color>]      current deck.                       \n"
            + "  start                                   Enter Game Mode for this deck! Do your\n"
            + "                                            best!                               \n"
            + "  done                                    Exits from Deck Mode and returns to   \n"
            + "                                            Normal Mode.                        \n"
            + "  exit                                    Exits the program.                    \n"
            + "  help                                    Show this output.                     \n"
            + "\n"
            + "Options:\n"
            + "  --version                               Show version.";
    //         --------------------------------------------------------------------------------

    public HelpCommand() {
        super();
    }

    @Override
    public void execute() {
        Ui.printHelp(DECK_HELP);
    }
}
