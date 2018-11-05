package com.mumtahinshafi.Role_Playing_Game.Stub;

import java.util.HashSet;

import com.mumtahinshafi.Role_Playing_Game.Character.Character;
import com.mumtahinshafi.Role_Playing_Game.Enemy.Villain;
import com.mumtahinshafi.Role_Playing_Game.Enemy.VillainControllerFactory;
import com.mumtahinshafi.Role_Playing_Game.Exception.FalseButtonException;
import com.mumtahinshafi.Role_Playing_Game.Explore.Explore;
import com.mumtahinshafi.Role_Playing_Game.Fight.Fight;


public class GameStub {

		public void initial(Character ch, String scanner) throws FalseButtonException {
				System.out.println(" Type 1 to start playing straight-forward level by level \n Type 2 to explore different level by visiting different villain and fighting with them \n Type 3 to create new character \n Type 4 to exit the game");
				String play = scanner;
				int countVillainNumber = 1;
				if(play.equals("1")) {
					firstOption(countVillainNumber, ch, "n");	
				}else if(play.equals("2")) {
					HashSet<String> exploredCages = new HashSet<String>();
					optionTwo(ch, exploredCages, "22", "11");
				}else if(play.equals("4")) {
					System.out.println("Oh, you are going!!! Anyways, See you later");
				}else {
					throw new FalseButtonException("Wrong button typed");
				}
		}

		public int firstOption(int countVillainNumber, Character ch, String option1) throws FalseButtonException {
				Villain villain = VillainControllerFactory.villainFactory(countVillainNumber);
				Fight fight = new Fight(ch, villain);
				String result = fight.fighting();
				if(result.equals("win")){
					countVillainNumber++;
					System.out.println("Do you want to proceed playing to next level? Then type y or n.");
					System.out.println("If you want to save the game then type save");
					String option = option1;
					if(option.equalsIgnoreCase("y")) {
						initial(ch, "4");
					}else if(option.equalsIgnoreCase("n")){
						initial(ch, "4");
					}
					return countVillainNumber;
				}
				if(result.equals("loss")) {
					initial(ch, "4");
				}
				return countVillainNumber;
		}

		public int optionTwo(Character ch, HashSet<String> exploredCages, String index11, String index22) {
				Explore explore = Explore.getExploreInstance();
				explore.setExploredPlace(exploredCages);
				explore.initializeCage();
				System.out.println("There are total ten level and ten cages are residing in each level"); 
				int percentage = explore.getExploredPlace().size() * 100 / 100;
				System.out.println("You have explored " + percentage + "% places so far");
				System.out.println("Please enter below your new desired exploring level and cage no. (any number between 1 to 10)");
				System.out.print("Level No. : ");
				String index1 = index11;
				System.out.print("Cage No. : ");
				String index2 = index22;
				String addInSet = index1 + index2;
				
				if(!explore.getExploredPlace().contains(addInSet)) {
					explore.getExploredPlace().add(addInSet);
					return 1;
				}else {
					return 0;
				}
						
		}

}
