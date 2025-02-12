package pawpal.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pawpal.tasks.Deadline;
import pawpal.tasks.Event;
import pawpal.tasks.Task;
import pawpal.tasks.ToDo;

/**
 * Manages the task list, including adding, removing, and updating tasks.
 */
public class TaskList {

    private final List<Task> tasks;
    private final Printer printer;
    private final Storage storage;

    /**
     * Constructs a new {@code TaskList} with an empty task list.
     * Initializes the printer and storage components.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.printer = new Printer();
        this.storage = storage;
        loadTasksFromStorage();
    }

    /**
     * Loads tasks from storage when the application starts.
     */
    private void loadTasksFromStorage() {
        List<Task> loadedTasks = null;
        try {
            loadedTasks = storage.loadTasks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tasks.addAll(loadedTasks);
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param description The description of the ToDo task.
     */
    public void addToDo(String description) {
        Task task = new ToDo(description);
        tasks.add(task);
        printer.printTaskAdded(task.toString(), tasks.size());
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param deadline    The deadline for the task.
     */
    public void addDeadline(String description, String deadline) {
        Task task = new Deadline(description, deadline);
        tasks.add(task);
        printer.printTaskAdded(task.toString(), tasks.size());
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the Event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public void addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        printer.printTaskAdded(task.toString(), tasks.size());
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param taskNumber The 1-based index of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.remove(taskNumber - 1);
            printer.printTaskDeleted(task, tasks.size());
        } else {
            printer.printInvalidTaskNumber();
        }
    }

    /**
     * Marks a task as done by its index.
     *
     * @param taskNumber The 1-based index of the task to be marked as done.
     */
    public void markTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            printer.printTaskMarked(task);
        } else {
            printer.printInvalidTaskNumber();
        }
    }

    /**
     * Marks a task as not done by its index.
     *
     * @param taskNumber The 1-based index of the task to be marked as not done.
     */
    public void unmarkTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsNotDone();
            printer.printTaskUnmarked(task);
        } else {
            printer.printInvalidTaskNumber();
        }
    }

    /**
     * Finds a task based on the keyword.
     * @param keyword The specific task the user wants to find.
     * @return
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Obtains a different line in /cheer.txt
     * @return returns the cheering text from cheer.txt
     * @throws IOException if the file is empty or corrupted.
     */
    public String getRandomQuote() throws IOException {
        String cheerFilePath = "./data/cheer.txt";
        return storage.getRandomQuote(cheerFilePath);
    }
    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }
}
