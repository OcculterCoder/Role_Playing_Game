package com.mumtahinshafi.Role_Playing_Game.Enemy;

import java.io.Serializable;

public class VillainControllerFactory implements Serializable{
	private Villain cobra;
	private Villain voldemort;
	private Villain sniper;
	private Villain hansGruber;
	private Villain joker;
	
	public static Villain villainFactory(int num) {
		if(num == 1) {
			return new Cobra(10, 50);
		}else if(num == 2) {
			return new HansGruber(25, 60);
		}else if(num == 3) {
			return new Sniper(40, 70);
		}else if(num == 4) {
			return new Voldemort(60, 80);
		}else if(num == 5) {
			return new Joker(80, 100);
		}
		return null;
	}
	
	public Villain getEnemy(int value) {
		if(value == 1) {
			return cobra;
		}else if(value == 2) {
			return hansGruber;
		}else if(value == 3) {
			return sniper;
		}else if(value == 4){
			return voldemort;
		}else if(value == 5) {
			return joker;
		}
		return null;
	}

	public Villain getCobra() {
		return cobra;
	}

	public Villain getVoldemort() {
		return voldemort;
	}

	public Villain getSniper() {
		return sniper;
	}

	public Villain getHansGruber() {
		return hansGruber;
	}

	public Villain getJoker() {
		return joker;
	}

}
