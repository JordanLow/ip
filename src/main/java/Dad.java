import java.util.Scanner;

public class Dad {
	public static void main(String[] args) {
		System.out.println("  ----------------------------------");
		System.out.println("	Hello I'm Dad");
		System.out.println("	Whaddya want?");
		System.out.println("  ----------------------------------\n");

		Scanner scanner = new Scanner(System.in);
		boolean sentinel = true;
		do {
			String command = scanner.nextLine();
			if (command.toLowerCase().equals("bye")) {
				sentinel = false;
			} else {
				System.out.println("  ----------------------------------");
				System.out.println("	" + command);
				System.out.println("  ----------------------------------\n");
			}
		} while (sentinel);
		System.out.println("  ----------------------------------");
		System.out.println("	'Kay I'm headin' out");
		System.out.println("  ----------------------------------\n");
	}
}
