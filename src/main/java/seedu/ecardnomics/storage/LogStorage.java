package seedu.ecardnomics.storage;

import seedu.ecardnomics.Ui;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/**
 * Class for instantiating loggers that log to files instead of stdout.
 */
public class LogStorage {
    private boolean willLog;
    private String name;
    private String filePath;
    private Logger logger;
    private FileHandler handler;
    private static final String FOLDER_PATH = "./log/";
    private static final File DIRECTORY = new File(FOLDER_PATH);

    /**
     * Constructor to initialize a logger for logging to file.
     *
     * @param name of class being logged by logger
     */
    public LogStorage(String name) {
        this.name = name;
        willLog = true;
        filePath = FOLDER_PATH + this.name + ".log";
        logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);

        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdir();
        }

        try {
            handler = new FileHandler(filePath);
            logger.addHandler(handler);
            handler.setFormatter(new SimpleFormatter());
        } catch (SecurityException se) {
            Ui.printMessage("No permission to create file, log file will not be created.");
            willLog = false;
        } catch (IOException ioe) {
            Ui.printMessage("Unable to open log file. Will not be logging.");
            willLog = false;
        }
    }

    /**
     * Create a log entry in the log file.
     *
     * @param level java.util.logging.Level object to indicate log level
     * @param message to be logged
     */
    public void log(Level level, String message) {
        if (willLog) {
            logger.log(level, message);
        }
    }
}
