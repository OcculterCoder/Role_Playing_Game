package com.mumtahinshafi.Role_Playing_Game.Enemy;

import java.io.Serializable;

public abstract class Villain implements Serializable{
	
	private String name;
	private int abilityToAttackCharacter;
	private int enemyFullHealth;
	
	public Villain(String name, int abilityToAttackCharacter, int enemyFullHealth) {
		this.name = name;
		this.abilityToAttackCharacter = abilityToAttackCharacter;
		this.enemyFullHealth = enemyFullHealth;
	}

	public String getName() {
		return name;
	}

	public int getAbilityToAttackCharacter() {
		return abilityToAttackCharacter;
	}

	public int getEnemyFullHealth() {
		return enemyFullHealth;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAbilityToAttackCharacter(int abilityToAttackCharacter) {
		this.abilityToAttackCharacter = abilityToAttackCharacter;
	}

	public void setEnemyFullHealth(int enemyFullHealth) {
		this.enemyFullHealth = enemyFullHealth;
	}

}
