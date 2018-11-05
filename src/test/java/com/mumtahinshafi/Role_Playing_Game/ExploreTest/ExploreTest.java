package com.mumtahinshafi.Role_Playing_Game.ExploreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mumtahinshafi.Role_Playing_Game.Explore.Explore;

public class ExploreTest {

	@Test
	public void testCageInitialization() {
		
		Explore explore = Explore.getExploreInstance();
		explore.initializeCage();
		int[][] cages = new int[10][10];
		cages = explore.getCages();
		
		assertTrue(cages[5][9] < 7 && cages[5][9] >=0);
		assertTrue(cages[8][2] < 7 && cages[8][2] >=0);
		assertTrue(cages[9][8] < 7 && cages[9][8] >=0);
	}

}
