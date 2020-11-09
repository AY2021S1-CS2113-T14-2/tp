package seedu.ecardnomics;

import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.command.deck.DoneEditCommand;
import seedu.ecardnomics.command.game.DoneGameCommand;
import seedu.ecardnomics.command.game.GameStartCommand;
import seedu.ecardnomics.command.normal.EditCommand;
import seedu.ecardnomics.command.ExitCommand;
import seedu.ecardnomics.command.normal.StartCommand;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.game.Game;
import seedu.ecardnomics.parser.DeckParser;
import seedu.ecardnomics.parser.NormalParser;
import seedu.ecardnomics.storage.LogStorage;
import seedu.ecardnomics.storage.Storage;

import java.io.IOException;

/**
 * Main Class for eCardnomics - Flash Card Manager Command Line Program.
 */
public class Main {

    public static final double VERSION_NUMBER = 2.1;
    public static DeckList deckList = new DeckList();
    public static NormalParser normalParser = new NormalParser(deckList);
    public static Storage storage = new Storage();

    /**
     * Executes the command.
     *
     * @param command command from parser
     */
    private static void executeCommand(Command command) {
        command.execute();
    }

    /**
     * Runs Deck Mode to edit a deck.
     *
     * @param deck Deck to edit
     * @return Command used to exit Deck Mode (either <code>done</code> or <code>exit</code>)
     */
    public static Command runDeckMode(Deck deck) {
        DeckParser deckParser = new DeckParser(deckList, deck);

        String userInput;
        Command command;

        do {
            Ui.printDeckPrompt(deck);
            userInput = Ui.readUserInput();

            command = deckParser.parse(userInput);

            executeCommand(command);

        } while (!DoneEditCommand.isDoneEdit(command)
                && !ExitCommand.isExit(command)
                && !StartCommand.isStart(command));

        return command;
    }

    /**
     * Runs Game Mode for specified deck.
     *
     * @param deck to run Game Mode on
     * @return Command used to exit Game Mode (either <code>done</code> or <code>exit</code>)
     */
    public static Command runGameMode(Deck deck) {
        GameStartCommand gameStartCommand = new GameStartCommand(deck);
        executeCommand(gameStartCommand);

        Game game = gameStartCommand.getGameInstance();
        return game.run();
    }

    /**
     * Runs Normal Mode in a loop until <code>exit</code> in input.
     * Enters Deck Mode when <code>edit</code> is input.
     */
    public static void runNormalMode() {
        Ui.printGreeting();

        String userInput;
        Command command;

        do {
            Ui.printNormalPrompt();
            userInput = Ui.readUserInput();

            command = normalParser.parse(userInput);

            executeCommand(command);

            if (EditCommand.isEdit(command)) {
                EditCommand editCommand = (EditCommand) command;
                command = runDeckMode(editCommand.getDeck());

                if (command instanceof DoneEditCommand) {
                    Ui.printNormalWelcome();
                }
            }

            if (StartCommand.isStart(command)) {
                StartCommand startCommand = (StartCommand) command;
                command = runGameMode(startCommand.getDeck());

                if (command instanceof DoneGameCommand) {
                    Ui.printNormalWelcome();
                }
            }

        } while (!ExitCommand.isExit(command));

        Ui.printExitLine();
    }

    /**
     * Main method.
     *
     * @param args Arguments from command line when user starts the program
     */
    public static void main(String[] args) {
        deckList = storage.load(deckList);

        runNormalMode();

        try {
            storage.write(Main.deckList);
        } catch (IOException e) {
            Ui.printMessage("Unable to write file...");
        }
    }
}
