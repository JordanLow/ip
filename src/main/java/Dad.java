import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Dad {

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

		public String toRecord() {
			return this.task;
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
		public String toRecord() {
			return "T|" + super.toRecord();
		}

		@Override
		public String toString() {
			return "[T] " + super.toString();
		}
	}

	private static class Event extends Task {

		public LocalDate from;
		public LocalDate to;

		public Event(String task, String from, String to) {
			super(task);
			this.from = LocalDate.parse(from.strip());
			this.to = LocalDate.parse(to.strip());		
		}

		@Override
		public String toRecord() {
			return "E|" + super.toRecord() + "|" + this.from + "|" + this.to;
		}

		@Override
		public String toString() {
			return "[E] " + super.toString() + " (from: " 
					+ this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " | to: " 
					+ this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
		}
	}

	private static class Deadline extends Task {

		public LocalDate by;

		public Deadline(String task, String by) {
			super(task);
			this.by = LocalDate.parse(by.strip());
		}

		@Override
		public String toRecord() {
			return "D|" + super.toString() + "|" + this.by;
		}

		@Override
		public String toString() {
			return "[D] " + super.toString() + "(by: " 
					+ this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
		}
	}

	public static void main(String[] args) {

		System.out.println("  ----------------------------------");
		System.out.println("	Hello I'm Dad");
		System.out.println("	Whaddya want?");
		System.out.println("  ----------------------------------\n");

		taskList = new ArrayList<>();

		try {
			File savedTasks = new File("./dad.txt");
			Scanner fileReader = new Scanner(savedTasks);
			while (fileReader.hasNextLine()) {
				String[] task = fileReader.nextLine().split("\\|");
				switch (task[0]) {
					case "T":
						taskList.add(new Todo(task[1]));
						break;
					case "D":
						taskList.add(new Deadline(task[1], task[2]));
						break;
					case "E":
						taskList.add(new Event(task[1], task[2], task[3]));
						break;
					default:
						break;
				}
			}
			fileReader.close();
		} catch (FileNotFoundException err) {
			try {
				File savedTasks = new File("./dad.txt");
				savedTasks.createNewFile();
			} catch (IOException err2) {
				err2.printStackTrace();
			}
		}



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
					case "delete":
						if (command.length > 1 && INT.matcher(command[1]).matches() &&
								taskList.size() >= Integer.valueOf(command[1]) && Integer.valueOf(command[1]) > 0) {
							deleteTask(Integer.valueOf(command[1]) - 1);
						} else {
							throw new DadException("Deletin' uhhhhhh... where...");
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

		try {
			File oldFile = new File("./dad.txt");
			oldFile.delete();
			oldFile.createNewFile();
			FileWriter taskWriter = new FileWriter("./dad.txt");
			for (int i = 0; i < taskList.size(); i++) {
				String task = taskList.get(i).toRecord();
				// System.out.println(task);
				taskWriter.write(taskList.get(i).toRecord() + "\n");
			}
			taskWriter.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
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

	private static void deleteTask(Integer idx) {
		System.out.println("  ----------------------------------");
		System.out.println("	Good riddance to this I s'pose: " + taskList.get(idx));
		taskList.remove(idx.intValue());
		System.out.println("	Ye got " + taskList.size() + " of 'em left");
		System.out.println("  ----------------------------------\n");

	}
}
