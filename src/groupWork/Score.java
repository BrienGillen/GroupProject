package groupWork;

/**
 * @author anthonymcdonald
 *
 */
public class Score {

	/**
	 * Variable to store match_id
	 */
	int match_id;
	/**
	 * Variable to store team home id
	 */
	int team_home_id;
	/**
	 * Variable to store team away id
	 */
	int team_away_id;
	/**
	 * Variable to store team home tries
	 */
	int home_scores_tries;
	/**
	 * Variable to store team home penalties
	 */
	int home_penalties;
	/**
	 * Variable to store team home conversions
	 */
	int home_conversion;
	/**
	 * Variable to store team home points
	 */
	int home_points;
	/**
	 * Variable to store team home bonus points
	 */
	int home_bonus_points;
	/**
	 * Variable to store team home total points
	 */
	int home_total_points;
	/**
	 * Variable to store team away tries
	 */
	int away_scores_tries;
	/**
	 * Variable to store team away penalties
	 */
	int away_penalties;
	/**
	 * Variable to store team away conversion
	 */
	int away_conversion;
	/**
	 * Variable to store team away points
	 */
	int away_points;
	/**
	 * Variable to store team away bonus points
	 */
	int away_bonus_points;
	/**
	 * Variable to store team away total points
	 */
	int away_total_points;
	/**
	 * Variable to store team home result
	 */
	String home_result;
	/**
	 * Variable to store team away result
	 */
	String away_result;

	/**
	 * Default Constructor
	 */
	public Score() {

	}
	
	/**
	 * Constructor with arguments for the score class
	 * @param match_id
	 * @param home_scores_tries
	 * @param home_penalties
	 * @param home_conversion
	 * @param away_scores_tries
	 * @param away_penalties
	 * @param away_conversion
	 */
	public Score(int match_id, int home_scores_tries, int home_penalties, int home_conversion, int away_scores_tries,
			int away_penalties, int away_conversion) {
		super();
		
		//sets the current values to the integer values instantiated
		this.match_id = match_id;
		this.home_scores_tries = home_scores_tries;
		this.home_penalties = home_penalties;
		this.home_conversion = home_conversion;
		this.away_scores_tries = away_scores_tries;
		this.away_penalties = away_penalties;
		this.away_conversion = away_conversion;
	}

	/**
	 * @return the match_id
	 */
	public int getMatch_id() {
		return match_id;
	}

	/**
	 * @param match_id
	 *            the match_id to set
	 */
	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	/**
	 * @return the team_home_id
	 */
	public int getTeam_home_id() {
		return team_home_id;
	}

	/**
	 * @param team_home_id
	 *            the team_home_id to set
	 */
	public void setTeam_home_id(int team_home_id) {
		this.team_home_id = team_home_id;
	}

	/**
	 * @return the team_away_id
	 */
	public int getTeam_away_id() {
		return team_away_id;
	}

	/**
	 * @param team_away_id
	 *            the team_away_id to set
	 */
	public void setTeam_away_id(int team_away_id) {
		this.team_away_id = team_away_id;
	}

	/**
	 * @return the home_scores_tries
	 */
	public int getHome_scores_tries() {
		return home_scores_tries;
	}

	/**
	 * @param home_scores_tries
	 *            the home_scores_tries to set
	 */
	public void setHome_scores_tries(int home_scores_tries) {
		this.home_scores_tries = home_scores_tries;
	}

	/**
	 * @return the home_penalties
	 */
	public int getHome_penalties() {
		return home_penalties;
	}

	/**
	 * @param home_penalties
	 *            the home_penalties to set
	 */
	public void setHome_penalties(int home_penalties) {
		this.home_penalties = home_penalties;
	}

	/**
	 * @return the home_conversion
	 */
	public int getHome_conversion() {
		return home_conversion;
	}

	/**
	 * @param home_conversion
	 *            the home_conversion to set
	 */
	public void setHome_conversion(int home_conversion) {
		this.home_conversion = home_conversion;
	}

	/**
	 * @return the home_bonus_points
	 */
	public int getHome_bonus_points() {
		return home_bonus_points;
	}

	/**
	 * @param home_bonus_points
	 *            the home_bonus_points to set
	 */
	public void setHome_bonus_points(int home_bonus_points) {
		this.home_bonus_points = home_bonus_points;
	}

	/**
	 * @return the home_total_points
	 */
	public int getHome_total_points() {
		return home_total_points;
	}

	/**
	 * @param home_total_points
	 *            the home_total_points to set
	 */
	public void setHome_total_points(int home_total_points) {
		this.home_total_points = home_total_points;
	}

	/**
	 * @return the away_scores_tries
	 */
	public int getAway_scores_tries() {
		return away_scores_tries;
	}

	/**
	 * @param away_scores_tries
	 *            the away_scores_tries to set
	 */
	public void setAway_scores_tries(int away_scores_tries) {
		this.away_scores_tries = away_scores_tries;
	}

	/**
	 * @return the away_penalties
	 */
	public int getAway_penalties() {
		return away_penalties;
	}

	/**
	 * @param away_penalties
	 *            the away_penalties to set
	 */
	public void setAway_penalties(int away_penalties) {
		this.away_penalties = away_penalties;
	}

	/**
	 * @return the away_conversion
	 */
	public int getAway_conversion() {
		return away_conversion;
	}

	/**
	 * @param away_conversion
	 *            the away_conversion to set
	 */
	public void setAway_conversion(int away_conversion) {
		this.away_conversion = away_conversion;
	}

	/**
	 * @return the away_bonus_points
	 */
	public int getAway_bonus_points() {
		return away_bonus_points;
	}

	/**
	 * @param away_bonus_points
	 *            the away_bonus_points to set
	 */
	public void setAway_bonus_points(int away_bonus_points) {
		this.away_bonus_points = away_bonus_points;
	}

	/**
	 * @return the away_total_points
	 */
	public int getAway_total_points() {
		return away_total_points;
	}

	/**
	 * @param away_total_points
	 *            the away_total_points to set
	 */
	public void setAway_total_points(int away_total_points) {
		this.away_total_points = away_total_points;
	}

	/**
	 * @return the home_result
	 */
	public String getHome_result() {
		return home_result;
	}

	/**
	 * @param home_result
	 *            the home_result to set
	 */
	public void setHome_result(String home_result) {
		this.home_result = home_result;
	}

	/**
	 * @return the away_result
	 */
	public String getAway_result() {
		return away_result;
	}

	/**
	 * @param away_result
	 *            the away_result to set
	 */
	public void setAway_result(String away_result) {
		this.away_result = away_result;
	}

	/**
	 * @return the home_points
	 */
	public int getHome_points() {
		return home_points;
	}

	/**
	 * @param home_points
	 *            the home_points to set
	 */
	public void setHome_points(int home_points) {
		this.home_points = home_points;
	}

	/**
	 * @return the away_points
	 */
	public int getAway_points() {
		return away_points;
	}

	/**
	 * @param away_points
	 *            the away_points to set
	 */
	public void setAway_points(int away_points) {
		this.away_points = away_points;
	}

}

