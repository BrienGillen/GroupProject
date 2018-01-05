package groupWork;

import java.sql.*;

/**
 * Class to handle points calculations
 * 
 * @author anthonymcdonald
 *
 */
public class PointsFinal {

	/**
	 * Creating Database connection object
	 */
	private final static DBConnect db = new DBConnect();

	/**
	 * Creating the score object
	 */
	public final Score s = new Score();

	// Total Points Variables
	int totalPoints;

	/**
	 * Method to generate the points based on the scores stored in the database
	 * 
	 * @param i
	 * @param j
	 */
	public void buildScores(int i, int j) {

		// Instantiation of connection with initial value of null
		Connection con = null;

		// Variables used to perform SQL querys
		PreparedStatement state, state2;
		ResultSet rs;
		String getMatches, updateMatches;

		// Creating query variables to store queries
		getMatches = "SELECT * FROM matches WHERE match_id=?;";
		updateMatches = "UPDATE matches SET home_points=?, home_bonus_points=?, home_total_points=?, away_points=?, away_bonus_points=?,"
				+ " away_total_points=?, home_result=?, away_result=? WHERE match_id = ?";

		// Try catch to surround the SQL code
		try {
			System.out.println("Calculating the final match results for Round: " + (j / 3) + "...");
			System.out.println();

			// Establishing connection
			con = db.getConnection();

			// Creating the prepared statements with query variables
			state = con.prepareStatement(getMatches);
			state2 = con.prepareStatement(updateMatches);

			// for loop runs getMatches query 15 times for each different match
			// ID
			for (; i < j; i++) {
				state.setInt(1, i + 1);
				rs = state.executeQuery();

				// while loop checks that the results set has values stored
				while (rs.next()) {
					s.setHome_scores_tries(rs.getInt("home_scores_tries"));
					s.setHome_conversion(rs.getInt("home_conversion"));
					s.setHome_penalties(rs.getInt("home_penalties"));

					s.setAway_scores_tries(rs.getInt("away_scores_tries"));
					s.setAway_conversion(rs.getInt("away_conversion"));
					s.setAway_penalties(rs.getInt("away_penalties"));

				}

				// Calling points methods and assigning the parameters with the
				// score variables
				homePoints(s.getHome_scores_tries(), s.getHome_penalties(), s.getHome_conversion());
				awayPoints(s.getAway_scores_tries(), s.getAway_penalties(), s.getAway_conversion());
				totalPoints(s.getHome_points(), s.getAway_points());

				// Setting the score variables to the database query inputs
				state2.setInt(1, s.getHome_points());
				state2.setInt(2, s.getHome_bonus_points());
				state2.setInt(3, s.getHome_total_points());
				state2.setInt(4, s.getAway_points());
				state2.setInt(5, s.getAway_bonus_points());
				state2.setInt(6, s.getAway_total_points());
				state2.setString(7, s.getHome_result());
				state2.setString(8, s.getAway_result());
				state2.setInt(9, i + 1);

				// Executing the query
				state2.executeUpdate();

			}

			// Closing the connection to the database resource
			con.close();

			// Surrounding the sql querys in a try catch to catch SQL exceptions
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method for calculating total points for home team - how many points the
	 * 
	 * @param homePoints
	 * @param awayPoints
	 */
	public void totalPoints(int homePoints, int awayPoints) {

		// If statement which will award home team 4 points if home team wins
		if (homePoints > awayPoints) { //
			s.setHome_points(4);
			s.setAway_points(0);

			s.setHome_result("Win");
			s.setAway_result("Loss");
			// if losing team is within 7 points of winning team award 1 point
			if (awayPoints >= homePoints - 7) {

				s.setAway_total_points(s.getAway_total_points() + 1);

			}
			// If statement which will award away team 4 points if away team
			// wins
		} else if (awayPoints > homePoints) {
			s.setHome_total_points(0);
			s.setAway_total_points(4);

			s.setHome_result("Loss");
			s.setAway_result("Win");

			// if losing team is within 7 points of winning team award 1 point
			if (homePoints >= awayPoints - 7) {
				s.setHome_total_points(s.getHome_total_points() + 1);
			}
			// if both teams have the same score award 2 points each
		} else if (homePoints == awayPoints) {
			s.setHome_total_points(2);
			s.setAway_total_points(2);

			s.setHome_result("Draw");
			s.setAway_result("Draw");
		}

	}

	/**
	 * Method for calculating the final home score for each match
	 * 
	 * @param homeTries
	 * @param homePenalties
	 * @param homeConversions
	 */
	public void homePoints(int homeTries, int homePenalties, int homeConversions) {

		homeTries = homeTries * 5;
		homePenalties = homePenalties * 3;
		homeConversions = homeConversions * 2;
		// if either team scores more than 4 tries they are awarded a bonus
		// point
		if (homeTries / 5 >= 4) {
			s.setHome_bonus_points(1);
		} else {
			s.setHome_bonus_points(0);
		}

		s.setHome_points(homeTries + homePenalties + homeConversions);
	}

	/**
	 * Method for calculating the final away score for each match
	 * 
	 * @param awayTries
	 * @param awayPenalties
	 * @param awayConversions
	 */
	public void awayPoints(int awayTries, int awayPenalties, int awayConversions) {

		awayTries = awayTries * 5;
		awayPenalties = awayPenalties * 3;
		awayConversions = awayConversions * 2;

		// if either team scores more than 4 tries they are awarded a bonus
		// point
		if (awayTries / 5 >= 4) {
			s.setAway_bonus_points(1);
		} else {
			s.setAway_bonus_points(0);
		}

		s.setAway_points(awayTries + awayPenalties + awayConversions);
	}

}