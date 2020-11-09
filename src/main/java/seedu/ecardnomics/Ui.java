package seedu.ecardnomics;

import org.apache.commons.math3.analysis.function.Log;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.deck.FlashCard;
import seedu.ecardnomics.game.Game;
import seedu.ecardnomics.powerpoint.ColorOption;
import seedu.ecardnomics.storage.LogStorage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import static seedu.ecardnomics.Main.VERSION_NUMBER;

public class Ui {

    // Output lines to users
    public static final String GREETING_LINES =
            "Hello! Welcome to eCardnomics,\n"
                    + "your one stop Flash Cards solution";
    public static final String NORMAL_WELCOME_LINE =
            "You are back in Normal Mode";
    public static final String DECK_WELCOME_LINE =
            "You are now in Deck Mode, editing: ";
    public static final String BYE_LINE =
            "Bye. Hope to see you again soon!";
    public static final String NOT_RECOGNISED_LINE =
            "Command not recognised";
    public static final String YN_LINE = "[y/n]";
    public static final String ADD_FLASHCARD_LINE =
            "You are now adding a FlashCard to: ";
    public static final String ENTER_QUESTION_LINE =
            "Enter question:";
    public static final String ENTER_ANSWER_LINE =
            "Enter answer:";
    public static final String FLASHCARD_ADDED_LINE =
            "FlashCard successfully added!";
    public static final String LIST_FLASHCARDS_LINE =
            "You are now viewing deck: ";
    public static final String DELETE_FLASHCARD_LINE =
            "Do you want to delete the following flash card? [y/n] ";
    public static final String FLASHCARD_DELETED_LINE =
            "The following flash card has been deleted:\n  '";
    public static final String NEW_DECK_CREATED_LINE =
            "New deck created: ";
    public static final String DECKS_AVAILABLE_LINE =
            "The following decks are available:\n";
    public static final String DELETED_DECK_QUESTION_LINE =
            "Do you want to delete %1$s deck? [y/n] ";
    public static final String DELETED_DECK_LINE =
            "%1$s has been deleted.";
    public static final String PPTX_DECK_QUESTION_LINE =
            "Do you want to print %1$s deck to PowerPoint? [y/n] ";
    public static final String PPTX_DECK_DEFAULT_LINE =
            "%s has been created as PowerPoint with default,\n  black background and white text.";
    public static final String PPTX_DECK_CS_LINE =
            "%s has been created as PowerPoint using Color Scheme,\n  with %s background and %s text.";
    public static final String PPTX_DECK_OC_LINE =
            "%s has been created as PowerPoint using Original Colors,\n  with %s background and %s text.";
    public static final String INVALID_YN_RESPONSE_LINE =
            "Response should be 'y' or 'n'\n  > ";
    private static final String EMPTY_DECK_LINE =
            "Deck is currently empty!";
    private static final String NEW_TAGS_LINE =
            "The deck %1$s has been tagged as: %2$s";
    private static final String REMOVED_TAGS_QUESTION_LINE =
            "Do you want to remove the tag(s) %1$s from %2$s? [y/n] ";
    private static final String REMOVED_TAGS_LINE =
            "The tag(s) %1$s have been removed from the deck %2$s.";
    private static final String NEW_QUESTION_LINE =
            "New Question: ";
    private static final String NEW_ANSWER_LINE =
            "New Answer: ";
    private static final String QUESTION_UPDATED_LINE =
            "Question updated.";
    private static final String ANSWER_UPDATED_LINE =
            "Answer updated.";
    private static final String QNA_UPDATED_LINE =
            "Question and answer updated.";
    private static final String NO_UPDATE_LINE =
            "Original question and answer retained";
    private static final String INCLUDE_EXCLUDE_LINE =
            "Do you want to re-attempt this question later? [y/n] ";
    private static final String ATTEMPT_FEEDBACK_LINE =
            "The score for your answer is:";
    private static final String ENTER_ATTEMPT_LINE =
            "  Enter your attempt below (or `done`, `exit`, `help`):";
    private static final String DONE_GAME_LINE =
            "You have completed all the flash cards in this deck!\n"
                    + "Returning to Normal Mode...";
    private static final String GAME_EMPTY_DECK_LINE =
            EMPTY_DECK_LINE + " Please add some flash cards first.";
    private static final String INVALID_TAGS_LINE =
            "Tag %s is not in the deck already!";
    private static final String DECKS_HAVING_TAGS_LINE =
            "The decks having tags you are searching for:";
    private static final String NO_DECKS_WITH_TAGS_LINE =
            "There is no decks having the tags you are looking for.";

