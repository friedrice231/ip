package pawpal.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Represents a task that has a specific time range (start and end).
 * Extends the Task class by adding start and end times.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private final String start;
    private final String end;
    private final Optional<LocalDateTime> startDateTime;
    private final Optional<LocalDateTime> endDateTime;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event task.
     * @param start       The starting time of the event.
     * @param end         The ending time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startDateTime = parseDateTime(start);
        this.endDateTime = parseDateTime(end);
    }

    /**
     * Parses the given date-time string into LocalDateTime if possible.
     *
     * @param dateTime The input date-time string.
     * @return An Optional containing the parsed LocalDateTime, or empty if parsing fails.
     */
    private Optional<LocalDateTime> parseDateTime(String dateTime) {
        try {
            return Optional.of(LocalDateTime.parse(dateTime, INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A string in the format "[E][status] description from: start to: end".
     */
    @Override
    public String toString() {
        String formattedStart = startDateTime
                .map(dateTime -> dateTime.format(OUTPUT_FORMAT))
                .orElse(start);
        String formattedEnd = endDateTime
                .map(dateTime -> dateTime.format(OUTPUT_FORMAT))
                .orElse(end);

        return "[E]" + super.toString() + " from: " + formattedStart + " to: " + formattedEnd;
    }
}
