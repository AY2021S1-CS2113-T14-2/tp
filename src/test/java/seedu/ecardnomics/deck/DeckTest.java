package seedu.ecardnomics.deck;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {
    @Test
    void getName() {
        Deck deck = new Deck("Pokemon");
        assertEquals("Pokemon", deck.getName());
    }

    @Test
    void setName() {
        Deck deck = new Deck("Pokemon");
        deck.setName("Pokemon Go");
        assertEquals("Pokemon Go", deck.getName());
    }

    // @Test
    // void get_emptyDeck_exceptionThrown() {
    //     Deck deck = new Deck("Pokemon");
    //     try {
    //         assertEquals(0, deck.get(0));
    //         fail();
    //     } catch (Exception e) {
    //         NullPointerException npe = new NullPointerException();
    //         assertEquals(npe.getMessage(), e.getMessage());
    //     }
    // }

    // @Test
    // void get_invalidIndex_exceptionThrown() {
    //     Deck deck = initialiseDeck(2);
    //     try {
    //         assertEquals(0, deck.get(-1));
    //         assertEquals(0, deck.get(2));
    //         assertEquals(0, deck.get(3));
    //         fail(); // test should not reach this line
    //     } catch (Exception e) {
    //         NullPointerException npe = new NullPointerException();
    //         assertEquals(npe.getMessage(), e.getMessage());
    //     }
    // }

    @Test
    void get_validIndex_success() {
        Deck deck = new Deck("Pokemon");
        FlashCard q1 = new FlashCard("q 1", "a 1");
        FlashCard q2 = new FlashCard("q 2", "a 2");
        deck.add(q1);
        deck.add(q2);
        assertEquals(q1, deck.get(0));
        assertEquals(q2, deck.get(1));
    }

    @Test
    void size() {
        Deck deck = initialiseDeck(2, new ArrayList<>());
        assertEquals(2, deck.size());
        deck.delete(1);
        assertEquals(1, deck.size());
        deck.delete(0);
        assertEquals(0, deck.size());
        deck.add(new FlashCard("q 1", "a 1"));
        assertEquals(1, deck.size());
    }

    @Test
    void constructor_newDeckWithTags() {
        ArrayList<String> tags =  new ArrayList<>();
        tags.add("anime");
        tags.add("unreal");
        Deck deck = initialiseDeck(2, tags);
        assertEquals(2, deck.getTag().size());
    }

    @Test
    void getTagString_StringOfTags() {
        ArrayList<String> tags =  new ArrayList<>();
        tags.add("anime");
        tags.add("unreal");
        Deck deck = initialiseDeck(2, tags);
        String expectedOutput = "anime | unreal";
        assertEquals(expectedOutput, deck.getTagString());
    }

    @Test
    void addTag_newTags_void() {
        ArrayList<String> tags =  new ArrayList<>();
        tags.add("anime");
        tags.add("unreal");
        Deck deck = initialiseDeck(2, tags);
        ArrayList<String> newTags = new ArrayList<>();
        newTags.add("for-kids");
        deck.addTag(newTags);
        assertEquals(3, deck.getTag().size());
    }

    @Test
    void removeTag_newTags_void() {
        ArrayList<String> tags =  new ArrayList<>();
        tags.add("anime");
        tags.add("unreal");
        tags.add("for-kids");
        Deck deck = initialiseDeck(2, tags);
        ArrayList<String> removedTags = new ArrayList<>();
        removedTags.add("for-kids");
        deck.removeTag(removedTags);
        assertEquals(2, deck.getTag().size());
    }

    // @Test
    // void delete_emptyDeck_exceptionThrown() {
    //     Deck deck = initialiseDeck(0);
    //     try {
    //         deck.delete(0);
    //         fail(); // test should not reach this line
    //     } catch (Exception e) {
    //         NullPointerException npe = new NullPointerException();
    //         assertEquals(npe.getMessage(), e.getMessage());
    //     }
    // }

    // @Test
    // void delete_invalidIndex_exceptionThrown() {
    //     Deck deck = initialiseDeck(2);
    //     try {
    //         deck.delete(-1);
    //         deck.delete(2);
    //         deck.delete(3);
    //         fail(); // test should not reach this line
    //     } catch (Exception e) {
    //         NullPointerException npe = new NullPointerException();
    //         assertEquals(npe.getMessage(), e.getMessage());
    //     }
    // }

    @Test
    void delete_validIndex_success() {
        Deck deck = initialiseDeck(2, new ArrayList<>());
        deck.delete(1);
        assertEquals(1, deck.size());
    }

    @Test
    void testToString_default_goodFormat() {
        Deck deck = initialiseDeck(2, new ArrayList<>());
        String deckString = "Pokemon:" + System.lineSeparator() + "1. Question: q 1"
                + System.lineSeparator() + "   Answer:   a 1\n\n2. Question: q 2"
                + System.lineSeparator() + "   Answer:   a 2";
        assertEquals(deckString, deck.toString());
    }

    @Test
    void testToString_withType_goodFormat() {
        Deck deck = initialiseDeck(2, new ArrayList<>());
        String deckString = "1. Question: q 1\n\n" + "2. Question: q 2";
        String deckStringAns = "1. Question: q 1" + System.lineSeparator() + "   Answer:   a 1\n\n"
                + "2. Question: q 2" + System.lineSeparator() + "   Answer:   a 2";
        assertEquals(deckString, deck.toString(true));
        assertEquals(deckStringAns, deck.toString(false));
    }

    Deck initialiseDeck(int size, ArrayList<String> tags) {
        Deck deck = new Deck("Pokemon", tags);
        for (int i = 1; i <= size; i++) {
            FlashCard flashCard = new FlashCard(String.format("q %d", i), String.format("a %d", i));
            deck.add(flashCard);
        }
        return deck;
    }
}