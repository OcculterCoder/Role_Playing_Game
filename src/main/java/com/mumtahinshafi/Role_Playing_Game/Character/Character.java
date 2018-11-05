package com.mumtahinshafi.Role_Playing_Game.Character;

import java.io.Serializable;

public class Character implements Serializable{
	
	private String name;
	private int fullHealth;
	private int noOfHealthBooster;
	private int enemyHealthDamageAbility;
	private int experience;
	
	public Character(String name) {
		this.name = name;
		this.noOfHealthBooster = 3;
		this.enemyHealthDamageAbility = 50;
		this.experience = 5;
		this.fullHealth = 100;
	}
	
	public Character getCharacter() {
		return this;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setFullHealth(int fullHealth) {
		this.fullHealth = fullHealth;
	}

	public void setNoOfHealthBooster(int noOfHealthBooster) {
		this.noOfHealthBooster = noOfHealthBooster;
	}

	public void setEnemyHealthDamageAbility(int enemyHealthDamageAbility) {
		this.enemyHealthDamageAbility = enemyHealthDamageAbility;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getName() {
		return name;
	}

	public int getFullHealth() {
		return fullHealth;
	}

	public int getNoOfHealthBooster() {
		return noOfHealthBooster;
	}

	public int getEnemyHealthDamageAbility() {
		return enemyHealthDamageAbility;
	}

	public int getExperience() {
		return experience;
	}

	
}
