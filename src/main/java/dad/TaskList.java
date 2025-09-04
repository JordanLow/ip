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
     * Adds the provided task into this task list and returns the appropriate response
     *
     * @return The output String
     */
    public String addTask(Task task) {
        String out = "";
        out += Ui.printLine() + "\n";
        out += Ui.print("  Puttin' it on the list: " + task);
        taskList.add(task);
        out += Ui.print("  Ye got " + taskList.size() + " of 'em here");
        return out + Ui.printLine();
    }

    /**
     * Returns a String representation of all current tasks
     */
    public String listTasks() {
        String out = Ui.printLine() + "\n";
        for (int i = 0; i < taskList.size(); i++) {
            out += Ui.print("  " + (i+1) + ": " + taskList.get(i));
        }
        return out + Ui.printLine();
    }

    /**
     * Lists out the tasks that contain the given String and returns its String representation 
     */
    public String findTasks(String search) {
        String out = Ui.printLine() + "\n";
        out += Ui.print("  Think I got what'cha lookin' for right here: ");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(search)) {
                out += Ui.print("  " + (i+1) + ": " + taskList.get(i));
            }
        }
        return out + Ui.printLine();
    }

    /**
     * Deletes the task at the given index from the list of tasks and returns the appropriate response
     *
     * @return The output String
     *
     * @throws DadException If the given argument is not a valid index or an out-of-range index
     */
    public String deleteTask(String idx) throws DadException {
        if (INT.matcher(idx).matches() &&
                taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
            String out = Ui.printLine() + "\n";    
            out += Ui.print("  Good riddance to this I s'pose: " + this.taskList.get(Integer.valueOf(idx) - 1));
            taskList.remove(Integer.valueOf(idx) - 1);
            out += Ui.print("  Ye got " + this.taskList.size() + " of 'em left");
            return out + Ui.printLine();
        } else {
            throw new DadException("Deletin' uhhhhhh... where...");
        }
    }

    /**
     * Marks as done the task at the given index from the list of tasks and returns the appropriate response
     *
     * @return The output String
     *
     * @throws DadException If the given argument is not a valid index or an out-of-range index
     */
    public String markTask(String idx) throws DadException {
        if (INT.matcher(idx).matches() && 
                taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
            return taskList.get(Integer.valueOf(idx) - 1).mark();
        } else {
            throw new DadException("Finishing...?");
        }
    }

    /**
     * Marks as not-done the task at the given index from the list of tasks and returns the appropriate response
     *
     * @return the output String
     *
     * @throws DadException If the given argumenti s not a valid index or an out-of-range index
     */
    public String unmarkTask(String idx) throws DadException {
        if (INT.matcher(idx).matches() &&
                taskList.size() >= Integer.valueOf(idx) && Integer.valueOf(idx) > 0) {
            return taskList.get(Integer.valueOf(idx) - 1).unmark();
        } else {
            throw new DadException("You undid what?");
        }
    }

    /**
     * Adds the provided task into this task list without returning a response message 
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
