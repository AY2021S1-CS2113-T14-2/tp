package seedu.ecardnomics.command.normal;

import org.junit.jupiter.api.Test;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCommandTest {

    @Test
    void execute_createNewDeck() {
        DeckList deckList = new DeckList();
        assertEquals(0, deckList.size());
        CreateCommand cmd = new CreateCommand(deckList, new Deck("Test"));
        cmd.execute();
        assertEquals(1, deckList.size());
        assertEquals("Test", deckList.getDeck(0).getName());
    }

}