    public static final String QUESTION = "Question ";
    public static final String ANSWER = "Answer";
    public static final String CREATE_NEW_FILE_ERROR_LINE = "Error creating new file - ";
    public static final String WRITE_TO_FILE_ERROR_LINE = "Error writing for file";
    public static final String FILE_NOT_FOUND_ERROR_LINE = " not found or file currently open";

    // Commands
    public static final String EXIT = "exit";
    public static final String EDIT = "edit";
    public static final String START = "start";
    public static final String DONE = "done";
    public static final String ADD = "add";
    public static final String CREATE = "create";
    public static final String LIST = "list";
    public static final String DECKS = "decks";
    public static final String DELETE = "delete";
    public static final String UPDATE = "update";
    public static final String HELP = "help";
    public static final String TAG = "tag";
    public static final String UNTAG = "untag";
    public static final String PPTX = "pptx";
    public static final String SEARCH = "search";

    public static final String VERSION_CMD = "--version";

    // Options
    public static final String FORCE_YES_OPT = "-y";
    public static final String COLOR_SCHEME_OPT = "-cs";
    public static final String ORIGINAL_COLOR_OPT = "-oc";

    //Regex
    public static final String DIGITS_REGEX = "\\d+";
    public static final String PUNC_REGEX = "\\p{Punct}";
    public static final String ORIGINAL_COLOR_REGEX = "-oc\\s+(\\w+)\\s+(\\w+)\\s*(-|$)";
    public static final String COLOR_SCHEME_REGEX = "-cs\\s(\\S+)\\s*(-|$)";

    public static final String Y = "y";
    public static final String N = "n";
    public static final String DASH_LINES = "------------------------------------------------------------"
            + "--------------------";
    public static final String MIDDLE_SEPARATOR = "                                ----------------                  "
            + "              ";


    public static final String LOGO1 = "        ___              _                       _";
    public static final String LOGO2 = "  ___  / __\\__ _ _ __ __| |_ __   ___  _ __ ___ (_) ___ ___";
    public static final String LOGO3 = " / _ \\/ /  / _` | '__/ _` | '_ \\ / _ \\| '_ ` _ \\| |/ __/ __|";
    public static final String LOGO4 = "|  __/ /__| (_| | | | (_| | | | | (_) | | | | | | | (__\\__ \\";
    public static final String LOGO5 = " \\___\\____/\\__,_|_|  \\__,_|_| |_|\\___/|_| |_| |_|_|\\___|___/";

    public static Scanner in = new Scanner(System.in);
    private static LogStorage logger = new LogStorage("UiLogger");

    /**
     * Reads user input from command line.
     *
     * @return String containing userInput
     */
    public static String readUserInput() {
        return in.nextLine();
    }

    /**
     * Displays dash line.
     */
    public static void printDashLines() {
        System.out.println(DASH_LINES);
    }

    public static void printMiddleSeparator() {
        System.out.println(MIDDLE_SEPARATOR);
    }

    /**
     * Displays the message given.
     *
     * @param message message to be displayed to user
     */
    public static void printMessage(String message) {
        printDashLines();
        System.out.println(message);
        printDashLines();
    }

    /**
     * Prints logo when user opens the program.
     */
    public static void printLogo() {
        System.out.println(LOGO1);
        System.out.println(LOGO2);
        System.out.println(LOGO3);
        System.out.println(LOGO4);
        System.out.println(LOGO5);
        System.out.println("");
    }

