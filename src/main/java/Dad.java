import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Arrays;

public class Dad {

	private static final Pattern INT = Pattern.compile("^\\d+$");

	private static List<Task> taskList;

	private static abstract class Task {

		private String task;
		private char done = ' ';

		public Task(String task) {
			this.task = task;
		}

		public void mark() {
			System.out.println("  ----------------------------------");
			this.done = 'X';
			System.out.println("	So you say huh?");
			System.out.println("	" + this);
			System.out.println("  ----------------------------------\n");
		}

		public void unmark() {
			System.out.println("  ----------------------------------");
			this.done = ' ';
			System.out.println("	If you say so...");
			System.out.println("	" + this);
			System.out.println("  ----------------------------------\n");
		}

		public String taskName() {
			return task;
		}

		@Override
			public String toString() {
				return "[" + this.done + "] " + this.task;
			}
	}

	private static class Todo extends Task {
		public Todo(String task) {
			super(task);
		}

		@Override
			public String toString() {
				return "[T] " + super.toString();
			}
	}

	private static class Event extends Task {

		public String from;
		public String to;

		public Event(String task, String from, String to) {
			super(task);
			this.from = from;
			this.to = to;		
		}

		@Override
			public String toString() {
				return "[E] " + super.toString() + " (from: " + this.from + " | to: " + this.to + ")";
			}
	}

	private static class Deadline extends Task {

		public String by;

		public Deadline(String task, String by) {
			super(task);
			this.by = by;
		}

		@Override
			public String toString() {
				return "[D] " + super.toString() + "(by: " + this.by + ")";
			}
	}

	public static void main(String[] args) {
		System.out.println("  ----------------------------------");
		System.out.println("	Hello I'm Dad");
		System.out.println("	Whaddya want?");
		System.out.println("  ----------------------------------\n");

		taskList = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);
		boolean sentinel = true;
		do {
			try {
				String[] command = scanner.nextLine().split(" ");
				String[] parse;
				switch (command[0].toLowerCase()) {
					case "bye":
						sentinel = false;
						break;
					case "list":
						listTasks();
						break;
					case "mark":
						if (command.length > 1 && INT.matcher(command[1]).matches() && 
								taskList.size() >= Integer.valueOf(command[1]) && Integer.valueOf(command[1]) > 0) {
							taskList.get(Integer.valueOf(command[1]) - 1).mark();
						} else {
							throw new DadException("Finishing...?");
						}
						break;
					case "unmark":
						if (command.length > 1 && INT.matcher(command[1]).matches() &&
								taskList.size() >= Integer.valueOf(command[1]) && Integer.valueOf(command[1]) > 0) {
							taskList.get(Integer.valueOf(command[1]) - 1).unmark();
						} else {
							throw new DadException("You undid what?");
						}
						break;
					case "todo":
						if (command.length == 1 || command[1].strip().equals("")) {
							throw new DadException("Whatcha doin'??");
						}
						addTask(new Todo(String.join(" ", Arrays.copyOfRange(command, 1, command.length))));
						break;
					case "deadline":
						parse = String.join(" ", Arrays.copyOfRange(command, 1, command.length)).strip().split("/by");
						if (parse.length == 2 && !parse[0].strip().equals("")) {
							addTask(new Deadline(parse[0], parse[1]));
						} else {
							throw new DadException("When and what's due??");
						}
						break;
					case "event":
						parse = String.join(" ", Arrays.copyOfRange(command, 1, command.length)).split("/from");
						if (parse.length == 2 && !parse[0].strip().equals("")) {
							String[] parse2 = parse[1].split("/to");
							if (parse2.length == 2 && !parse2[0].strip().equals("")) {
								addTask(new Event(parse[0], parse2[0], parse2[1]));
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
		} while (sentinel);
		System.out.println("  ----------------------------------");
		System.out.println("	'Kay I'm headin' out");
		System.out.println("  ----------------------------------\n");
	}

	private static void addTask(Task task) {
		System.out.println("  ----------------------------------");
		System.out.println("	Puttin' it on the list: " + task);
		taskList.add(task);
		System.out.println("	Ye got " + taskList.size() + " of 'em here");
		System.out.println("  ----------------------------------\n");
	}

	private static void listTasks() {
		System.out.println("  ----------------------------------");	
		for (int i = 0; i < taskList.size(); i++) {
			System.out.println("	" + (i+1) + ": " + taskList.get(i));
		}
		System.out.println("  ----------------------------------\n");
	}
}
