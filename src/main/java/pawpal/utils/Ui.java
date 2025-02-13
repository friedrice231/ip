package pawpal.utils;

import java.util.List;
import java.util.Scanner;

import pawpal.tasks.Task;

/**
 * Handles interactions with the user, including displaying messages and reading input.
 * Delegates message generation to the Printer class.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a new {@code Ui} instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        System.out.println(Printer.getGreetingMessage("PawPal"));
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showBye() {
        System.out.println(Printer.getByeMessage());
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        System.out.println(Printer.getLoadingErrorMessage());
    }

    /**
     * Displays an error message when saving tasks fails.
     */
    public void showSavingError() {
        System.out.println(Printer.getSavingErrorMessage());
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println(Printer.getTaskListString(tasks));
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param task      The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(Printer.getTaskAddedMessage(task.toString(), taskCount));
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param task      The task that was deleted.
     * @param taskCount The current number of tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(Printer.getTaskDeletedMessage(task, taskCount));
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println(Printer.getTaskMarkedMessage(task));
    }

    /**
     * Displays a message when a task is unmarked (set to incomplete).
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(Printer.getTaskUnmarkedMessage(task));
    }

    /**
     * Displays an error message when the user enters an invalid task number.
     */
    public void showInvalidTaskNumber() {
        System.out.println(Printer.getInvalidTaskNumberMessage());
    }

    /**
     * Displays an error message when the user enters an invalid command.
     */
    public void showInvalidCommand() {
        System.out.println(Printer.getInvalidCommandMessage());
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user input as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
