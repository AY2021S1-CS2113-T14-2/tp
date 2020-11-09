package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.DeckList;

import java.util.ArrayList;

/**
 * Removes tags from the existing deck.
 */
public class UntagCommand extends NormalCommand {
    private int index;
    private ArrayList<String> removedTags;
    private boolean isYes = false;

    /** Constructor. */
    public UntagCommand(DeckList decks, int index, ArrayList<String> removedTags, boolean isYes) {
        super(decks);
        assert (index >= 0 && index < decks.size()) : "Index must be within range.";
        this.index = index;
        assert  (removedTags.size() != 0) : "Remove tags must be provided.";
        this.removedTags = removedTags;
        this.isYes = isYes;
    }

    @Override
    public void execute() {
        String deckName = deckList.getDeck(index).getName();
        boolean isTagsValid = checkTagsExist(removedTags);

        if (isTagsValid) {
            if (isYes) {
                deckList.getDeck(index).removeTag(removedTags);
                Ui.printTagsRemovedLine(deckName, removedTags);
            } else if (!isYes) {
                boolean isTagsRemoved = Ui.getRemovedTagsConfirmation(removedTags, deckName);
                if (isTagsRemoved) {
                    deckList.getDeck(index).removeTag(removedTags);
                }
            }
        }
    }

    /**
     * Checks if the deck specified contains the removed tags.
     *
     * @param removedTags String[] list tags to be removed
     * @return a boolean value indicating if all the tags exist
     */
    public boolean checkTagsExist(ArrayList<String> removedTags) {
        boolean isExist = true;
        ArrayList<String> availableTagList = deckList.getDeck(index).getTag();
        outerLoop:
        for (String removedTag : removedTags) {
            if (!availableTagList.contains(removedTag)) {
                isExist = false;
                Ui.printInvalidTagsLine(removedTag);
                break outerLoop;
            }
        }
        return isExist;
    }

}
