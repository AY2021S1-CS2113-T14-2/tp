package seedu.ecardnomics.parser;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.command.ExitCommand;
import seedu.ecardnomics.command.VersionCommand;
import seedu.ecardnomics.command.VoidCommand;
import seedu.ecardnomics.command.normal.CreateCommand;
import seedu.ecardnomics.command.normal.DecksCommand;
import seedu.ecardnomics.command.normal.DeleteDeckCommand;
import seedu.ecardnomics.command.normal.EditCommand;
import seedu.ecardnomics.command.normal.HelpCommand;
import seedu.ecardnomics.command.normal.PowerPointCommand;
import seedu.ecardnomics.command.normal.StartCommand;
import seedu.ecardnomics.command.normal.TagCommand;
import seedu.ecardnomics.command.normal.UntagCommand;
import seedu.ecardnomics.command.normal.SearchCommand;
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
import seedu.ecardnomics.exceptions.NoSeparatorException;
import seedu.ecardnomics.exceptions.NumberTooBigException;
import seedu.ecardnomics.powerpoint.ColorOption;
import seedu.ecardnomics.exceptions.DuplicateDeckException;
import seedu.ecardnomics.storage.LogStorage;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.awt.Color;
import org.beryx.awt.color.ColorFactory;

import static seedu.ecardnomics.Ui.FORCE_YES_OPT;

/**
 * Parser for commands supplied in Normal Mode.
 */
public class NormalParser extends Parser {
    DeckList deckList;
    private static LogStorage logger = new LogStorage("NormalParserLogger");
    public static final int HIGHEST_CS_INDEX = 9;

    /** Constructor. */
    public NormalParser(DeckList deckList) {
        this.deckList = deckList;
    }

    /**
     * Check whether the index input from users is valid.
     *
     * @param arguments Argument from user input which is index
     * @return the index from user if it is valid
     * @throws IndexFormatException if index input is not a number
     * @throws DeckRangeException if index is out of range
     * @throws NumberTooBigException if the index is too big
     */
    @Override
    protected int getIndex(String arguments) throws IndexFormatException,
            DeckRangeException, NumberTooBigException {

        arguments = arguments.trim();

        logger.log(Level.INFO, "Logging method getIndex() in NormalParser.");

        if (!arguments.matches(Ui.DIGITS_REGEX)) {
            logger.log(Level.WARNING, "User did not enter a valid integer index. string = " + arguments);
            throw new IndexFormatException();
        }

        int index;
        try {
            index = Integer.parseInt(arguments) - INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new NumberTooBigException();
        }

        if ((index >= deckList.size()) || (index < LOWEST_POSSIBLE_INDEX)) {
            logger.log(Level.WARNING, "User did not enter an index in the valid range.");
            throw new DeckRangeException();
        }

        return index;
    }

    /**
     * Retrieves deck at index specified in arguments from deckList.
     * getIndex() is used to convert arguments from String to an int index.
     *
     * @param arguments String that contains the ID number of the deck requested
     * @return Reference to requested deck
     * @throws IndexFormatException if arguments is not a digit
     * @throws DeckRangeException if index obtained from arguments is not in range
     */
    private Deck prepareDeck(String arguments) throws Exception {
        return deckList.getDeck(getIndex(arguments));
    }

    /**
     * Prepares an instance of DeleteDeckCommand from given arguments.
     *
     * @param arguments String that contains the user arguments for the delete command
     * @return DeleteDeckCommand that can be executed to delete the deck.
     * @throws Exception if index is invalid or no index is supplied.
     */
    private Command prepareDeleteDeck(String arguments) throws Exception {
        boolean isDeckDeleted;
        int deckID;
        if (arguments.contains(FORCE_YES_OPT)) {
            arguments = arguments.replaceAll(FORCE_YES_OPT, "");
            deckID = getIndex(arguments);
            isDeckDeleted = true;
        } else {
            deckID = getIndex(arguments);
            isDeckDeleted = Ui.getDeletedDeckConfirmation(deckList.getDeck(deckID).getName());
        }

        if (isDeckDeleted) {
            Ui.printDeckDeletedLine(deckList.getDeck(deckID).getName());
        }

        Ui.printDashLines();

        return new DeleteDeckCommand(deckList, deckID, isDeckDeleted);
    }

