package groupWork;

//Imports needed for this class

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class used to set up the uploading of the data for the fixtures from a text
 * file, where the user can upload round by round
 *
 */
public class MainUpload {

	/**
	 * Creating Database connection object
	 */
	private final static DBConnect db = new DBConnect();

	/**
	 * Method to upload results from Round 1 txt file to database
	 * 
	 */
	public void bulkUploadRound1() {

		// console output to user
		System.out.println();
		System.out.println("Uploading the scores from text file for round 1...");
		System.out.println();

		// instantiating the Connection variable con
		Connection con;
		con = db.getConnection();

		// Auto Generated catch block around the SQL statement to catch SQL
		// errors
		try {

			// variables to be used for writing the SQL statement
			String query;
			PreparedStatement statement;

			// SQL statement to update the matches table and columns
			// SQL statement will update each value to ?
			// ? refers to the input value contained in the FOR loop setInt
			// below
			query = "UPDATE matches SET home_scores_tries = ?, away_scores_tries = ?,  home_penalties = ?, away_penalties = ?, home_conversion = ?, away_conversion = ? WHERE match_id = ?";

			// creating a connection between our SQL query and the Database
			statement = con.prepareStatement(query);

			/**
			 * Array list from Scores class called listScores and reading from
			 * text file
			 */
			ArrayList<Score> listScores = getListScoresFromTextFile(
					"/Users/davidgilen/Documents/workspace/GroupProject/Round_1.txt");

			// for loop which will cycle through the above ArrayList
			// This will then set the above "?" symbol in the SQL query to the
			// appropriate value from the text file
			for (int i = 0; i < listScores.size(); i++) {

				statement.setInt(1, listScores.get(i).getHome_scores_tries());
				statement.setInt(2, listScores.get(i).getAway_scores_tries());
				statement.setInt(3, listScores.get(i).getHome_penalties());
				statement.setInt(4, listScores.get(i).getAway_penalties());

				// if statement which provides basic validition for the home
				// team
				// ensures that the user cannot have a value for conversions
				// exceeding the value for tries
				if (listScores.get(i).getHome_conversion() > listScores.get(i).getHome_scores_tries()) {
					statement.setInt(5, 0);

					// else statement which will then set the value for
					// Home_conversion
				} else {
					statement.setInt(5, listScores.get(i).getHome_conversion());
				}
				// if statement which provides basic validation for the away
				// team
				// ensures that the user cannot have a value for conversions
				// exceeding the value for tries
				if (listScores.get(i).getAway_conversion() > listScores.get(i).getAway_scores_tries()) {
					statement.setInt(6, 0);

					// else statement which will then set the value for
					// Away_conversion
				} else {
					statement.setInt(6, listScores.get(i).getAway_conversion());
				}
				statement.setInt(7, listScores.get(i).getMatch_id());

				// updating the database with the new results set
				statement.executeUpdate();

			}

			// Closing the connection resource to the database
			con.close();

			// End of auto generated try catch
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to bulk upload results from Round 2 txt file to database
	 */
	public void bulkUploadRound2() {

		// console output to user
		System.out.println("Uploading the scores from text file for round 2...");
		System.out.println();

		// Auto Generated catch block around the SQL statement to catch SQL
		// errors
		try {

			// instantiating the Connection variable con
			Connection con;
			con = db.getConnection();

			// variables to be used for writing the SQL statement
			String query;
			PreparedStatement statement;

			// SQL statement to update the matches table and columns
			// SQL statement will update each value to ?
			// ? refers to the input value contained in the FOR loop setInt
			// below
			query = "UPDATE matches SET home_scores_tries = ?, away_scores_tries = ?,  home_penalties = ?, away_penalties = ?, home_conversion = ?, away_conversion = ? WHERE match_id = ?";

			// creating a connection between our SQL query and the Database
			statement = con.prepareStatement(query);

			/**
			 * Array list from Scores class called listScores and reading from
			 * text file
			 */
			ArrayList<Score> listScores = getListScoresFromTextFile(
					"/Users/davidgilen/Documents/workspace/GroupProject/Round_2.txt");

			// for loop which will cycle through the above ArrayList
			// This will then set the above "?" symbol in the SQL query to the
			// appropriate value from the text file
			for (int i = 0; i < listScores.size(); i++) {

				statement.setInt(1, listScores.get(i).getHome_scores_tries());
				statement.setInt(2, listScores.get(i).getAway_scores_tries());
				statement.setInt(3, listScores.get(i).getHome_penalties());
				statement.setInt(4, listScores.get(i).getAway_penalties());
				statement.setInt(5, listScores.get(i).getHome_conversion());
				statement.setInt(6, listScores.get(i).getAway_conversion());
				statement.setInt(7, listScores.get(i).getMatch_id());

				// updating the databse with the new results set for the second
				// round
				statement.executeUpdate();

			}

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) { // End of auto generated try catch
			e.printStackTrace();
		}

	}

	/**
	 * Method to bulk upload results from Round 3 txt file to database
	 */
	public void bulkUploadRound3() {

		// instantiating the Connection variable con
		Connection con;
		con = db.getConnection();

		// console output to user
		System.out.println("Uploading the scores from text file for round 3...");
		System.out.println();

		// Auto Generated catch block around the SQL statement to catch SQL
		// errors
		try {

			// variables to be used for writing the SQL statement
			String query;
			PreparedStatement statement;

			// SQL statement to update the matches table and columns
			// SQL statement will update each value to ?
			// ? refers to the input value contained in the FOR loop setInt
			// below
			query = "UPDATE matches SET home_scores_tries = ?, away_scores_tries = ?,  home_penalties = ?, away_penalties = ?, home_conversion = ?, away_conversion = ? WHERE match_id = ?";

			// creating a connection between our SQL query and the Database
			statement = con.prepareStatement(query);

			/**
			 * Array list from Scores class called listScores and reading from
			 * text file
			 */
			ArrayList<Score> listScores = getListScoresFromTextFile(
					"/Users/davidgilen/Documents/workspace/GroupProject/Round_3.txt");

			// for loop which will cycle through the above ArrayList
			// This will then set the above "?" symbol in the SQL query to the
			// appropriate value from the text file
			for (int i = 0; i < listScores.size(); i++) {

				statement.setInt(1, listScores.get(i).getHome_scores_tries());
				statement.setInt(2, listScores.get(i).getAway_scores_tries());
				statement.setInt(3, listScores.get(i).getHome_penalties());
				statement.setInt(4, listScores.get(i).getAway_penalties());
				statement.setInt(5, listScores.get(i).getHome_conversion());
				statement.setInt(6, listScores.get(i).getAway_conversion());
				statement.setInt(7, listScores.get(i).getMatch_id());

				// updating the databse with the new results set for the third
				// round
				statement.executeUpdate();

			}

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) { // End of auto generated try catch
			e.printStackTrace();
		}

	}

	/**
	 * Method to bulk upload results from Round 4 txt file to database
	 */
	public void bulkUploadRound4() {

		// instantiating the Connection variable con
		Connection con;
		con = db.getConnection();

		// console output to user
		System.out.println("Uploading the scores from text file for round 4...");
		System.out.println();

		// Auto Generated catch block around the SQL statement to catch SQL
		// errors
		try {

			// variables to be used for writing the SQL statement
			String query;
			PreparedStatement statement;

			// SQL statement to update the matches table and columns
			// SQL statement will update each value to ?
			// ? refers to the input value contained in the FOR loop setInt
			// below
			query = "UPDATE matches SET home_scores_tries = ?, away_scores_tries = ?,  home_penalties = ?, away_penalties = ?, home_conversion = ?, away_conversion = ? WHERE match_id = ?";

			// creating a connection between our SQL query and the Database
			statement = con.prepareStatement(query);

			/**
			 * Array list from Scores class called listScores and reading from
			 * text file
			 */
			ArrayList<Score> listScores = getListScoresFromTextFile(
					"/Users/davidgilen/Documents/workspace/GroupProject/Round_4.txt");

			// for loop which will cycle through the above ArrayList
			// This will then set the above "?" symbol in the SQL query to the
			// appropriate value from the text file
			for (int i = 0; i < listScores.size(); i++) {

				statement.setInt(1, listScores.get(i).getHome_scores_tries());
				statement.setInt(2, listScores.get(i).getAway_scores_tries());
				statement.setInt(3, listScores.get(i).getHome_penalties());
				statement.setInt(4, listScores.get(i).getAway_penalties());
				statement.setInt(5, listScores.get(i).getHome_conversion());
				statement.setInt(6, listScores.get(i).getAway_conversion());
				statement.setInt(7, listScores.get(i).getMatch_id());

				// updating the databse with the new results set for the fourth
				// round
				statement.executeUpdate();

			}

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) { // End of auto generated try catch
			e.printStackTrace();
		}

	}

	/**
	 * Method to bulk upload results from Round 5 txt file to database
	 */
	public void bulkUploadRound5() {

		// instantiating the Connection variable con
		Connection con;
		con = db.getConnection();

		// console output to user
		System.out.println("Uploading the scores from text file for round 5...");
		System.out.println();

		// Auto Generated catch block around the SQL statement to catch SQL
		// errors
		try {

			// variables to be used for writing the SQL statement
			String query;
			PreparedStatement statement;

			// SQL statement to update the matches table and columns
			// SQL statement will update each value to ?
			// ? refers to the input value contained in the FOR loop setInt
			// below
			query = "UPDATE matches SET home_scores_tries = ?, away_scores_tries = ?,  home_penalties = ?, away_penalties = ?, home_conversion = ?, away_conversion = ? WHERE match_id = ?";

			// creating a connection between our SQL query and the Database
			statement = con.prepareStatement(query);

			/**
			 * Array list from Scores class called listScores and reading from
			 * text file
			 */
			ArrayList<Score> listScores = getListScoresFromTextFile(
					"/Users/davidgilen/Documents/workspace/GroupProject/Round_5.txt");

			// for loop which will cycle through the above ArrayList
			// This will then set the above "?" symbol in the SQL query to the
			// appropriate value from the text file
			for (int i = 0; i < listScores.size(); i++) {

				statement.setInt(1, listScores.get(i).getHome_scores_tries());
				statement.setInt(2, listScores.get(i).getAway_scores_tries());
				statement.setInt(3, listScores.get(i).getHome_penalties());
				statement.setInt(4, listScores.get(i).getAway_penalties());
				statement.setInt(5, listScores.get(i).getHome_conversion());
				statement.setInt(6, listScores.get(i).getAway_conversion());
				statement.setInt(7, listScores.get(i).getMatch_id());

				// updating the databse with the new results set for the fifth
				// round
				statement.executeUpdate();

			}

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) { // End of auto generated try catch
			e.printStackTrace();
		}

	}

	/**
	 * ArrayList to read the scores from the database
	 * 
	 * @param filePath
	 * @return
	 */
	public ArrayList<Score> getListScoresFromTextFile(String filePath) {

		// initialisation of variables for reading from a text file
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		// instantiating a new ArrayList object
		ArrayList<Score> listScores = new ArrayList<Score>();

		// auto generated try catch to catch exceptions when reading from text
		// file
		try {
			fis = new FileInputStream(filePath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			String line = null;

			String[] strScores = null;

			// loop and get all data from text file
			// while loop which will retrieve all the data from a text file
			while (true) {
				line = br.readLine();

				// check line empty, exit loop
				if (line == null) {
					break;

				} else {
					strScores = line.split(",");
					listScores.add(new Score(Integer.parseInt(strScores[0]), Integer.parseInt(strScores[1]),
							Integer.parseInt(strScores[2]), Integer.parseInt(strScores[3]),
							Integer.parseInt(strScores[4]), Integer.parseInt(strScores[5]),
							Integer.parseInt(strScores[6])));

				}
			}
		} catch (Exception e) {
			System.out.println("Read File Error");
			e.printStackTrace();
		} finally {

			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {

			}

		}
		// returns the array list
		return listScores;

	}

	public void blankUpload() {

		// instantiating the Connection variable con
		Connection con;
		con = db.getConnection();

		// console output to user
		System.out.println("Uploading blank scores to database, please manually input these...");
		System.out.println();

		// auto generated try catch block to catch SQL errors
		try {

			// variables to be used for writing the SQL statement
			String query;
			PreparedStatement statement;

			// SQL statement to update the matches table and columns
			// SQL statement will update each value to ?
			// ? refers to the input value contained in the FOR loop setInt
			// below
			query = "UPDATE matches SET home_scores_tries = ?, away_scores_tries = ?,  home_penalties = ?, away_penalties = ?, home_conversion = ?, away_conversion = ? WHERE match_id = ?";

			// creating a connection between our SQL query and the Database
			statement = con.prepareStatement(query);

			// for loop which will cycle through the above ArrayList
			// This will then set the above "?" symbol in the SQL query to 0
			for (int i = 0; i < 15; i++) {

				statement.setInt(1, 0);
				statement.setInt(2, 0);
				statement.setInt(3, 0);
				statement.setInt(4, 0);
				statement.setInt(5, 0);
				statement.setInt(6, 0);
				statement.setInt(7, 0);

				// updating the databse with blank results set for the fifth
				// round
				statement.executeUpdate();

			}

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) { // End of auto generated try catch
			e.printStackTrace();
		}

	}
}
