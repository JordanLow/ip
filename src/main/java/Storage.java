import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;;

class Storage {

	private String fileName;

	public Storage(String fileName) {
		this.fileName = fileName;
	}

	public TaskList loadFile() {
		TaskList savedTasks = new TaskList();
		try {
			File taskFile = new File(this.fileName);
			Scanner fileReader = new Scanner(taskFile);
			while (fileReader.hasNextLine()) {
				String[] task = fileReader.nextLine().split("\\|");
				switch (task[0]) {
					case "T":
						savedTasks.addTaskSilent(new Todo(task[1]));
						break;
					case "D":
						savedTasks.addTaskSilent(new Deadline(task[1], task[2]));
						break;
					case "E":
						savedTasks.addTaskSilent(new Event(task[1], task[2], task[3]));
						break;
					default:
						break;
				}
			}
			fileReader.close();
		} catch (FileNotFoundException err) {
			try {
				File taskFile = new File(this.fileName);
				taskFile.createNewFile();
			} catch (IOException err2) {
				err2.printStackTrace();
			}
		} finally {
			return savedTasks;
		}
	}

	public void saveFile(TaskList tasks) {
		try {
			File oldFile = new File(this.fileName);
			oldFile.delete();
			oldFile.createNewFile();
			FileWriter taskWriter = new FileWriter(this.fileName);
			tasks.saveTasksHelper(taskWriter);	
			taskWriter.close();
		} catch (IOException err) {
			err.printStackTrace();
		}

	}
}