    /**
     * Prepares new Tag Command from given arguments.
     *
     * @param arguments arguments input from user
     * @return a Tag Command
     * @throws Exception if index is invalid or empty arguments
     */
    private Command prepareTagCommand(String arguments) throws Exception {
        String argumentsWithSpace = arguments + " ";
        String[] idAndNewTags = argumentsWithSpace.split("/tag", 2);

        if (idAndNewTags.length < 2) {
            logger.log(Level.WARNING, "User did not provide /tag when adding tag.");
            throw new NoSeparatorException();
        }
        assert (arguments.contains("/tag")) :
                "Tags to be added are after /tag label.";
        int deckID = getIndex(idAndNewTags[0]);

        if (idAndNewTags[1].trim().isEmpty()) {
            logger.log(Level.WARNING, "User did not supply tags when adding tag.");
            throw new EmptyInputException();
        }

        String[] newTags = idAndNewTags[1].trim().split(" ");

        return new TagCommand(deckList, deckID, getUniqueValues(newTags));
    }

    /**
     * Prepares new Tag Command from given arguments.
     *
     * @param arguments arguments input from user
     * @return a Tag Command
     * @throws Exception if index is invalid or empty arguments
     */
    private Command prepareUntagCommand(String arguments) throws Exception {
        String argumentsWithSpace = arguments + " ";
        String[] idAndRemovedTags = argumentsWithSpace.split("/tag ", 2);
        boolean isYes = false;
        if (idAndRemovedTags.length < 2) {
            logger.log(Level.WARNING, "User did not provide /tag when removing tags.");
            throw new NoSeparatorException();
        }
        assert (arguments.contains("/tag")) :
                "tags to be removed are after /tag label";

        if (idAndRemovedTags[1].trim().isEmpty()) {
            logger.log(Level.WARNING, "User did not supply tags when removing tags.");
            throw new EmptyInputException();
        }
        String[] removedTags = idAndRemovedTags[1].trim().split(" ");
        ArrayList<String> uniqueTags = getUniqueValues(removedTags);
        if (uniqueTags.get(uniqueTags.size() - 1).equals(FORCE_YES_OPT)) {
            isYes = true;
            uniqueTags.remove(uniqueTags.size() - 1);
        }

        if (uniqueTags.isEmpty()) {
            logger.log(Level.WARNING, "User did not supply tags when removing tags.");
            throw new EmptyInputException();
        }

        int deckID = getIndex(idAndRemovedTags[0]);
        return new UntagCommand(deckList, deckID, uniqueTags, isYes);
    }

    /**
     * Returns an array list of unique values.
     *
     * @param tags a String array of tags
     * @return a unique array list
     */
    private ArrayList<String> getUniqueValues(String[] tags) {
        ArrayList<String> uniqueTags = new ArrayList<>();
        for (String tag: tags) {
            String trimmedTag = tag.trim();
            if (!uniqueTags.contains(trimmedTag) & !trimmedTag.isEmpty()) {
                uniqueTags.add(tag.trim());
            }
        }
        return uniqueTags;
    }

    /**
     * Creates a new deck for adding to deckList.
     *
     * @param arguments String that represents the name of deck to be created
     * @return Reference to the deck created
     * @throws EmptyInputException if no name is supplied for the deck
     * @throws DuplicateDeckException if duplicated name is entered
     */
    private Deck prepareNewDeck(String arguments) throws Exception {
        logger.log(Level.INFO, "Logging method prepareNewDeck() in NormalParser.");
        if (arguments.trim().isEmpty()) {
            logger.log(Level.WARNING, "User did not supply name when creating a new deck.");
            throw new EmptyInputException();
        }

        if (deckList.getAllNames().contains(arguments.trim())) {
            throw new DuplicateDeckException();
        }

        String argumentsWithSpace = arguments + " ";
        if (argumentsWithSpace.contains("/tag ")) {
            return prepareNewDeckWithTags(argumentsWithSpace);
        } else {
            return new Deck(arguments);
        }
    }

    /**
     * Prepare a CreateCommand with tag argument.
     *
     * @param arguments input from user after command word
     * @return a create command
     * @throws Exception if input is empty or if duplicate name presents
     */
    private Deck prepareNewDeckWithTags(String arguments) throws Exception {
        assert (arguments.contains("/tag ")) : "User did enter tag label.";
        String[] nameAndTags = arguments.split("/tag ", 2);

        if (nameAndTags.length != 2) {
            throw new EmptyInputException();
        }

        assert (nameAndTags.length == 2) : "Input should contain name and tags.";

        String name = nameAndTags[0].trim();
        if (name.isEmpty()) {
            throw new EmptyInputException();
        }
        if (deckList.getAllNames().contains(name)) {
            throw new DuplicateDeckException();
        }
        if (nameAndTags[1].trim().isEmpty()) {
            throw new EmptyInputException();
        }

        String[] tags = nameAndTags[1].trim().split(" ");
        ArrayList<String> tagsList = getUniqueValues(tags);
        return new Deck(name, tagsList);
    }

