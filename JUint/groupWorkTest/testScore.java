package groupWorkTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import groupWork.Score;

public class testScore {

	int matchIdValidLower, matchIdValidMid, matchIdValidUpper, homeTeamIdValidLower, homeTeamIdValidMid,
			homeTeamIdValidUpper, homeTriesValid, homePenaltiesValid, homeConversionValid, homeBonusPointsValid,
			homeTotalPointsValid, homePointsValid, awayTeamIdValidLower, awayTeamIdValidMid, awayTeamIdValidUpper,
			awayTriesValid, awayPenaltiesValid, awayConversionValid, awayBonusPointsValid, awayTotalPointsValid,
			awayPointsValid, matchIdInvalidLower, matchIdInvalidUpper, homeTeamIdInvalidLower, homeTeamIdInvalidUpper,
			homeTriesInvalid, homePenaltiesInvalid, homeConversionInvalid, homeBonusPointsInvalid,
			homeTotalPointsInvalid, homePointsInvalid, awayTeamIdInvalidLower, awayTeamIdInvalidUpper, awayTriesInvalid,
			awayPenaltiesInvalid, awayConversionInvalid, awayBonusPointsInvalid, awayTotalPointsInvalid,
			awayPointsInvalid;

	String homeResultValidWin, homeResultValidLoss, homeResultValidDraw, homeResultInvalid, awayResultValidWin,
			awayResultValidLoss, awayResultValidDraw, awayResultInvalid;

	@Before
	public void setUp() throws Exception {

		// Valid INTs
		matchIdValidLower = 0;
		matchIdValidMid = 8;
		matchIdValidUpper = 15;
		homeTeamIdValidLower = 0;
		homeTeamIdValidMid = 3;
		homeTeamIdValidUpper = 5;
		homeTriesValid = 1;
		homePenaltiesValid = 1;
		homeConversionValid = 1;
		homeBonusPointsValid = 1;
		homeTotalPointsValid = 1;
		homePointsValid = 1;
		awayTeamIdValidLower = 0;
		awayTeamIdValidMid = 3;
		awayTeamIdValidUpper = 5;
		awayTriesValid = 1;
		awayPenaltiesValid = 1;
		awayConversionValid = 1;
		awayBonusPointsValid = 1;
		awayTotalPointsValid = 1;
		awayPointsValid = 1;
		// Invalid INTS
		matchIdInvalidLower = -1;
		matchIdInvalidUpper = 16;
		homeTeamIdInvalidLower = -1;
		homeTeamIdInvalidUpper = 6;
		homeTriesInvalid = -1;
		homePenaltiesInvalid = -1;
		homeConversionInvalid = -1;
		homeBonusPointsInvalid = -1;
		homeTotalPointsInvalid = -1;
		homePointsInvalid = -1;
		awayTeamIdInvalidLower = -1;
		awayTeamIdInvalidUpper = -6;
		awayTriesInvalid = -1;
		awayPenaltiesInvalid = -1;
		awayConversionInvalid = -1;
		awayBonusPointsInvalid = -1;
		awayTotalPointsInvalid = -1;
		awayPointsInvalid = -1;
		// Strings
		homeResultValidWin = "Win";
		homeResultValidLoss = "Loss";
		homeResultValidDraw = "Draw";
		homeResultInvalid = "Fail";
		awayResultValidWin = "Win";
		awayResultValidLoss = "Loss";
		awayResultValidDraw = "Draw";
		awayResultInvalid = "Fail";
		
	}

	/**
	 * Testing Default Constructor
	 */
	@Test
	public void testScoreDefaultConstructor() {
		Score myScore = new Score();
		assertNotNull(myScore);
	}

	@Test
	public void testSetMatch_idValid() {
		Score groupScore = new Score();
		groupScore.setMatch_id(matchIdValidMid);
		assertEquals(matchIdValidMid, groupScore.getMatch_id());
	}

	@Test
	public void testSetMatch_idValidLower() {
		Score groupScore = new Score();
		groupScore.setMatch_id(matchIdValidLower);
		assertTrue(0 <= groupScore.getMatch_id() && groupScore.getMatch_id() <= 15);
	}

