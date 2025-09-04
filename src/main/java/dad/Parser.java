package dad;
import java.util.Arrays;


class Parser {

    /**
      * Parses the given raw text into its component command and arguments and returns an appropriate response.
      * 
      * @param rawText The raw input
      * @param taskList The current list of tasks
      * @return The String to be fed to the output 
      */
    public static String parse(String rawText, TaskList tasks) {
        try {
            String[] commands = rawText.split(" ");
            String[] parses;
            
            switch (commands[0].toLowerCase()) {
            case "list":
                return tasks.listTasks();
            case "find":
                if (commands.length > 1) {
                    return tasks.findTasks(String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)));
                } else {
                    throw new DadException("Hm, lookin' for what?");
                }
           case "mark":
                if (commands.length > 1) {
                    return tasks.markTask(commands[1]);
                } else {
                    throw new DadException("Finishing...?");
                }
            case "unmark":
                if (commands.length > 1) {
                    return tasks.unmarkTask(commands[1]);
                } else {
                    throw new DadException("You undid what?");
                }
            case "delete":
                if (commands.length > 1) {
                    return tasks.deleteTask(commands[1]);
                } else {
                    throw new DadException("Deletin' uhhhhhh... where...");
                }
            case "todo":
                if (commands.length == 1 || commands[1].strip().equals("")) {
                    throw new DadException("Whatcha doin'??");
                }
                return tasks.addTask(new Todo(String.join(" ", Arrays.copyOfRange(commands, 1, commands.length))));
            case "deadline":
                parses = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)).strip().split("/by");
                if (parses.length == 2 && !parses[0].strip().equals("")) {
                    return tasks.addTask(new Deadline(parses[0], parses[1]));
                } else {
                    throw new DadException("When and what's due??");
                }
            case "event":
                parses = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)).split("/from");
                if (parses.length == 2 && !parses[0].strip().equals("")) {
                    String[] parses2 = parses[1].split("/to");
                    if (parses2.length == 2 && !parses2[0].strip().equals("")) {
                        return tasks.addTask(new Event(parses[0], parses2[0], parses2[1]));
                    } else {
                        throw new DadException("What event when??");
                    }
                } else {
                    throw new DadException("Huh? Event?");
                }
            default:
                throw new DadException("I don't get it");
            }
        } catch (DadException e) {
            return e.toString(); 
        }
    }
}


