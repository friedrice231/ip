package pawpal.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import pawpal.tasks.*;
import pawpal.utils.*;

class PawPalTest {

    @Test
    void testAddEvent() {
        // Arrange
        Storage mockStorage = new Storage("test_tasks.txt");
        TaskList taskList = new TaskList(mockStorage);
        Printer mockPrinter = new Printer();

        // Act
        taskList.addEvent("Team Meeting", "10/02/2025 1400", "10/02/2025 1600");
        List<Task> tasks = taskList.getTasks();

        // Assert
        assertEquals(1, tasks.size(), "Task list should contain one task");
        assertInstanceOf(Event.class, tasks.getFirst(), "The task should be an instance of Event");
        assertEquals("[E][ ] Team Meeting from: Feb 10 2025, 2:00 PM to: Feb 10 2025, 4:00 PM", tasks.getFirst().toString());
    }

    @Test
    void testParseCommand() {
        // Arrange
        TaskList mockTaskList = new TaskList(new Storage("test_tasks.txt"));
        Parser parser = new Parser(mockTaskList);

        // Act & Assert
        assertEquals(Command.LIST, parser.parseCommand("list"), "Command should be parsed as LIST");
        assertEquals(Command.MARK, parser.parseCommand("mark"), "Command should be parsed as MARK");
        assertEquals(Command.INVALID, parser.parseCommand("unknownCommand"), "Invalid command should return INVALID");
    }
}
