package seedu.ecardnomics.game;

import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.FlashCard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class GameStorage {
    Deck originalDeck;
    ArrayDeque<FlashCard> deque;
    ArrayList<FlashCard> retestStore;

    GameStorage(Deck deck) {
        originalDeck = deck;
        deque = createRandomisedStack(deck);
        retestStore = new ArrayList<>();
    }

    ArrayDeque<FlashCard> createRandomisedStack(Deck deck) {
        ArrayList<FlashCard> randomisedDeck = new ArrayList<>(deck.getDeck());
        Collections.shuffle(randomisedDeck);
        return new ArrayDeque<>(randomisedDeck);
    }

    ArrayDeque<FlashCard> createRandomisedStack(ArrayList<FlashCard> retestStore) {
        Collections.shuffle(retestStore);
        return new ArrayDeque<>(retestStore);
    }

    void refreshRetestStore() {
        retestStore = new ArrayList<>();
    }
}
