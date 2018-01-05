package groupWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class to write the IDs for matches, team_home and team_away to the matches
 * table
 * 
 * @author briengillen
 *
 */
public class GetFixIDs {

	/**
	 * Creating the database connection object
	 */
	DBConnect db = new DBConnect();

	/**
	 * Empty main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * Method to write the IDs listed above to the database given the input of
	 * the ArrayList of type String fixtures
	 * 
	 * @param fixtures
	 */
	public void getFixIDs(ArrayList<String> fixtures) {

		// String variables used to input SQL queries
		String team = "", team2 = "", updateHomeMatchesSql, clearMatchesSql, selectTeams;

		// Int variables used to set the number of teams in the tournament,
		// to get the team IDs and to get the match IDs
		int teamID = 0, teamID2 = 0, matchID;

		// A prepared statement and result set used to run a SQL query and get a
		// set of results from that query
		PreparedStatement state, ps;
		ResultSet rs;

		// Instantiation of a connection
		Connection con;

		// Instantiation of SQL queries to be ran with variable inputs replacing
		// ?s
		updateHomeMatchesSql = "INSERT INTO matches (match_id, team_home_id, team_away_id) VALUES (?, ?, ?)";
		clearMatchesSql = "DELETE FROM matches";
		selectTeams = "SELECT * FROM teams WHERE team_name = ?;";

		// Try catch to surround all database and SQL code
		try {
			// Initialising the connection to the database
			con = db.getConnection();

			// Setting up and executing an SQL statement to wipe the matches
			// table on the database before each insert of new fixtures
			state = con.prepareStatement(clearMatchesSql);
			state.executeUpdate();

			// Setting the initial value of the match ID to be 1
			matchID = 1;

			// Running a for loop with two separate integers to count the odd
			// and even numbers between 0 and 29 (30 values as, 15 fixtures,
			// with 2 teams per fixture = 30 teams total, but as iteration for
			// each integer is +=2 the loop only runs 15 times)
			for (int counter = 0, counter2 = 1; counter < 30 && counter2 < 30; counter += 2, counter2 += 2) {
				// Setting the values of the Strings team and team2 to be the
				// home and and way teams from each fixture respectively
				team = fixtures.get(counter);
				team2 = fixtures.get(counter2);

				// Preparing a statement to get the team IDs of each of the team
				// names gathered from above
				ps = con.prepareStatement(selectTeams);

				// Setting the team name in the WHERE clause of the statement to
				// the home team name for each fixture initially
				ps.setString(1, team);

				// Running the query with the returned values going into a
				// result set
				rs = ps.executeQuery();

				// Checking that the result set has data inside and running as
				// long as there is data
				while (rs.next()) {
					// Setting the first teamID (home_team_ID in the database)
					// to be the result of the select statement
					teamID = rs.getInt(1);
				}

				// Setting the team name in the WHERE clause of the statement to
				// the away team name for each fixture secondly
				ps.setString(1, team2);

				// Running the query with the returned values going into a
				// result set
				rs = ps.executeQuery();

				// Checking that the result set has data inside and running as
				// long as there is data
				while (rs.next()) {
					// Setting the second teamID (away_team_ID in the database)
					// to be the result of the select statement
					teamID2 = rs.getInt(1);
				}

				// Preparing another statement to execute where the values that
				// where taken from above, matchID (initially set as 1), teamID
				// and teamID2 are filled into the database as match_id,
				// team_home_id and team_away_id respectively
				state = con.prepareStatement(updateHomeMatchesSql);
				state.setInt(1, matchID);
				state.setInt(2, teamID);
				state.setInt(3, teamID2);

				// Executing the update to complete the insert before the values
				// are changed on the next iteration of the for loop
				state.executeUpdate();

				// Incrementing the matchID so that the insert is completed on a
				// different matchID each iteration of the for loop
				matchID++;
			} // End of the for loop for inserting IDs

			// Closing the connection resource to the database
			con.close();

			// Automatically generated catch block for SQL code
		} catch (SQLException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}
	}
}
