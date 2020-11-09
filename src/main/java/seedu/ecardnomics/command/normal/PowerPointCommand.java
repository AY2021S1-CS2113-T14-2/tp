package seedu.ecardnomics.command.normal;

import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.powerpoint.PowerPoint;

import java.awt.Color;

public class PowerPointCommand extends NormalCommand {
    Deck deck;
    PowerPoint pptx;
    boolean isPpptxCreated;

    /** Constructor for default printing. */
    public PowerPointCommand(DeckList deckList, Deck deck, boolean isPpptxCreated) {
        super(deckList);
        assert deck != null : "Do not operate on a null reference.";
        this.deck = deck;
        pptx = new PowerPoint(deck);
        this.isPpptxCreated = isPpptxCreated;
    }

    /** Constructor for -cs option. */
    public PowerPointCommand(DeckList deckList, Deck deck, boolean isPpptxCreated, int csIndex) {
        super(deckList);
        assert deck != null : "Do not operate on a null reference.";
        this.deck = deck;
        pptx = new PowerPoint(deck, csIndex);
        this.isPpptxCreated = isPpptxCreated;
    }

    /** Constructor for -oc option. */
    public PowerPointCommand(DeckList deckList, Deck deck, boolean isPpptxCreated, String bgColorString,
                             String txtColorString, Color bgColor, Color txtColor) {
        super(deckList);
        assert deck != null : "Do not operate on a null reference.";
        this.deck = deck;
        pptx = new PowerPoint(deck, bgColorString, txtColorString, bgColor, txtColor);
        this.isPpptxCreated = isPpptxCreated;
    }

    @Override
    public void execute() {
        if (isPpptxCreated) {
            pptx.createNewPowerPoint();
        }
    }
}
