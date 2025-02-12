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
 * Handles loading and saving PawPal.core.PawPal.tasks from and to a file.
 */
public class Storage {

    private final String taskFilePath;
    private final Printer printer;

    /**
     * Constructs a new Storage instance.
     *
     * @param filePath The path to the file where PawPal.core.PawPal.tasks are stored.
     */
    public Storage(String filePath) {
        this.taskFilePath = filePath;
        this.printer = new Printer();
    }

    /**
     * Loads PawPal.core.PawPal.tasks from the file and returns them as a list of PawPal.core.PawPal.tasks.
     *
     * @return A list of PawPal.core.PawPal.tasks loaded from the file.
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
     * Saves the current PawPal.core.PawPal.tasks to the file.
     *
     * @param tasks The list of PawPal.core.PawPal.tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(taskFilePath);
        file.getParentFile().mkdirs(); // Ensure the directory exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
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
                task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].replace(")", "").trim());
                break;
            case "E":
                String[] eventParts = details.split("from: | to: ");
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

    public String getRandomQuote(String cheerFilePath) throws IOException {
        List<String> quotes = new ArrayList<>();
        File file = new File(cheerFilePath);

        if (!file.exists()) {
            return printer.printFileNotFound();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    quotes.add(line.trim());
                }
            }
        }

        if (quotes.isEmpty()) {
            return printer.printEmptyCheerFile();
        }

        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }
}