	@Test
	public void testSetMatch_idValidMid() {
		Score groupScore = new Score();
		groupScore.setMatch_id(matchIdValidMid);
		assertTrue(0 <= groupScore.getMatch_id() && groupScore.getMatch_id() <= 15);
	}

	@Test
	public void testSetMatch_idValidUpper() {
		Score groupScore = new Score();
		groupScore.setMatch_id(matchIdValidUpper);
		assertTrue(0 <= groupScore.getMatch_id() && groupScore.getMatch_id() <= 15);
	}

	@Test
	public void testSetTeam_home_idVaild() {
		Score groupScore = new Score();
		groupScore.setTeam_home_id(homeTeamIdValidMid);
		assertEquals(homeTeamIdValidMid, groupScore.getTeam_home_id());
	}

	@Test
	public void testSetTeam_home_idVaildLower() {
		Score groupScore = new Score();
		groupScore.setTeam_home_id(homeTeamIdValidLower);
		assertTrue(0 <= groupScore.getTeam_home_id() && groupScore.getTeam_home_id() <= 15);
	}

	@Test
	public void testSetTeam_home_idVaildMid() {
		Score groupScore = new Score();
		groupScore.setTeam_home_id(homeTeamIdValidMid);
		assertTrue(0 <= groupScore.getTeam_home_id() && groupScore.getTeam_home_id() <= 15);
	}

	@Test
	public void testSetTeam_home_idVaildUpper() {
		Score groupScore = new Score();
		groupScore.setTeam_home_id(homeTeamIdValidUpper);
		assertTrue(0 <= groupScore.getTeam_home_id() && groupScore.getTeam_home_id() <= 15);
	}

	@Test
	public void testSetTeam_away_idValid() {
		Score groupScore = new Score();
		groupScore.setTeam_away_id(awayTeamIdValidLower);
		assertEquals(awayTeamIdValidLower, groupScore.getTeam_away_id());
	}

	@Test
	public void testSetTeam_away_idValidLower() {
		Score groupScore = new Score();
		groupScore.setTeam_away_id(awayTeamIdValidLower);
		assertTrue(0 <= groupScore.getTeam_away_id() && groupScore.getTeam_away_id() <= 5);
	}

	@Test
	public void testSetTeam_away_idValidMid() {
		Score groupScore = new Score();
		groupScore.setTeam_away_id(awayTeamIdValidMid);
		assertTrue(0 <= groupScore.getTeam_away_id() && groupScore.getTeam_away_id() <= 5);
	}

	@Test
	public void testSetTeam_away_idValidUpper() {
		Score groupScore = new Score();
		groupScore.setTeam_away_id(awayTeamIdValidUpper);
		assertTrue(0 <= groupScore.getTeam_away_id() && groupScore.getTeam_away_id() <= 5);
	}

	@Test
	public void testSetHome_scores_triesValid() {
		Score groupScore = new Score();
		groupScore.setHome_scores_tries(homeTriesValid);
		assertEquals(homeTriesValid, groupScore.getHome_scores_tries());
	}

	@Test
	public void testSetHome_penaltiesValid() {
		Score groupScore = new Score();
		groupScore.setHome_penalties(homePenaltiesValid);
		assertEquals(homePenaltiesValid, groupScore.getHome_penalties());
	}

	@Test
	public void testSetHome_conversionValid() {
		Score groupScore = new Score();
		groupScore.setHome_conversion(homeConversionValid);
		assertEquals(homeConversionValid, groupScore.getHome_conversion());
	}

	@Test
	public void testSetHome_bonus_pointsValid() {
		Score groupScore = new Score();
		groupScore.setHome_bonus_points(homeBonusPointsValid);
		assertEquals(homeBonusPointsValid, groupScore.getHome_bonus_points());
	}

	@Test
	public void testSetHome_total_pointsValid() {
		Score groupScore = new Score();
		groupScore.setHome_total_points(homeTotalPointsValid);
		assertEquals(homeTotalPointsValid, groupScore.getHome_total_points());
	}

