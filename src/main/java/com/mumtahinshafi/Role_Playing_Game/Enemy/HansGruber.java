package com.mumtahinshafi.Role_Playing_Game.Enemy;

import java.io.Serializable;

public class HansGruber extends Villain implements Serializable{

	public HansGruber(int abilityToAttackCharacter, int enemyFullHealth) {
		super("Hans Gruber", abilityToAttackCharacter, enemyFullHealth);
	}

}
