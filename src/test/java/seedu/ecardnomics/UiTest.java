package seedu.ecardnomics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.FlashCard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ecardnomics.Ui.printTagsRemovedLine;
import static seedu.ecardnomics.Ui.printNewDeck;
import static seedu.ecardnomics.Ui.printNewTags;
import static seedu.ecardnomics.Ui.printDeletedDeckQuestion;
import static seedu.ecardnomics.Ui.printDeckDeletedLine;
import static seedu.ecardnomics.Ui.printInvalidTagsLine;
import static seedu.ecardnomics.Ui.printRemovedTagsQuestion;
import static seedu.ecardnomics.Ui.printUpdateAnswerLine;
import static seedu.ecardnomics.Ui.printUpdateQuestionLine;
import static seedu.ecardnomics.Ui.printSearchDecksLine;
import static seedu.ecardnomics.Ui.formStringOfTags;



public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void printMessage_singleLine_goodFormat() {
        String singleLine = "Hello! Welcome to eCardnomics!\t\t\tFlashCards Application.";
        String message = Ui.DASH_LINES + System.lineSeparator() + singleLine + System.lineSeparator()
                + Ui.DASH_LINES + System.lineSeparator();
        Ui.printMessage(singleLine);
        assertEquals(message, outContent.toString());
    }

    @Test
    void printNormalPrompt() {
    }

    @Test
    void printDeckPrompt() {
    }

    @Test
    void printPrompt() {
    }

    @Test
    void printNormalWelcome() {
    }

    @Test
    void printDeckWelcome() {
    }

    @Test
    void printGreeting() {
    }

    @Test
    void printExitLine() {
    }

    @Test
    void printNotRecognisedLine() {
    }

    @Test
    void printAddFlashCardLine() {
    }

    @Test
    void printEnterQuestionLine() {
    }

    @Test
    void printEnterAnswerLine() {
    }

    @Test
    void printFlashCardAddedLine() {
    }

    @Test
    void printDeck() {
    }

    @Test
    void printDeleteFlashCardLine() {
    }

    @Test
    void printFlashCardDeletedLine() {
    }

    @Test
    void printHelp() {
    }

    @Test
    void printNewDeck_newDeckName_deckName() {

        String expectedOutput =  "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "New deck created: Pokemon" + System.lineSeparator() + "--------------------"
                + "------------------------------------------------------------" + System.lineSeparator();
        printNewDeck(new Deck("Pokemon"));
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printDeckList() {
    }

    @Test
    void printDeletedDeckQuestion_deletedDeckName_questionLine() {
        String expectedOutput = "Do you want to delete Pokemon deck? [y/n] ";
        Ui.printDeletedDeckQuestion("Pokemon");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printDeletedDeck_deletedDeckName_confirmation() {
        String expectedOutput = "Pokemon has been deleted." + System.lineSeparator();
        printDeckDeletedLine("Pokemon");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printNewTags_none_newTagsLine() {
        ArrayList<String> newTagsArray = new ArrayList<>();
        newTagsArray.add("Anime");
        newTagsArray.add("Unreal");
        printNewTags("Pokemon", newTagsArray);
        String expectedOutput = "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "The deck Pokemon has been tagged as: Anime | Unreal" + System.lineSeparator()
                + "--------------------------------------------------------------------------------"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printInvalidTagsLine_none_warning() {
        String expectedOutput = "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "Tag Cons is not in the deck already!" + System.lineSeparator() + "--------------------"
                + "------------------------------------------------------------" + System.lineSeparator();
        printInvalidTagsLine("Cons");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printRemovedTagsQuestion_StringArrayOfTagsAndDeckName_question() {
        ArrayList<String> removedTagsArray = new ArrayList<>();
        removedTagsArray.add("Beginner");
        removedTagsArray.add("Year2");
        printRemovedTagsQuestion("Micro-Economics", removedTagsArray);
        String expectedOutput = "Do you want to remove the tag(s) Beginner | Year2 from Micro-Economics? [y/n] ";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printTagsRemovedLine_removedTagsAndDeckName_confirmation() {
        ArrayList<String> removedTagsArray = new ArrayList<>();
        removedTagsArray.add("Beginner");
        removedTagsArray.add("Year2");
        printTagsRemovedLine("Micro-Economics", removedTagsArray);
        String expectedOutput = "The tag(s) Beginner | Year2 have been removed from the deck Micro-Economics."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void formStringOfTags_StringArrayOfTags_aStringOfTags() {
        ArrayList<String> arrayStringOfTags = new ArrayList<>();
        arrayStringOfTags.add("Advanced");
        arrayStringOfTags.add("Year4");
        arrayStringOfTags.add("Finance");
        String expectedOutput = "Advanced | Year4 | Finance";

        String actualOutput = formStringOfTags(arrayStringOfTags);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void printUpdateQuestionLine_existingFlashCard_updateQuestionLine() {
        String question = "Old question";
        String answer = "Old answer";
        FlashCard existingCard = new FlashCard(question, answer);
        String expectedOutput = existingCard.toString(true, 0) + System.lineSeparator()
                + "New Question: " + System.lineSeparator() + "  > ";
        printUpdateQuestionLine(existingCard);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printUpdateAnswerLine_existingFlashCard_updateAnswerLine() {
        String question = "Old question";
        String answer = "Old answer";
        FlashCard existingCard = new FlashCard(question, answer);
        String expectedOutput = System.lineSeparator() + existingCard.toString(false, 0)
                + System.lineSeparator() + "New Answer: " + System.lineSeparator() + "  > ";
        printUpdateAnswerLine(existingCard);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printSearchDecksLine_emptyString_NoDecksLine() {
        String expectedOutput = "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "There is no decks having the tags you are looking for."
                + System.lineSeparator() + "--------------------"
                + "------------------------------------------------------------" + System.lineSeparator();
        printSearchDecksLine("");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void printSearchDecksLine_stringOfMatchedDecks_decksListings() {
        String expectedOutput = "------------------------------------------------------------"
                + "--------------------" + System.lineSeparator()
                + "The decks having tags you are searching for:"
                + "\n1. Micro-Economics"
                + "\n3. Object-oriented Programming" + System.lineSeparator() + "--------------------"
                + "------------------------------------------------------------" + System.lineSeparator();
        String input = "\n1. Micro-Economics\n3. Object-oriented Programming";
        printSearchDecksLine(input);
        assertEquals(expectedOutput, outContent.toString());
    }
}