	@Test
	public void testSetAway_scores_triesValid() {
		Score groupScore = new Score();
		groupScore.setAway_scores_tries(awayTriesValid);
		assertEquals(awayTriesValid, groupScore.getAway_scores_tries());
	}

	@Test
	public void testSetAway_penaltiesValid() {
		Score groupScore = new Score();
		groupScore.setAway_penalties(awayPenaltiesValid);
		assertEquals(awayPenaltiesValid, groupScore.getAway_penalties());
	}

	@Test
	public void testSetAway_conversionValid() {
		Score groupScore = new Score();
		groupScore.setAway_conversion(awayConversionValid);
		assertEquals(awayConversionValid, groupScore.getAway_conversion());
	}

	@Test
	public void testSetAway_bonus_pointsValid() {
		Score groupScore = new Score();
		groupScore.setAway_bonus_points(awayBonusPointsValid);
		assertEquals(awayBonusPointsValid, groupScore.getAway_bonus_points());
	}

	@Test
	public void testSetAway_total_pointsValid() {
		Score groupScore = new Score();
		groupScore.setAway_total_points(awayTotalPointsValid);
		assertEquals(awayTotalPointsValid, groupScore.getAway_total_points());
	}

	@Test
	public void testSetHome_pointsValid() {
		Score groupScore = new Score();
		groupScore.setHome_points(homePointsValid);
		assertEquals(homePointsValid, groupScore.getHome_points());
	}

	@Test
	public void testSetAway_pointsValid() {
		Score groupScore = new Score();
		groupScore.setAway_points(awayPointsValid);
		assertEquals(awayPointsValid, groupScore.getAway_points());
	}

	@Test
	public void testSetHome_resultValidWin() {
		Score groupScore = new Score();
		groupScore.setHome_result(homeResultValidWin);
		assertEquals(homeResultValidWin, groupScore.getHome_result());
	}

	@Test
	public void testSetHome_resultValidLoss() {
		Score groupScore = new Score();
		groupScore.setHome_result(homeResultValidLoss);
		assertEquals(homeResultValidLoss, groupScore.getHome_result());
	}

	@Test
	public void testSetHome_resultValidDraw() {
		Score groupScore = new Score();
		groupScore.setHome_result(homeResultValidDraw);
		assertEquals(homeResultValidDraw, groupScore.getHome_result());
	}

	@Test
	public void testSetAway_resultValidWin() {
		Score groupScore = new Score();
		groupScore.setAway_result(awayResultValidWin);
		assertEquals(awayResultValidWin, groupScore.getAway_result());
	}

	@Test
	public void testSetAway_resultValidLoss() {
		Score groupScore = new Score();
		groupScore.setAway_result(awayResultValidLoss);
		assertEquals(awayResultValidLoss, groupScore.getAway_result());
	}

	@Test
	public void testSetAway_resultValidDraw() {
		Score groupScore = new Score();
		groupScore.setAway_result(awayResultValidDraw);
		assertEquals(awayResultValidDraw, groupScore.getAway_result());
	}

	@Test(expected = AssertionError.class)
	public void testSetMatch_idInvalidLower() {
		Score groupScore = new Score();
		groupScore.setMatch_id(matchIdInvalidLower);
		assertTrue(0 <= groupScore.getMatch_id() && groupScore.getMatch_id() <= 15);
	}

	@Test(expected = AssertionError.class)
	public void testSetMatch_idInvalidUpper() {
		Score groupScore = new Score();
		groupScore.setMatch_id(matchIdInvalidUpper);
		assertTrue(0 <= groupScore.getMatch_id() && groupScore.getMatch_id() <= 15);
	}

	@Test(expected = AssertionError.class)
	public void testSetTeam_home_idInvaildLower() {
		Score groupScore = new Score();
		groupScore.setTeam_home_id(homeTeamIdInvalidLower);
		assertTrue(0 <= groupScore.getTeam_home_id() && groupScore.getTeam_home_id() <= 5);
	}