    /**
     * Displays the prompt for user input in Normal Mode.
     */
    public static void printNormalPrompt() {
        System.out.println("[Normal]");
        System.out.print("  > ");
    }

    /**
     * Displays the prompt for user input in Deck Mode.
     */
    public static void printDeckPrompt(Deck deck) {
        System.out.println("[Deck - " + deck.getName() + "]");
        System.out.print("  > ");
    }

    /**
     * Displays the prompt for user input in Game Mode.
     */
    public static void printGamePrompt(Deck deck) {
        System.out.println("[Game - " + deck.getName() + "]");
        System.out.print("  > ");
    }

    /**
     * Displays the prompt for user input without specifying current mode.
     */
    public static void printPrompt() {
        System.out.print("  > ");
    }

    /**
     * Displays the welcome message from Deck Mode to Normal Mode.
     */
    public static void printNormalWelcome() {
        printMessage(NORMAL_WELCOME_LINE);
    }

    /**
     * Displays the welcome message from Normal Mode to Deck Mode.
     */
    public static void printDeckWelcome(int index, Deck deck) {
        printMessage(DECK_WELCOME_LINE + "[" + index + "] " + deck.getName());
    }

    /**
     * Displays the welcome message for Game Mode.
     */
    public static void printGameWelcome(int index, Deck deck) {
        printMessage(Game.WELCOME_MESSAGE + "[" + index + "] " + deck.getName());
    }

    /**
     * Displays the greeting message.
     */
    public static void printGreeting() {
        printDashLines();
        printLogo();
        System.out.println(GREETING_LINES);
        printDashLines();
    }

    /**
     * Displays the exit message.
     */
    public static void printExitLine() {
        printMessage(BYE_LINE);
    }

    /**
     * Displays the add FlashCard line.
     *
     * @param deck current deck
     */
    public static void printAddFlashCardLine(Deck deck) {
        printMessage(ADD_FLASHCARD_LINE + deck.getName());
    }

    /**
     * Displays the enter question line.
     */
    public static void printEnterQuestionLine() {
        System.out.println(ENTER_QUESTION_LINE);
        printPrompt();
    }

    /**
     * Displays the enter answer line.
     */
    public static void printEnterAnswerLine() {
        System.out.println(ENTER_ANSWER_LINE);
        printPrompt();
    }

    /**
     * Displays the FlashCard added line.
     */
    public static void printFlashCardAddedLine() {
        System.out.println(FLASHCARD_ADDED_LINE);
    }

    /**
     * Displays an index list of FlashCards in the deck.
     *
     * @param deck           deck to display
     * @param isQuestionOnly print question only if true, question and answer otherwise
     */
    public static void printDeck(Deck deck, boolean isQuestionOnly) {
        String deckMessage = "";
        if (deck.toString(isQuestionOnly).trim().equals("")) {
            deckMessage += EMPTY_DECK_LINE;
        } else {
            deckMessage += LIST_FLASHCARDS_LINE + deck.getName() + System.lineSeparator()
                    + deck.toString(isQuestionOnly);
        }
        printMessage(deckMessage);
    }

    /**
     * Displays the delete FlashCard line.
     *
     * @param question The question of the FlashCard to delete
     */
    public static void printDeleteFlashCardLine(String question) {
        System.out.print(DELETE_FLASHCARD_LINE + "?\n  '" + question + "' ");
    }

    /**
     * Displays the FlashCard deleted line.
     *
     * @param question The question of the FlashCard to delete
     */
    public static void printFlashCardDeletedLine(String question) {
        System.out.println(FLASHCARD_DELETED_LINE + question + "'");
    }

    /**
     * Displays the help page of commands.
     *
     * <p>Displays a different help page for Normal Mode and Deck Mode.</p>
     *
     * @param helpDisplay help text to display
     */
    public static void printHelp(String helpDisplay) {
        printMessage(helpDisplay);
    }

