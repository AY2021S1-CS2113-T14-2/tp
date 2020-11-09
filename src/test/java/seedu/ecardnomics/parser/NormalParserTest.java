package seedu.ecardnomics.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ecardnomics.command.VersionCommand;
import seedu.ecardnomics.command.VoidCommand;
import seedu.ecardnomics.command.ExitCommand;
import seedu.ecardnomics.command.normal.DeleteDeckCommand;
import seedu.ecardnomics.command.normal.EditCommand;
import seedu.ecardnomics.command.normal.CreateCommand;
import seedu.ecardnomics.command.normal.DecksCommand;
import seedu.ecardnomics.command.normal.PowerPointCommand;
import seedu.ecardnomics.command.normal.HelpCommand;
import seedu.ecardnomics.command.normal.StartCommand;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.exceptions.BothOcAndCsException;
import seedu.ecardnomics.exceptions.ColorsNotAvailException;
import seedu.ecardnomics.exceptions.CsIndexFormatException;
import seedu.ecardnomics.exceptions.CsIndexRangeException;
import seedu.ecardnomics.exceptions.DeckRangeException;
import seedu.ecardnomics.exceptions.EmptyInputException;
import seedu.ecardnomics.exceptions.IndexFormatException;
import seedu.ecardnomics.exceptions.InvalidOptionsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class NormalParserTest {
    NormalParser normalParser;

    @Test
    void getIndex_validIndex_success() throws Exception {
        assertEquals(0, normalParser.getIndex("1"));
        assertEquals(1, normalParser.getIndex("2"));
    }

    @Test
    void getIndex_validIndexSpacePadded_success() throws Exception {
        assertEquals(0, normalParser.getIndex(" 1"));
        assertEquals(0, normalParser.getIndex("\t1"));
        assertEquals(1, normalParser.getIndex("2\t"));
        assertEquals(1, normalParser.getIndex("     2 "));
    }

    @Test
    void getIndex_outOfRangeIndex_exceptionThrown() {
        try {
            assertEquals(1, normalParser.getIndex("3"));
            assertEquals(1, normalParser.getIndex("-1"));
            assertEquals(1, normalParser.getIndex("0"));
            fail();
        } catch (Exception e) {
            assertEquals((new DeckRangeException()).getMessage(), e.getMessage());
        }
    }

    @Test
    void getIndex_noIndex_exceptionThrown() {
        try {
            assertEquals(1, normalParser.getIndex(""));
            assertEquals(1, normalParser.getIndex("   "));
            assertEquals(1, normalParser.getIndex("something"));
            assertEquals(1, normalParser.getIndex(" something"));
            fail();
        } catch (Exception e) {
            assertEquals((new IndexFormatException()).getMessage(), e.getMessage());
        }
    }

    @Test
    void getCsIndex_indexOutOfRange_exceptionThrown() {
        try {
            assertEquals(1, normalParser.getCsIndex("11"));
            assertEquals(1, normalParser.getCsIndex("0"));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof CsIndexRangeException);
        }
    }

    @Test
    void getCsCsIndex_indexWrongFormat_exceptionThrown() {
        try {
            assertEquals(1, normalParser.getCsIndex("-1"));
            assertEquals(1, normalParser.getCsIndex("blue"));
            assertEquals(1, normalParser.getIndex(""));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof CsIndexFormatException);
        }
    }

    @Test
    void preparePptxCommand_forceYes_success() {
        try {
            assertTrue(normalParser.preparePptxCommand("1 -y") instanceof PowerPointCommand);
            assertTrue(normalParser.preparePptxCommand("-y 1") instanceof PowerPointCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void preparePptxCommand_forceYesOriginalColor_success() {
        try {
            assertTrue(normalParser.preparePptxCommand("1 -oc blue green -y") instanceof PowerPointCommand);
            assertTrue(normalParser.preparePptxCommand("1 -y -oc blue green") instanceof PowerPointCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void preparePptxCommand_forceYesColorScheme_success() {
        try {
            assertTrue(normalParser.preparePptxCommand("1 -cs 1 -y") instanceof PowerPointCommand);
            assertTrue(normalParser.preparePptxCommand("1 -y -cs 1") instanceof PowerPointCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void preparePptxCommand_bothOcAndCs_exceptionThrown() {
        try {
            normalParser.preparePptxCommand("1 -y -oc green black -cs 1");
            normalParser.preparePptxCommand("1 -y -cs 1 -oc black green");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof BothOcAndCsException);
        }
    }

    @Test
    void preparePptxCommand_originalColorUnavailableColor_exceptionThrown() {
        try {
            normalParser.preparePptxCommand("1 -y -oc green block");
            normalParser.preparePptxCommand("1 -y -oc block green");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ColorsNotAvailException);
        }
    }

    @Test
    void preparePptxCommand_invalidOptions_exceptionThrown() {
        try {
            normalParser.preparePptxCommand("1 -y -cc green black");
            normalParser.preparePptxCommand("1 -y -ss 1");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidOptionsException);
        }
    }

    @Test
    void parseCommand_VersionCommand_success() throws Exception {
        assertTrue(normalParser.parseCommand("--version", "") instanceof VersionCommand);
    }


    @Test
    void parseCommand_ExitCommand_success() throws Exception {
        assertTrue(normalParser.parseCommand("exit", "") instanceof ExitCommand);
    }

    @Test
    void parseCommand_EditCommand_success() throws Exception {
        assertTrue(normalParser.parseCommand("edit", "1") instanceof EditCommand);
        assertTrue(normalParser.parseCommand("edit", "2") instanceof EditCommand);
    }

    @Test
    void parseCommand_EditCommandNoIndex_exceptionThrown() {
        try {
            assertTrue(normalParser.parseCommand("edit", "") instanceof EditCommand);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IndexFormatException);
        }
    }

    @Test
    void parseCommand_EditCommandOutOfRangeIndex_exceptionThrown() {
        try {
            normalParser.parseCommand("edit", "0");
            normalParser.parseCommand("edit", "3");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof DeckRangeException);
        }
    }

    @Test
    void parseCommand_CreateCommand_success() throws Exception {
        assertTrue(normalParser.parseCommand("create", "Deck 3") instanceof CreateCommand);
    }

    @Test
    void parseCommand_CreateCommandEmptyInput_exceptionThrown() throws Exception {
        try {
            normalParser.parseCommand("create", "");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof EmptyInputException);
        }
    }


    @Test
    void parseCommand_DecksCommand_success() throws Exception {
        assertTrue(normalParser.parseCommand("decks", "") instanceof DecksCommand);
    }

    @Test
    void parseCommand_DeleteDeckCommand_force_success() throws Exception {
        assertTrue(normalParser.parseCommand("delete", "1 -y") instanceof DeleteDeckCommand);
    }

    @Test
    void parseCommand_DeleteDeckCommandNoIndex_exceptionThrown() {
        try {
            normalParser.parseCommand("delete", "");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IndexFormatException);
        }
    }

    @Test
    void parseCommand_DeleteDeckCommandOutOfRangeIndex_exceptionThrown() {
        try {
            normalParser.parseCommand("delete", "3");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof DeckRangeException);
        }
    }



    @Test
    void parseCommand_PptxCommandOutOfRangeIndex_exceptionThrown() {
        try {
            normalParser.parseCommand("pptx", "3");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof DeckRangeException);
        }
    }

    @Test
    void parseCommand_StartCommandValidArgumentsSpacePadded_success() {
        try {
            assertTrue(normalParser.parseCommand("start", "1") instanceof StartCommand);
            assertTrue(normalParser.parseCommand("start", "     1  ") instanceof StartCommand);
            assertTrue(normalParser.parseCommand("start", "\t1\n") instanceof StartCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parseCommand_StartCommandNoIndex_exceptionThrown() {
        try {
            normalParser.parseCommand("start", "");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IndexFormatException);
        }
    }

    @Test
    void parseCommand_StartCommandTooHighRangeIndex_exceptionThrown() {
        try {
            normalParser.parseCommand("start", "3");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof DeckRangeException);
        }
    }

    // @Test
    // void parseCommand_StartCommandTooLowRangeIndex_exceptionThrown() {
    //     try {
    //         normalParser.parseCommand("start", "-1");
    //         fail();
    //     } catch (Exception e) {
    //         assertTrue(e instanceof DeckRangeException);
    //     }
    // }

    @Test
    void parseCommand_HelpCommand_success() throws Exception {
        assertTrue(normalParser.parseCommand("help", "") instanceof HelpCommand);
    }

    @Test
    void parseCommand_VersionCommandAnyArguments_success() {
        try {
            assertTrue(normalParser.parseCommand("--version", "1") instanceof VersionCommand);
            assertTrue(normalParser.parseCommand("--version", "\t1") instanceof VersionCommand);
            assertTrue(normalParser.parseCommand("--version", "\n hdkljfashdfs\t\t") instanceof VersionCommand);
        } catch (Exception e) {
            System.out.println(" error");
        }
    }

    @Test
    void parse_invalidCommand_returnsVoidCommand() {
        assertTrue(normalParser.parse("something random") instanceof VoidCommand);
        assertTrue(normalParser.parse("") instanceof VoidCommand);
        assertTrue(normalParser.parse("   ") instanceof VoidCommand);
        assertTrue(normalParser.parse("\t") instanceof VoidCommand);
        assertTrue(normalParser.parse("blah") instanceof VoidCommand);
    }

    @BeforeEach
    void initialiseNormalParser() {
        DeckList deckList = initialiseDeckList(2);
        normalParser = new NormalParser(deckList);
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