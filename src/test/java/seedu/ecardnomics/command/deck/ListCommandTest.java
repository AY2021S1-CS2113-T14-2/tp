package seedu.ecardnomics.command.deck;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.FlashCard;
import seedu.ecardnomics.storage.LogStorage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void execute_emptyDeck_listCommand() {
        String expectedOutput =
                "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "Deck is currently empty!" + System.lineSeparator() + "--------------------"
                + "------------------------------------------------------------" + System.lineSeparator();

        Deck deck = new Deck("Test");
        ListCommand cmd = new ListCommand(deck, "/ans");
        cmd.execute();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_nonEmptyDeck_ansArgument_listCommand() {
        String expectedOutput =
                "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "You are now viewing deck: Test" + System.lineSeparator()
                + "1. Question: Test Question" + System.lineSeparator()
                + "   Answer:   Test Answer" + System.lineSeparator() + "--------------------"
                + "------------------------------------------------------------" + System.lineSeparator();

        Deck deck = new Deck("Test");
        deck.add(new FlashCard("Test Question", "Test Answer"));
        ListCommand cmd = new ListCommand(deck, "/ans");
        cmd.execute();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_nonEmptyDeck_noAnsArgument_listCommand() {
        String expectedOutput =
                "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "You are now viewing deck: Test" + System.lineSeparator()
                + "1. Question: Test Question" + System.lineSeparator() + "--------------------"
                + "------------------------------------------------------------" + System.lineSeparator();

        Deck deck = new Deck("Test");
        deck.add(new FlashCard("Test Question", "Test Answer"));
        ListCommand cmd = new ListCommand(deck, "");
        cmd.execute();
        assertEquals(expectedOutput, outContent.toString());
    }
}
