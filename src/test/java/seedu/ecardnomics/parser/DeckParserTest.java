package seedu.ecardnomics.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.command.VersionCommand;
import seedu.ecardnomics.command.VoidCommand;
import seedu.ecardnomics.command.deck.DoneEditCommand;
import seedu.ecardnomics.command.deck.AddCommand;
import seedu.ecardnomics.command.deck.DeleteCommand;
import seedu.ecardnomics.command.deck.ListCommand;
import seedu.ecardnomics.command.deck.HelpCommand;
import seedu.ecardnomics.command.ExitCommand;
import seedu.ecardnomics.command.normal.PowerPointCommand;
import seedu.ecardnomics.command.normal.StartCommand;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.deck.FlashCard;
import seedu.ecardnomics.exceptions.BothOcAndCsException;
import seedu.ecardnomics.exceptions.ColorsNotAvailException;
import seedu.ecardnomics.exceptions.CsIndexFormatException;
import seedu.ecardnomics.exceptions.CsIndexRangeException;
import seedu.ecardnomics.exceptions.EmptyQnAException;
import seedu.ecardnomics.exceptions.FlashCardRangeException;
import seedu.ecardnomics.exceptions.IndexFormatException;
import seedu.ecardnomics.exceptions.InvalidOptionsException;
import seedu.ecardnomics.exceptions.InvalidPptxArgumentException;
import seedu.ecardnomics.exceptions.NoAlphaNumericInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeckParserTest {
    DeckParser deckParser;

    @Test
    void getIndex_validIndex_success() throws Exception {
        assertEquals(0, deckParser.getIndex("1"));
        assertEquals(1, deckParser.getIndex("2"));
    }

    @Test
    void getIndex_validIndexSpacePadded_success() throws Exception {
        assertEquals(0, deckParser.getIndex(" 1"));
        assertEquals(0, deckParser.getIndex("\t1"));
        assertEquals(1, deckParser.getIndex("2\t"));
        assertEquals(1, deckParser.getIndex("     2 "));
    }

    @Test
    void getIndex_outOfRangeIndex_exceptionThrown() {
        try {
            assertEquals(1, deckParser.getIndex("3"));
            assertEquals(1, deckParser.getIndex("0"));
            fail();
        } catch (Exception e) {
            assertEquals((new FlashCardRangeException()).getMessage(), e.getMessage());
        }
    }

    @Test
    void getIndex_noIndex_exceptionThrown() {
        try {
            assertEquals(1, deckParser.getIndex(""));
            assertEquals(1, deckParser.getIndex("   "));
            assertEquals(1, deckParser.getIndex("something"));
            assertEquals(1, deckParser.getIndex(" something"));
            fail();
        } catch (Exception e) {
            assertEquals((new IndexFormatException()).getMessage(), e.getMessage());
        }
    }

    @Test
    void prepareFlashCard_oneLinerEmptyInputQuestion_EmptyQnAExceptionThrown() {
        try {
            assertEquals(1, deckParser.prepareFlashCard("      /ans some answer"));
            // question input checked first
            assertEquals(1, deckParser.prepareFlashCard("      /ans !@#"));
        } catch (Exception e) {
            assertEquals(new EmptyQnAException().getMessage(), e.getMessage());
        }
    }

    @Test
    void prepareFlashCard_oneLinerEmptyInputAnswerValidQuestion_EmptyQnAExceptionThrown() {
        try {
            // question input checked first
            assertEquals(1, deckParser.prepareFlashCard("some question /ans     "));
        } catch (Exception e) {
            assertEquals(new EmptyQnAException().getMessage(), e.getMessage());
        }
    }

    @Test
    void prepareFlashCard_oneLinerNoAlphaNumericQuestion_NoAlphaNumericExceptionThrown() {
        try {
            assertEquals(1, deckParser.prepareFlashCard("!@#.. /ans some answer"));
            // question input checked first
            assertEquals(1, deckParser.prepareFlashCard("!@#.. /      "));
        } catch (Exception e) {
            assertEquals(new NoAlphaNumericInputException().getMessage(), e.getMessage());
        }
    }

    @Test
    void prepareFlashCard_oneLinerNoAlphaNumericAnswerValidQuestion_NoAlphaNumericExceptionThrown() {
        try {
            // question input checked first
            assertEquals(1, deckParser.prepareFlashCard("some question /ans !@#.."));
        } catch (Exception e) {
            assertEquals(new NoAlphaNumericInputException().getMessage(), e.getMessage());
        }
    }

    @Test
    void prepareFlashCard_oneLinerInValidAnswerInvalidQuestion_questionDependentExceptionsThrown() {
        try {
            // question input checked first
            // throws EmptyInputException
            assertEquals(1, deckParser.prepareFlashCard("     /ans !@#.."));
            // throws NoAlphaNumericException
            assertEquals(1, deckParser.prepareFlashCard("!@#. /ans      "));
        } catch (EmptyQnAException eie) {
            assertEquals(new EmptyQnAException().getMessage(), eie.getMessage());
        } catch (NoAlphaNumericInputException nae) {
            assertEquals(new NoAlphaNumericInputException().getMessage(), nae.getMessage());
        }
    }

    @Test
    void prepareFlashCard_oneLinerValidQuestionContainsAns() throws EmptyQnAException, NoAlphaNumericInputException {
        assertTrue(deckParser.prepareFlashCard("question with/ans /ans answer") instanceof AddCommand);
    }

    @Test
    void prepareFlashCard_oneLinerValidAnswerContainsAns() throws EmptyQnAException, NoAlphaNumericInputException {
        assertTrue(deckParser.prepareFlashCard("question /ans answer with/ans") instanceof AddCommand);
        assertTrue(deckParser.prepareFlashCard("question /ans answer /ans") instanceof  AddCommand);
    }

    @Test
    void prepareFlashCard_oneLinerEmptyQuestionEmptyAnswer() {
        try {
            assertTrue(deckParser.prepareFlashCard("/ans") instanceof AddCommand);
        } catch (EmptyQnAException ee) {
            assertEquals(new EmptyQnAException().getMessage(), ee.getMessage());
        } catch (NoAlphaNumericInputException nae) {
            assertTrue(!NoAlphaNumericInputException.NO_ALPHANUMERIC_LINE.equals(nae.getMessage()));
        }
        try {
            assertTrue(deckParser.prepareFlashCard("      /ans      ") instanceof  AddCommand);
        } catch (EmptyQnAException ee) {
            assertEquals(new EmptyQnAException().getMessage(), ee.getMessage());
        } catch (NoAlphaNumericInputException nae) {
            assertTrue(!NoAlphaNumericInputException.NO_ALPHANUMERIC_LINE.equals(nae.getMessage()));
        }
    }

    @Test
    void parseCommand_ExitCommand_success() throws Exception {
        assertTrue(deckParser.parseCommand("exit", "") instanceof ExitCommand);
    }

    @Test
    void parseCommand_DoneEditCommand_success() throws Exception {
        assertTrue(deckParser.parseCommand("done", "") instanceof DoneEditCommand);
    }

    @Test
    void parseCommand_AddCommand_oneLine_success() throws Exception {
        assertTrue(deckParser.parseCommand("add", "qn /ans ans") instanceof AddCommand);
    }

    @Test
    void parseCommand_ListCommand_success() throws Exception {
        assertTrue(deckParser.parseCommand("list", "") instanceof ListCommand);
    }

    @Test
    void parseCommand_DeleteCommand_success() throws Exception {
        assertTrue(deckParser.parseCommand("delete", "1 -y") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_DeleteCommandNoIndex_exceptionThrown() {
        try {
            deckParser.parseCommand("delete", "");
        } catch (Exception e) {
            assertTrue(e instanceof IndexFormatException);
        }
    }

    @Test
    void parseCommand_DeleteCommandOutOfRangeIndex_exceptionThrown() {
        try {
            deckParser.parseCommand("delete", "3");
        } catch (Exception e) {
            assertTrue(e instanceof FlashCardRangeException);
        }
    }

    @Test
    void parseCommand_UpdateCommandNoIndex_exceptionThrown() {
        try {
            deckParser.parseCommand("update", "");
        } catch (Exception e) {
            assertTrue(e instanceof IndexFormatException);
        }
    }

    @Test
    void parseCommand_UpdateCommandOutOfRangeIndex_exceptionThrown() {
        try {
            deckParser.parseCommand("update", "3");
        } catch (Exception e) {
            assertTrue(e instanceof FlashCardRangeException);
        }
    }

    @Test
    void parseCommand_PptxCommandForceYes_success() {
        try {
            assertTrue(deckParser.parseCommand("pptx", "-y") instanceof PowerPointCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parseCommand_PptxCommandForceYesOriginalColor_success() {
        try {
            assertTrue(deckParser.parseCommand("pptx", "-oc blue green -y") instanceof PowerPointCommand);
            assertTrue(deckParser.parseCommand("pptx", "-y -oc blue green") instanceof PowerPointCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parseCommand_PptxCommandForceYesColorScheme_success() {
        try {
            assertTrue(deckParser.parseCommand("pptx", "-cs 1 -y") instanceof PowerPointCommand);
            assertTrue(deckParser.parseCommand("pptx", "-y -cs 1") instanceof PowerPointCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parseCommand_PptxCommandExtraArguments_exceptionThrown() {
        try {
            deckParser.parseCommand("pptx", "1");
            deckParser.parseCommand("pptx", "1 -y");
            deckParser.parseCommand("pptx", "-y 1");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidPptxArgumentException);
        }
    }

    @Test
    void parseCommand_PptxCommandBothOcAndCs_exceptionThrown() {
        try {
            deckParser.parseCommand("pptx", "-y -oc green black -cs 1");
            deckParser.parseCommand("pptx", "-y -cs 1 -oc black green");
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(e instanceof BothOcAndCsException);
        }
    }

    @Test
    void parseCommand_PptxCommandOriginalColorUnavailableColor_exceptionThrown() {
        try {
            deckParser.parseCommand("pptx", "-y -oc green block");
            deckParser.parseCommand("pptx", "-y -oc block green");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ColorsNotAvailException);
        }
    }

    @Test
    void parseCommand_PptxCommandInvalidOptions_exceptionThrown() {
        try {
            deckParser.parseCommand("pptx", "-y -cc green black");
            deckParser.parseCommand("pptx", "-y -ss 1");
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(e instanceof InvalidOptionsException);
        }
    }

    @Test
    void parseCommand_PptxCommandCsIndexOutOfRange_exceptionThrown() {
        try {
            deckParser.parseCommand("pptx", "-y -cs 0");
            deckParser.parseCommand("pptx", "-y -cs 11");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof CsIndexRangeException);
        }
    }

    @Test
    void parseCommand_PptxCommandCsIndexWrongFormat_exceptionThrown() {
        try {
            deckParser.parseCommand("pptx", "-y -cs -1");
            deckParser.parseCommand("pptx", "-y -cs blue red");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof CsIndexFormatException);
        }
    }

    @Test
    void parseCommand_HelpCommand_success() throws Exception {
        assertTrue(deckParser.parseCommand("help", "") instanceof HelpCommand);
    }

    @Test
    void parseCommand_StartCommandArgumentsSpacePadded_success() {
        try {
            assertTrue(deckParser.parseCommand("start", "") instanceof StartCommand);
            assertTrue(deckParser.parseCommand("start", "\t") instanceof StartCommand);
            assertTrue(deckParser.parseCommand("start", "\n") instanceof StartCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parseCommand_StartCommandRandomArguments_success() {
        try {
            assertTrue(deckParser.parseCommand("start", "1") instanceof StartCommand);
            assertTrue(deckParser.parseCommand("start", "\t1") instanceof StartCommand);
            assertTrue(deckParser.parseCommand("start", "\n1") instanceof StartCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parseCommand_VersionCommandAnyArguments_success() {
        try {
            assertTrue(deckParser.parseCommand("--version", "1") instanceof VersionCommand);
            assertTrue(deckParser.parseCommand("--version", "\t1") instanceof VersionCommand);
            assertTrue(deckParser.parseCommand("--version", "\n hdkljfashdfs\t\t") instanceof VersionCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parse_invalidCommand_returnsVoidCommand() {
        assertTrue(deckParser.parse("something random") instanceof VoidCommand);
        assertTrue(deckParser.parse("") instanceof VoidCommand);
        assertTrue(deckParser.parse("   ") instanceof VoidCommand);
        assertTrue(deckParser.parse("\t") instanceof VoidCommand);
        assertTrue(deckParser.parse("blah") instanceof VoidCommand);
    }

    //@BeforeAll
    //public static void addUserInput() {
    //String userInputs = "q1" + System.getProperty("line.separator") + "a1" + System.getProperty("line.separator")
    //+ "y" + System.getProperty("line.separator") + "y";
    //ByteArrayInputStream input = new ByteArrayInputStream(userInputs.getBytes());
    //System.setIn(input);
    //}

    @BeforeEach
    void initialiseDeckParser() {
        DeckList deckList = initialiseDeckList(2);
        Deck deck = initialiseDeck(deckList, 2);
        deckParser = new DeckParser(deckList, deck);
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