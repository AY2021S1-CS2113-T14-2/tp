package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;

import java.util.ArrayList;

public class SearchCommand extends NormalCommand {
    private String[] relevantTags;

    /** Constructor. */
    public SearchCommand(DeckList decks, String[] relevantTags) {
        super(decks);
        assert  (relevantTags.length != 0) : "Relevant tags must be provided.";
        this.relevantTags = relevantTags;
    }

    @Override
    public void execute() {
        String decksHavingTags = "";
        ArrayList<String> uniqueTagList = new ArrayList<>();
        for (String tag: relevantTags) {
            String lowerCaseTrimmedTag = tag.toLowerCase();
            if (!uniqueTagList.contains(lowerCaseTrimmedTag) & !lowerCaseTrimmedTag.isEmpty()) {
                uniqueTagList.add(tag);
            }
        }
        for (String tag: uniqueTagList) {
            for (int i = 0; i < deckList.size(); i++) {
                String tagLowerCase = deckList.getDeck(i).getTagString().toLowerCase();
                if (tagLowerCase.contains(tag.toLowerCase())) {
                    decksHavingTags += "\n" + deckList.getNameWithTags(i);
                }
            }
        }
        Ui.printSearchDecksLine(decksHavingTags);
    }
}
