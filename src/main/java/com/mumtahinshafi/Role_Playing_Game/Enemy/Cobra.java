package com.mumtahinshafi.Role_Playing_Game.Enemy;

import java.io.Serializable;

public class Cobra extends Villain implements Serializable{

	public Cobra(int abilityToAttackCharacter, int enemyFullHealth) {
		super("Cobra", abilityToAttackCharacter, enemyFullHealth);
	}

}
