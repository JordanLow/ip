package dad;

class Task {

	private String task;
	private char done = ' ';

	public Task(String task) {
		this.task = task;
	}

	/** 
	 * Marks this task as done and prints to Ui accordingly
	 */
	public void mark() {
		Ui.printLine(false);
		this.done = 'X';
		Ui.print("	So you say huh?");
		Ui.print("	" + this);
		Ui.printLine(true);
	}

	/**
	 * Marks this task as not-done and prints to Ui accordingly
	 */
	public void unmark() {
		Ui.printLine(false);
		this.done = ' ';
		Ui.print("	If you say so...");
		Ui.print("	" + this);
		Ui.printLine(true);
	}

	/**
	 * Returns the main body of the task
	 */
	public String taskName() {
		return task;
	}

	/**
	 * Returns the serialized version of this task for storage purposes
	 */
	public String toRecord() {
		return this.task;
	}

	@Override
	public String toString() {
		return "[" + this.done + "] " + this.task;
	}
}
