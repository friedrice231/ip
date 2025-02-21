package pawpal.tasks;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a generic task with a description and completion status.
 * This class serves as the base class for specific task types such as ToDo, Deadline, and Event.
 */
public class Task implements Comparable<Task> {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task instance.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        assert description.isEmpty() : "Description should not be empty!";
        this.isDone = false; // Tasks are not done by default
    }

    /**
     * Returns the status icon representing the task's completion status.
     *
     * @return "X" if the task is done, otherwise a blank space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns priority order of task type:
     * 0 - ToDo
     * 1 - Deadline
     * 2 - Event
     */
    protected int getTaskTypePriority() {
        return switch (this) {
            case ToDo toDo -> 0;
            case Deadline deadline -> 1;
            case Event event -> 2;
            default -> 3;
        };
    }

    /**
     * Retrieves the relevant date-time for sorting.
     * ToDo tasks return MAX value to always stay in order.
     */
    protected Optional<LocalDateTime> getSortDateTime() {
        return Optional.empty(); // Default for ToDo tasks
    }

    @Override
    public int compareTo(Task other) {
        int typeComparison = Integer.compare(this.getTaskTypePriority(), other.getTaskTypePriority());
        if (typeComparison != 0) {
            return typeComparison; // Sort by task type first
        }
        // If same type, compare by date
        return this.getSortDateTime().orElse(LocalDateTime.MAX)
                .compareTo(other.getSortDateTime().orElse(LocalDateTime.MAX));
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
