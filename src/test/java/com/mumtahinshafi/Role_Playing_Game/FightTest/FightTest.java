package com.mumtahinshafi.Role_Playing_Game.FightTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mumtahinshafi.Role_Playing_Game.Character.Character;
import com.mumtahinshafi.Role_Playing_Game.Enemy.Sniper;
import com.mumtahinshafi.Role_Playing_Game.Enemy.Villain;
import com.mumtahinshafi.Role_Playing_Game.Fight.Fight;


public class FightTest {

	@Test
	public void testIncreasementOfExperienceOnWinning() {
		
		Character ch = new Character("jalu");
		Sniper sn = new Sniper(20, 50);
		Villain vl = sn;
		Fight fight = new Fight(ch, vl);
		
		fight.fighting();
		
		assertEquals(10, ch.getExperience());	
		
	}
	
	@Test
	public void testIncreasementOfHealthBoosterOnWinning() {
		
		Character ch = new Character("jalu");
		Sniper sn = new Sniper(20, 50);
		Villain vl = sn;
		Fight fight = new Fight(ch, vl);
		
		fight.fighting();
		
		assertEquals(4, ch.getNoOfHealthBooster());	
		
	}

}

