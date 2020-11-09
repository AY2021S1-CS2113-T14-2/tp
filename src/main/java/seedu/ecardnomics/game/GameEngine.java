package seedu.ecardnomics.game;

import seedu.ecardnomics.Ui;
import seedu.ecardnomics.command.Command;
import seedu.ecardnomics.command.ExitCommand;
import seedu.ecardnomics.command.VersionCommand;
import seedu.ecardnomics.command.game.DoneGameCommand;
import seedu.ecardnomics.command.game.GameResponseCommand;
import seedu.ecardnomics.command.game.HelpCommand;
import seedu.ecardnomics.deck.FlashCard;
import seedu.ecardnomics.parser.GameParser;

public class GameEngine {
    GameStorage storage;
    GameParser gameParser;

    GameEngine(GameStorage storage) {
        gameParser = new GameParser(storage.originalDeck);
        this.storage = storage;
    }

    Command runGameLoop() {
        Command command;

        do {
            if (isDeckInitiallyEmpty()) {
                Ui.printGameEmptyDeckLine();
                return forceDoneGame();
            }
            FlashCard flashCard = getQuestion();
            do {
                poseQuestion(flashCard);
                command = getAttempt();
                if (isHelpCommand(command) || isVersionCommand(command)) {
                    command.execute();
                }
            } while (isHelpCommand(command) || isVersionCommand(command));
            if (isTerminate(command)) {
                break;
            }
            Ui.printAnswerGameMode(flashCard.getAnswer());
            try {
                Ui.printAttemptFeedback(checkAttempt(command, flashCard));
            } catch (Exception e) {
                e.getMessage();
            }
            command = update(Ui.getInclExclConfirmation(), flashCard);

        } while (!isTerminate(command) && !isNoMoreCards());

        return command;
    }

    Command update(boolean isResponseY, FlashCard flashCard) {
        updateRetestStore(isResponseY, flashCard);

        if (storage.deque.isEmpty()) {
            if (storage.retestStore.isEmpty()) {
                Ui.printMiddleSeparator();
                Ui.printDoneGameMessage();
                return forceDoneGame();
            } else {
                updateDeque();
            }
        }

        Ui.printMiddleSeparator();
        return null;
    }

    void updateDeque() {
        storage.deque = storage.createRandomisedStack(storage.retestStore);
        storage.refreshRetestStore();
    }

    void updateRetestStore(boolean response, FlashCard flashCard) {
        if (response) {
            storage.retestStore.add(flashCard);
        }
    }

    FlashCard getQuestion() {
        return storage.deque.pop();
    }

    void poseQuestion(FlashCard flashCard) {
        Ui.printGameQuestion(flashCard.getQuestion());
    }

    Command getAttempt() {
        String userInput = Ui.readUserInput();
        Command command = gameParser.parse(userInput);
        return command;
    }

    double checkAttempt(Command command, FlashCard flashCard) {
        assert command instanceof GameResponseCommand : "command not instance of GameResponseCommand!";

        String attempt = ((GameResponseCommand) command).getAttempt();
        String answer = flashCard.getAnswer();

        return getMatchPercentage(attempt, answer);
    }

    double getMatchPercentage(String attempt, String answer) {
        String[] attemptArray = attempt.replaceAll(Ui.PUNC_REGEX,"").split(" ");
        String[] answerArray = answer.replaceAll(Ui.PUNC_REGEX,"").split(" ");
        int answerLength = answerArray.length;
        if (answerLength <= 1 && !isValidAnswer(answerArray)) {
            return 0;
        }
        int matchCount = 0;
        for (String word1 : answerArray) {
            for (String word2: attemptArray) {
                if (word2.trim().equalsIgnoreCase(word1.trim())) {
                    matchCount++;
                    break;
                }
            }
        }

        assert matchCount <= answerLength : "matchCount > answerLength!";
        return (double) matchCount / answerLength * 100;
    }

    boolean isValidAnswer(String[] answerArray) {
        for (String word : answerArray) {
            if (!word.isBlank()) {
                return true;
            }
        }
        return false;
    }

    Command forceDoneGame() {
        return gameParser.parse("done");
    }

    boolean isDeckInitiallyEmpty() {
        return storage.originalDeck.size() == 0;
    }

    boolean isTerminate(Command command) {
        return DoneGameCommand.isDoneGame(command) || ExitCommand.isExit(command);
    }

    boolean isNoMoreCards() {
        return storage.deque.isEmpty() && storage.retestStore.isEmpty();
    }

    boolean isHelpCommand(Command command) {
        return command instanceof HelpCommand;
    }

    boolean isVersionCommand(Command command) {
        return command instanceof VersionCommand;
    }
}
