package getters;

import java.util.Scanner;

/**
 * Provides the public static method getInput() that prompts the user to enter a
 * test type. User is asked to provide an input until the input is valid.
 * Returns the String corresponding to the input.
 * 
 * Note that TestGetter just ensures the input string has length 0 < x < 30, but
 * does not make sure the test type is valid. For instance, one could input the
 * test type as '19784g9asd' and it would still be valid.
 *
 */
public class TestGetter {
	private String aInput; // Store the input as a string.
	private boolean aValidInput = false; // aInput contains nothing (or an invalid value) as long as this boolean is
											// false.

	private Scanner scanner = new Scanner(System.in); // Scanner object to read user input.

	private static final TestGetter TESTGETTER = new TestGetter(); // Singleton pattern. Object set
																	// as private since client only
																	// needs the getInput function.

	/**
	 * Private constructor to ensure Singleton design pattern.
	 */
	private TestGetter() {
	}

	/**
	 * Prompts the user for input. this.aValidInt is set to false by default.
	 */
	private void promptInput() {
		// Prompt user for observations
		System.out.println("Please enter the type of test:");
		this.aInput = this.scanner.nextLine();
		this.aValidInput = false;
	}

	/**
	 * Checks if the input provided by this.promptInput() is a valid string.
	 */
	private void validateInput() {
		// Check if the user entered nothing.
		if (this.aInput.length() == 0) {
			this.aValidInput = false;
			return;
		}

		// Check if the observation is less than or equal to 30 characters.
		if (this.aInput.length() > 30) {
			this.aValidInput = false;
			return;
		}

		// Note that because of the \0, the user can only input 29 characters.
		this.aValidInput = true;
	}

	/**
	 * Prompts the user for input until a valid string is obtained.
	 * 
	 * @return the test type.
	 */
	public static String getInput() {
		// Get input
		TestGetter.TESTGETTER.promptInput();
		TestGetter.TESTGETTER.validateInput();

		// Re-prompt for input until it is valid
		while (!TestGetter.TESTGETTER.aValidInput) {
			System.out.println("Error: test type must not be empty or exceed 30 characters\n");
			TestGetter.TESTGETTER.promptInput();
			TestGetter.TESTGETTER.validateInput();
		}
		return TestGetter.TESTGETTER.aInput;
	}
}
