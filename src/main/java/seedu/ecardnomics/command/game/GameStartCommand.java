package seedu.ecardnomics.command.game;

import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.game.Game;

public class GameStartCommand extends GameCommand {
    Game game;

    /** Constructor. */
    public GameStartCommand(Deck deck) {
        super(deck);
    }

    @Override
    public void execute() {
        game = new Game(currentDeck);
    }

    public Game getGameInstance() {
        return game;
    }
}
