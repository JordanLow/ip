package dad;

class Task {

	private String task;
	private char done = ' ';

	public Task(String task) {
		this.task = task;
	}

	public void mark() {
		Ui.printLine(false);
		this.done = 'X';
		Ui.print("	So you say huh?");
		Ui.print("	" + this);
		Ui.printLine(true);
	}

	public void unmark() {
		Ui.printLine(false);
		this.done = ' ';
		Ui.print("	If you say so...");
		Ui.print("	" + this);
		Ui.printLine(true);
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
