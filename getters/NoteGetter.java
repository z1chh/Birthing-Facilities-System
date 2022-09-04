package getters;

import java.util.Scanner;

/**
 * Provides the public static method getInput() that prompts the user to enter
 * an observation. User is asked to provide an input until the input is valid.
 * Returns the String corresponding to the input.
 *
 */
public class NoteGetter {
	private String aInput; // Store the input as a string.
	private boolean aValidInput = false; // aInput contains nothing (or an invalid value) as long as this boolean is
											// false.

	private Scanner scanner = new Scanner(System.in); // Scanner object to read user input.

	private static final NoteGetter NOTEGETTER = new NoteGetter(); // Singleton pattern. Object set
																	// as private since client only
																	// needs the getInput function.

	/**
	 * Private constructor to ensure Singleton design pattern.
	 */
	private NoteGetter() {
	}

	/**
	 * Prompts the user for input. this.aValidInt is set to false by default.
	 */
	private void promptInput() {
		// Prompt user for observations
		System.out.println("Please type your observation:");
		this.aInput = this.scanner.nextLine();
		this.aValidInput = false;
	}

	/**
	 * Checks if the input provided by this.promptInput() is a valid observation.
	 */
	private void validateInput() {
		// Check if the user entered nothing.
		if (this.aInput.length() == 0) {
			this.aValidInput = false;
			return;
		}

		// Check if the observation is less than or equal to 150 characters.
		if (this.aInput.length() > 150) {
			this.aValidInput = false;
			return;
		}

		// Note that because of the \0, the user can only input 149 characters.
		this.aValidInput = true;
	}

	/**
	 * Prompts the user for input until a valid observation is obtained.
	 * 
	 * @return the observations.
	 */
	public static String getInput() {
		// Get input
		NoteGetter.NOTEGETTER.promptInput();
		NoteGetter.NOTEGETTER.validateInput();

		// Re-prompt for input until it is valid
		while (!NoteGetter.NOTEGETTER.aValidInput) {
			System.out.println("Error: observations must not be empty or exceed 150 characters\n");
			NoteGetter.NOTEGETTER.promptInput();
			NoteGetter.NOTEGETTER.validateInput();
		}
		return NoteGetter.NOTEGETTER.aInput;
	}
}
