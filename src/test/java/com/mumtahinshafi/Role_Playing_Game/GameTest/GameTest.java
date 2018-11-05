package com.mumtahinshafi.Role_Playing_Game.GameTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;

import org.junit.Test;
import org.mockito.Mockito;

import com.mumtahinshafi.Role_Playing_Game.Character.Character;
import com.mumtahinshafi.Role_Playing_Game.Exception.FalseButtonException;
import com.mumtahinshafi.Role_Playing_Game.Stub.GameStub;

public class GameTest {
	
	@Test
	public void testCorrectMethodRespondWhenCalledFromAnotherMethod() throws ClassNotFoundException, FalseButtonException {
		Character ch = new Character("Halu");
		GameStub test = Mockito.spy(GameStub.class);
		test.initial(ch, "1");
		Mockito.verify(test).firstOption(1, ch, "n");  
	}
	
	@Test
	public void testFalseButtonExceptionTrownWhenTypedWrong() {
		GameStub test = new GameStub();
		Character ch = new Character("Halu");
	    try {
			test.initial(ch, "6");
			fail("Right Button Typed");
		} catch (FalseButtonException e) {
			
		}
	    
	}
	
	@Test
	public void testVillainNoIsIncreasingByOneWhenWinning() throws FalseButtonException{
		GameStub test = new GameStub();
		Character ch = new Character("Halu");
		
		int countVillainNumber = 2;
		int checkCountVillainNumber = test.firstOption(countVillainNumber, ch, "n");
		assertEquals(3, checkCountVillainNumber);
	}
	
	@Test
	public void testForbiddingToExploreAlreadyVisitedPlace() throws FalseButtonException{
		GameStub test = new GameStub();
		Character ch = new Character("Halu");
		HashSet<String> exploredPlaces = new HashSet<>();
		exploredPlaces.add("21");
		exploredPlaces.add("12");
		exploredPlaces.add("31");
		
		int testExploredPlacesForbidden = test.optionTwo(ch, exploredPlaces, "2", "1");
		assertEquals(0, testExploredPlacesForbidden);
	}
	
	

}
