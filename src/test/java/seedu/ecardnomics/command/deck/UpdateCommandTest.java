package seedu.ecardnomics.command.deck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.FlashCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateCommandTest {
    Deck currentDeck;
    int flashCardID;
    static final String OLD_Q = "Knock knock";
    static final String OLD_A = "No joke";

    @Test
    void execute_update() {
        String newQ = "newQ";
        String newA = "newA";
        UpdateCommand cmd = new UpdateCommand(currentDeck, flashCardID, newQ, newA);
        cmd.execute();
        assertEquals(newQ, currentDeck.get(flashCardID).getQuestion());
        assertEquals(newA, currentDeck.get(flashCardID).getAnswer());
    }

    @BeforeEach
    void initializeDeck() {
        currentDeck = new Deck("test deck");
        currentDeck.add(new FlashCard(OLD_Q, OLD_A));
        flashCardID = 0;
    }
}