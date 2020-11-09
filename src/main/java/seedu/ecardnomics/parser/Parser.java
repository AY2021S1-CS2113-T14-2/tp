package seedu.ecardnomics.parser;

import seedu.ecardnomics.command.Command;

public abstract class Parser {

    public static final int LOWEST_POSSIBLE_INDEX = 0;
    public static final int INDEX_OFFSET = 1;

    /**
     * Checks whether argument from user is a number and whether the index given is a within the correct range of
     * decks/ flash cards.
     *
     * @param arguments Argument from user input
     * @return int representing the index from the argument given
     */
    protected abstract int getIndex(String arguments)
            throws Exception;

    /**
     * Determines the Command subclass to return based on the commandWord
     * and arguments provided.
     *
     * @param commandWord String that corresponds to a command
     * @param arguments String that lists the arguments for the command
     * @return Command representing the command to be executed
     * @throws Exception if arguments are inappropriate
     */
    protected abstract Command parseCommand(String commandWord, String arguments)
            throws Exception;

    /**
     * Parse user input to determine which <code>Command</code> to output.
     *
     * @param userInput Input from user
     * @return Command representing command to be executed
     */
    public abstract Command parse(String userInput);
}
