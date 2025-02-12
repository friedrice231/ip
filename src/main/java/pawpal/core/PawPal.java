package pawpal.core;

import java.io.IOException;

import pawpal.utils.Storage;
import pawpal.utils.TaskList;
import pawpal.utils.Ui;
/**
 * The main class for the PawPal chatbot application.
 * PawPal allows users to manage tasks such as ToDo, Deadline, and Event tasks.
 * It interacts with the user via the command line.
 */
public class PawPal {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;
    /**
     * Constructs a new PawPal.core.PawPal instance.
     * Initializes the storage, task list, UI, and parser components.
     */
    public PawPal() {
        String filePath = "./data/tasks.txt";
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
        this.ui = new Ui();
        this.parser = new Parser(taskList);
    }
    /**
     * The entry point of the PawPal application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new PawPal().run();
    }

    /**
     * Runs the main loop of the PawPal.core.PawPal chatbot.
     * Continuously reads user input, processes commands, and prints responses.
     * The loop exits when the user enters the "bye" command.
     */
    public void run() {
        ui.showGreeting();

        // Main input loop
        while (true) {
            String input = ui.readCommand();

            // Exit the application when the user types "bye"
            if (input.equalsIgnoreCase("bye")) {
                ui.showBye();
                break;
            }

            // Pass the user input to the parser for processing
            parser.parse(input);

            try {
                storage.saveTasks(taskList.getTasks());
            } catch (IOException e) {
                ui.showSavingError();
            }
        }
    }
}
