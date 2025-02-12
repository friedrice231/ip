package pawpal.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a specific time range (start and end).
 * Extends the Task class by adding start and end times.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String rawStart;
    private String rawEnd;
    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event task.
     * @param start       The starting time of the event.
     * @param end         The ending time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);

        // Attempt to parse start time
        try {
            this.startDateTime = LocalDateTime.parse(start, INPUT_FORMAT);
            this.rawStart = null;
        } catch (DateTimeParseException e) {
            this.startDateTime = null;
            this.rawStart = start;
        }

        // Attempt to parse end time
        try {
            this.endDateTime = LocalDateTime.parse(end, INPUT_FORMAT);
            this.rawEnd = null;
        } catch (DateTimeParseException e) {
            this.endDateTime = null;
            this.rawEnd = end;
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A string in the format "[E][status] description from: start to: end".
     */
    @Override
    public String toString() {
        String startStr = (startDateTime != null) ? startDateTime.format(OUTPUT_FORMAT) : rawStart;
        String endStr = (endDateTime != null) ? endDateTime.format(OUTPUT_FORMAT) : rawEnd;

        return "[E]" + super.toString() + " from: " + startStr + " to: " + endStr;
    }
}
