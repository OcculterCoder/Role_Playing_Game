package com.mumtahinshafi.Role_Playing_Game.Fight;


import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

import com.mumtahinshafi.Role_Playing_Game.Enemy.Villain;
import com.mumtahinshafi.Role_Playing_Game.Character.Character;

public class Fight implements Serializable{
	
	private Character character;
	private Villain villain;
	private transient Random random;
	private transient Scanner sc;
	
	public Fight(Character character, Villain villain) {
		this.character = character;
		this.villain = villain;
		random = new Random();
		sc = new Scanner(System.in);
	}

	public String fighting() {
		int count = 0;
		while(character.getFullHealth() > 0) {
				count++;
				int amouontHarmEnemy = random.nextInt(character.getEnemyHealthDamageAbility()) + 1;
				int amountHarmCharacter = random.nextInt(villain.getAbilityToAttackCharacter()) + 1;
				int characterHarmedByEnemy = character.getFullHealth() - amountHarmCharacter;
				int enemyHarmedByCharacter = villain.getEnemyFullHealth() - amouontHarmEnemy;
				character.setFullHealth(characterHarmedByEnemy);
				villain.setEnemyFullHealth(enemyHarmedByCharacter);
				System.out.println("-------------------------------------------------");
				System.out.println("After the " + count + " fight your's and enemy's stats are: ");
				System.out.println();
				System.out.println("Character Stats: ");
				System.out.println("   Character Name: " + character.getName());
				if(character.getFullHealth()<1) {
					System.out.println("   Health: " + "0");
				}else {
					System.out.println("   Health: " + character.getFullHealth());
				}
				System.out.println("   Health Booster Left: " + character.getNoOfHealthBooster());
				System.out.println("   Experience: " + character.getExperience());
				System.out.println();
				System.out.println("Enemy Stats: ");
				System.out.println("   Enemy Name: " + villain.getName());
				if(villain.getEnemyFullHealth()<1) {
					System.out.println("   Health: " + "0");
				}else {
					System.out.println("   Health: " + villain.getEnemyFullHealth());
				}
				
				if(villain.getEnemyFullHealth() < 1) {
					System.out.println("**************************************");
					System.out.println("Congratulations. You have defeated the enemy. ");
					System.out.println("Receive +1 healthBooster and +5 experience as reward");
					int increaseHealthBooster = character.getNoOfHealthBooster() + 1;
					int increaseExperience = character.getExperience() + 5;
					character.setNoOfHealthBooster(increaseHealthBooster);
					character.setExperience(increaseExperience);
					System.out.println("-------------------------------------------------");
					System.out.println("Your Stats: ");
					System.out.println("   Character Name: " + character.getName());
					System.out.println("   Health: " + character.getFullHealth());
					System.out.println("   Health Booster Left: " + character.getNoOfHealthBooster());
					System.out.println("   Experience: " + character.getExperience());
					System.out.println("#################################################");
					return "win";
				}else if(character.getFullHealth() < 1) {
					break;
				}else if(character.getFullHealth() < 50 && character.getNoOfHealthBooster() >= 1) {
					System.out.println("Your life is in Danger.You can use health booster");
					System.out.println("Do you want to use health booster? Type y or n");
					String healthBoosterOption = sc.nextLine();
					if(healthBoosterOption.equalsIgnoreCase("y")) {
						int increaseCharacterHealth = character.getFullHealth() + 30;
						character.setFullHealth(increaseCharacterHealth);
						int increaseHealthBooster = character.getNoOfHealthBooster() - 1;
						character.setNoOfHealthBooster(increaseHealthBooster);
						System.out.println();
						System.out.println("Your current health is "+character.getFullHealth()+ " after taking health booster");
						continue;
					}else if(healthBoosterOption.equalsIgnoreCase("n")) {
						continue;
					}
				}
		}
		System.out.println(";);););););););););););););););)");
		System.out.println("Sorry You Lose");
		System.out.println(";);););););););););););););););)");
		return "loss";
	}
	

}
