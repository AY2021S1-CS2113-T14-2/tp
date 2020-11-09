package seedu.ecardnomics.command;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute() {
    }

    /** Returns if command given is an instance of <code>ExitCommand</code>.  */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
