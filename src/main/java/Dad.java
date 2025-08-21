import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Dad {

	private static final Pattern INT = Pattern.compile("^\\d+$");
	
	private static List<Task> taskList;

	private static class Task {

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
			return "[" + this.done + "] " + task;
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
			String[] command = scanner.nextLine().split(" ");
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
						addTask(new Task(String.join(" ", command)));
					}
					break;
				case "unmark":
					if (command.length > 1 && INT.matcher(command[1]).matches() &&
					taskList.size() >= Integer.valueOf(command[1]) && Integer.valueOf(command[1]) > 0) {
						taskList.get(Integer.valueOf(command[1]) - 1).unmark();
					} else {
						addTask(new Task(String.join(" ", command)));
					}
					break;
				default:
					addTask(new Task(String.join(" ", command)));
			}
		} while (sentinel);
		System.out.println("  ----------------------------------");
		System.out.println("	'Kay I'm headin' out");
		System.out.println("  ----------------------------------\n");
	}

	private static void addTask(Task task) {
		System.out.println("  ----------------------------------");
		System.out.println("	added: " + task.taskName());
		System.out.println("  ----------------------------------\n");
		taskList.add(task);
	}

	private static void listTasks() {
		System.out.println("  ----------------------------------");	
		for (int i = 0; i < taskList.size(); i++) {
			System.out.println("	" + (i+1) + ": " + taskList.get(i));
		}
		System.out.println("  ----------------------------------\n");
	}
}
