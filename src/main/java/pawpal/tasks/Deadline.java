package pawpal.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Extends the Task class by adding a deadline field.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime deadlineDateTime;
    private String rawDeadline;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the deadline task.
     * @param deadline    The date or time by which the task must be completed.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            // Attempt to parse deadline as a date-time string
            this.deadlineDateTime = LocalDateTime.parse(deadline, INPUT_FORMAT);
            this.rawDeadline = null;
        } catch (DateTimeParseException e) {
            // If parsing fails, store as raw string
            this.rawDeadline = deadline;
            this.deadlineDateTime = null;
        }
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A string in the format "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        if (deadlineDateTime != null) {
            return "[D]" + super.toString() + " (by: " + deadlineDateTime.format(OUTPUT_FORMAT) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + rawDeadline + ")";
        }
    }
}
