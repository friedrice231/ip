package pawpal.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import pawpal.tasks.Deadline;
import pawpal.tasks.Event;
import pawpal.tasks.Task;
import pawpal.tasks.ToDo;

/**
 * Handles loading and saving tasks from and to a file.
 */
public class Storage {

    private final String taskFilePath;

    /**
     * Constructs a new Storage instance.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.taskFilePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(taskFilePath);

        if (!file.exists()) {
            return tasks; // Return an empty list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Optional<Task> maybeTask = parseTask(line);
                maybeTask.ifPresent(tasks::add);
            }
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(taskFilePath);
        file.getParentFile().mkdirs(); // Ensure the directory exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Parses a line from the task file and creates the corresponding task object.
     *
     * @param line The line representing a task in the saved file.
     * @return The parsed task object, or null if the line is malformed.
     */
    private Optional<Task> parseTask(String line) {
        // Guard clause: blank line or null
        if (line == null || line.isBlank()) {
            return Optional.empty();
        }

        // Validate length to avoid IndexOutOfBounds
        if (line.length() < 7) {
            System.out.println("Skipping malformed (too short) task line: " + line);
            return Optional.empty();
        }

        String taskType = line.substring(1, 2);
        boolean isDone = (line.charAt(4) == 'X');
        String details = line.substring(7).trim();

        // 1) Get Optional<Task> from helper
        Optional<Task> maybeTask = createTaskFromType(taskType, details);

        // 2) If empty, skip
        if (maybeTask.isEmpty()) {
            System.out.println("Skipping malformed (invalid format) task line: " + line);
            return Optional.empty();
        }

        // 3) Extract the Task and mark if done
        Task task = maybeTask.get();
        if (isDone) {
            task.markAsDone();
        }

        return Optional.of(task);
    }

    private Optional<Task> createTaskFromType(String taskType, String details) {
        switch (taskType) {
        case "T":
            return parseToDo(details);
        case "D":
            return parseDeadline(details);
        case "E":
            return parseEvent(details);
        default:
            return Optional.empty();
        }
    }

    private Optional<Task> parseToDo(String details) {
        // If you want to guard for empty details, do so here
        return Optional.of(new ToDo(details));
    }

    private Optional<Task> parseDeadline(String details) {
        // e.g., "Some desc (by: 12/12/2024 1800)"
        String[] parts = details.split("\\(by: ", 2);
        if (parts.length < 2) {
            return Optional.empty();
        }
        String desc = parts[0].trim();
        String deadlineStr = parts[1].replace(")", "").trim();
        return Optional.of(new Deadline(desc, deadlineStr));
    }

    private Optional<Task> parseEvent(String details) {
        // e.g. "Meeting from: 12/12/2024 1800 to: 12/12/2024 2000"
        String[] parts = details.split("from: | to: ");
        if (parts.length < 3) {
            return Optional.empty();
        }
        return Optional.of(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
    }



    /**
     * Retrieves a random motivational quote from a file.
     *
     * @param cheerFilePath The path to the cheer quotes file.
     * @return A randomly selected quote from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public String getRandomQuote(String cheerFilePath) throws IOException {
        File file = new File(cheerFilePath);

        if (!file.exists()) {
            return "File not found!";
        }

        List<String> quotes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    quotes.add(line.trim());
                }
            }
        }

        if (quotes.isEmpty()) {
            return "No cheers found!";
        }

        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }
}
