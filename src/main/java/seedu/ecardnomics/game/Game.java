package seedu.ecardnomics.game;

import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.deck.Deck;

public class Game {
    GameStorage storage;
    GameEngine engine;
    public static final String GAMEPLAY_MESSAGE = ""
            + "Questions will be displayed in a randomised order. At each question, you can\n"
            + "    1. Try to attempt an answer at the question, by typing at the prompt\n"
            + "    2. Press <enter> (with an empty attempt if you want to do it in your head)\n"
            + System.lineSeparator()
            + "Then, our 'advanced' algorithms will check your answer and score your answer (if\n"
            + "any), and display the correct answer for you to check your answer against.\n"
            + "Finally, we will ask if you think you got it right. If you did not, the question\n"
            + "will be inserted back into the question pool, and you will get a chance to\n"
            + "attempt it again!";
    public static final String WELCOME_MESSAGE = ""
            + "Welcome to Game Mode!"
            + "\n"
            + System.lineSeparator()
            + "In this mode, you test your knowledge against the flash cards in the deck.\n"
            + System.lineSeparator()
            + GAMEPLAY_MESSAGE
            + "\n"
            + System.lineSeparator()
            + "Type `done` to return to Normal Mode, `exit` to exit application.\n"
            + "Type `help` to see a list of commands. Have fun!\n"
            + System.lineSeparator()
            + "Game Mode is started for: ";

    public Game(Deck deck) {
        storage = new GameStorage(deck);
        engine = new GameEngine(storage);
    }

    public Command run() {
        return engine.runGameLoop();
    }
}
