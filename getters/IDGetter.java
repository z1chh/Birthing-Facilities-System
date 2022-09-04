package getters;

import java.util.Scanner;

/**
 * Provides the public static method getInput() that prompts the user to enter
 * an integer. User is asked to provide an input until the input is either a
 * number or 'E'. Returns the integer corresponding to the input, or -1 if the
 * user wishes to exit.
 *
 */
public class IDGetter {
	private String aInput; // Store the input as a string.
	private int aID; // Stores the integer entered by the user.
	private boolean aValidInt = false; // aID contains nothing (or an invalid value) as long as this boolean is false.
	private boolean aExit = false; // True if the user wishes to exit.

	private Scanner scanner = new Scanner(System.in); // Scanner object to read user input.

	private static final IDGetter IDGETTER = new IDGetter(); // Singleton pattern. Object set as private since client
																// only needs the getInput function.

	/**
	 * Private constructor to ensure Singleton design pattern.
	 */
	private IDGetter() {
	}

	/**
	 * Prompts the user for input. this.aValidInt is set to false by default.
	 */
	private void promptInput() {
		// Prompt user for practitioner ID
		System.out.println("Please enter your practitioner id [E] to exit:");
		this.aInput = this.scanner.nextLine();
		this.aValidInt = false;
	}

	/**
	 * Checks if the input provided by this.promptInput() is a valid integer or 'E'.
	 */
	private void validateInput() {
		// Check if the user entered nothing.
		if (this.aInput.length() == 0) {
			this.aValidInt = false;
			return;
		}

		// Check if the user wants to exit the program.
		if (this.aInput.length() == 1 && (this.aInput.charAt(0) == 'E' || this.aInput.charAt(0) == 'e')) {
			this.aExit = true;
			return;
		}

		// Check if the given input is an integer.
		for (int i = 0; i < this.aInput.length(); i++) {
			if (this.aInput.charAt(i) < 48 || this.aInput.charAt(i) > 57) {
				this.aValidInt = false;
				return;
			}
		}
		this.aExit = false;
		this.aValidInt = true;
		this.aID = Integer.parseInt(this.aInput);
	}

	/**
	 * Prompts the user for input until a valid integer is obtained, or 'E'.
	 * 
	 * @return the practitioner ID or -1 for exit. Note this program assumes the
	 *         practitioner ID must be positive (including 0).
	 */
	public static int getInput() {
		// Get input
		IDGetter.IDGETTER.promptInput();
		IDGetter.IDGETTER.validateInput();

		// Return -1 to exit
		if (IDGetter.IDGETTER.aExit)
			return -1;

		// Re-prompt for input until it is valid
		while (!IDGetter.IDGETTER.aValidInt) {
			System.out.println("Error: input must be a number\n");
			IDGetter.IDGETTER.promptInput();
			IDGetter.IDGETTER.validateInput();
			if (IDGetter.IDGETTER.aExit)
				return -1;
		}
		return IDGetter.IDGETTER.aID;
	}
}
