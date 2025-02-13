package pawpal.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import pawpal.tasks.Task;
import pawpal.utils.Storage;
import pawpal.utils.TaskList;

class TaskListTest {
    private final Storage storage = new Storage("./test_tasks.txt"); // Test storage file
    private final TaskList taskList = new TaskList(storage);

    @Test
    void addToDo_validDescription_taskAdded() {
        // Positive test case: Adding a ToDo task
        taskList.addToDo("Buy groceries");
        List<Task> tasks = taskList.getTasks();

        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Buy groceries", tasks.getFirst().toString());
    }

    @Test
    void addToDo_emptyDescription_throwsException() {
        // Negative test case: Adding an empty ToDo task should not be allowed
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskList.addToDo("");
        });

        assertEquals("Task description cannot be empty!", exception.getMessage());
    }
}
