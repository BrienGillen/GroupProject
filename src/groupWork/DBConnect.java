package groupWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to create a connection to the database
 *
 */
public class DBConnect {

	// Variables used to connect to the database
	private static String username = "BrienAdmin";
	private static String password = "sickness";
	private static String port = "3306";
	private static String host = "west1-briendb.cef6inqq8lsv.eu-west-1.rds.amazonaws.com";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String dbName = "six_nations";
	private static String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + username + "&password="
			+ password;
	private static Connection con;

	// Empty main method on class
	public static void main(String[] args) {
	}

	/**
	 * Method used to return the connection to the database
	 * 
	 * @return con = DriverManager.getConnection(url, username, password)
	 */
	public Connection getConnection() {
		// Try for setting up the class for a driver
		try {

			// Setting up the driver (JDBC) to communicate with the database
			Class.forName(driver);

			// Try for setting up the connection to the database
			try {

				// Setting up the connection using the variables from above
				con = DriverManager.getConnection(url, username, password);

				// Catch to let the user know if the database connection fails
				// for any reason
			} catch (SQLException ex) {
				// log an exception. from example:
				System.out.println("Failed to create the database connection.");
			}

			// Catch to let the user know that the class was unable to set up,
			// most likely because the driver is not installed
		} catch (ClassNotFoundException ex) {
			// log an exception. for example:
			System.out.println("Driver not found.");
		}

		// Returning the connection for the class
		return con;

	}
}