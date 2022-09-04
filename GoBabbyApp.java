/**
 * @author Zi Chen Hu
 * https://github.com/z1chh
 * 
 * Birthing Facilities System
 */

import java.sql.*;
import java.util.ArrayList;

import getters.*;

public class GoBabbyApp {
	// Practitioner ID
	private int aPID;

	// Appointments at a given date
	private String aDate;
	private int aNumApps;
	private String aAppointments;
	private final ArrayList<Integer> aAppointmentIDs = new ArrayList<>();
	private final ArrayList<Integer> aPregnancyIDs = new ArrayList<>();
	private final ArrayList<Integer> aMomIDs = new ArrayList<>();
	private final ArrayList<String> aMomNames = new ArrayList<>();
	private final ArrayList<String> aMomInfo = new ArrayList<>();

	// Appointment info
	private int aAppointmentNum;

	// Work info
	private int aChoice;
	private int aAppointmentID;
	private int aPregnancyID;
	private int aMomID;
	private String aMomName;

	public static void main(String[] args) throws SQLException {
		GoBabbyApp g = new GoBabbyApp();
		g.execute();
	}

	public void execute() throws SQLException {
		// Prompt user for a valid PID.
		this.getPID();

		boolean dateLoop = true;
		boolean chooseAppLoop = true;
		boolean appLoop = true;

		while (dateLoop) {
			// Prompt user for a date for the appointments.
			this.getAppointments();
			while (this.aNumApps == 0) {
				this.getAppointments();
			}

			while (chooseAppLoop) {
				// Prompt user for an appointment.
				this.getAppointment();

				// Exit code -2: Enter a new date
				if (this.aAppointmentNum == -2)
					break;

				// Execute options 1 to 4 until user wishes to go back to the appointments.
				while (appLoop) {
					this.getOption();

					if (this.aChoice == 1)
						this.reviewNotes();
					else if (this.aChoice == 2)
						this.reviewTests();
					else if (this.aChoice == 3)
						this.addNote();
					else if (this.aChoice == 4)
						this.prescribeTest();
					else // (this.aChoice == 5)
						break;
				}
			}

		}
	}

