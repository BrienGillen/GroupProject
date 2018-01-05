package groupWork;

import java.sql.*;
import java.util.Scanner;

/**
 * Class to output to the console the league table, the fixtures table and to
 * allow the user to search for data within these tables
 * 
 * @author briengillen
 *
 */
public class Searching {

	// Establishing a connection class to connect to the database
	DBConnect db = new DBConnect();

	/**
	 * Empty main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * Method to show the contents of the league table on the console with the
	 * inclusion of team_names
	 */
	public void displayLeague() {

		// Creating a connection
		Connection con;

		// Creating a generic Object array of size 9
		Object[] leagueRow = new Object[9];

		// Creating a number of strings for SQL queries and getting the team
		// name
		String selectLeague, getTeamName, teamName = null;

		// Creating integers to store the values obtained from the league table
		// to be printed to screen
		int leaguePos = 1, teamId = 0, gamesPlayed = 0, pointsScored = 0, pointsConceaded = 0, triesScored = 0,
				bonusPoints = 0, totalPoints = 0;

		// Creating the queries to draw the data from the league table in order
		// of total_points and if the total_points are the same then in order of
		// tries scored and to get the team name from the team table given the
		// team id
		selectLeague = "SELECT * FROM league ORDER BY total_points, tries_scored DESC";
		getTeamName = "SELECT team_name FROM teams WHERE team_id = ?";

		// Establishing the database connection
		con = db.getConnection();

		// Try catch to surround the SQL code
		try {
			System.out.println("League: \n\n");

			System.out.println(
					"League Position              Team ID               Games Played            Points Scored  "
					+ "        Points Conceaded         Tries Scored        Bonus Points Awarded      Total Points Awarded          Team");

			// Number of prepared statements to run the queries from above (two
			// prepared statements so that the queries can be run
			// simultaneously) and result sets to store the data from the
			// queries
			PreparedStatement ps = con.prepareStatement(selectLeague);
			PreparedStatement ps2 = con.prepareStatement(getTeamName);
			ResultSet rs, rs2;

			// Execute the selecting from league table
			rs = ps.executeQuery();

			// While the result has data within it we set the variables to be
			// the appropriate date from the table (the table headings
			// correspond with the variable names)
			while (rs.next()) {
				teamId = (rs.getInt(2));
				gamesPlayed = (rs.getInt(3));
				pointsScored = (rs.getInt(4));
				pointsConceaded = (rs.getInt(5));
				triesScored = (rs.getInt(6));
				bonusPoints = (rs.getInt(7));
				totalPoints = (rs.getInt(8));

				// We then set the team ID in the select from teams query to be
				// the ID obtained for each team in the league table in order to
				// include team name on the printing to console
				ps2.setInt(1, teamId);
				rs2 = ps2.executeQuery();

				// While the second queries result set has data we set the
				// teamName variable to be the String obtained from the query
				while (rs2.next()) {
					teamName = rs2.getString(1);
				}

				// We then populate the Object array with the variables set
				// above
				leagueRow[0] = leaguePos;
				leagueRow[1] = teamId;
				leagueRow[2] = gamesPlayed;
				leagueRow[3] = pointsScored;
				leagueRow[4] = pointsConceaded;
				leagueRow[5] = triesScored;
				leagueRow[6] = bonusPoints;
				leagueRow[7] = totalPoints;
				leagueRow[8] = teamName;

				// The league Position is incremented with each iteration
				// through the while loop
				leaguePos++;

				// An advance for loop is used to get each object from the array
				// so that we have no concern when printing to screen about the
				// [] brackets and commas that normally surround the data in an
				// array
				for (Object item : leagueRow) {
					System.out.print("\t" + item + "\t\t");
					// A new line is entered when the for loop reaches the end
					// of one row of the table
					if (item.equals(teamName)) {
						System.out.println();
					}
				} // End of advance for loop

			} // End of while for selecting from league

			System.out.println("\n\n\n");

			// Closing the connect to database resource
			con.close();

			// Catch for the SQL code
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method used to display the entire match table in the console
	 */
	public void displayMatches() {

		// Creating a connection
		Connection con;

		// Creating a generic Object array of size 22
		Object[] matchesRow = new Object[22];

		// Creating a number of strings for SQL queries and getting the team
		// name
		String selectMatches, getTeamName, teamHomeName = "", teamAwayName = "";

		// Creating integers to store the values obtained from the matches table
		// to be printed to screen
		int match_id = 0, teamHomeId = 0, teamAwayId = 0, homeTries = 0, homePenalties = 0, homeConversion = 0,
				homeScore = 0, homeBonus = 0, homePoints = 0, awayTries = 0, awayPenalties = 0, awayConversion = 0,
				awayScore = 0, awayBonus = 0, awayPoints = 0;

		// Creating strings to store the results from the match
		String homeResult, awayResult;

		// Creating the queries to draw the data from the matches table and to
		// get the team name from the team table given the
		// team id
		selectMatches = "SELECT * FROM matches";
		getTeamName = "SELECT team_name FROM teams WHERE team_id = ?";

		// Establishing the database connection
		con = db.getConnection();

		// Number of prepared statements to run the queries from above (two
		// prepared statements so that the queries can be run
		// simultaneously) and result sets to store the data from the
		// queries
		try {
			PreparedStatement ps = con.prepareStatement(selectMatches);
			PreparedStatement ps2 = con.prepareStatement(getTeamName);
			ResultSet rs, rs2;

			// Executing the select from matches table
			rs = ps.executeQuery();
			
			System.out.println("Match Results: \n\n");
			System.out.println("Match Number     Team ID          Team Name          Tries Scored    Penalties/Drop Goals"
					+ " Conversions     Score        Bonus Points      Points            Result");

			while (rs.next()) {
				match_id = rs.getInt(1);
				teamHomeId = rs.getInt(2);
				teamAwayId = rs.getInt(3);
				homeTries = rs.getInt(4);
				homePenalties = rs.getInt(5);
				homeConversion = rs.getInt(6);
				homeScore = rs.getInt(7);
				homeBonus = rs.getInt(8);
				homePoints = rs.getInt(9);
				awayTries = rs.getInt(10);
				awayPenalties = rs.getInt(11);
				awayConversion = rs.getInt(12);
				awayScore = rs.getInt(13);
				awayBonus = rs.getInt(14);
				awayPoints = rs.getInt(15);
				homeResult = rs.getString(16);
				awayResult = rs.getString(17);

				// We then set the team ID in the select from teams query to be
				// the ID obtained for each team in the matches table in order
				// to
				// include team name on the printing to console
				ps2.setInt(1, teamHomeId);
				rs2 = ps2.executeQuery();

				// While the second queries result set has data we set the
				// teamHomeName variable to be the String obtained from the
				// query
				while (rs2.next()) {
					teamHomeName = rs2.getString(1);
				}

				// We then set the team ID in the select from teams query to be
				// the ID obtained for each team in the matches table in order
				// to
				// include team name on the printing to console
				ps2.setInt(1, teamAwayId);
				rs2 = ps2.executeQuery();

				// While the second queries result set has data we set the
				// teamAwayName variable to be the String obtained from the
				// query
				while (rs2.next()) {
					teamAwayName = rs2.getString(1);
				}

				// We then populate the Object array with the variables set
				// above
				matchesRow[0] = match_id;
				matchesRow[1] = teamHomeId;
				matchesRow[2] = teamHomeName;
				matchesRow[3] = homeTries;
				matchesRow[4] = homePenalties;
				matchesRow[5] = homeConversion;
				matchesRow[6] = homeScore;
				matchesRow[7] = homeBonus;
				matchesRow[8] = homePoints;
				matchesRow[9] = homeResult;
				matchesRow[10] = "///";
				matchesRow[11] = match_id;
				matchesRow[12] = teamAwayId;
				matchesRow[13] = teamAwayName;
				matchesRow[14] = awayTries;
				matchesRow[15] = awayPenalties;
				matchesRow[16] = awayConversion;
				matchesRow[17] = awayScore;
				matchesRow[18] = awayBonus;
				matchesRow[19] = awayPoints;
				matchesRow[20] = awayResult;
				matchesRow[21] = "///";

				// An advance for loop is used to get each object from the array
				// so that we have no concern when printing to screen about the
				// [] brackets and commas that normally surround the data in an
				// array
				for (Object item : matchesRow) {
					System.out.print("    " + item + "\t\t");
					
					if (item.equals("///")) {
						System.out.println();
					}
				} // End of advance for loop

			} // End of while for selecting from matches

			System.out.println("\n\n\n");
			// Closing the connect to database resource
			con.close();

			// Catch for the SQL code
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to allow the user to return predetermined data (data we think the
	 * user could be interested in) for a certain team
	 */
	public void searchTeam() {

		// Initialisation of scanner object
		Scanner scan = new Scanner(System.in);

		// Creating a connection
		Connection con;

		// Creating strings to hold SQL queries and the team name
		String getLeague, getTeamID;
		String teamName;

		// Creating integers to hold the relevant data from the league table
		int gamesPlayed = 0, pointsScored = 0, pointsConceaded = 0, triesScored = 0, totalPoints = 0;

		// Establishing a database connection
		con = db.getConnection();

		// Setting the queries to select the relevant data we want from the
		// league table for each team and to get the team id when the user
		// inputs the team name
		getLeague = "SELECT games_played, points_scored, points_conceded, tries_scored, total_points FROM league WHERE team_id = ?";
		getTeamID = "SELECT team_id FROM teams WHERE team_name = ?";

		// Creating a boolean to let the user loop through the selecting team
		// search
		boolean conti = true;

		// Try catch to surround the SQL code
		try {

			// Do while loop to allow the user to continue to search for data
			// about certain teams until they choose to exit
			do {

				// Creating a prepared statement to run the SQL queries and 2
				// result sets to hold the data returned from them
				PreparedStatement ps = con.prepareStatement(getTeamID);
				ResultSet rs, rs2;

				System.out.println("Which team would you like to view final stats for? (Enter 'N' to cancel)");

				// Setting the String variable to be the user input
				teamName = scan.next();

				// If statement allowing the user to exit the searching by team
				// system by breaking the do while loop
				if (teamName.equalsIgnoreCase("n")) {
					System.out.println("Thank you, returning to search menu...");
					conti = false;

					// Else statement for if the user inputs any of the team
					// names (This should be set up using a select from the
					// league teams table ideally rather than hard coded values)
				} else if (teamName.equalsIgnoreCase("Ireland") || teamName.equalsIgnoreCase("Wales")
						|| teamName.equalsIgnoreCase("Scotland") || teamName.equalsIgnoreCase("England")
						|| teamName.equalsIgnoreCase("France") || teamName.equalsIgnoreCase("Italy")) {

					// Setting the string in the select from teams query to be
					// the user input name to get the team ID
					ps.setString(1, teamName);
					rs = ps.executeQuery();

					// While the select query returns data
					while (rs.next()) {
						// We then set up the prepare statement to return the
						// data we have preselected from the league table given
						// the users input team name but converted to an integer
						// as our league table doesn't store team names
						ps = con.prepareStatement(getLeague);
						ps.setInt(1, rs.getInt(1));
						rs2 = ps.executeQuery();

						// While the result set has data we set the variables
						// correspondingly
						while (rs2.next()) {
							gamesPlayed = (rs2.getInt(1));
							pointsScored = (rs2.getInt(2));
							pointsConceaded = (rs2.getInt(3));
							triesScored = (rs2.getInt(4));
							totalPoints = (rs2.getInt(5));
						}
						// Print out print a title and then a formated string to
						// give the user the data as a console display
						System.out.println(
								"Team Name     Games Played     Points Scored     Points Conceaded     Tries Scored     Total Points Awarded");
						System.out.printf(
								teamName + "          %d                %d                 %d                  %d                  %d               ",
								gamesPlayed, pointsScored, pointsConceaded, triesScored, totalPoints);
						System.out.println();

					} // End of do while loop for the initial queries result set

					// Else to let the user know that their input doesn't match
					// the suggestions and what the suggestions are
				} else {
					System.out.println("Please check your input is either a Team Name or 'N'");
				}
				// End of do while to allow the user to exit from the searching
				// by teams function
			} while (conti);

			// Closing the connect to database resource
			con.close();

			// Automatically generated catch for SQL code
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Class used to search the database for results from a given fixture for
	 * predetermined values (we decided on what we though a user might like to
	 * see)
	 */
	public void searchFixtures() {

		// Creating a scanner object
		Scanner scan = new Scanner(System.in);

		// Creating a connection
		Connection con;

		// Creating strings for the SQL queries
		String getFixture, getTeamName;

		// Creating strings for the variables we want to display
		String teamHomeName = null, teamAwayName = null, winner = null;

		// Creating integers for the variables we want to display
		int homePoints = 0, awayPoints = 0, matchID = 0;

		// Creating strings that will allow us to determine which team won the
		// match
		String homeResult, awayResult;

		// Establishing a database connection
		con = db.getConnection();

		// Creating the SQL queries
		getFixture = "SELECT team_home_id, team_away_id, home_points, away_points, home_result, away_result FROM matches WHERE match_id = ?";
		getTeamName = "SELECT team_name FROM teams WHERE team_id = ?";

		// Creating a boolean to allow the user to loop through the searching
		// fixtures until they choose to exit
		boolean conti = true;

		// Try catch used to surround the SQL code
		try {

			// Do while loop to allow the user to loop through the searching
			// fixtures until they choose to exit
			do {
				// Creating a prepared statement to run the SQL queries and two
				// result sets to hold the data returned by them
				PreparedStatement ps = con.prepareStatement(getFixture);
				ResultSet rs, rs2;

				System.out
						.println("Please enter the match number you would like to search for (1-15, or '0' to cancel)");

				// Checking that the user has input an integer value
				if (scan.hasNextInt()) {

					// Setting the variable for matchID to be the users input
					matchID = scan.nextInt();

					// Checking that the match ID is within the specific range
					if (matchID > 0 && matchID <= 15) {

						// Setting the integer for the select query to be the
						// user's input match ID
						ps.setInt(1, matchID);
						rs = ps.executeQuery();

						// While the result set contains data
						while (rs.next()) {

							// Change the prepare statement to run the query for
							// getting the team name from the ID
							ps = con.prepareStatement(getTeamName);

							// Set the team ID for this query to be the ID
							// returned from the first query initially as
							// team_home_id
							ps.setInt(1, rs.getInt(1));

							rs2 = ps.executeQuery();

							// While this query returns data we set the variable
							// for teamHomeName correspondingly
							while (rs2.next()) {
								teamHomeName = rs2.getString(1);
							}

							// Set the team ID for this query to be the ID
							// returned from the first query secondly as
							// team_away_id
							ps.setInt(1, rs.getInt(2));

							rs2 = ps.executeQuery();

							// While this query returns data we set the variable
							// for teamAwayName correspondingly
							while (rs2.next()) {
								teamAwayName = rs2.getString(1);
							}

							// Set the variables to be the rest of the data
							// returned by the initial query
							homePoints = rs.getInt(3);
							awayPoints = rs.getInt(4);
							homeResult = rs.getString(5);
							awayResult = rs.getString(6);

							// Check which team won and set the winner string
							// correspondingly (to print out the winning team
							// name, or that the match was a draw)
							if (homeResult.equalsIgnoreCase("Win")) {
								winner = teamHomeName;
							} else if (awayResult.equalsIgnoreCase("Win")) {
								winner = teamAwayName;
							} else if (homeResult.equalsIgnoreCase("Draw")) {
								winner = "Draw";
							}

							// Printing out first headings for each result
							// followed by a formatted string to return the
							// results in the easiest way to read
							System.out.println("Home Team       Away Team    Home Score    Away Score    Winner");
							System.out.printf(
									teamHomeName + "    \t" + teamAwayName + "     \t%d       \t%d   \t" + winner,
									homePoints, awayPoints);
							System.out.println();

						} // End of the while loop to run through the first
							// query

						// Else if statement so that if the user enters 0 they
						// are taken out of this method by breaking the do while
						// loop
					} else if (matchID == 0) {
						System.out.println("Thank you, returning to seaching menu...");
						conti = false;

						// Else statement to let the user know their input is
						// invalid
					} else {
						System.out.println("Please check the input matches the suggestions");
					}

					// Else statement for if the user tries to input a value
					// that isn't and integer
				} else {
					System.out.println("Integers only for match numbers please");
					scan.next();
				}

				// End of the do while loop used to continue running through the
				// above code until the user chooses to exit
			} while (conti);

			// Closing the connection to the database resource
			con.close();

			// Automatically generated catch for the running of SQL code
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
