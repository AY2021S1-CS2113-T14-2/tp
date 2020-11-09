package seedu.ecardnomics.command;

import seedu.ecardnomics.Ui;

public class VersionCommand extends Command {

    public VersionCommand() {
    }

    @Override
    public void execute() {
        Ui.printVersionNumber();
    }
}
