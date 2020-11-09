package seedu.ecardnomics.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.command.ExitCommand;
import seedu.ecardnomics.command.VersionCommand;
import seedu.ecardnomics.command.VoidCommand;
import seedu.ecardnomics.command.game.DoneGameCommand;
import seedu.ecardnomics.command.game.GameResponseCommand;
import seedu.ecardnomics.command.game.HelpCommand;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.deck.FlashCard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameParserTest {
    GameParser gameParser;

    @Test
    void parseCommand_ExitCommand_success() {
        assertTrue(gameParser.parseCommand("exit", "") instanceof ExitCommand);
    }

    @Test
    void parseCommand_DoneGameCommand_success() {
        assertTrue(gameParser.parseCommand("done", "") instanceof DoneGameCommand);


    }

    @Test
    void parseCommand_HelpCommandAnyArguments_success() {
        assertTrue(gameParser.parseCommand("help", "") instanceof HelpCommand);
        assertTrue(gameParser.parseCommand("help", "gibberish\t") instanceof HelpCommand);
        assertTrue(gameParser.parseCommand("help", "\n   12gibberish\t") instanceof HelpCommand);
    }

    @Test
    void parseCommand_VersionCommandAnyArguments_success() {
        assertTrue(gameParser.parseCommand("--version", "") instanceof VersionCommand);
        assertTrue(gameParser.parseCommand("--version", "\t1") instanceof VersionCommand);
        assertTrue(gameParser.parseCommand("--version", "\n hdkljfashdfs\t\t") instanceof VersionCommand);
    }

    @Test
    void parseCommand_AnythingElse_returnsVoidCommand() {
        assertTrue(gameParser.parseCommand("random", "") instanceof VoidCommand);
        assertTrue(gameParser.parseCommand("lmaoo sdalkfe we", "") instanceof VoidCommand);
    }

    @Test
    void parse_someGameModeCommandPermissibleFormat_success() {
        assertTrue(gameParser.parse("--version") instanceof VersionCommand);
        assertTrue(gameParser.parse("--version   ") instanceof VersionCommand);
        assertTrue(gameParser.parse("done") instanceof DoneGameCommand);
        assertTrue(gameParser.parse("done   ") instanceof DoneGameCommand);
        assertTrue(gameParser.parse("exit") instanceof ExitCommand);
        assertTrue(gameParser.parse("help") instanceof HelpCommand);
    }

    @Test
    void parse_commandWordWithOtherWords_returnsGameResponseCommand() {
        assertTrue(gameParser.parse("--version s df") instanceof GameResponseCommand);
        assertTrue(gameParser.parse("--version  22 ") instanceof GameResponseCommand);
        assertTrue(gameParser.parse("done sdsd") instanceof GameResponseCommand);
        assertTrue(gameParser.parse("done   22") instanceof GameResponseCommand);
        assertTrue(gameParser.parse("exit sd2") instanceof GameResponseCommand);
        assertTrue(gameParser.parse("help sde 2") instanceof GameResponseCommand);
    }

    @Test
    void parse_AnythingElseGivesGameResponseCommand_responseRecordedTrimmed() {
        GameResponseCommand response = (GameResponseCommand) gameParser.parse("Some answer here");
        assertEquals(response.getAttempt(), "Some answer here");
        response = (GameResponseCommand) gameParser.parse("     Some answer here      ");
        assertEquals(response.getAttempt(), "Some answer here");
    }

    @BeforeEach
    void initialiseGameParser() {
        DeckList deckList = initialiseDeckList(2);
        Deck deck = initialiseDeck(deckList, 2);
        gameParser = new GameParser(deck);
    }

    DeckList initialiseDeckList(int size) {
        DeckList deckList = new DeckList();
        for (int i = 1; i <= size; i++) {
            Deck deck = new Deck(String.format("deck %d", i));
            deckList.addDeck(deck);
        }
        return deckList;
    }

    Deck initialiseDeck(DeckList deckList, int size) {
        deckList.addDeck(new Deck("Pokemon"));
        Deck deck = deckList.getDeck(2);
        for (int i = 1; i <= size; i++) {
            FlashCard flashCard = new FlashCard(String.format("q %d", i), String.format("a %d", i));
            deck.add(flashCard);
        }
        return deck;
    }
}