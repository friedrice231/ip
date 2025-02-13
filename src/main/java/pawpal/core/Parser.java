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
        default:
            return Printer.getInvalidCommandMessage();
        }
    }

    private Command parseCommand(String input) {
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

    private String processToDoCommand(String input) {
        return taskList.addToDo(input.substring(4).trim());
    }

    private String processDeadlineCommand(String input) {
        String commandBody = input.substring(9).trim();
        String[] parts = commandBody.split(" /by ", 2);

        if (parts.length != 2) {
            return Printer.getInvalidCommandMessage();
        }

        String taskDescription = parts[0].trim();
        String deadline = parts[1].trim();
        return taskList.addDeadline(taskDescription, deadline);
    }

    private String processEventCommand(String input) {
        String commandBody = input.substring(6).trim();
        String[] parts = commandBody.split(" /from | /to ", 3);

        if (parts.length != 3) {
            return Printer.getInvalidCommandMessage();
        }

        String eventDescription = parts[0].trim();
        String startTime = parts[1].trim();
        String endTime = parts[2].trim();
        return taskList.addEvent(eventDescription, startTime, endTime);
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

    private String processByeCommand() {
        return Printer.printBye();
    }
}
