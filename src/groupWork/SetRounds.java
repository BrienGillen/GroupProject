package groupWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to enable the generation of rounds
 * 
 * @author anthonymcdonald
 *
 */
public class SetRounds {

	/**
	 * Creating Database connection object
	 */
	private final static DBConnect db = new DBConnect();

	/**
	 * Method that will generate rounds
	 * 
	 * @param i
	 * @param j
	 * @param numberOfMatches
	 */
	public void setRounds(int i, int j, int numberOfMatches) {

		// String to store the SQL queries to run
		String selectMatches, updateRounds, populateRounds;

		// Int to store the match id from the SQL query
		int matchID;

		// Instantiation of a connection and assigning it with getConnection
		// method
		Connection con = null;
		con = db.getConnection();
		// A prepared statement and result set used to run a SQL query and get a
		// set of results from that query
		PreparedStatement state, ps;
		ResultSet rs;

		// Initialisation of the selectMatches query
		selectMatches = "SELECT match_id FROM matches WHERE match_id = ?;";
		populateRounds = "INSERT INTO rounds (round_id) VALUES (?);";
		updateRounds = "UPDATE rounds SET match_id=?, round_no=? WHERE round_id=?;";

		try {

			state = con.prepareStatement(selectMatches);

			ps = con.prepareStatement("SELECT * FROM rounds");
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = con.prepareStatement(populateRounds);
				for (int x = 0; x < numberOfMatches; x++) {
					ps.setInt(1, x + 1);
					ps.executeUpdate();
				}
			}

			for (; i < j; i++) {
				state.setInt(1, i + 1);
				rs = state.executeQuery();
				while (rs.next()) {
					matchID = rs.getInt("match_id");
					ps = con.prepareStatement(updateRounds);
					ps.setInt(3, i + 1);
					ps.setInt(1, matchID);
					if (i < 3) {
						ps.setInt(2, 1);
					} else if (i >= 3 && i < 6) {
						ps.setInt(2, 2);
					} else if (i >= 6 && i < 9) {
						ps.setInt(2, 3);
					} else if (i >= 9 && i < 12) {
						ps.setInt(2, 4);
					} else if (i >= 12 && i < 15) {
						ps.setInt(2, 5);
					}
					ps.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
