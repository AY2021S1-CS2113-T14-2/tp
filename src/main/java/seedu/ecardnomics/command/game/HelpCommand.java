package seedu.ecardnomics.command.game;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.game.Game;

public class HelpCommand extends GameCommand {
    public static final String GAME_HELP = ""
            //         --------------------------------------------------------------------------------
            + "eCardnomics.\n"
            + "Game Mode.\n"
            + "\n"
            + "Usage:\n"
            + "  done           Exits from Game Mode and returns to Normal Mode.\n"
            + "  exit           Exits the program.\n"
            + "  help           Show this output.\n"
            + "\n"
            + "Options:\n"
            + "  --version      Show version.\n"
            + "\n"
            + "Gameplay:\n"
            + Game.GAMEPLAY_MESSAGE;
    //         --------------------------------------------------------------------------------

    public HelpCommand() {
        super();
    }

    @Override
    public void execute() {
        Ui.printHelp(GAME_HELP);
    }
}
