package pawpal.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pawpal.utils.Command;

class ParserTest {
    private final Parser parser = new Parser(null); // Pass null for TaskList since we're testing parsing only

    @Test
    void parseCommand_validCommand_returnsCorrectEnum() {
        // Positive test case: Recognizing a valid command
        assertEquals(Command.TODO, parser.parseCommand("todo read book"));
        assertEquals(Command.DEADLINE, parser.parseCommand("deadline submit report /by tomorrow"));
    }

    @Test
    void parseCommand_invalidCommand_returnsInvalid() {
        // Negative test case: Handling an invalid command
        assertEquals(Command.INVALID, parser.parseCommand("dance tango"));
    }
}
