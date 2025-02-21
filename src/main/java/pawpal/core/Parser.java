package pawpal.core;

import java.io.IOException;

import pawpal.utils.Command;
import pawpal.utils.Printer;
import pawpal.utils.TaskList;

/**
 * Parses and processes user input for PawPal.
 */
class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public String parse(String input) {
        Command command = parseCommand(input);
        switch (command) {
        case LIST:
            return Printer.getTaskListString(taskList.getTasks());
        case MARK:
            return processMarkCommand(input, true);
        case UNMARK:
            return processMarkCommand(input, false);
        case TODO:
            return processToDoCommand(input);
        case DEADLINE:
            return processDeadlineCommand(input);
        case EVENT:
            return processEventCommand(input);
        case DELETE:
            return processDeleteCommand(input);
        case FIND:
            return processFindCommand(input);
        case CHEER:
            return processCheerCommand();
        case BYE:
            return processByeCommand();
        case SORT:
            return processSortCommand();
        default:
            return Printer.getInvalidCommandMessage();
        }
    }

    Command parseCommand(String input) {
        try {
            return Command.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
    }

    // Helper to parse task number from input
    private int parseTaskNumber(String input) {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(parts[1].trim());
    }

    private String processMarkCommand(String input, boolean mark) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1].trim());
            return mark ? taskList.markTask(taskNumber) : taskList.unmarkTask(taskNumber);
        } catch (Exception e) {
            return Printer.getInvalidTaskNumberMessage();
        }
    }

    private String processDeleteCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1].trim());
            return taskList.deleteTask(taskNumber);
        } catch (Exception e) {
            return Printer.getInvalidTaskNumberMessage();
        }
    }

    /*
    Checks that the user has used the Todo command correctly
     */
    private String processToDoCommand(String input) {
        if (input.trim().equalsIgnoreCase("todo") || input.length() <= 4) {
            return Printer.getTodoUsageMessage();
        }
        // Extract description
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            return Printer.getTodoUsageMessage();
        }
        // Add the ToDo if valid
        return taskList.addToDo(description);
    }
    /*
    Checks that the user has used the deadline command correctly
     */
    private String processDeadlineCommand(String input) {
        if (input.trim().equalsIgnoreCase("deadline") || input.length() <= 9) {
            return Printer.getDeadlineUsageMessage();
        }
        // After removing "deadline ", split by " /by "
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            return Printer.getDeadlineUsageMessage();
        }
        String description = parts[0].trim();
        String deadline = parts[1].trim();
        // Ensure both parts are non-empty
        if (description.isEmpty() || deadline.isEmpty()) {
            return Printer.getDeadlineUsageMessage();
        }
        return taskList.addDeadline(description, deadline);
    }
    /*
    Checks that the user has used the event command correctly
     */
    private String processEventCommand(String input) {
        if (input.trim().equalsIgnoreCase("event") || input.length() <= 6) {
            return Printer.getEventUsageMessage();
        }
        // After removing "event ", split by " /from " and " /to "
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) {
            return Printer.getEventUsageMessage();
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        // Ensure no empty pieces
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            return Printer.getEventUsageMessage();
        }
        return taskList.addEvent(description, from, to);
    }

    private String processFindCommand(String input) {
        return taskList.findTasks(input.substring(4).trim());
    }

    private String processCheerCommand() {
        try {
            return taskList.getRandomQuote();
        } catch (IOException e) {
            return "Error retrieving cheer message.";
        }
    }

    private String processSortCommand() {
        taskList.sortTasks();
        return Printer.getTaskListString(taskList.getTasks());
    }

    private String processByeCommand() {
        return Printer.printBye();
    }
}
