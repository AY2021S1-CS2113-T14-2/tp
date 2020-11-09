package seedu.ecardnomics.deck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckListTest {
    @Test
    void size() {
        DeckList deckList = initialiseDeckList(2);
        assertEquals(2, deckList.size());
        deckList.removeDeck(1);
        assertEquals(1, deckList.size());
        deckList.removeDeck(0);
        assertEquals(0, deckList.size());
        deckList.addDeck(new Deck("deck 1"));
        assertEquals(1, deckList.size());
    }

    @Test
    void testToString() {
        DeckList deckList = initialiseDeckList(2);
        String deckListString = "1. deck 1\n2. deck 2";
        assertEquals(deckListString, deckList.toString());
    }

    DeckList initialiseDeckList(int size) {
        DeckList deckList = new DeckList();
        for (int i = 1; i <= size; i++) {
            Deck deck = new Deck(String.format("deck %d", i));
            deckList.addDeck(deck);
        }
        return deckList;
    }
}