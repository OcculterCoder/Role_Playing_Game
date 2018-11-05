package com.mumtahinshafi.Role_Playing_Game.Enemy;
import java.io.Serializable;

public class Sniper extends Villain implements Serializable{

	public Sniper(int abilityToAttackCharacter, int enemyFullHealth)  {
		super("Sniper", abilityToAttackCharacter, enemyFullHealth);
	}

}
