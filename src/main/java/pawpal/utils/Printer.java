package pawpal.utils;

import java.util.List;

import pawpal.tasks.Task;

/**
 * Defines the printer class and its statements.
 */
public class Printer {

    // Added methods for greeting, bye, and error messages
    public static String getGreetingMessage(String appName) {
        return "Meow there! Welcome to " + appName + "!\nHow can I help you pounce on your tasks today?";
    }

    public static String getByeMessage() {
        return "Farewell, human! May your naps be long and your adventures many! Meow!";
    }

    public static String getLoadingErrorMessage() {
        return "Oops! I tripped over a yarn ball while loading tasks from file.";
    }

    public static String getSavingErrorMessage() {
        return "Uh-oh! A sneaky cat toy got in the way while saving tasks to file.";
    }

    // Existing task-related messages
    public static String getTaskAddedMessage(String description, int taskCount) {
        return "Purrfect! I've added the task: " + description
                + "\nNow you have " + taskCount + " tasks to chase.";
    }

    public static String getTaskDeletedMessage(Task task, int taskCount) {
        return "I've swiped away the task: " + task
                + "\nNow there are " + taskCount + " tasks remaining in your kitty checklist.";
    }

    public static String getTaskMarkedMessage(Task task) {
        return "That task is now purr-fectly completed:\n" + task;
    }

    public static String getTaskUnmarkedMessage(Task task) {
        return "Even the best cats need a second try. Task reset to not done:\n" + task;
    }

    public static String getTaskListString(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Your task list is as empty as a food bowl before dinner!";
        }
        StringBuilder sb = new StringBuilder("Here are your tasks, ready for a purr-fect day:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public static String getMatchingTasksMessage(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found. Did the cat hide them?";
        }
        StringBuilder sb = new StringBuilder("Here are your matching tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public static String getInvalidTaskNumberMessage() {
        return "Invalid task number! Did you mistake a shadow for a task?";
    }

    public static String getInvalidCommandMessage() {
        return "Hiss! I don't understand that command. Please try a valid one.";
    }

    public static String printBye() {
        return "Bye! I'll be purring until we meet again.";
    }

    public static String getTodoUsageMessage() {
        return """
                To create a ToDo, use the format:
                  todo <description>
                Don't leave out the purr-scription!""";
    }

    public static String getDeadlineUsageMessage() {
        return """
                For a Deadline, please use the format:
                  deadline <description> /by <deadline info>
                Don't let your tasks slip through your paws!""";
    }

    public static String getEventUsageMessage() {
        return """
                For an Event, please use the format:
                  event <description> /from <start> /to <end>
                Time to mark your calendars and chase the fun!""";
    }

}
