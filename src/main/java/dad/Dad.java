package dad;
import dad.Ui;;
import java.util.Scanner;


class Dad {

    public static void main(String[] args) {

        Ui.printLine(false);
        Ui.printIntro();
        Ui.printLine(true);

        Storage savedTasks = new Storage("./dad.txt");
        TaskList tasks = savedTasks.loadFile();

        Scanner scanner = new Scanner(System.in);
        boolean isDone = true;
        do {
            if (Parser.parse(scanner.nextLine(), tasks)) {
                isDone = false;
            }
        } while (isDone);

        Ui.printLine(false);
        Ui.printOutro();
        Ui.printLine(true);

        savedTasks.saveFile(tasks);
    }


}
