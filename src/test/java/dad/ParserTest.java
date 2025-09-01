package dad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
	@Test
	public void parseTest() {
		TaskList taskList = new TaskList();
		assertEquals(Parser.parse("todo item one", taskList), false);
		assertEquals(Parser.parse("not a command", taskList), false);
		assertEquals(Parser.parse("event broken /from bad args", taskList), false);
		assertEquals(Parser.parse("bye", taskList), true);
	}
}
