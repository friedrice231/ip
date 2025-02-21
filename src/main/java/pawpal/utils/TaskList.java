package pawpal.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import pawpal.tasks.Deadline;
import pawpal.tasks.Event;
import pawpal.tasks.Task;
import pawpal.tasks.ToDo;

/**
 * Manages the task list, including adding, removing, and updating tasks.
 */
public class TaskList {

    private final List<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a new {@code TaskList} with an empty task list.
     * Initializes storage and loads tasks.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
        loadTasksFromStorage();
    }

    /**
     * Loads tasks from storage when the application starts.
     */
    private void loadTasksFromStorage() {
        try {
            tasks.addAll(storage.loadTasks());
        } catch (IOException e) {
            throw new RuntimeException("Error loading tasks.", e);
        }
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param description The description of the ToDo task.
     * @return The response message after successfully adding the task.
     */
    public String addToDo(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty!");
        }
        Task task = new ToDo(description);
        tasks.add(task);
        return Printer.getTaskAddedMessage(task.toString(), tasks.size());
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param deadline    The due date and time for the task.
     * @return The response message after successfully adding the task.
     */
    public String addDeadline(String description, String deadline) {
        Task task = new Deadline(description, deadline);
        tasks.add(task);
        return Printer.getTaskAddedMessage(task.toString(), tasks.size());
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the Event task.
     * @param from        The starting time of the event.
     * @param to          The ending time of the event.
     * @return The response message after successfully adding the task.
     */
    public String addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        return Printer.getTaskAddedMessage(task.toString(), tasks.size());
    }

    /**
     * Deletes a task from the list based on the task number.
     *
     * @param taskNumber The 1-based index of the task to be deleted.
     * @return The response message indicating success or failure.
     */
    public String deleteTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.remove(taskNumber - 1);
            return Printer.getTaskDeletedMessage(task, tasks.size());
        }
        return Printer.getInvalidTaskNumberMessage();
    }

    /**
     * Marks a task as completed based on the task number.
     *
     * @param taskNumber The 1-based index of the task to be marked as completed.
     * @return The response message indicating success or failure.
     */
    public String markTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            return Printer.getTaskMarkedMessage(task);
        }
        return Printer.getInvalidTaskNumberMessage();
    }

    /**
     * Marks a task as not completed based on the task number.
     *
     * @param taskNumber The 1-based index of the task to be marked as not completed.
     * @return The response message indicating success or failure.
     */
    public String unmarkTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsNotDone();
            return Printer.getTaskUnmarkedMessage(task);
        }
        return Printer.getInvalidTaskNumberMessage();
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return The response message listing the matching tasks.
     */
    public String findTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        return Printer.getMatchingTasksMessage(matchingTasks);
    }


    /**
     * Retrieves a random motivational quote from a file.
     *
     * @return A randomly selected quote from the stored list.
     * @throws IOException If an error occurs while reading the file.
     */
    public String getRandomQuote() throws IOException {
        return storage.getRandomQuote("./data/cheer.txt");
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sorts the tasks in the following order:
     * 1. ToDo tasks first
     * 2. Deadline tasks sorted by the nearest deadline
     * 3. Event tasks sorted by the earliest start time
     */
    public void sortTasks() {
        Collections.sort(tasks);
    }
}
