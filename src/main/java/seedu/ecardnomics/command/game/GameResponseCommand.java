package seedu.ecardnomics.command.game;

public class GameResponseCommand extends GameCommand {
    String attempt;

    public GameResponseCommand(String userInput) {
        super();
        attempt = userInput;
    }

    @Override
    public void execute() {
        //
    }

    public String getAttempt() {
        return attempt;
    }
}
