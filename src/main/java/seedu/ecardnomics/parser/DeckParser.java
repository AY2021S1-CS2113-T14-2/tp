package seedu.ecardnomics.parser;

import seedu.ecardnomics.Main;
import seedu.ecardnomics.Ui;
import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.command.VersionCommand;
import seedu.ecardnomics.command.deck.AddCommand;
import seedu.ecardnomics.command.deck.DeleteCommand;
import seedu.ecardnomics.command.deck.DoneEditCommand;
import seedu.ecardnomics.command.deck.HelpCommand;
import seedu.ecardnomics.command.deck.ListCommand;
import seedu.ecardnomics.command.deck.UpdateCommand;
import seedu.ecardnomics.command.ExitCommand;
import seedu.ecardnomics.command.VoidCommand;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.exceptions.EmptyQnAException;
import seedu.ecardnomics.exceptions.FlashCardRangeException;
import seedu.ecardnomics.exceptions.IndexFormatException;
import seedu.ecardnomics.exceptions.InvalidListCommandException;
import seedu.ecardnomics.exceptions.InvalidOptionsException;
import seedu.ecardnomics.exceptions.InvalidPptxArgumentException;
import seedu.ecardnomics.exceptions.NoAlphaNumericInputException;
import seedu.ecardnomics.exceptions.NumberTooBigException;
import seedu.ecardnomics.storage.LogStorage;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for commands supplied in Deck Mode.
 */
public class DeckParser extends Parser {
    public Deck deck;
    public DeckList deckList;
    private static LogStorage logger = new LogStorage("DeckParserLogger");

    /**
     * Constructor.
     */
    public DeckParser(DeckList deckList, Deck deck) {
        this.deckList = deckList;
        this.deck = deck;
    }

    protected Command prepareDeletedFlashCard(String arguments) throws Exception {
        int flashCardID;
        boolean isFlashCardDeleted;

        if (arguments.contains("-y")) {
            arguments = arguments.replaceAll(Ui.FORCE_YES_OPT, "");
            flashCardID = getIndex(arguments);
            isFlashCardDeleted = true;
        } else {
            flashCardID = getIndex(arguments);
            isFlashCardDeleted = Ui.getDeletedFlashCardConfirmation(deck.get(flashCardID).getQuestion());
        }
        logger.log(Level.INFO, "returning DeleteCommand object");

        if (isFlashCardDeleted) {
            Ui.printFlashCardDeletedLine(deck.get(flashCardID).getQuestion());
        }

        Ui.printDashLines();

        return new DeleteCommand(deck, flashCardID, isFlashCardDeleted);
    }

    public static boolean containsNoAlphaNumerics(String field) {
        String puncRemovedField = field.replaceAll(Ui.PUNC_REGEX, "");
        return puncRemovedField.isBlank();
    }

    /**
     * Verify that a String is contains meaningful contents.
     *
     * @param field String to be verified
     * @throws EmptyQnAException if string is empty after trim
     */
    private void verifyStringField(String field) throws EmptyQnAException, NoAlphaNumericInputException {
        if (field.trim().length() == 0) {
            logger.log(Level.WARNING, "User entered nothing or a series of blank spaces for field");
            throw new EmptyQnAException();
        }
        if (containsNoAlphaNumerics(field.trim())) {
            logger.log(Level.WARNING, "User entered no alphanumeric characters as field!");
            throw new NoAlphaNumericInputException();
        }
    }

