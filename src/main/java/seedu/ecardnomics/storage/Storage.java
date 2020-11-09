package seedu.ecardnomics.storage;

import seedu.ecardnomics.deck.Deck;
import seedu.ecardnomics.deck.DeckList;
import seedu.ecardnomics.deck.FlashCard;
import seedu.ecardnomics.parser.DeckParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "./data/deckList.txt";
    public static final String FOLDER_PATH = "./data";
    public static final String DIVIDER =
            "================================================================================";
    public static final String DECK_NAME_REGEX = "deck\\s\\|\\s.+";
    public static final String TAGS_REGEX = "tags\\s\\|.+";
    public static final String NO_TAGS_REGEX = "tags\\s\\|";
    public static final String QUESTION_REGEX = "Q\\s\\|\\s.+";
    public static final String EMPTY_QUESTION_REGEX = "Q\\s\\|\\s+";
    public static final String ANSWER_REGEX = "A\\s\\|\\s.+";
    public static final String EMPTY_ANSWER_REGEX = "A\\s\\|\\s+";
    public static final String DIVIDER_REGEX = "={10,}";

    public DeckList load(DeckList deckList) {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(FILE_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return deckList;
        }

        String line;

        while (scanner.hasNext()) {
            line = scanner.nextLine();
            String deckName = null;
            Deck deck = null;
            if (line.matches(DECK_NAME_REGEX)) {
                deckName = line.substring(7);
                deck = new Deck(deckName);
            } else {
                continue;
            }
            line = scanner.nextLine();
            if (line.matches(TAGS_REGEX)) {
                line = line.substring(line.indexOf("|") + 2).trim();
                ArrayList<String> arrayOfTags = new ArrayList<>();
                if (!line.isBlank()) {
                    String[] tags = line.split("\\|");
                    for (String tag: tags) {
                        arrayOfTags.add(tag.trim());
                    }
                    deck.addTag(arrayOfTags);
                }
            } else if (!line.matches(NO_TAGS_REGEX)) {
                continue;
            }
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String question = null;
                if (line.matches(QUESTION_REGEX) && !line.matches(EMPTY_QUESTION_REGEX)) {
                    question = line.substring(4).trim();
                    if (DeckParser.containsNoAlphaNumerics(question)) {
                        continue;
                    }
                } else if (line.matches(DIVIDER_REGEX)) {
                    break;
                } else {
                    continue;
                }
                line = scanner.nextLine();
                String answer = null;
                if (line.matches(ANSWER_REGEX) && !line.matches(EMPTY_ANSWER_REGEX)) {
                    answer = line.substring(4).trim();
                    if (DeckParser.containsNoAlphaNumerics(answer)) {
                        continue;
                    }
                } else if (line.matches(DIVIDER_REGEX)) {
                    break;
                } else {
                    continue;
                }
                FlashCard flashCard = new FlashCard(question, answer);
                deck.add(flashCard);
            }
            if (!deckList.contains(deck)) {
                deckList.addDeck(deck);
            }
        }
        return deckList;
    }

    public void write(DeckList deckList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(DIVIDER);
        fw.write(System.lineSeparator());

        for (int i = 0; i < deckList.size(); i++) {
            Deck deck = deckList.getDeck(i);
            fw.write("deck | " + deck.getName());
            fw.write(System.lineSeparator());
            fw.write("tags | " + deck.getTagString());
            fw.write(System.lineSeparator());
            for (int j = 0; j < deck.size(); j++) {
                fw.write("Q | " + deck.get(j).getQuestion());
                fw.write(System.lineSeparator());
                fw.write("A | " + deck.get(j).getAnswer());
                fw.write(System.lineSeparator());
            }
            fw.write(DIVIDER);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
