package pawpal.utils;

import java.util.List;

import pawpal.tasks.Task;

/**
 * Defines the printer class and its statements.
 */
public class Printer {

    // Added methods for greeting, bye, and error messages
    public static String getGreetingMessage(String appName) {
        return "Hello from " + appName + "!\nWhat can I do for you?";
    }

    public static String getByeMessage() {
        return "Goodbye! Hope to see you again soon!";
    }

    public static String getLoadingErrorMessage() {
        return "Error loading tasks from file.";
    }

    public static String getSavingErrorMessage() {
        return "Error saving tasks to file.";
    }

    // Existing task-related messages
    public static String getTaskAddedMessage(String description, int taskCount) {
        return "Task added: " + description + "\nNow you have " + taskCount + " tasks.";
    }

    public static String getTaskDeletedMessage(Task task, int taskCount) {
        return "Task removed: " + task + "\nNow you have " + taskCount + " tasks left.";
    }

    public static String getTaskMarkedMessage(Task task) {
        return "Task marked as done:\n" + task;
    }

    public static String getTaskUnmarkedMessage(Task task) {
        return "Task marked as not done:\n" + task;
    }

    public static String getTaskListString(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks in your list!";
        }
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public static String getMatchingTasksMessage(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder("Matching tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public static String getInvalidTaskNumberMessage() {
        return "Invalid task number!";
    }

    public static String getInvalidCommandMessage() {
        return "Invalid command!";
    }

}
