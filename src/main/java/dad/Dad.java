package dad;
import dad.Ui;;
import java.util.Scanner;


class Dad {

	public static void main(String[] args) {

		Ui.printLine(false);
		Ui.printIntro();
		Ui.printLine(true);

		Storage savedTasks = new Storage("./dad.txt");

		TaskList taskList = savedTasks.loadFile();

		Scanner scanner = new Scanner(System.in);
		boolean sentinel = true;
		do {
			if (Parser.parse(scanner.nextLine(), taskList)) {
				sentinel = false;
			}
		} while (sentinel);

		Ui.printLine(false);
		Ui.printOutro();
		Ui.printLine(true);
		savedTasks.saveFile(taskList);
	}


}
