package getters;

import java.util.Scanner;

/**
 * Provides the public static method getInput() that prompts the user to enter a
 * date. The date must be entered as the following format: YYYYMMDD User is
 * asked to provide an input until the input is either a number or 'E'. Returns
 * the Date corresponding to the input, or "Exit" if the user wishes to exit.
 *
 */
public class DateGetter {
	private String aInput; // Store the input as a string.
	private boolean aValidDate = false; // aDate contains nothing (or an invalid value) as long as this boolean is
										// false.
	private boolean aExit = false; // True if the user wishes to exit.

	private Scanner scanner = new Scanner(System.in); // Scanner object to read user input.

	private static final DateGetter DATEGETTER = new DateGetter(); // Singleton pattern. Object set as private since
																	// client only needs the getInput function.

	/**
	 * Private constructor to ensure Singleton design pattern.
	 */
	private DateGetter() {
	}

	/**
	 * Prompts the user for input. this.aValidDate is set to false by default.
	 */
	private void promptInput() {
		// Prompt user for date for appointment list
		System.out.println("Please enter the date for appointment list [E] to exit:");
		this.aInput = this.scanner.nextLine();
		this.aValidDate = false;
	}

	/**
	 * Checks if the input provided by this.promptInput() is a valid date or 'E'.
	 */
	private void validateInput() {
		// Check if the user entered nothing.
		if (this.aInput.length() == 0) {
			this.aValidDate = false;
			return;
		}

		// Check if the user wants to exit the program.
		if (this.aInput.length() == 1 && (this.aInput.charAt(0) == 'E' || this.aInput.charAt(0) == 'e')) {
			this.aExit = true;
			return;
		}

		// Check if the given input is a valid date (integer of length 10).
		// First check if the date format is valid.
		if (this.aInput.length() != 10) {
			this.aValidDate = false;
			return;
		}

		for (int i = 0; i < this.aInput.length(); i++) {
			if (i == 4 || i == 7)
				continue;
			if (this.aInput.charAt(i) < 48 || this.aInput.charAt(i) > 57) {
				this.aValidDate = false;
				return;
			}
		}

		if (this.aInput.charAt(4) != 45 || this.aInput.charAt(7) != 45) {
			this.aValidDate = false;
			return;
		}

		// Then check if the date is valid.
		String tmp = "";
		tmp += this.aInput.charAt(0);
		tmp += this.aInput.charAt(1);
		tmp += this.aInput.charAt(2);
		tmp += this.aInput.charAt(3);
		int year = Integer.parseInt(tmp);

		tmp = "";
		tmp += this.aInput.charAt(5);
		tmp += this.aInput.charAt(6);
		int month = Integer.parseInt(tmp);

		tmp = "";
		tmp += this.aInput.charAt(8);
		tmp += this.aInput.charAt(9);
		int day = Integer.parseInt(tmp);

		// Year
		if (year < 0 || year > 2100) {
			this.aValidDate = false;
			return;
		}

		// Month and Day
		switch (month) {
		case 1:
			if (day < 1 || day > 31) {
				this.aValidDate = false;
				return;
			}
			break;
		/*
		 * February: There is a 29th day every four years. However, if the year is also
		 * divisible by 100, then there are only 28 days. However, if the year is also
		 * divisible by 400, then there still is a 29th day.
		 * 
		 * For instance, Feb 2001 obviously has 28 days. 1900 is divisible by 4, but Feb
		 * 1900 doesn't have 29 days since 1900 is also divisible by 100 (and not 400).
		 * Finally, 2000 is divisible by 4. Even though it is also divisible by 100, Feb
		 * 2000 still has 29 days since it's divisible by 400.
		 */
		case 2:
			if (year % 4 != 0) {
				if (day < 1 || day > 28) {
					this.aValidDate = false;
					return;
				}
				break;
			} else if (year % 100 != 0) {
				if (day < 1 || day > 29) {
					this.aValidDate = false;
					return;
				}
				break;
			} else if (year % 400 != 0) {
				if (day < 1 || day > 28) {
					this.aValidDate = false;
					return;
				}
				break;
			} else {
				if (day < 1 || day > 29) {
					this.aValidDate = false;
					return;
				}
				break;
			}
		case 3:
			if (day < 1 || day > 31) {
				this.aValidDate = false;
				return;
			}
			break;
		case 4:
			if (day < 1 || day > 30) {
				this.aValidDate = false;
				return;
			}
			break;
		case 5:
			if (day < 1 || day > 31) {
				this.aValidDate = false;
				return;
			}
			break;
		case 6:
			if (day < 1 || day > 30) {
				this.aValidDate = false;
				return;
			}
			break;
		case 7:
			if (day < 1 || day > 31) {
				this.aValidDate = false;
				return;
			}
			break;
		case 8:
			if (day < 1 || day > 31) {
				this.aValidDate = false;
				return;
			}
			break;
		case 9:
			if (day < 1 || day > 30) {
				this.aValidDate = false;
				return;
			}
			break;
		case 10:
			if (day < 1 || day > 31) {
				this.aValidDate = false;
				return;
			}
			break;
		case 11:
			if (day < 1 || day > 30) {
				this.aValidDate = false;
				return;
			}
			break;
		case 12:
			if (day < 1 || day > 31) {
				this.aValidDate = false;
				return;
			}
			break;
		// Invalid month
		default:
			this.aValidDate = false;
			return;
		}

		// Month and Date are valid if we found a case and broke out of it (did not go
		// in the if statement).
		this.aExit = false;
		this.aValidDate = true;
	}

	/**
	 * Prompts the user for input until a valid integer is obtained, or 'E'.
	 * 
	 * @return the Date or "Exit" for exit.
	 */
	public static String getInput() {
		// Get input
		DateGetter.DATEGETTER.promptInput();
		DateGetter.DATEGETTER.validateInput();

		// Return "Exit" to exit
		if (DateGetter.DATEGETTER.aExit)
			return "Exit";

		// Re-prompt for input until it is valid
		while (!DateGetter.DATEGETTER.aValidDate) {
			System.out.println("Error: invalid input or date format: YYYY-MM-DD\n");
			DateGetter.DATEGETTER.promptInput();
			DateGetter.DATEGETTER.validateInput();
			if (DateGetter.DATEGETTER.aExit)
				return "Exit";
		}
		return DateGetter.DATEGETTER.aInput;
	}
}
