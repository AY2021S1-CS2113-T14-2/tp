package seedu.ecardnomics.deck;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashCardTest {
    static FlashCard flashCard;
    static FlashCard longFlashCard;
    static FlashCard longWordFlashCard;
    static final String LABEL_PAD = " ".repeat("Question: ".length());

    @Test
    void testToString_default_goodFormat() {
        String flashCardString = "Question: Who's that Pokemon?" + System.lineSeparator()
                + "Answer:   It's Pikachu!";
        assertEquals(flashCardString, flashCard.toString());
    }

    @Test
    void testToString_default_wrappedLine() {
        String flashCardString = "Question: "
                + "Ok, long question let's goooooooo! Ahhhhhhhhhhhhh. Make this question " + System.lineSeparator()
                + LABEL_PAD + "veryyyyyy longggg!" + System.lineSeparator()
                + "Answer:   " + "Ahhhhhhhhhhhhhhhhhhhhhhhh!!!!!!!!!!!!!! Get an even longer answer. How "
                + System.lineSeparator() + LABEL_PAD
                + "many lines will this answer span? I do not know. Maybe we can get it "
                + System.lineSeparator() + LABEL_PAD
                + "to three lines? Perhaps. Anyway span reminds me of linear algebra.";
        assertEquals(flashCardString, longFlashCard.toString());
    }

    @Test
    void testToString_default_wrapLongWord() {
        String flashCardString = "Question: Do you have Pneumonoultramicroscopicsilicovolcanoconiosis?"
                + System.lineSeparator() + "Answer:   Try to enter the loop so create an answer with "
                + "more than 2 lines: Aaaa" + System.lineSeparator() + LABEL_PAD
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhh"
                + System.lineSeparator() + LABEL_PAD + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
                + "hhhhhhhhhhhhh" + System.lineSeparator() + LABEL_PAD
                + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhSaveMeeeeeeeeeeeeeee"
                + System.lineSeparator() + LABEL_PAD + "e";
        assertEquals(flashCardString, longWordFlashCard.toString());
    }

    @Test
    void testToString_withType_goodFormat() {
        String flashCardQn = "Question: Who's that Pokemon?";
        String flashCardAns = "Answer:   It's Pikachu!";
        assertEquals(flashCardQn, flashCard.toString(true, 0));
        assertEquals(flashCardAns, flashCard.toString(false, 0));
    }

    @Test
    void testToString_withType_wrappedLine() {
        String flashCardQn = "Question: "
                + "Ok, long question let's goooooooo! Ahhhhhhhhhhhhh. Make this question " + System.lineSeparator()
                + LABEL_PAD + "veryyyyyy longggg!";
        String flashCardAns = "Answer:   "
                + "Ahhhhhhhhhhhhhhhhhhhhhhhh!!!!!!!!!!!!!! Get an even longer answer. How " + System.lineSeparator()
                + LABEL_PAD + "many lines will this answer span? I do not know. Maybe we can get it "
                + System.lineSeparator()
                + LABEL_PAD + "to three lines? Perhaps. Anyway span reminds me of linear algebra.";
        assertEquals(flashCardQn, longFlashCard.toString(true, 0));
        assertEquals(flashCardAns, longFlashCard.toString(false, 0));
    }

    @Test
    void testToString_withType_wrapLongWord() {
        String largeSerialNumber = Integer.MAX_VALUE + ". ";
        String indexPad = " ".repeat(largeSerialNumber.length());
        String flashCardQn = largeSerialNumber + "Question: Do you have "
                + "Pneumonoultramicroscopicsilicovolcanoconiosis?";
        String flashCardAns = indexPad + "Answer:   Try to enter the loop so create an answer with more than 2 "
                + System.lineSeparator() + indexPad + LABEL_PAD + "lines: Aaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + System.lineSeparator() + indexPad + LABEL_PAD + "aaaahhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
                + "hhhhhhhhhhhhhhhhhhhhhhhhh"
                + System.lineSeparator() + indexPad + LABEL_PAD + "hhhhhhhhhhhhhhhhhhhhhhhhhh"
                + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
                + System.lineSeparator() + indexPad + LABEL_PAD + "hhhhhhhhhhhhhhhhhhhhhhhhhhhSav"
                + "eMeeeeeeeeeeeeeeee";
        assertEquals(flashCardQn, largeSerialNumber
                + longWordFlashCard.toString(true, largeSerialNumber.length()));
        assertEquals(flashCardAns, longWordFlashCard.toString(false, largeSerialNumber.length()));
    }

    @BeforeAll
    public static void createFlashCard() {
        flashCard = new FlashCard("Who's that Pokemon?", "It's Pikachu!");

        String longQuestion = "Ok, long question let's goooooooo! Ahhhhhhhhhhhhh."
                + " Make this question veryyyyyy longggg!";
        String longAnswer = "Ahhhhhhhhhhhhhhhhhhhhhhhh!!!!!!!!!!!!!! "
                + "Get an even longer answer. How many lines will this answer span? "
                + "I do not know. Maybe we can get it to three lines? Perhaps. "
                + "Anyway span reminds me of linear algebra.";
        longFlashCard = new FlashCard(longQuestion, longAnswer);

        String longWordQn = "Do you have Pneumonoultramicroscopicsilicovolcanoconiosis?";
        String longWordAns = "Try to enter the loop so create an answer with more than 2 lines: "
                + "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhhhhhhhh"
                + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
                + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhSaveMeeeeeeeeeeeeeeee";
        longWordFlashCard = new FlashCard(longWordQn, longWordAns);
    }
}