package groupWorkTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import groupWork.PointsFinal;
import groupWork.Score;


public class testPointsFinal {
	PointsFinal PointsFinal = new PointsFinal();
	
	Score s = PointsFinal.s;
	
	int homePointsWin, homePointsLoss, homePointsLossBonusPoint, homePointsDraw, awayPointsWin, awayPointsLoss,
			awayPointsLossBonusPoint, awayPointsDraw, homeTotPoints, awayTotPoints, intActual, intExpected,
			homeTriesBonusPoint, homeTriesNoBonusPoint, homePenalties, homeConversions, awayTriesBonusPoint,
			awayTriesNoBonusPoint, awayPenalties, awayConversions,homeTriesInvalid,awayTriesInvalid;
	String homeResult, awayResult, expected, actual;

	@Before
	public void setUp() throws Exception {
		//valid
		homePointsWin = 30;
		homePointsLoss = 10;
		homePointsLossBonusPoint = 25;
		homePointsDraw = 15;
		awayPointsWin = 30;
		awayPointsLoss = 10;
		awayPointsLossBonusPoint = 25;
		awayPointsDraw = 15;
		homeTriesBonusPoint = 4;
		homeTriesNoBonusPoint = 3;
		homePenalties = 3;
		homeConversions = 3;
		awayTriesBonusPoint = 4;
		awayTriesNoBonusPoint = 3;
		awayPenalties = 3;
		awayConversions = 3;
		//invalid
		homeTriesInvalid = 1;
		awayTriesInvalid = 1;
		
	
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildScores() {
		fail("Not yet implemented");
	}

	@Test
	public void testTotalPointsHomeWinHomeResultValid() {
		PointsFinal.totalPoints(homePointsWin, awayPointsLoss);
		actual = PointsFinal.s.getHome_result();
		expected = "Win";
		assertEquals(expected, actual);
	}

	@Test
	public void testTotalPointsHomeWinAwayResultValid() {
		PointsFinal.totalPoints(homePointsWin, awayPointsLoss);
		expected = "Win";
		actual = s.getHome_result();

		assertEquals(expected, actual);
	}

	@Test
	public void testTotalPointsHomeWinHomeTotalPointsValid() {
		PointsFinal.totalPoints(homePointsWin, awayPointsLoss);
		intActual = s.getHome_points();
		intExpected = 4;
		assertEquals(intExpected, intActual);

	}

	@Test
	public void testTotalPointsHomeWinAwayTotalPointsValid() {
		PointsFinal.totalPoints(homePointsWin, awayPointsLoss);
		intActual = s.getAway_points();
		intExpected = 0;
		assertEquals(intExpected, intActual);

	}

	@Test
	public void testTotalPointsHomeWinAwayBonusPointsValid() {
		PointsFinal.totalPoints(homePointsWin, awayPointsLossBonusPoint);
		intActual = s.getAway_total_points();
		intExpected = 1;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testTotalPointsHomeWinAwayNoBonusPointsValid() {
		PointsFinal.totalPoints(homePointsWin, awayPointsLoss);
		intActual = s.getAway_points();
		intExpected = 0;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testTotalPointsAwayWinAwayResultValid() {
		PointsFinal.totalPoints(homePointsLoss, awayPointsWin);
		actual = s.getAway_result();
		expected = "Win";
		assertEquals(expected, actual);
	}

	@Test
	public void testTotalPointsAwayWinHomeResultValid() {
		PointsFinal.totalPoints(homePointsLoss, awayPointsWin);
		actual = s.getHome_result();
		expected = "Loss";
		assertEquals(expected, actual);
	}

	@Test
	public void testTotalPointsAwayWinAwayTotalPointsValid() {
		PointsFinal.totalPoints(homePointsLoss, awayPointsWin);
		intActual = s.getAway_total_points();
		intExpected = 4;
		assertEquals(intExpected, intActual);

	}

	@Test
	public void testTotalPointsAwayWinHomeTotalPointsValid() {
		PointsFinal.totalPoints(homePointsLoss, awayPointsWin);
		intActual = s.getHome_points();
		intExpected = 0;
		assertEquals(intExpected, intActual);

	}

	@Test
	public void testTotalPointsAwayWinHomeBonusPointsValid() {
		PointsFinal.totalPoints(homePointsLossBonusPoint, awayPointsWin);
		intActual = s.getHome_total_points();
		intExpected = 1;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testTotalPointsAwayWinHomeNoBonusPointsValid() {
		PointsFinal.totalPoints(homePointsLoss, awayPointsWin);
		intActual = s.getHome_points();
		intExpected = 0;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testTotalPointsHomeDrawResultValid() {
		PointsFinal.totalPoints(homePointsDraw, awayPointsDraw);
		actual = s.getHome_result();

		expected = "Draw";
		assertEquals(expected, actual);
	}

	@Test
	public void testTotalPointsHomeDrawTotalPointsValid() {
		PointsFinal.totalPoints(homePointsDraw, awayPointsDraw);
		intActual = s.getHome_total_points();

		intExpected = 2;
		assertEquals(intExpected, intActual);

	}

	@Test
	public void testTotalPointsAwayDrawResultValid() {
		PointsFinal.totalPoints(homePointsDraw, awayPointsDraw);
		actual = s.getAway_result();
		expected = "Draw";
		assertEquals(expected, actual);
	}

	@Test
	public void testTotalPointsAwayDrawTotalPointsValid() {
		PointsFinal.totalPoints(homePointsDraw, awayPointsDraw);
		intActual = s.getHome_total_points();

		intExpected = 2;
		assertEquals(intExpected, intActual);

	}

	@Test
	public void testHomePointsTriesBonusPoint() {
		PointsFinal.homePoints(homeTriesBonusPoint, homePenalties, homeConversions);
		intActual = s.getHome_bonus_points();
		intExpected = 1;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testHomePointsTriesNoBonusPoint() {
		PointsFinal.homePoints(homeTriesNoBonusPoint,  homePenalties, homeConversions);
		intActual = s.getHome_bonus_points();
		intExpected = 0;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testHomePointsVaild() {
		intExpected = homeTriesBonusPoint*5 + homePenalties*3 + homeConversions*2;
		PointsFinal.homePoints(homeTriesBonusPoint,  homePenalties, homeConversions);
		intActual = s.getHome_points();
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testAwayPointsTriesBonusPoint() {
		PointsFinal.awayPoints(awayTriesBonusPoint,  homePenalties, homeConversions);
		intActual = s.getAway_bonus_points();
		intExpected = 1;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testAwayPointsTriesNoBonusPoint() {
		PointsFinal.awayPoints(awayTriesNoBonusPoint,  homePenalties, homeConversions);
		intActual = s.getHome_bonus_points();
		intExpected = 0;
		assertEquals(intExpected, intActual);
	}

	@Test
	public void testAwayPointsVaild() {
		intExpected = awayTriesBonusPoint*5 + awayPenalties*3 + awayConversions*2;
		PointsFinal.awayPoints(awayTriesBonusPoint,  homePenalties, homeConversions);
		intActual = s.getAway_points();
		assertEquals(intExpected, intActual);
	}
	@Test(expected = AssertionError.class)
	public void testHomePointsInvaild() {
		intExpected = homeTriesInvalid*5 + homePenalties*3 + homeConversions*2;
		PointsFinal.homePoints(homeTriesBonusPoint,  homePenalties, homeConversions);
		intActual = s.getHome_points();
		assertEquals(intExpected, intActual);
	}
	@Test(expected = AssertionError.class)
	public void testAwayPointsInvaild() {
		intExpected = awayTriesInvalid*5 + awayPenalties*3 + awayConversions*2;
		PointsFinal.awayPoints(awayTriesBonusPoint,  homePenalties, homeConversions);
		intActual = s.getAway_points();
		assertEquals(intExpected, intActual);
	}
}
