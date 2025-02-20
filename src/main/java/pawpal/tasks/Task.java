package pawpal.tasks;

/**
 * Represents a generic task with a description and completion status.
 * This class serves as the base class for specific task types such as ToDo, Deadline, and Event.
 */
public class Task {

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
     * Returns the string representation of the task.
     *
     * @return A string in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
