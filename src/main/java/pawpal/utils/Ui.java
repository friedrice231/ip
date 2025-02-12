package pawpal.utils;

import java.util.List;
import java.util.Scanner;

import pawpal.tasks.Task;

/**
 * Handles interactions with the user, including displaying messages and reading input.
 * Delegates message printing to the Printer class.
 */
public class Ui {

    private final Scanner scanner;
    private final Printer printer;

    /**
     * Constructs a new {@code Ui} instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        printer = new Printer();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        printer.printGreeting("pawpal");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showBye() {
        printer.printBye();
    }

    /**
     * Displays an error message when loading PawPal.core.PawPal.tasks fails.
     */
    public void showLoadingError() {
        printer.printLoadingErrorMessage();
    }

    public void showSavingError() {
        printer.printSavingErrorMessage();
    }

    /**
     * Displays a list of PawPal.core.PawPal.tasks to the user.
     *
     * @param tasks The list of PawPal.core.PawPal.tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        printer.printTaskList(tasks);
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param task      The task that was added.
     * @param taskCount The current number of PawPal.core.PawPal.tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        printer.printTaskAdded(task.getDescription(), taskCount);
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param task      The task that was deleted.
     * @param taskCount The current number of PawPal.core.PawPal.tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        printer.printTaskDeleted(task, taskCount);
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        printer.printTaskMarked(task);
    }

    /**
     * Displays a message when a task is unmarked (set to incomplete).
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        printer.printTaskUnmarked(task);
    }

    /**
     * Displays an error message when the user enters an invalid task number.
     */
    public void showInvalidTaskNumber() {
        printer.printInvalidTaskNumber();
    }

    /**
     * Displays an error message when the user enters an invalid command.
     */
    public void showInvalidCommand() {
        printer.printInvalidCommand();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