    protected Command prepareFlashCard(String arguments) throws EmptyQnAException, NoAlphaNumericInputException {
        String[] questionAndAnswer = new String[2];

        if (arguments.isEmpty()) {
            // Ask for both question and answer
            Ui.printAddFlashCardLine(deck);
            Ui.printEnterQuestionLine();
            questionAndAnswer[0] = Ui.readUserInput();
            logger.log(Level.INFO, "Reading user input for question");
            verifyStringField(questionAndAnswer[0]);

            Ui.printEnterAnswerLine();
            questionAndAnswer[1] = Ui.readUserInput();
            logger.log(Level.INFO, "Reading user input for answer");
        } else if (arguments.contains(" /ans ")) {
            // Split by the first /ans only
            questionAndAnswer = arguments.split("/ans", 2);
            // Expect a qn and ans
            if (questionAndAnswer.length != 2) {
                throw new EmptyQnAException();
            }
            verifyStringField(questionAndAnswer[0]);
        } else if (arguments.trim().equals("/ans")) {
            throw new EmptyQnAException();
        } else {
            questionAndAnswer[0] = arguments;
            // Valid question provided but not answer
            Ui.printAddFlashCardLine(deck);
            Ui.printEnterAnswerLine();
            questionAndAnswer[1] = Ui.readUserInput();
            logger.log(Level.INFO, "Reading user input for answer");
        }
        verifyStringField(questionAndAnswer[1]);

        assert questionAndAnswer[0].length() > 0 : "question field empty!";
        assert questionAndAnswer[1].length() > 0 : "answer field empty!";
        assert !containsNoAlphaNumerics(questionAndAnswer[0]) : "question field nonsensical!";
        assert !containsNoAlphaNumerics(questionAndAnswer[1]) : "question field nonsensical!";

        Ui.printDashLines();
        Ui.printFlashCardAddedLine();
        Ui.printDashLines();

        logger.log(Level.INFO, "returning AddCommand object");
        return new AddCommand(deck, questionAndAnswer[0].trim(), questionAndAnswer[1].trim());
    }

    protected Command prepareUpdate(int flashCardID) throws EmptyQnAException, NoAlphaNumericInputException {
        String[] newQnA = new String[2];
        Ui.printUpdateQuestionLine(deck.get(flashCardID));
        newQnA[0] = Ui.readUserInput();
        logger.log(Level.INFO, "Reading user input for question");
        boolean hasNewQ = true;
        boolean hasNewA = true;
        if (newQnA[0].trim().length() == 0) {
            logger.log(Level.INFO, "User entered nothing or a series of blank spaces for question. "
                    + "Keep current question.");
            newQnA[0] = deck.get(flashCardID).getQuestion();
            hasNewQ = false;
        }
        Ui.printUpdateAnswerLine(deck.get(flashCardID));
        newQnA[1] = Ui.readUserInput();
        logger.log(Level.INFO, "Reading user input for answer");
        if (newQnA[1].trim().length() == 0) {
            logger.log(Level.INFO, "User entered nothing or a series of blank spaces for answer. "
                    + "Keep current answer.");
            newQnA[1] = deck.get(flashCardID).getAnswer();
            hasNewA = false;
        }
        verifyStringField(newQnA[0]);
        verifyStringField(newQnA[1]);

        assert newQnA[0].length() > 0 : "question field empty!";
        assert newQnA[1].length() > 0 : "answer field empty!";
        Ui.printDashLines();
        Ui.printFlashCardUpdatedLine(hasNewQ, hasNewA);
        Ui.printDashLines();
        return new UpdateCommand(deck, flashCardID, newQnA[0], newQnA[1]);
    }

    protected Command prepareListCommand(String arguments) throws InvalidListCommandException {
        if (arguments.trim().equals("/ans") || arguments.trim().isBlank()) {
            return new ListCommand(deck, arguments);
        }
        throw new InvalidListCommandException();
    }

    protected void checkForValidPptxCommand(String arguments) throws Exception {
        String validArg = arguments;

        String dashOrEnd = "";
        if (validArg.contains(Ui.ORIGINAL_COLOR_OPT)) {
            Pattern p1 = Pattern.compile(Ui.ORIGINAL_COLOR_REGEX);
            Matcher m1 = p1.matcher(arguments);
            if (m1.find()) {
                dashOrEnd = m1.group(3);
            }
            validArg = validArg.replaceAll(Ui.ORIGINAL_COLOR_REGEX, dashOrEnd);
        }

        if (validArg.contains(Ui.COLOR_SCHEME_OPT)) {
            Pattern p2 = Pattern.compile(Ui.COLOR_SCHEME_REGEX);
            Matcher m2 = p2.matcher(arguments);
            if (m2.find()) {
                dashOrEnd = m2.group(2);
            }
            validArg = validArg.replaceAll(Ui.COLOR_SCHEME_REGEX, dashOrEnd);
        }

        if (validArg.contains(Ui.FORCE_YES_OPT)) {
            validArg = validArg.replaceAll(Ui.FORCE_YES_OPT, "");
        }

        Pattern p = Pattern.compile("-\\w+");
        Matcher m = p.matcher(validArg);
        if (m.find()) {
            throw new InvalidOptionsException();
        }

        System.out.println(validArg);

        if (!validArg.isBlank()) {
            throw new InvalidPptxArgumentException();
        }
    }

