package groupWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to carry out the league table functionality
 * 
 * @author anthonymcdonald
 *
 */
public class LeagueTable {

	/**
	 * Creating the database connection object
	 */
	DBConnect db = new DBConnect();

	/**
	 * Class to call all league table methods
	 */
	public void updateLeagueTable() {
		// Calling all league table methods
		generateLeagueTable();
		bonusPoints();
		totalPoints();
		triesScored();
		pointsConceded();
		pointsScored();
		gamesPlayed();
		grandSlam();
	}

	/**
	 * Class to generate teams in the league table
	 */
	public void generateLeagueTable() {

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String deleteQuery = "DELETE FROM league";
		String insertQuery = "INSERT INTO league(team_id) VALUES (?);";

		try {

			// Creating the prepared statement with predefined query
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			// Executing query
			ps.executeUpdate();

			// Loop to generate 6 team ID's in team league
			for (int i = 0; i < 6; i++) {
				// Creating query to insert the team ID's
				ps = con.prepareStatement(insertQuery);
				// Setting the team ID's
				ps.setInt(1, i + 1);
				// Executing query
				ps.executeUpdate();

			}
			System.out.println();
			System.out.println("League Created");
			System.out.println();
			
			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creating a total points method for the league table
	 */
	public void totalPoints() {

		// Variables to store total points and team ID
		int teamTotalPoints = 0;
		int teamID = 1;

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String selectHomeQuery = "SELECT SUM(home_total_points) FROM matches WHERE team_home_id=?";
		String selectAwayQuery = "SELECT SUM(away_total_points) FROM matches WHERE team_away_id=?";
		String selectBonusQuery = "SELECT bonus_points FROM league WHERE team_id = ?";
		String updateQuery = "UPDATE league SET total_points = ? WHERE team_id=?;";

		try {
			// For loop to cycle through each team ID
			for (int i = 0; i < 6; i++) {
				// Calling the prepared statement
				PreparedStatement ps = con.prepareStatement(selectHomeQuery);
				// Setting the team IDs
				ps.setInt(1, teamID);
				// Executing Query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning total points to each home team
					teamTotalPoints = rs.getInt(1);
				}
				// Calling the prepared statement
				ps = con.prepareStatement(selectAwayQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				// Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					// Adding total points to each away team from the home team
					// total points
					teamTotalPoints += rs.getInt(1);
				}
				
				// Calling the prepared statement
				ps = con.prepareStatement(selectBonusQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				//Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					//Adding the bonus points for each team to the final
					//total points
					teamTotalPoints += rs.getInt(1);
				}

				// Calling the prepared statement
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				// Setting team ID
				stmt.setInt(2, teamID);
				// Setting the final total points calculation to the league
				// table
				stmt.setInt(1, teamTotalPoints);
				// Executing query
				stmt.executeUpdate();
				// Incrementing team ID
				teamID++;
			}
			System.out.println("Points Updated");
			System.out.println();

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creating the bonus points method for league table
	 */
	public void bonusPoints() {

		// Variables to store bonus points and team ID
		int teamBonusPoints = 0;
		int teamID = 1;

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String selectHomeQuery = "SELECT SUM(home_bonus_points) FROM matches WHERE team_home_id=?";
		String selectAwayQuery = "SELECT SUM(away_bonus_points) FROM matches WHERE team_away_id=?";
		String updateQuery = "UPDATE league SET bonus_points = ? WHERE team_id=?;";

		try {
			// For loop to cycle through the team ID's
			for (int i = 0; i < 6; i++) {
				// Calling the prepared statement
				PreparedStatement ps = con.prepareStatement(selectHomeQuery);
				// Setting the team IDs
				ps.setInt(1, teamID);
				// Executing Query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning bonus points to each home team
					teamBonusPoints = rs.getInt(1);
				}
				// Calling the prepared statement
				ps = con.prepareStatement(selectAwayQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				// Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					// Adding bonus points to each away team from the home team
					// bonus points
					teamBonusPoints += rs.getInt(1);
				}
				// Calling the prepared statement
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				// Setting team ID
				stmt.setInt(2, teamID);
				// Setting the final bonus points calculation to the league
				// table
				stmt.setInt(1, teamBonusPoints);
				// Executing query
				stmt.executeUpdate();
				// Incrementing team ID
				teamID++;
			}
			System.out.println("Bonus Points Updated");
			System.out.println();

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creating a tries scored method for the league table
	 */
	public void triesScored() {

		// Variables to store tries scored and team ID
		int teamTriesScored = 0;
		int teamID = 1;

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String selectHomeQuery = "SELECT SUM(home_scores_tries) FROM matches WHERE team_home_id=?";
		String selectAwayQuery = "SELECT SUM(away_scores_tries) FROM matches WHERE team_away_id=?";
		String updateQuery = "UPDATE league SET tries_scored = ? WHERE team_id=?;";

		try {
			// For loop to cycle through each team ID
			for (int i = 0; i < 6; i++) {
				// Calling the prepared statement
				PreparedStatement ps = con.prepareStatement(selectHomeQuery);
				// Setting the team IDs
				ps.setInt(1, teamID);
				// Executing Query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning tries scored to each home team
					teamTriesScored = rs.getInt(1);
				}
				// Calling the prepared statement
				ps = con.prepareStatement(selectAwayQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				// Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					// Adding tries scored to each away team from the home team
					// tries scored
					teamTriesScored += rs.getInt(1);
				}
				// Calling the prepared statement
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				// Setting team ID
				stmt.setInt(2, teamID);
				// Setting the final tries scored calculation to the league
				// table
				stmt.setInt(1, teamTriesScored);
				// Executing query
				stmt.executeUpdate();
				// Incrementing team ID
				teamID++;
			}
			System.out.println("Tries Updated");
			System.out.println();

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creating a points conceded method for league table
	 */
	public void pointsConceded() {

		// Variables to store points conceded and team ID
		int teamPointsConceded = 0;
		int teamID = 1;

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String selectHomeQuery = "SELECT SUM(away_points) FROM matches WHERE team_home_id=?";
		String selectAwayQuery = "SELECT SUM(home_points) FROM matches WHERE team_away_id=?";
		String updateQuery = "UPDATE league SET points_conceded = ? WHERE team_id=?;";

		try {
			// For loop to cycle through each team ID
			for (int i = 0; i < 6; i++) {
				// Calling the prepared statement
				PreparedStatement ps = con.prepareStatement(selectHomeQuery);
				// Setting the team IDs
				ps.setInt(1, teamID);
				// Executing Query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning points conceded to each home team
					teamPointsConceded = rs.getInt(1);
				}
				// Calling the prepared statement
				ps = con.prepareStatement(selectAwayQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				// Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning points conceded to each away team
					teamPointsConceded += rs.getInt(1);
				}
				// Calling the prepared statement
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				// Setting team ID
				stmt.setInt(2, teamID);
				// Setting the final points conceded calculation to the league
				// table
				stmt.setInt(1, teamPointsConceded);
				// Executing query
				stmt.executeUpdate();
				// Incrementing team ID
				teamID++;
			}
			System.out.println("Conceded Points Updated");
			System.out.println();

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creating a points scored method for the league table
	 */
	public void pointsScored() {

		// Variables to store points scored and team ID
		int teamPointsScored = 0;
		int teamID = 1;

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String selectHomeQuery = "SELECT SUM(home_points) FROM matches WHERE team_home_id=?";
		String selectAwayQuery = "SELECT SUM(away_points) FROM matches WHERE team_away_id=?";
		String updateQuery = "UPDATE league SET points_scored = ? WHERE team_id=?;";

		try {
			// For loop to cycle through each team ID
			for (int i = 0; i < 6; i++) {
				// Calling the prepared statement
				PreparedStatement ps = con.prepareStatement(selectHomeQuery);
				// Setting the team IDs
				ps.setInt(1, teamID);
				// Executing Query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning points scored to each home team
					teamPointsScored = rs.getInt(1);
				}
				// Calling the prepared statement
				ps = con.prepareStatement(selectAwayQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				// Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					// Adding points scored to each away team from the home team
					// total points
					teamPointsScored += rs.getInt(1);
				}
				// Calling the prepared statement
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				// Setting team ID
				stmt.setInt(2, teamID);
				// Setting the final points scored calculation to the league
				// table
				stmt.setInt(1, teamPointsScored);
				// Executing query
				stmt.executeUpdate();
				// Incrementing team ID
				teamID++;
			}
			System.out.println("Points Scored Updated");
			System.out.println();

			// Closing the connection resource to the database
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creating a method to calculate games played for each team
	 */
	public void gamesPlayed() {

		// Variables to store games played and team ID
		int teamGamesPlayed = 0;
		int teamID = 1;

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String selectHomeQuery = "SELECT COUNT(team_home_id) FROM matches WHERE team_home_id=?";
		String selectAwayQuery = "SELECT COUNT(team_away_id) FROM matches WHERE team_away_id=?";
		String updateQuery = "UPDATE league SET games_played = ? WHERE team_id=?;";

		try {
			// For loop to cycle through each team ID
			for (int i = 0; i < 6; i++) {
				// Calling the prepared statement
				PreparedStatement ps = con.prepareStatement(selectHomeQuery);
				// Setting the team IDs
				ps.setInt(1, teamID);
				// Executing Query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning games played to each home team
					teamGamesPlayed = rs.getInt(1);
				}
				// Calling the prepared statement
				ps = con.prepareStatement(selectAwayQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				// Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					// Adding games played to each away team from the home team
					// games played
					teamGamesPlayed += rs.getInt(1);
				}
				// Calling the prepared statement
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				// Setting team ID
				stmt.setInt(2, teamID);
				// Setting the games played calculation to the league table
				stmt.setInt(1, teamGamesPlayed);
				// Executing query
				stmt.executeUpdate();
				// Incrementing team ID
				teamID++;
				

			}
			System.out.println("Games Played Updated");
			System.out.println();

			// Closing the connection resource to the database
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creating the grand slam method for league table
	 */
	public void grandSlam() {

		// Variables to store grand slam and team ID
		int grandSlam = 0;
		int teamID = 1;

		// Initializing connection
		Connection con;
		// Calling the connection method
		con = db.getConnection();

		// Creating variables to store SQL statements
		String selectHomeQuery = "SELECT COUNT(home_result) FROM matches WHERE team_home_id=? AND home_result = 'Win'";
		String selectAwayQuery = "SELECT COUNT(away_result) FROM matches WHERE team_away_id=? AND away_result = 'Win'";
		String updateQuery = "UPDATE league SET total_points= '+3' WHERE team_id=?;";

		try {
			// For loop to cycle through the team ID's
			for (int i = 0; i < 6; i++) {
				// Calling the prepared statement
				PreparedStatement ps = con.prepareStatement(selectHomeQuery);
				// Setting the team IDs
				ps.setInt(1, teamID);
				// Executing Query
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Assigning grand slam to each home team
					grandSlam = rs.getInt(1);

				}
				// Calling the prepared statement
				ps = con.prepareStatement(selectAwayQuery);
				// Setting team ID
				ps.setInt(1, teamID);
				// Executing query
				rs = ps.executeQuery();
				while (rs.next()) {
					// Adding grand slam calculation to each away team from the
					// home team grand slam
					grandSlam += rs.getInt(1);
				}
				// Incrementing team ID
				teamID++;
			}

			// When a team is found to have 5 wins
			if (grandSlam == 5) {
				// Run prepared statement for updating the total points for the
				// team that achieved a grand slam
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				// Setting team ID
				stmt.setInt(1, teamID);
				// Executing query
				stmt.executeUpdate();
				System.out.println("Grand Slam Calculated");
				System.out.println();

			}
			
			// Closing the connection resource to the database
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}