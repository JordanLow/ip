package dad;
import java.util.Arrays;


class Parser {

    public static boolean parse(String rawText, TaskList tasks) {
        try {
            String[] commands = rawText.split(" ");
            String[] parses;
            
            switch (commands[0].toLowerCase()) {
            case "bye":
                return true;
            case "list":
                tasks.listTasks();
                break;
            case "mark":
                if (commands.length > 1) {
                    tasks.markTask(commands[1]);
                } else {
                    throw new DadException("Finishing...?");
                }
                break;
            case "unmark":
                if (commands.length > 1) {
                    tasks.unmarkTask(commands[1]);
                } else {
                    throw new DadException("You undid what?");
                }
                break;
            case "delete":
                if (commands.length > 1) {
                    tasks.deleteTask(commands[1]);
                } else {
                    throw new DadException("Deletin' uhhhhhh... where...");
                }
                break;
            case "todo":
                if (commands.length == 1 || commands[1].strip().equals("")) {
                    throw new DadException("Whatcha doin'??");
                }
                tasks.addTask(new Todo(String.join(" ", Arrays.copyOfRange(commands, 1, commands.length))));
                break;
            case "deadline":
                parses = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)).strip().split("/by");
                if (parses.length == 2 && !parses[0].strip().equals("")) {
                    tasks.addTask(new Deadline(parses[0], parses[1]));
                } else {
                    throw new DadException("When and what's due??");
                }
                break;
            case "event":
                parses = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)).split("/from");
                if (parses.length == 2 && !parses[0].strip().equals("")) {
                    String[] parses2 = parses[1].split("/to");
                    if (parses2.length == 2 && !parses2[0].strip().equals("")) {
                        tasks.addTask(new Event(parses[0], parses2[0], parses2[1]));
                    } else {
                        throw new DadException("What event when??");
                    }
                } else {
                    throw new DadException("Huh? Event?");
                }
                break;
            default:
                throw new DadException("I don't get it");
            }

        } catch (DadException e) {
            System.out.println(e);
        }
        return false;
    }

}


