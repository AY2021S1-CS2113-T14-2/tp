package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.DeckList;

public class HelpCommand extends NormalCommand {
    public static final String NORMAL_HELP = ""
            // --------------------------------------------------------------------------------
            + "eCardnomics.\n"
            + "Normal Mode.\n"
            + "\n"
            + "Usage:\n"
            + "  create <nm>   [/tag <tag1> [<tag2> ...]]     Creates a new deck of flash cards\n"
            + "                                                 named <nm>.                    \n"
            + "  decks                                        Lists all available decks.       \n"
            + "  edit   <ix>                                  Enter Deck Mode for editing the  \n"
            + "                                                 deck at list index <ix>.       \n"
            + "  start  <ix>                                  Enter Game Mode for deck at list \n"
            + "                                                 index <ix>! Do your best!      \n"
            + "  delete <ix>   [-y]                           Deletes the deck at list index   \n"
            + "                                                 <ix> from list of decks.       \n"
            + "  pptx   <ix>   [-y] [-cs <index> | -oc        Creates a PowerPoint slides based\n"
            + "                 <bg color> <txt color>]         on the deck at list index <ix>.\n"
            + "  tag    <ix>   /tag <tag1> [<tag2> ...]       Tags the deck at list index <ix> \n"
            + "                                                 with 1 or more tags.           \n"
            + "  untag  <ix>   /tag <tag1> [<tag2> ...]       Untags specified <tag>s of the   \n"
            + "                                                 deck at list index <ix>.       \n"
            + "  search <tag1> [<tag2> ...]                   Search deck list for decks tagged\n"
            + "                                                 with specified <tag>s.         \n"
            + "  exit                                         Exits the program.\n"
            + "  help                                         Show this output.\n"
            + "\n"
            + "Options:\n"
            + "  --version                                    Show version.";
    //         --------------------------------------------------------------------------------

    public HelpCommand() {
        super();
    }

    @Override
    public void execute() {
        Ui.printHelp(NORMAL_HELP);
    }
}
