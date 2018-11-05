package com.mumtahinshafi.Role_Playing_Game.Enemy;

import java.io.Serializable;

public class Joker extends Villain implements Serializable{

	public Joker(int abilityToAttackCharacter, int enemyFullHealth) {
		super("Joker", abilityToAttackCharacter, enemyFullHealth);
	}

}