    /**
     * Prints new deck added in the Normal Mode.
     *
     * @param deck in new Deck added
     */
    public static void printNewDeck(Deck deck) {
        String tagsOfNewDeck = deck.getTagString();
        if (!tagsOfNewDeck.isEmpty()) {
            tagsOfNewDeck = " #Tag: " + tagsOfNewDeck;
        }
        printMessage(NEW_DECK_CREATED_LINE + deck.getName() + tagsOfNewDeck);
    }

    /**
     * Prints all decks available in the Normal Mode.
     *
     * @param decks all decks in the list
     */
    public static void printDeckList(DeckList decks) {
        printMessage(DECKS_AVAILABLE_LINE + decks.toString());
    }

    /**
     * Confirms the deck the user wants to delete.
     *
     * @param deletedDeckName name of the deleted deck
     */
    public static void printDeletedDeckQuestion(String deletedDeckName) {
        System.out.print(String.format(DELETED_DECK_QUESTION_LINE, deletedDeckName));
    }

    /**
     * Prints the name of the deleted deck.
     *
     * @param deletedDeckName name of the deleted deck
     */
    public static void printDeckDeletedLine(String deletedDeckName) {
        System.out.println(String.format(DELETED_DECK_LINE, deletedDeckName));
    }

    /**
     * Confirms the deck the user wants to print to PowerPoint.
     *
     * @param pptxDeckName name of the pptx deck
     */
    public static void printPptxDeckQuestion(String pptxDeckName) {
        System.out.print(String.format(PPTX_DECK_QUESTION_LINE, pptxDeckName));
    }

    /**
     * Prints the name of the deck to be printed to PowerPoint.
     *
     * @param pptxDeckName name of the pptx deck
     */
    public static void printDeckPptxLine(String pptxDeckName, String bgColor, String txtColor, ColorOption colorOpt) {
        switch (colorOpt) {
        case DEFAULT:
            printMessage(String.format(PPTX_DECK_DEFAULT_LINE, pptxDeckName));
            break;
        case COLOR_SCHEME:
            printMessage(String.format(PPTX_DECK_CS_LINE, pptxDeckName, bgColor, txtColor));
            break;
        case ORGINAL_COLOR:
            printMessage(String.format(PPTX_DECK_OC_LINE, pptxDeckName, bgColor, txtColor));
            break;
        default:
            //
            break;
        }
    }

    /**
     * Prints the update question lines for a flashcard.
     *
     * @param flashCard for which the question should be updated.
     */
    public static void printUpdateQuestionLine(FlashCard flashCard) {
        // No offset since printing from start of line.
        System.out.println(flashCard.toString(true, 0));
        System.out.println(NEW_QUESTION_LINE);
        printPrompt();
    }

    /**
     * Prints the update answer lines for a flashcard.
     *
     * @param flashCard for which the answer should be updated.
     */
    public static void printUpdateAnswerLine(FlashCard flashCard) {
        // No offset since printing from start of line.
        System.out.println(System.lineSeparator() + flashCard.toString(false, 0));
        System.out.println(NEW_ANSWER_LINE);
        printPrompt();
    }

    /**
     * Prints line informing updated flashcards.
     *
     * @param hasNewQ boolean parameter indicating new Question provided
     * @param hasNewA boolean parameter indicating new Answer providid
     */
    public static void printFlashCardUpdatedLine(boolean hasNewQ, boolean hasNewA) {
        if (hasNewQ && hasNewA) {
            System.out.println(QNA_UPDATED_LINE);
        } else if (hasNewQ) {
            // !hasNewA
            System.out.println(QUESTION_UPDATED_LINE);
        } else if (hasNewA) {
            // !hasNewQ
            System.out.println(ANSWER_UPDATED_LINE);
        } else {
            System.out.println(NO_UPDATE_LINE);
        }
    }