	private void getPID() throws SQLException {
		int sqlCode = 0; // Variable to hold SQLCODE
		String sqlState = "00000"; // Variable to hold SQLSTATE

		// Register the driver so we can use it.
		try {
			DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		// This is the url I used for DB2. Can be updated to connect to your own DB2
		String url = "jdbc:db2://winter2022-comp421.cs.mcgill.ca:50000/cs421";

		String your_userid = null;
		String your_password = null;
		// Set your username and password in the shell environment in the Unix (as shown below) and read it from there.
		// $ export SOCSPASSWD=yoursocspasswd
		// Or simply replace the null values with your credentials.
		if (your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null) {
			System.err.println("Error!! do not have a user to connect to the database!");
			System.exit(1);
		}
		if (your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null) {
			System.err.println("Error!! do not have a password to connect to the database!");
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(url, your_userid, your_password);
		Statement statement = con.createStatement();

		// Prompt user for practitioner ID.
		this.aPID = IDGetter.getInput();
		if (this.aPID == -1) {
			System.out.println("\nSuccessfully exited.");
			System.exit(1);
		}

		// Check that there was one midwife found.
		try {
			String querySQL = String.format("SELECT COUNT(*) from midwife WHERE pid = %d", this.aPID);
			java.sql.ResultSet rs = statement.executeQuery(querySQL);

			rs.next();
			int pidFound = rs.getInt(1);

			// If there is not exactly one midwife, prompt for pid again.
			while (pidFound != 1) {
				try {
					System.out.println("Error: practitioner id invalid.\n");
					this.aPID = IDGetter.getInput();
					if (this.aPID == -1) {
						System.out.println("\nSuccessfully exited.");
						System.exit(1);
					}
					querySQL = String.format("SELECT COUNT(*) FROM midwife " + "WHERE pid = %d", this.aPID);
					rs = statement.executeQuery(querySQL);

					rs.next();
					pidFound = rs.getInt(1);
				} catch (SQLException e) {
					sqlCode = e.getErrorCode(); // Get SQLCODE
					sqlState = e.getSQLState(); // Get SQLSTATE

					// Your code to handle errors comes here;
					// something more meaningful than a print would be good
					System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
					System.out.println(e);
				}
			}
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			System.out.println(e);
		}

		// Finally but importantly close the statement and connection
		statement.close();
		con.close();
		System.out.println();
	}

	private void getAppointments() throws SQLException {
		int sqlCode = 0; // Variable to hold SQLCODE
		String sqlState = "00000"; // Variable to hold SQLSTATE

		// Register the driver. You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		// This is the url you must use for DB2.
		// Note: This url may not valid now ! Check for the correct year and semester
		// and server name.
		String url = "jdbc:db2://winter2022-comp421.cs.mcgill.ca:50000/cs421";

		// REMEMBER to remove your user id and password before submitting your code!!
		String your_userid = null;
		String your_password = null;
		// AS AN ALTERNATIVE, you can just set your password in the shell environment in
		// the Unix (as shown below) and read it from there.
		// $ export SOCSPASSWD=yoursocspasswd
		if (your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null) {
			System.err.println("Error!! do not have a user to connect to the database!");
			System.exit(1);
		}
		if (your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null) {
			System.err.println("Error!! do not have a password to connect to the database!");
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(url, your_userid, your_password);
		Statement statement = con.createStatement();

		// Now, the input is a valid PID with exactly one midwife associated to it.
		// Prompt user for date.
		this.aDate = DateGetter.getInput();
		if (this.aDate.equals("Exit")) {
			System.out.println("\nSuccessfully exited.");
			System.exit(1);
		}

		// If there are appointments on that date, retrieve and display them.
		this.aAppointments = "";
		this.aNumApps = 0;
		this.aAppointmentIDs.clear();
		this.aPregnancyIDs.clear();
		this.aMomIDs.clear();
		this.aMomNames.clear();
		this.aMomInfo.clear();

		// Primary
		try {
			String querySQL = String.format("WITH fullInfo(app_time, name, qchcardid, pid, aid) AS\r\n"
					+ "(WITH mothersAppOnDate(app_time, qchcardid, pid, aid) AS\r\n"
					+ "(WITH appOnDate(pid, cid, app_time, aid) AS\r\n" + "(WITH pregnancies(pid, cid) AS\r\n"
					+ "(SELECT pid, cid FROM pregnancy\r\n" + "WHERE first_m = %d)\r\n"
					+ "SELECT A.pid, cid, app_time, aid FROM pregnancies P JOIN appointment A\r\n"
					+ "ON P.pid = A.pid\r\n" + "WHERE app_date = DATE'%s')\r\n"
					+ "SELECT app_time, qchcardid, pid, aid FROM appOnDate A JOIN couple C\r\n"
					+ "ON A.cid = C.cid)\r\n"
					+ "SELECT app_time, name, A.qchcardid, pid, aid FROM mothersAppOnDate A JOIN mother M\r\n"
					+ "ON A.qchcardid = M.qchcardid)\r\n"
					+ "SELECT app_time, 'P' AS midwife_pos, name, qchcardid, pid, aid FROM fullInfo\r\n"
					+ "ORDER BY app_time", this.aPID, this.aDate);
			java.sql.ResultSet rs = statement.executeQuery(querySQL);

			while (rs.next()) {
				this.aNumApps = this.aNumApps + 1;
				Time t = rs.getTime(1);
				String pos = rs.getString(2);
				String name = rs.getString(3);
				int qchcardid = rs.getInt(4);
				int pid = rs.getInt(5);
				int aid = rs.getInt(6);
				this.aAppointmentIDs.add(aid);
				this.aPregnancyIDs.add(pid);
				this.aMomIDs.add(qchcardid);
				this.aMomNames.add(name);
				this.aMomInfo.add(String.format("%s\t%d", name, qchcardid));
				this.aAppointments = this.aAppointments
						+ String.format("%d:\t%s\t%s\t%s\t%d\n", this.aNumApps, t.toString(), pos, name, qchcardid);
			}
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			System.out.println(e);
		}

		// Backup
		try {
			String querySQL = String.format("WITH fullInfo(app_time, name, qchcardid, pid, aid) AS\r\n"
					+ "(WITH mothersAppOnDate(app_time, qchcardid, pid, aid) AS\r\n"
					+ "(WITH appOnDate(pid, cid, app_time, aid) AS\r\n" + "(WITH pregnancies(pid, cid) AS\r\n"
					+ "(SELECT pid, cid FROM pregnancy\r\n" + "WHERE second_m = %d)\r\n"
					+ "SELECT A.pid, cid, app_time, aid FROM pregnancies P JOIN appointment A\r\n"
					+ "ON P.pid = A.pid\r\n" + "WHERE app_date = DATE'%s')\r\n"
					+ "SELECT app_time, qchcardid, pid, aid FROM appOnDate A JOIN couple C\r\n"
					+ "ON A.cid = C.cid)\r\n"
					+ "SELECT app_time, name, A.qchcardid, pid, aid FROM mothersAppOnDate A JOIN mother M\r\n"
					+ "ON A.qchcardid = M.qchcardid)\r\n"
					+ "SELECT app_time, 'B' AS midwife_pos, name, qchcardid, pid, aid FROM fullInfo\r\n"
					+ "ORDER BY app_time", this.aPID, this.aDate);
			java.sql.ResultSet rs = statement.executeQuery(querySQL);

			while (rs.next()) {
				this.aNumApps = this.aNumApps + 1;
				Time t = rs.getTime(1);
				String pos = rs.getString(2);
				String name = rs.getString(3);
				int qchcardid = rs.getInt(4);
				int pid = rs.getInt(5);
				int aid = rs.getInt(6);
				this.aAppointmentIDs.add(aid);
				this.aPregnancyIDs.add(pid);
				this.aMomIDs.add(qchcardid);
				this.aMomNames.add(name);
				this.aMomInfo.add(String.format("%s\t%d", name, qchcardid));
				this.aAppointments = this.aAppointments
						+ String.format("%d:\t%s\t%s\t%s\t%d\n", this.aNumApps, t.toString(), pos, name, qchcardid);
			}
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			System.out.println(e);
		}

		if (this.aNumApps == 0) {
			System.out.println("Error: no appointments on that date\n");
		} else
			System.out.println(
					String.format("\nYou have the following appointments on %s\n%s", this.aDate, this.aAppointments));

		// Finally but importantly close the statement and connection
		statement.close();
		con.close();
	}

	private void getAppointment() {
		// Prompt user for appointment number.
		this.aAppointmentNum = AppointmentGetter.getInput(this.aNumApps);
		if (this.aAppointmentNum == -1) {
			System.out.println("\nSuccessfully exited.");
			System.exit(1);
		}

		// Code -2: Go back to choosing a date
		if (this.aAppointmentNum == -2) {
			System.out.println();
			return;
		}
		this.aAppointmentID = this.aAppointmentIDs.get(this.aAppointmentNum - 1);
		this.aPregnancyID = this.aPregnancyIDs.get(this.aAppointmentNum - 1);
		this.aMomID = this.aMomIDs.get(this.aAppointmentNum - 1);
		this.aMomName = this.aMomNames.get(this.aAppointmentNum - 1);
	}

	private void getOption() {
		// Print information regarding that appointment.
		System.out.println(String.format("For %s\n", this.aMomInfo.get(this.aAppointmentNum - 1)));
		System.out.println("1.\tReview notes");
		System.out.println("2.\tReview tests");
		System.out.println("3.\tAdd a note");
		System.out.println("4.\tPrescribe a test");
		System.out.println("5.\tGo back to the appointments.");
		System.out.println("\nEnter your choice:");

		this.aChoice = ChoiceGetter.getInput();
		if (this.aChoice == 5) {
			System.out.println(
					String.format("\nYou have the following appointments on %s\n%s", this.aDate, this.aAppointments));
			return;
		}
	}

	private void reviewNotes() throws SQLException {
		int sqlCode = 0; // Variable to hold SQLCODE
		String sqlState = "00000"; // Variable to hold SQLSTATE

		// Register the driver. You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		// This is the url you must use for DB2.
		// Note: This url may not valid now ! Check for the correct year and semester
		// and server name.
		String url = "jdbc:db2://winter2022-comp421.cs.mcgill.ca:50000/cs421";

		// REMEMBER to remove your user id and password before submitting your code!!
		String your_userid = null;
		String your_password = null;
		// AS AN ALTERNATIVE, you can just set your password in the shell environment in
		// the Unix (as shown below) and read it from there.
		// $ export SOCSPASSWD=yoursocspasswd
		if (your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null) {
			System.err.println("Error!! do not have a user to connect to the database!");
			System.exit(1);
		}
		if (your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null) {
			System.err.println("Error!! do not have a password to connect to the database!");
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(url, your_userid, your_password);
		Statement statement = con.createStatement();

		// Querying the notes for the given pregnancy.
		try {
			System.out.println(String.format("\nNotes for %s's pregnancy:", this.aMomName));
			String querySQL = String.format("WITH appointments(aid) AS\r\n"
					+ "(SELECT aid FROM pregnancy P JOIN appointment A\r\n" + "ON P.pid = A.pid\r\n"
					+ "WHERE P.pid = %d)\r\n"
					+ "SELECT date, time, LEFT(observations, 50) AS observations FROM appointments A JOIN note N\r\n"
					+ "ON A.aid = N.aid\r\n" + "ORDER BY date DESC, time DESC", this.aPregnancyID);
			java.sql.ResultSet rs = statement.executeQuery(querySQL);

			while (rs.next()) {
				Date date = rs.getDate(1);
				Time time = rs.getTime(2);
				String observations = rs.getString(3);
				System.out.println(String.format("%s\t%s\t%s", date.toString(), time.toString(), observations));
			}
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			System.out.println(e);
		}
		System.out.println("\n");
	}

	private void reviewTests() throws SQLException {
		int sqlCode = 0; // Variable to hold SQLCODE
		String sqlState = "00000"; // Variable to hold SQLSTATE

		// Register the driver. You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		// This is the url you must use for DB2.
		// Note: This url may not valid now ! Check for the correct year and semester
		// and server name.
		String url = "jdbc:db2://winter2022-comp421.cs.mcgill.ca:50000/cs421";

		// REMEMBER to remove your user id and password before submitting your code!!
		String your_userid = null;
		String your_password = null;
		// AS AN ALTERNATIVE, you can just set your password in the shell environment in
		// the Unix (as shown below) and read it from there.
		// $ export SOCSPASSWD=yoursocspasswd
		if (your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null) {
			System.err.println("Error!! do not have a user to connect to the database!");
			System.exit(1);
		}
		if (your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null) {
			System.err.println("Error!! do not have a password to connect to the database!");
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(url, your_userid, your_password);
		Statement statement = con.createStatement();

		// Querying the tests for the given pregnancy.
		try {
			System.out.println(String.format("\nTests for %s's pregnancy:", this.aMomName));
			String querySQL = String.format(
					"SELECT prescription_date, type, COALESCE (LEFT(results, 50), 'PENDING') FROM Pregnancy P JOIN test T\r\n"
							+ "ON P.pid = T.pid\r\n" + "WHERE P.pid = %d AND T.qchcardid = %d",
					this.aPregnancyID, this.aMomID);
			java.sql.ResultSet rs = statement.executeQuery(querySQL);

			while (rs.next()) {
				Date date = rs.getDate(1);
				String type = rs.getString(2);
				String results = rs.getString(3);
				System.out.println(String.format("%s\t[%s]\t%s", date.toString(), type.toString(), results));
			}
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			System.out.println(e);
		}
		System.out.println("\n");
	}

	private void addNote() throws SQLException {
		int sqlCode = 0; // Variable to hold SQLCODE
		String sqlState = "00000"; // Variable to hold SQLSTATE

		// Register the driver. You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		// This is the url you must use for DB2.
		// Note: This url may not valid now ! Check for the correct year and semester
		// and server name.
		String url = "jdbc:db2://winter2022-comp421.cs.mcgill.ca:50000/cs421";

		// REMEMBER to remove your user id and password before submitting your code!!
		String your_userid = null;
		String your_password = null;
		// AS AN ALTERNATIVE, you can just set your password in the shell environment in
		// the Unix (as shown below) and read it from there.
		// $ export SOCSPASSWD=yoursocspasswd
		if (your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null) {
			System.err.println("Error!! do not have a user to connect to the database!");
			System.exit(1);
		}
		if (your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null) {
			System.err.println("Error!! do not have a password to connect to the database!");
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(url, your_userid, your_password);
		Statement statement = con.createStatement();

		// Updating by adding a new note.
		try {
			String observations = NoteGetter.getInput();
			String querySQL = String.format(
					"INSERT INTO note (aid, date, time, observations)\r\n" + "VALUES\r\n"
							+ "(%d, CAST(NOW() AS DATE), CAST(NOW() AS TIME), '%s')",
					this.aAppointmentID, observations);
			statement.executeUpdate(querySQL);
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			System.out.println(e);
		}
		System.out.println("\n");
	}

	private void prescribeTest() throws SQLException {
		int sqlCode = 0; // Variable to hold SQLCODE
		String sqlState = "00000"; // Variable to hold SQLSTATE

		// Register the driver. You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		// This is the url you must use for DB2.
		// Note: This url may not valid now ! Check for the correct year and semester
		// and server name.
		String url = "jdbc:db2://winter2022-comp421.cs.mcgill.ca:50000/cs421";

		// REMEMBER to remove your user id and password before submitting your code!!
		String your_userid = null;
		String your_password = null;
		// AS AN ALTERNATIVE, you can just set your password in the shell environment in
		// the Unix (as shown below) and read it from there.
		// $ export SOCSPASSWD=yoursocspasswd
		if (your_userid == null && (your_userid = System.getenv("SOCSUSER")) == null) {
			System.err.println("Error!! do not have a user to connect to the database!");
			System.exit(1);
		}
		if (your_password == null && (your_password = System.getenv("SOCSPASSWD")) == null) {
			System.err.println("Error!! do not have a password to connect to the database!");
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(url, your_userid, your_password);
		Statement statement = con.createStatement();

		// Updating by prescribing (adding) a new test.
		try {
			String test_type = TestGetter.getInput();
			String querySQL = String.format(
					"INSERT INTO test (tid, techid, qchcardid, pid, type, sample_date, prescription_date)\r\n"
							+ "VALUES\r\n"
							+ "((SELECT MAX(tid) FROM test) + 1, 1, %d, %d, '%s', CAST(NOW() AS DATE), CAST(NOW() AS DATE))",
					this.aMomID, this.aPregnancyID, test_type);
			statement.executeUpdate(querySQL);
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
			System.out.println(e);
		}
		System.out.println("\n");
	}

}
