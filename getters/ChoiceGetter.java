package getters;

import java.util.Scanner;

public class ChoiceGetter {

	private String aInput; // Store the input as a string.
	private int aInt; // Stores the choice entered by the user.
	private boolean aValidInt = false; // aInt contains nothing (or an invalid value) as long as this boolean is false.

	private Scanner scanner = new Scanner(System.in); // Scanner object to read user input.

	private static final ChoiceGetter CHOICEGETTER = new ChoiceGetter(); // Singleton pattern. Object set
																			// as private since client only needs the
																			// getInput function.

	/**
	 * Private constructor to ensure Singleton design pattern.
	 */
	private ChoiceGetter() {
	}

	/**
	 * Prompts the user for input. this.aValidInt is set to false by default.
	 */
	private void promptInput() {
		// Prompt user for choice
		this.aInput = this.scanner.nextLine();
		this.aValidInt = false;
	}

	/**
	 * Checks if the input provided by this.promptInput() is a valid integer between
	 * 1 and 5 (included).
	 */
	private void validateInput() {
		// Check if the user entered nothing.
		if (this.aInput.length() == 0) {
			this.aValidInt = false;
			return;
		}

		// Check if the user entered a single digit between 1 and 5.
		if (this.aInput.length() == 1 && this.aInput.charAt(0) > 48 && this.aInput.charAt(0) < 54) {
			this.aValidInt = true;
			this.aInt = Integer.parseInt(this.aInput);
			return;
		}

		// Otherwise, the input is invalid.
		this.aValidInt = false;
	}

	/**
	 * Prompts the user for input until a valid integer between 1 and 5 (included).
	 * 
	 * @return the choice.
	 */
	public static int getInput() {
		// Get input
		ChoiceGetter.CHOICEGETTER.promptInput();
		ChoiceGetter.CHOICEGETTER.validateInput();

		// Re-prompt for input until it is valid
		while (!ChoiceGetter.CHOICEGETTER.aValidInt) {
			System.out.println("Error: input must be a number between 1 and 5\n");
			ChoiceGetter.CHOICEGETTER.promptInput();
			ChoiceGetter.CHOICEGETTER.validateInput();
		}
		return ChoiceGetter.CHOICEGETTER.aInt;
	}
}