    /**
     * Prints a line prompting user to enter only 'y' or 'n'.
     */
    public static void printInvalidYorNResponse() {
        System.out.print(INVALID_YN_RESPONSE_LINE);
    }

    public static void printVersionNumber() {
        printMessage("Version: " + VERSION_NUMBER);
    }

    public static void printIncludeExcludeLine() {
        System.out.print(INCLUDE_EXCLUDE_LINE);
    }

    public static void printAttemptFeedback(double matchPercentage) {
        System.out.println(String.format("%s %.2f", ATTEMPT_FEEDBACK_LINE, matchPercentage));
    }

    public static boolean getInclExclConfirmation() {
        logger.log(Level.INFO, "Logging method getInclExclConfirmation() in Ui.");
        Ui.printIncludeExcludeLine();
        return checkYorNResponse();
    }

    /**
     * Displays question in the flashcard.
     *
     * @param question String representing the question
     */
    public static void printGameQuestion(String question) {
        final String label = "Q: ";
        System.out.println(label + prettyPrintFormatter(question, label.length()));
        System.out.println(ENTER_ATTEMPT_LINE);
        printPrompt();
    }

    public static void printGameEmptyDeckLine() {
        System.out.println(GAME_EMPTY_DECK_LINE);
    }

    public static void printAnswerGameMode(String answer) {
        final String label = "A: ";
        System.out.println(label + prettyPrintFormatter(answer, label.length()));
    }

    public static void printDoneGameMessage() {
        System.out.println(DONE_GAME_LINE);
    }

    /**
     * Prints the new tags added to the deck.
     *
     * @param name    the name of the deck
     * @param newTags the tag(s) will be added to the deck
     */
    public static void printNewTags(String name, ArrayList<String> newTags) {
        String tagString = formStringOfTags(newTags);
        printMessage(String.format(NEW_TAGS_LINE, name, tagString));
    }

    /**
     * Prints out warning about invalid tags provided.
     */
    public static void printInvalidTagsLine(String tags) {
        logger.log(Level.WARNING, "User did not supply valid tags.");
        printMessage(String.format((INVALID_TAGS_LINE), tags));
    }

    /**
     * Prints confirmation question before tag removal.
     *
     * @param deckName the name of the deck having tags being removed
     * @param tags     the tags will be removed
     */
    public static void printRemovedTagsQuestion(String deckName, ArrayList<String> tags) {
        String removedTags = formStringOfTags(tags);
        System.out.print(String.format(REMOVED_TAGS_QUESTION_LINE, removedTags, deckName));
    }

    /**
     * Prints the tags that are removed from the specified deck.
     *
     * @param deckName the name of the deck having removed tags
     * @param tags     tags were removed
     */
    public static void printTagsRemovedLine(String deckName, ArrayList<String> tags) {
        String removedTags = formStringOfTags(tags);
        System.out.println(String.format(REMOVED_TAGS_LINE, removedTags, deckName));
    }

    /**
     * Forms a String of provided tags.
     *
     * @param tags tags to be formed to String
     * @return a String of tags
     */
    public static String formStringOfTags(ArrayList<String> tags) {
        String stringOfTags = "";

        for (int i = 0; i < tags.size(); i++) {
            stringOfTags += tags.get(i);
            if (i < tags.size() - 1) {
                stringOfTags += " | ";
            }
        }
        return stringOfTags;
    }

    /**
     * Checks y or n response from user.
     *
     * @return true if user enters confirms, otherwise false
     */
    private static boolean checkYorNResponse() {
        logger.log(Level.INFO, "Logging method checkYorNResponse() in Ui.");
        String response;
        do {
            response = readUserInput();
            assert response != null : "response should not be null";
            switch (response.trim()) {
            case Y:
                response = Y;
                break;
            case N:
                response = N;
                break;
            default:
                logger.log(Level.INFO, "User entered response other than 'y' or 'n'");
                logger.log(Level.INFO, "Re-prompting...");
                printInvalidYorNResponse();
            }
        } while (!response.trim().equals(Y) && !response.trim().equals(N));
        assert (response.equals(Y) || response.equals(N)) : "Response should be y/n";
        return response.equals(Y);
    }

