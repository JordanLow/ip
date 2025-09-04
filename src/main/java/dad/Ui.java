package dad;

class Ui {

	/**
	 * Prints the intro to output	 
	 */
	public static void printIntro() {
		System.out.println("	Hello I'm Dad");
		System.out.println("	Whaddya want?");	
	}

	/**
	 * Prints the outro to output
	 */
	public static void printOutro() {
		System.out.println("	'Kay I'm headin' out");
	}

	/**
	 * Prints a line to output, with a newline if it's a closing line
	 *
	 * @param isCloser A boolean for if the intended line is an opening line or a closing line
	 */
	public static void printLine(boolean isCloser) {
		if (isCloser) {
			System.out.println("  ----------------------------------\n");
		} else {
			System.out.println("  ----------------------------------");
		}
	}

	/**
	 * Prints the given input to output
	 *
	 * @param contents The String to be printed to output
	 */
	public static void print(String contents) {
		System.out.println(contents);
	}
}


