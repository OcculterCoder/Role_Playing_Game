package com.mumtahinshafi.Role_Playing_Game.Enemy;

import java.io.Serializable;

public class Voldemort extends Villain implements Serializable{

	public Voldemort(int abilityToAttackCharacter, int enemyFullHealth) {
		super("Voldemort", abilityToAttackCharacter, enemyFullHealth);
	}

}