	@Test(expected = AssertionError.class)
	public void testSetTeam_home_idInvaildUpper() {
		Score groupScore = new Score();
		groupScore.setTeam_home_id(homeTeamIdInvalidUpper);
		assertTrue(0 <= groupScore.getTeam_home_id() && groupScore.getTeam_home_id() <= 5);
	}

	@Test(expected = AssertionError.class)
	public void testSetTeam_away_idInvalidLower() {
		Score groupScore = new Score();
		groupScore.setTeam_away_id(awayTeamIdInvalidLower);
		assertTrue(0 <= groupScore.getTeam_away_id() && groupScore.getTeam_away_id() <= 5);
	}

	@Test(expected = AssertionError.class)
	public void testSetTeam_away_idInvalidUpper() {
		Score groupScore = new Score();
		groupScore.setTeam_away_id(awayTeamIdInvalidUpper);
		assertTrue(0 <= groupScore.getTeam_away_id() && groupScore.getTeam_away_id() <= 5);
	}

	@Test(expected = AssertionError.class)
	public void testSetHome_scores_triesInvalid() {
		Score groupScore = new Score();
		groupScore.setHome_scores_tries(homeTriesInvalid);
		assertTrue(groupScore.getHome_scores_tries() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetHome_penaltiesInvalid() {
		Score groupScore = new Score();
		groupScore.setHome_penalties(homePenaltiesInvalid);
		assertTrue(groupScore.getHome_penalties() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetHome_conversionInvalid() {
		Score groupScore = new Score();
		groupScore.setHome_conversion(homeConversionInvalid);
		assertTrue(groupScore.getHome_conversion() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetHome_bonus_pointsInvalid() {
		Score groupScore = new Score();
		groupScore.setHome_bonus_points(homeBonusPointsInvalid);
		assertTrue(groupScore.getHome_bonus_points() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetHome_total_pointsInvalid() {
		Score groupScore = new Score();
		groupScore.setHome_total_points(homeTotalPointsInvalid);
		assertTrue(groupScore.getHome_total_points() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetAway_scores_triesInvalid() {
		Score groupScore = new Score();
		groupScore.setAway_scores_tries(awayTriesInvalid);
		assertTrue(groupScore.getAway_scores_tries() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetAway_penaltiesInvalid() {
		Score groupScore = new Score();
		groupScore.setAway_penalties(awayPenaltiesInvalid);
		assertTrue(groupScore.getAway_penalties() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetAway_conversionInvalid() {
		Score groupScore = new Score();
		groupScore.setAway_conversion(awayConversionInvalid);
		assertTrue(groupScore.getAway_conversion() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetAway_bonus_pointsInvalid() {
		Score groupScore = new Score();
		groupScore.setAway_bonus_points(awayBonusPointsInvalid);
		assertTrue(groupScore.getAway_bonus_points() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetAway_total_pointsInvalid() {
		Score groupScore = new Score();
		groupScore.setAway_total_points(awayTotalPointsInvalid);
		assertTrue(groupScore.getAway_total_points() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetHome_pointsInvalid() {
		Score groupScore = new Score();
		groupScore.setHome_points(homePointsInvalid);
		assertTrue(groupScore.getHome_points() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetAway_pointsInvalid() {
		Score groupScore = new Score();
		groupScore.setAway_points(awayPointsInvalid);
		assertTrue(groupScore.getAway_points() >= 0);
	}

	@Test(expected = AssertionError.class)
	public void testSetHome_resultInvalid() {
		Score groupScore = new Score();
		groupScore.setHome_result(homeResultInvalid);
		assertTrue(groupScore.getHome_result().equals("Win") || groupScore.getHome_result().equals("Loss")
				|| groupScore.getHome_result().equals("Draw"));
	}

	@Test(expected = AssertionError.class)
	public void testSetAway_resultInvalid() {
		Score groupScore = new Score();
		groupScore.setAway_result(awayResultInvalid);
		assertTrue(groupScore.getAway_result().equals("Win") || groupScore.getAway_result().equals("Loss")
				|| groupScore.getAway_result().equals("Draw"));
	}

}
