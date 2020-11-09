package seedu.ecardnomics.command.deck;

import org.junit.jupiter.api.Test;
import seedu.ecardnomics.deck.Deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void execute_addNewFlashCard() {
        Deck deck = new Deck("Test");
        AddCommand cmd = new AddCommand(deck, "Test Question", "Test Amswer");
        cmd.execute();
        assertEquals(1, deck.size());
    }
}
