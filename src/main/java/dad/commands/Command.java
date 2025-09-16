package dad.commands;

import dad.DadException;
import dad.TaskList;
import java.util.Stack;

public abstract class Command {

    public static Stack<Command> pushdown = new Stack<>();

    public static Command of(String[] commands, TaskList tasks) throws DadException {
        
        switch (commands[0].toLowerCase()) {
        case "list":
            return new ListCommand(tasks);
        case "find":
            return new FindCommand(commands, tasks);
        case "mark":
            return new MarkCommand(commands, tasks);
        case "unmark": 
            return new UnmarkCommand(commands, tasks);
        case "delete":
            return new DeleteCommand(commands, tasks);
        case "todo":
            return new TodoCommand(commands, tasks);
        case "deadline":
            return new DeadlineCommand(commands, tasks);
        case "event":
            return new EventCommand(commands, tasks);
        case "undo":
            return new UndoCommand();
        default:
            throw new DadException("I don't get it... (no such command " + commands[0] + ")");
        }
    }

    abstract public String execute();

    public String undo() {
        return "";
    }
}
