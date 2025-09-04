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

    /**
     * Adds the provided task into this task list and prints to Ui accordingly
     */
    public void addTask(Task task) {
        Ui.printLine(false);
        Ui.print("  Puttin' it on the list: " + task);
        taskList.add(task);
        Ui.print("  Ye got " + taskList.size() + " of 'em here");
        Ui.printLine(true);
    }

    /**
     * Prints the current task list to the Ui accordingly
     */
    public void listTasks() {
        Ui.printLine(false);
        for (int i = 0; i < taskList.size(); i++) {
            Ui.print("  " + (i+1) + ": " + taskList.get(i));
        }
        Ui.printLine(true);
    }


    /**
     * Deletes the task at the given index from the list of tasks and prints to Ui accordingly
     *
     * @throws DadException If the given argument is not a valid index or an out-of-range index
     */
    public void deleteTask(String idx) throws DadException {
        if (INT.matcher(idx).matches() &&
                taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
            Ui.printLine(false);    
            Ui.print("  Good riddance to this I s'pose: " + this.taskList.get(Integer.valueOf(idx) - 1));
            taskList.remove(Integer.valueOf(idx) - 1);
            Ui.print("  Ye got " + this.taskList.size() + " of 'em left");
            Ui.printLine(true);
        } else {
            throw new DadException("Deletin' uhhhhhh... where...");
        }
    }

    /**
     * Marks as done the task at the given index from the list of tasks
     *
     * @throws DadException If the given argument is not a valid index or an out-of-range index
     */
    public void markTask(String idx) throws DadException {
        if (INT.matcher(idx).matches() && 
                taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
            taskList.get(Integer.valueOf(idx) - 1).mark();
        } else {
            throw new DadException("Finishing...?");
        }
    }

    /**
     * Marks as not-done the task at the given index from the list of tasks
     *
     * @throws DadException If the given argumenti s not a valid index or an out-of-range index
     */
    public void unmarkTask(String idx) throws DadException {
        if (INT.matcher(idx).matches() &&
                taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
            taskList.get(Integer.valueOf(idx) - 1).unmark();
        } else {
            throw new DadException("You undid what?");
        }
    }

    /**
     * Adds the provided task into this task list without printing to Ui 
     */
    public void addTaskSilent(Task task) {
        taskList.add(task);
    }

    /**
     * Writes the current serialized list of tasks into a storage file
     * 
     * @param writer The writer for the file to be written to
     # @throws IOException If writing using the writer is somehow an invalid action
     */
    public void saveTasksHelper(FileWriter writer) throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            String task = taskList.get(i).toRecord();
            writer.write(taskList.get(i).toRecord() + "\n");
        }
    }
}
