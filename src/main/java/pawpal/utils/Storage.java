package pawpal.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
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
    private Task parseTask(String line) {
        try {
            if (line.isBlank()) {
                return null;
            }

            String taskType = line.substring(1, 2);
            boolean isDone = line.charAt(4) == 'X';
            String details = line.substring(7).trim();

            Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(details);
                break;
            case "D":
                String[] deadlineParts = details.split("\\(by: ", 2);
                if (deadlineParts.length < 2) {
                    return null;
                }
                task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].replace(")", "").trim());
                break;
            case "E":
                String[] eventParts = details.split("from: | to: ");
                if (eventParts.length < 3) {
                    return null;
                }
                task = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
                break;
            default:
                return null;
            }

            if (isDone) {
                task.markAsDone();
            }
            return task;

        } catch (Exception e) {
            System.out.println("Skipping malformed task line: " + line);
            return null;
        }
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
