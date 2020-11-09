package seedu.ecardnomics.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.deck.FlashCard;

class GameStorageTest {
    GameStorage storage;
    Deck originalDeck;

    @Test
    void createRandomisedStack_takesDeck_originalDeckUnchanged() throws Exception {
        Deck deckCopy = new Deck("Pokemon");
        copyDeck(originalDeck, deckCopy);
        storage.createRandomisedStack(originalDeck);
        checkDeckEquality(originalDeck, deckCopy);
    }

    void checkDeckEquality(Deck original, Deck copy) throws Exception {
        boolean isEqual = true;
        for (int i = 0; i < original.size(); i++) {
            if (!original.get(i).equals(copy.get(i))) {
                isEqual = false;
                break;
            }
        }

        if (!isEqual) {
            throw new Exception("Decks not equal!");
        }
    }

    void copyDeck(Deck originalDeck, Deck deckCopy) {
        for (FlashCard fc : originalDeck.getDeck()) {
            deckCopy.add(fc);
        }
    }

    @BeforeEach
    void preliminaries() {
        DeckList deckList = initialiseDeckList(2);
        originalDeck = initialiseDeck(deckList, 2);
        storage = new GameStorage(originalDeck);
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