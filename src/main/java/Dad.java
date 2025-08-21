import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Dad {
	public static void main(String[] args) {
		System.out.println("  ----------------------------------");
		System.out.println("	Hello I'm Dad");
		System.out.println("	Whaddya want?");
		System.out.println("  ----------------------------------\n");
		
		List<String> list = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);
		boolean sentinel = true;
		do {
			String command = scanner.nextLine();
			if (command.toLowerCase().equals("bye")) {
				sentinel = false;
			} else if (command.toLowerCase().equals("list")) {
				System.out.println("  ----------------------------------");	
				for (int i = 0; i < list.size(); i++) {
					System.out.println("	" + (i+1) + ": " + list.get(i));
				}
				System.out.println("  ----------------------------------\n");
			} else {
				System.out.println("  ----------------------------------");
				System.out.println("	added: " + command);
				System.out.println("  ----------------------------------\n");
				list.add(command);
			}
		} while (sentinel);
		System.out.println("  ----------------------------------");
		System.out.println("	'Kay I'm headin' out");
		System.out.println("  ----------------------------------\n");
	}
}