    /**
     * Gets the index for the -cs option in pptx command.
     * @param arguments String representing the user input for the index
     * @return int representing the index for the the color scheme chosen
     * @throws CsIndexFormatException when format of index is not an integer
     * @throws CsIndexRangeException when the index integer is not within the range [1,10], number of cs available
     */
    protected int getCsIndex(String arguments) throws CsIndexFormatException,
            CsIndexRangeException {

        arguments = arguments.trim();

        logger.log(Level.INFO, "Logging method getIndex() in NormalParser.");

        if (!arguments.matches(Ui.DIGITS_REGEX)) {
            logger.log(Level.WARNING, "User did not enter a valid integer index. string = " + arguments);
            throw new CsIndexFormatException();
        }

        int index;
        try {
            index = Integer.parseInt(arguments) - INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new CsIndexFormatException();
        }

        if (index > HIGHEST_CS_INDEX || index < LOWEST_POSSIBLE_INDEX) {
            logger.log(Level.WARNING, "User did not enter an index in the valid range for -cs.");
            throw new CsIndexRangeException();
        }

        return index;
    }

    /**
     * Prepares the PowerPoint command when the user input is pptx.
     * @param arguments String representing the arguments of user input
     * @return PowerPointCommand to be executed in the Main
     * @throws Exception when arguments (index and options) are not valid
     */
    protected PowerPointCommand preparePptxCommand(String arguments) throws Exception {
        Color bgColor = null;
        Color txtColor = null;
        String bgColorString = "";
        String txtColorString = "";
        ColorOption colorOpt = ColorOption.DEFAULT;
        int colorSchemeIndex = 0;

        boolean isPptxCreated = false;
        boolean bothOCandCS = false;
        boolean colorInvalid = false;
        boolean colorSchemeInvalid = false;

        Exception csException = null;

        if (arguments.contains(FORCE_YES_OPT)) {
            arguments = arguments.replaceAll(FORCE_YES_OPT, "").trim();
            isPptxCreated = true;
        }

        if (arguments.contains(Ui.ORIGINAL_COLOR_OPT) && arguments.contains(Ui.COLOR_SCHEME_OPT)) {
            bothOCandCS = true;
        }

        if (arguments.contains(Ui.ORIGINAL_COLOR_OPT)) {
            String dashOrEnd = "";

            Pattern pattern = Pattern.compile(Ui.ORIGINAL_COLOR_REGEX);
            Matcher matcher = pattern.matcher(arguments);


            if (matcher.find()) {
                bgColorString = matcher.group(1);
                txtColorString = matcher.group(2);
                dashOrEnd = matcher.group(3);

                try {
                    bgColor = ColorFactory.valueOf(bgColorString);
                    txtColor = ColorFactory.valueOf(txtColorString);

                } catch (IllegalArgumentException e) {
                    colorInvalid = true;
                }
            }

            colorOpt = ColorOption.ORGINAL_COLOR;
            arguments = arguments.replaceAll(Ui.ORIGINAL_COLOR_REGEX, dashOrEnd).trim();
        }

        if (arguments.contains(Ui.COLOR_SCHEME_OPT)) {
            String dashOrEnd = "";
            Pattern pattern = Pattern.compile(Ui.COLOR_SCHEME_REGEX);
            Matcher matcher = pattern.matcher(arguments);
            if (matcher.find()) {
                String numArg = matcher.group(1);
                dashOrEnd = matcher.group(2);


                try {
                    colorSchemeIndex = getCsIndex(numArg);
                } catch (Exception e) {
                    csException = e;
                    colorSchemeInvalid = true;
                }

                colorOpt = ColorOption.COLOR_SCHEME;
                arguments = arguments.replaceAll(Ui.COLOR_SCHEME_REGEX, dashOrEnd).trim();
            }
        }

        if (arguments.contains("-")) {
            throw new InvalidOptionsException();
        }

        if (bothOCandCS) {
            throw new BothOcAndCsException();
        }

        if (colorInvalid) {
            throw new ColorsNotAvailException();
        }

        if (colorSchemeInvalid) {
            throw csException;
        }

        int deckID = getIndex(arguments);
        Deck deck = deckList.getDeck(deckID);

        if (!isPptxCreated) {
            isPptxCreated = Ui.getPptxDeckConfirmation(deck.getName());
        }

        if (colorOpt == ColorOption.ORGINAL_COLOR) {
            return new PowerPointCommand(deckList, deck, isPptxCreated, bgColorString, txtColorString,
                    bgColor, txtColor);
        }

        if (colorOpt == ColorOption.COLOR_SCHEME) {
            return new PowerPointCommand(deckList, deck, isPptxCreated, colorSchemeIndex);
        }

        return new PowerPointCommand(deckList, deck, isPptxCreated);
    }

