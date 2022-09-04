package getters;

import java.util.Scanner;

/**
 * Provides the public static method getInput() that prompts the user to enter
 * an integer. User is asked to provide an input until the input is either a
 * number or 'E'. Returns the integer corresponding to the input, or -1 if the
 * user wishes to exit, and 0 if the user wishes to go back to another date.
 *
 */
public class AppointmentGetter {
	private String aInput; // Store the input as a string.
	private int aInt; // Stores the integer entered by the user.
	private boolean aValidInt = false; // aInt contains nothing (or an invalid value) as long as this boolean is false.
	private boolean aExit = false; // True if the user wishes to exit.
	private boolean aDate = false; // True if the user wishes to go back to another date.

	private Scanner scanner = new Scanner(System.in); // Scanner object to read user input.

	private static final AppointmentGetter APPOINTMENTGETTER = new AppointmentGetter(); // Singleton pattern. Object set
																						// as private since client only
																						// needs the getInput function.

	/**
	 * Private constructor to ensure Singleton design pattern.
	 */
	private AppointmentGetter() {
	}

	/**
	 * Prompts the user for input. this.aValidInt is set to false by default.
	 */
	private void promptInput() {
		// Prompt user for practitioner ID
		System.out.println(
				"Enter the appointment number that you would like to work on.\t\t[E] to exit [D] to go back to another date:");
		this.aInput = this.scanner.nextLine();
		this.aValidInt = false;
	}

	/**
	 * Checks if the input provided by this.promptInput() is a valid integer or 'E'.
	 */
	private void validateInput(int numberOfAppointments) {
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

		// Check if the user wants to go back to another date.
		if (this.aInput.length() == 1 && (this.aInput.charAt(0) == 'D' || this.aInput.charAt(0) == 'd')) {
			this.aExit = false;
			this.aDate = true;
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
		this.aDate = false;
		this.aValidInt = true;

		this.aInt = Integer.parseInt(this.aInput);

		if (this.aInt < 1 || this.aInt > numberOfAppointments)
			this.aValidInt = false;
	}

	/**
	 * Prompts the user for input until a valid integer is obtained, or 'E'.
	 * 
	 * @return the appointment number or -1 for exit.
	 */
	public static int getInput(int numberOfAppointments) {
		// Get input
		AppointmentGetter.APPOINTMENTGETTER.promptInput();
		AppointmentGetter.APPOINTMENTGETTER.validateInput(numberOfAppointments);

		// Return -1 to exit
		if (AppointmentGetter.APPOINTMENTGETTER.aExit)
			return -1;

		if (AppointmentGetter.APPOINTMENTGETTER.aDate)
			return -2;

		// Re-prompt for input until it is valid
		while (!AppointmentGetter.APPOINTMENTGETTER.aValidInt) {
			System.out.println("Error: input must be a number\n");
			AppointmentGetter.APPOINTMENTGETTER.promptInput();
			AppointmentGetter.APPOINTMENTGETTER.validateInput(numberOfAppointments);
			if (AppointmentGetter.APPOINTMENTGETTER.aExit)
				return -1;
			if (AppointmentGetter.APPOINTMENTGETTER.aDate)
				return -2;
		}
		return AppointmentGetter.APPOINTMENTGETTER.aInt;
	}
}
