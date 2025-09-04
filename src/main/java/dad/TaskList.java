package dad;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;



class TaskList {

	private final Pattern INT = Pattern.compile("^\\d+$");
	private List<Task> taskList;

	public TaskList() {
		taskList = new ArrayList<>();
	}

	public void addTask(Task task) {
		Ui.printLine(false);
		Ui.print("	Puttin' it on the list: " + task);
		taskList.add(task);
		Ui.print("	Ye got " + taskList.size() + " of 'em here");
		Ui.printLine(true);
	}

	public void listTasks() {
		Ui.printLine(false);
		for (int i = 0; i < taskList.size(); i++) {
			Ui.print("	" + (i+1) + ": " + taskList.get(i));
		}
		Ui.printLine(true);
	}

	/**
	 * Lists out the tasks that contain the given String and prints it to Ui 
	 */
	public void findTasks(String search) {
		Ui.printLine(false);
		Ui.print("	Think I got what'cha lookin' for right here: ");
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).toString().contains(search)) {
				Ui.print("	" + (i+1) + ": " + taskList.get(i));
			}
		}
	}

	public void deleteTask(String idx) throws DadException {
		if (INT.matcher(idx).matches() &&
				taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
			Ui.printLine(false);	
			Ui.print("	Good riddance to this I s'pose: " + this.taskList.get(Integer.valueOf(idx) - 1));
			taskList.remove(Integer.valueOf(idx) - 1);
			Ui.print("	Ye got " + this.taskList.size() + " of 'em left");
			Ui.printLine(true);
		} else {
			throw new DadException("Deletin' uhhhhhh... where...");
		}
	}

	public void markTask(String idx) throws DadException {
		if (INT.matcher(idx).matches() && 
				taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
			taskList.get(Integer.valueOf(idx) - 1).mark();
		} else {
			throw new DadException("Finishing...?");
		}
	}

	public void unmarkTask(String idx) throws DadException {
		if (INT.matcher(idx).matches() &&
				taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
			taskList.get(Integer.valueOf(idx) - 1).unmark();
		} else {
			throw new DadException("You undid what?");
		}
	}

	public void addTaskSilent(Task task) {
		taskList.add(task);
	}

	public void saveTasksHelper(FileWriter writer) throws IOException {
		for (int i = 0; i < taskList.size(); i++) {
			String task = taskList.get(i).toRecord();
			writer.write(taskList.get(i).toRecord() + "\n");
		}
	}
}
