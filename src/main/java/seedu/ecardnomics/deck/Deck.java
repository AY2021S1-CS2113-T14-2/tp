package seedu.ecardnomics.deck;

import java.util.ArrayList;

/**
 * Deck of flashcards.
 */
public class Deck {
    private String name;
    private ArrayList<String> tags;
    private ArrayList<FlashCard> deck;

    /** Constructor. */
    public Deck(String name) {
        assert (name != null && !name.isEmpty()) : "A deck requires a name.";
        this.name = name;
        deck = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * Constructors with tags parameter.
     *
     * @param name the name of the new deck
     * @param tags tags of the new deck
     */
    public Deck(String name, ArrayList<String> tags) {
        assert (name != null && !name.isEmpty()) : "A deck requires a name.";
        this.name = name;
        deck = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.tags = tags;
    }

    /**
     * Gets the name of the deck.
     *
     * @return name of deck
     */
    public String getName() {
        return name;
    }

    public String getTagString() {
        String tagString = "";
        for (int j = 0; j < tags.size(); j++) {
            tagString += tags.get(j);
            if (j < tags.size() - 1) {
                tagString += " | ";
            }
        }
        return tagString;
    }

    public ArrayList<String> getTag() {
        return tags;
    }

    /**
     * Adds tag to the deck.
     *
     * @param newTags an unique ArraList to be added to the deck
     */
    public void addTag(ArrayList<String> newTags) {
        for (String tag: newTags) {
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
    }

    public void removeTag(ArrayList<String> deletedTags) {
        for (String tag: deletedTags) {
            tags.remove(tag);
        }
    }

    /**
     * Sets the name of the deck.
     *
     * @param name new name of the deck
     */
    public void setName(String name) {
        assert (name != null && !name.isEmpty()) : "A deck requires a name.";
        this.name = name;
    }

    /**
     * Retrieves the inherent ArrayList data structure of the deck.
     *
     * @return ArrayList of FlashCards
     */
    public ArrayList<FlashCard> getDeck() {
        return deck;
    }

    /**
     * Retrieves the flashcard at specified index.
     *
     * @param index Index of flashcard to be found
     * @return FlashCard at index
     */
    public FlashCard get(int index) {
        assert (index >= 0 && index < deck.size()) : "Index should be within range";
        return deck.get(index);
    }

    /**
     * Adds a flashcard to the deck.
     *
     * @param flashCard Reference to the flashcard to be added
     */
    public void add(FlashCard flashCard) {
        assert flashCard != null : "Do not add null objects into deck";
        deck.add(flashCard);
    }

    /**
     * Returns size of current deck.
     *
     * @return size of current deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Deletes the flashcard at specified index from the deck.
     *
     * @param index Index of flashcard to be deleted
     */
    public void delete(int index) {
        assert (index >= 0 && index < deck.size()) : "Index should be within range";
        deck.remove(index);
    }

    @Override
    public String toString() {
        String output = name + ":" + System.lineSeparator();
        output += this.toString(false);
        return output;
    }

    public String toString(boolean isQuestionOnly) {
        String output = "";
        if (this.deck.size() == 0) {
            return output;
        }

        for (int i = 0; i < deck.size(); i++) {
            String serialNumber = (i + 1) + ". ";
            // Always print the question.
            output += serialNumber + deck.get(i).toString(true, serialNumber.length());
            if (!isQuestionOnly) {
                // Add in the answer
                output += System.lineSeparator() + deck.get(i).toString(false, serialNumber.length());
            }
            if (i != deck.size() - 1) {
                output += "\n\n";
            }
        }
        return output;
    }
}
