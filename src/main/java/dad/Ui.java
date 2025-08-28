package dad;

class Ui {

	public static void printIntro() {
		System.out.println("	Hello I'm Dad");
		System.out.println("	Whaddya want?");	
	}

	public static void printOutro() {
		System.out.println("	'Kay I'm headin' out");
	}

	public static void printLine(boolean isCloser) {
		if (isCloser) {
			System.out.println("  ----------------------------------\n");
		} else {
			System.out.println("  ----------------------------------");
		}
	}

	public static void print(String contents) {
		System.out.println(contents);
	}
}


