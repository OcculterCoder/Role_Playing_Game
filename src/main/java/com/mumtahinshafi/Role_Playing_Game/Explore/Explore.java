package com.mumtahinshafi.Role_Playing_Game.Explore;

import java.util.HashSet;
import java.util.Random;

public class Explore {
	
	private int[][] cages = new int[10][10];
	private Random random = new Random();
	private HashSet<String> exploredPlace = new HashSet<>();

	public int[][] getCages() {
		return cages;
	}

	private Explore() {
		
	}
	
	public static Explore getExploreInstance() {
		return new Explore();
	}
	
	public void setExploredPlace(HashSet<String> exploredPlace) {
		this.exploredPlace = exploredPlace;
	}

	public HashSet<String> getExploredPlace() {
		return exploredPlace;
	}

	public void initializeCage(){
		for(int i=0; i<cages.length; i++) {
			for(int j=0; j<cages[i].length; j++) {
				int descriptionIndex = random.nextInt(7);
				cages[i][j] = descriptionIndex;
			}
		}
	}
	
	private String[] description = {
			"You are in a Dark cage, I will catch you. You can not see me",
			"You are in a Slippery cage. I will push you out from this cage",
			"Oh god!!! Ghost cage",
			"Danger!!!Deamon's cage",
			"Go away! Your presence is disturbing me",
			"Free Speech, Under attack. Out from my cage",
			"Chemistry Lab. Really awkward smell everywhere. I can not breathe"
	};
	
	public void startExploring(int villainNo, String index1, String index2) {
		int firstIndex = Integer.parseInt(index1);
		int secondIndex = Integer.parseInt(index2);
		System.out.println("Welcome to the cage no. " + index2 + " of level " + index1);
		System.out.println("Cage Description: " + descriptionInitialize(cages[firstIndex - 1][secondIndex - 1]));
		if(villainNo == 0) {
			System.out.println("You are fortunate. No villain here in this cage");
		}
	}
	
	private String descriptionInitialize(int index) {
		return description[index];
	}
	
	
}