    /**
     * Get confirmation for deleting a deck.
     *
     * @param deckName String representing the index of the deck in deckList
     * @return true if delete is confirmed, otherwise false
     */
    public static boolean getDeletedDeckConfirmation(String deckName) {
        logger.log(Level.INFO, "Logging method getDeletedDeckConfirmation() in Ui.");
        printDashLines();
        printDeletedDeckQuestion(deckName);

        return checkYorNResponse();
    }

    public static boolean getDeletedFlashCardConfirmation(String question) {
        logger.log(Level.INFO, "Logging method getDeletedFlashCardConfirmation() in Ui.");
        printDashLines();
        Ui.printDeleteFlashCardLine(question);

        return checkYorNResponse();
    }

    /**
     * Checks whether the user want to remove the tags specified
     * from the deck specified.
     *
     * @param tags     String[] representing the tags be removed
     * @param deckName String representing the index of the deck of the tags
     * @return true if the removal is confirmed, otherwise false
     */
    public static boolean getRemovedTagsConfirmation(ArrayList<String> tags, String deckName) {
        printDashLines();
        printRemovedTagsQuestion(deckName, tags);
        boolean isConfirmed = checkYorNResponse();

        if (isConfirmed) {
            printTagsRemovedLine(deckName, tags);
            printDashLines();
        }

        return isConfirmed;
    }

    public static void printSearchDecksLine(String decksHavingTags) {
        if (decksHavingTags.isEmpty()) {
            printMessage(NO_DECKS_WITH_TAGS_LINE);
        } else {
            printMessage(DECKS_HAVING_TAGS_LINE + decksHavingTags);
        }
    }

    /**
     * Get confirmation from user on whether to print deck to PowerPoint.
     *
     * @param deckName name of deck to be printed to PowerPoint
     * @return true if delete is confirmed, otherwise false
     */
    public static boolean getPptxDeckConfirmation(String deckName) {
        logger.log(Level.INFO, "Logging method getPptxDeckConfirmation() in Ui.");
        Ui.printPptxDeckQuestion(deckName);
        return checkYorNResponse();
    }

    /**
     * Format the target string to properly wrap around the end
     * of each line. The response will occupy the area between offset and
     * Ui.DASH_LINES.length().
     *
     * @param target String to be formatted
     * @param offset Number of characters from the start of the line
     * @return String that stores the formatted question or answer
     */
    public static String prettyPrintFormatter(String target, int offset) {
        String result = "";
        String[] words = target.split(" ");
        int lineLength = Ui.DASH_LINES.length();
        int usableLength = lineLength - offset;
        assert usableLength > 0 : "Otherwise we cannot print anything.";

        int currentLength = 0;
        for (String word : words) {
            // Handle the case where a word is too long to print on one line
            if (word.length() > usableLength) {
                // Find number of characters that can be printed on current line
                int remainLength = usableLength - currentLength;
                result += word.substring(0, remainLength);
                String leftover = word.substring(remainLength);
                // Separate the word into parts that can fit in a line
                while (leftover.length() > usableLength) {
                    result += System.lineSeparator() + " ".repeat(offset)
                            + leftover.substring(0, usableLength);
                    leftover = leftover.substring(usableLength);
                }
                // Place remainder of word into line and continue
                result += System.lineSeparator() + " ".repeat(offset) + leftover + " ";
                currentLength = leftover.length() + 1;
                continue;
            }
            currentLength += word.length();
            if (currentLength > usableLength) {
                // Repeat enough spaces so that text is aligned to usable area.
                result += System.lineSeparator() + " ".repeat(offset) + word;
                currentLength = word.length();
            } else {
                result += word;
            }
            result += " ";
            // Account for the " " after the word.
            ++currentLength;
        }
        return result.trim();
    }
}
