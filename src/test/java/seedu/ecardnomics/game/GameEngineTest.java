package seedu.ecardnomics.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.command.game.DoneGameCommand;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.deck.FlashCard;
import seedu.ecardnomics.parser.GameParser;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {
    Deck deck;
    DeckList deckList;
    GameStorage storage;
    GameParser gameParser;
    GameEngine engine;

    FlashCard[] createfcStubs(int number) {
        FlashCard[] fcStubs = new FlashCard[number];
        for (int i = 0; i < number; i++) {
            fcStubs[i] = new FlashCard(String.format("ques-stub%d", i), String.format("ans-stub%d", i));
        }
        return fcStubs;
    }

    /**
     * Checks if storage.deque is randomised enough by sequentially popping and ensuring there is some degree of
     * randomness.
     *
     * @return true if random "enough", otherwise false
     */
    boolean checkRandomnessOfDequeFromReference(ArrayDeque<FlashCard> dequeFromRetestStubs,
                                                  ArrayList<FlashCard> referenceList) {
        int mismatchCount = 0;
        while (!dequeFromRetestStubs.isEmpty()) {
            // flash cards follow strict format
            int index = 0;
            FlashCard fc = dequeFromRetestStubs.pop();
            if (!fc.getQuestion().equals(referenceList.get(index))) {
                mismatchCount++;
            }
            index++;
        }

        double mismatchRatio = (double) referenceList.size() / mismatchCount;
        double threshold = 0.25;
        if (mismatchRatio >= threshold) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    void update_storageDequeAndRetestStoreEmptyUserEntersN_returnsDoneGameCommand() {
        storage.deque.clear();
        storage.retestStore.clear();
        FlashCard fcStub = createfcStubs(1)[0];
        assertTrue(engine.update(false, fcStub) instanceof DoneGameCommand);
    }

    @Test
    void update_storageDequeNotEmpty_returnsNull() {
        FlashCard fcStub = createfcStubs(1)[0];
        assertTrue(engine.update(true, fcStub) == null);
        assertTrue(engine.update(false, fcStub) == null);

    }

    @Test
    void updateDeque_storageDequeEmptyAndUpdateFromRetestStore_success() {
        storage.deque.clear();
        FlashCard[] fcStubs = createfcStubs(5);
        for (FlashCard fc : fcStubs) {
            storage.retestStore.add(fc);
        }
        ArrayList<FlashCard> retestStoreRef = storage.retestStore;
        storage.createRandomisedStack(storage.retestStore);
        engine.updateDeque();
        assertTrue(checkRandomnessOfDequeFromReference(storage.deque, retestStoreRef));
        assertEquals(0, storage.retestStore.size());
    }

    @Test
    void updateRetestStore_responseY_addsFlashCard() {
        storage.retestStore.clear();
        FlashCard fcStub = createfcStubs(1)[0];
        engine.updateRetestStore(true, fcStub);
        assertTrue(storage.retestStore.contains(fcStub));
    }

    @Test
    void updateRetestStore_responseN_doNothing() {
        storage.retestStore.clear();
        FlashCard fcStub = createfcStubs(1)[0];
        engine.updateRetestStore(false, fcStub);
        assertFalse(storage.retestStore.contains(fcStub));
    }

    @Test
    void checkAttempt_validCommand_success() {
        Command attempt = gameParser.parse("ans-stub0");
        FlashCard fcStub = createfcStubs(1)[0];
        assertEquals(100.0, engine.checkAttempt(attempt, fcStub));
    }

    @Test
    void getMatchPercentage_completeMatchSpacePunctuationPadded_success() {
        double match = engine.getMatchPercentage("this is an answer.!` -_+=)(*&^%$#@!", "this is an answer   .");
        assertEquals(100.0, match);
    }

    @Test
    void getMatchPercentage_completeMatchSpacePaddedMatchedWords_success() {
        double match = engine.getMatchPercentage("this  \t\t is   an    answer.", "this is an answer");
        assertEquals(100.0, match);
    }

    @Test
    void getMatchPercentage_zeroMatchSpacePunctuationPadded_success() {
        double match = engine.getMatchPercentage("completely wrong.!` -_+=)(*&^%$#@!", "this is an answer   .");
        assertEquals(0.0, match);
    }

    @Test
    void getMatchPercentage_punctuationAnswerZeroMatch_success() {
        double match = engine.getMatchPercentage("!@#//...    . %^*()", "!@#//...    . %^*()");
        assertEquals(0.0, match);
    }

    @BeforeEach
    void preliminaries() {
        deckList = initialiseDeckList(2);
        deck = initialiseDeck(deckList, 2);
        storage = new GameStorage(deck);
        gameParser = new GameParser(deck);
        engine = new GameEngine(storage);
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