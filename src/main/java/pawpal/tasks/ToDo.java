package pawpal.tasks;

/**
 * Represents a task without a specific deadline or time range.
 * This class extends the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
