package groupWorkTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import groupWork.Fixtures;

public class testFixtures {
	
	Fixtures fix = new Fixtures();
	ArrayList<String> teams = new ArrayList<String>();
	boolean getFix, booExpected, booActual;

	
	@Before
	public void setUp() throws Exception {
		teams.add("Ireland");
		teams.add("England");

	}

	@Test
	public void testGetFixtures() {
		if(!fix.getFixtures(teams).isEmpty()){
			booActual = true;
		}
		booExpected = true;
		assertEquals(booExpected, booActual);
	}

}
