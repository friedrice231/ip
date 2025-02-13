package pawpal.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Represents a task with a deadline.
 * Extends the Task class by adding a deadline field.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private final Optional<LocalDateTime> deadlineDateTime;
    private final String deadline;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the deadline task.
     * @param deadline    The date or time by which the task must be completed.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.deadlineDateTime = parseDateTime(deadline);
    }

    /**
     * Parses the given deadline string into LocalDateTime if possible.
     *
     * @param deadline The input deadline string.
     * @return An Optional containing the parsed LocalDateTime, or empty if parsing fails.
     */
    private Optional<LocalDateTime> parseDateTime(String deadline) {
        try {
            return Optional.of(LocalDateTime.parse(deadline, INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A string in the format "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        String formattedDeadline = deadlineDateTime
                .map(dateTime -> dateTime.format(OUTPUT_FORMAT))
                .orElse(deadline);
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