    /**
     * Gets input from users and prepares a search command.
     *
     * @param arguments input from users after command word
     * @return a search command
     * @throws EmptyInputException if input is empty
     */
    private Command prepareSearchCommand(String arguments) throws EmptyInputException {
        logger.log(Level.INFO, "Logging method prepareSearchCommand() in NormalParser.");

        if (arguments.trim().isEmpty()) {
            logger.log(Level.WARNING, "User did not supply tags when searching for decks.");
            throw new EmptyInputException();
        }

        String[] relevantTags = arguments.trim().split(" ");
        return new SearchCommand(deckList, relevantTags);
    }

    /**
     * Prepare Command for execution in Main.
     *
     * @param commandWord String that corresponds to a command
     * @param arguments String that lists the arguments for the command
     * @return respective Command object
     * @throws Exception when something wrong with the argument
     */
    @Override
    protected Command parseCommand(String commandWord, String arguments)
            throws Exception {

        assert (commandWord != null && arguments != null) :
                "commandWord and arguments should not be null";

        logger.log(Level.INFO, "Logging method parseCommand() in NormalParser.");

        switch (commandWord) {
        // Version
        case Ui.VERSION_CMD:
            return new VersionCommand();
        // Exit
        case Ui.EXIT:
            logger.log(Level.INFO, "User issued command to terminate program.");
            return new ExitCommand();
        // Help
        case Ui.HELP:
            logger.log(Level.INFO, "User issued command to view help.");
            return new HelpCommand();
        // Edit
        case Ui.EDIT:
            Deck deck = prepareDeck(arguments);
            logger.log(Level.INFO, "User issued command to edit deck " + deck.getName() + ".");
            return new EditCommand(deckList, deck);
        // Start
        case Ui.START:
            Deck startDeck = prepareDeck(arguments);
            logger.log(Level.INFO, "User issued command to start deck " + startDeck.getName() + ".");
            return new StartCommand(deckList, startDeck);
        // Create
        case Ui.CREATE:
            Deck newDeck = prepareNewDeck(arguments);
            logger.log(Level.INFO, "User issued command to create deck " + newDeck.getName() + ".");
            return new CreateCommand(deckList, newDeck);
        // Decks
        case Ui.DECKS:
            logger.log(Level.INFO, "User issued command to list decks.");
            return new DecksCommand(deckList);
        // Delete
        case Ui.DELETE:
            logger.log(Level.INFO, "User issued command to delete deck");
            return prepareDeleteDeck(arguments);
        // Tag
        case Ui.TAG:
            logger.log(Level.INFO, "User issued command to tag a deck.");
            return prepareTagCommand(arguments);
        // Untag
        case Ui.UNTAG:
            logger.log(Level.INFO, "User issued command to untag a deck.");
            return prepareUntagCommand(arguments);
        // Create new PowerPoint
        case Ui.PPTX:
            logger.log(Level.INFO, "User issued command to create a PowerPoint.");
            return preparePptxCommand(arguments);
        // Search
        case Ui.SEARCH:
            logger.log(Level.INFO, "User issued command to search for decks.");
            return prepareSearchCommand(arguments);
        default:
            logger.log(Level.INFO, "User issued an invalid command.");
            return new VoidCommand();
        }
    }

    /**
     * Parses User Input from Main.
     *
     * @param userInput Input from user, passed through Main
     * @return Command to be executed
     */
    @Override
    public Command parse(String userInput) {
        logger.log(Level.INFO, "Logging method parse() in NormalParser.");
        String[] splitString = userInput.split(" ", 2);
        String commandWord = splitString[0];
        logger.log(Level.INFO, "Parsed commandWord");
        boolean argumentsExist =  splitString.length > 1;
        String arguments = "";

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