    @Override
    protected int getIndex(String arguments) throws IndexFormatException,
            FlashCardRangeException, NumberTooBigException {

        arguments = arguments.trim();

        if (!arguments.matches(Ui.DIGITS_REGEX)) {
            logger.log(Level.WARNING, "User did not enter valid integer");
            throw new IndexFormatException();
        }

        assert arguments.length() > 0 : "arguments empty!";

        int index;
        try {
            index = Integer.parseInt(arguments) - INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new NumberTooBigException();
        }

        if (index >= deck.size() || index < LOWEST_POSSIBLE_INDEX) {
            logger.log(Level.WARNING, "Flash card index larger than total number of cards");
            throw new FlashCardRangeException();
        }
        return index;
    }

    @Override
    protected Command parseCommand(String commandWord, String arguments)
            throws Exception {

        switch (commandWord) {
        // Version
        case Ui.VERSION_CMD:
            return new VersionCommand();
        // Exit
        case Ui.EXIT:
            logger.log(Level.INFO, "returning ExitCommand object");
            return new ExitCommand();
        // Done with Edit Mode
        case Ui.DONE:
            logger.log(Level.INFO, "returning DoneEditCommand object");
            return new DoneEditCommand(deck);
        // Help
        case Ui.HELP:
            logger.log(Level.INFO, "returning HelpCommand object");
            return new HelpCommand();
        // Start
        case Ui.START:
            logger.log(Level.INFO, "Starting Game Mode");
            return new NormalParser(Main.deckList).parseCommand(commandWord,
                    String.valueOf(deckList.getIndexOf(deck) + 1));
        // Add a FlashCard
        case Ui.ADD:
            logger.log(Level.INFO, "Preparing FlashCard to add");
            return prepareFlashCard(arguments);
        // List all FlashCards
        case Ui.LIST:
            logger.log(Level.INFO, "returning ListCommand object");
            return prepareListCommand(arguments);
        // Delete a FlashCard
        case Ui.DELETE:
            logger.log(Level.INFO, "Preparing FlashCard to delete");
            return prepareDeletedFlashCard(arguments);
        // Update a FlashCard
        case Ui.UPDATE:
            logger.log(Level.INFO, "Preparing FlashCard to update");
            int flashCardID = getIndex(arguments);
            assert flashCardID >= LOWEST_POSSIBLE_INDEX : "flash card ID less than lowest possible flash card index!";
            return prepareUpdate(flashCardID);
        // Create PowerPoint
        case Ui.PPTX:
            logger.log(Level.INFO, "Printing to PowerPoint");
            checkForValidPptxCommand(arguments);
            return new NormalParser(Main.deckList).parseCommand(commandWord,
                    (deckList.getIndexOf(deck) + 1) + " " + arguments);
        default:
            logger.log(Level.WARNING, "returning VoidCommand object");
            return new VoidCommand();
        }
    }

    @Override
    public Command parse(String userInput) {
        logger.log(Level.INFO, "Parsing user input string");
        String[] splitString = userInput.split(" ", 2);
        String commandWord = splitString[0];
        logger.log(Level.INFO, "Parsed commandWord");
        String arguments = "";
        boolean argumentsExist = splitString.length > 1;
        if (argumentsExist) {
            arguments = splitString[1];
            logger.log(Level.INFO, "Parsed arguments");
        }
        try {
            logger.log(Level.INFO, "Parsing command");
            return parseCommand(commandWord, arguments);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Parsed void or invalid command");
            return new VoidCommand(e.getMessage());
        }
    }
}
