package dad;
import java.util.Arrays;


class Parser {

	public static boolean parse(String rawText, TaskList taskList) {
		try {
			String[] command = rawText.split(" ");
			String[] parse;
			switch (command[0].toLowerCase()) {
				case "bye":
					return true;
				case "list":
					taskList.listTasks();
					break;
				case "mark":
					if (command.length > 1) {
						taskList.markTask(command[1]);
					} else {
						throw new DadException("Finishing...?");
					}
					break;
				case "unmark":

					if (command.length > 1) {
						taskList.unmarkTask(command[1]);
					} else {
						throw new DadException("You undid what?");
					}
					break;
				case "delete":
					if (command.length > 1) {
						taskList.deleteTask(command[1]);
					} else {
						throw new DadException("Deletin' uhhhhhh... where...");
					}
					break;
				case "todo":
					if (command.length == 1 || command[1].strip().equals("")) {
						throw new DadException("Whatcha doin'??");
					}
					taskList.addTask(new Todo(String.join(" ", Arrays.copyOfRange(command, 1, command.length))));
					break;
				case "deadline":
					parse = String.join(" ", Arrays.copyOfRange(command, 1, command.length)).strip().split("/by");
					if (parse.length == 2 && !parse[0].strip().equals("")) {
						taskList.addTask(new Deadline(parse[0], parse[1]));
					} else {
						throw new DadException("When and what's due??");
					}
					break;
				case "event":
					parse = String.join(" ", Arrays.copyOfRange(command, 1, command.length)).split("/from");
					if (parse.length == 2 && !parse[0].strip().equals("")) {
						String[] parse2 = parse[1].split("/to");
						if (parse2.length == 2 && !parse2[0].strip().equals("")) {
							taskList.addTask(new Event(parse[0], parse2[0], parse2[1]));
						} else {
							throw new DadException("What event when??");
						}
					} else {
						throw new DadException("Huh? Event?");
					}
					break;
				case "find":
					if (command.length > 1) {
						taskList.findTasks(String.join(" ", Arrays.copyOfRange(command, 1, command.length)));
					} else {
						throw new DadException("Hm, lookin' for what?");
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


