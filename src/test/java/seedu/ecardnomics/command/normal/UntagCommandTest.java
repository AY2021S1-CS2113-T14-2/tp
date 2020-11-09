package seedu.ecardnomics.command.normal;

import org.junit.jupiter.api.Test;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UntagCommandTest {

    @Test
    void execute_removeTagsFromDeck() {
        DeckList deckList = new DeckList();
        ArrayList<String> tags = new ArrayList<>();
        tags.add("anime");
        tags.add("unreal");
        Deck newDeck = new Deck("Pokemon", tags);
        deckList.addDeck(newDeck);
        ArrayList<String> removedTags = new ArrayList<>();
        removedTags.add("unreal");
        UntagCommand cmd = new UntagCommand(deckList, 0, removedTags, true);
        cmd.execute();
        assertEquals(1, deckList.getDeck(0).getTag().size());
    }
}
