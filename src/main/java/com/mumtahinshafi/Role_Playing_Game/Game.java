package com.mumtahinshafi.Role_Playing_Game;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.mumtahinshafi.Role_Playing_Game.Character.Character;
import com.mumtahinshafi.Role_Playing_Game.Enemy.Villain;
import com.mumtahinshafi.Role_Playing_Game.Enemy.VillainControllerFactory;
import com.mumtahinshafi.Role_Playing_Game.Exception.FalseButtonException;
import com.mumtahinshafi.Role_Playing_Game.Explore.Explore;
import com.mumtahinshafi.Role_Playing_Game.Fight.Fight;


public class Game {
	
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("*********************************************");
		System.out.println("Welcome to the role playing game");
		System.out.println("*********************************************");
		System.out.println();
		beforeInitial();			
	}

	public static void beforeInitial() throws ClassNotFoundException {
			Scanner sc = new Scanner(System.in);
			System.out.println("Create a new character by typing its name below OR type a old character name to start from where you left last time");
				String load = sc.nextLine();
				
				File file = new File(System.getProperty("user.dir"));
				File[] matching_Files = file.listFiles(new FilenameFilter() {
				    public boolean accept(File dir, String name) {
				        return name.startsWith(load) && name.endsWith(".ser");
				    }
				});
				
				if(matching_Files.length > 0) {
					System.out.println("Do you want to load from standard version(Type 1) or explored version(Type 2)?");
					String version = sc.nextLine();
					if(version.equals("1")) {
						File f = new File(System.getProperty("user.dir"));
						File[] matchingFiles = f.listFiles(new FilenameFilter() {
						    public boolean accept(File dir, String name) {
						        return name.startsWith(load) && name.endsWith("1.ser");
						    }
						});
						String matchingFileName = load + "1.ser";
						if(matchingFiles.length == 0) {
							System.out.println("Sorry there is no such version was saved associated with this character name. Maybe you want to play loaded version of 2");
							beforeInitial();
						}else if(matchingFiles.length > 0) {
							ObjectInputStream ois;
							try {
								ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(matchingFileName)));
								ArrayList<Object> getSave = new ArrayList<Object>();
								getSave = (ArrayList<Object>) ois.readObject();
								Character ch = (Character) getSave.get(0);
								Villain villain = (Villain) getSave.get(1);
								Fight fight = (Fight) getSave.get(2);
								int countVillainNumber = (int) getSave.get(3);
								firstOption(countVillainNumber, ch);
								ois.close();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}else if(version.equals("2")) {
						File f = new File(System.getProperty("user.dir"));
						File[] matchingFiles = f.listFiles(new FilenameFilter() {
						    public boolean accept(File dir, String name) {
						        return name.startsWith(load) && name.endsWith("2.ser");
						    }
						});
						String matchingFileName = load + "2.ser";
						if(matchingFiles.length == 0) {
							System.out.println("Sorry there is no such version was saved associated with this character name. Maybe you want to play loaded version of 1");
							beforeInitial();
						}if(matchingFiles.length > 0) {
							ObjectInputStream ois;
							try {
								ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(matchingFileName)));
								ArrayList<Object> getSave = new ArrayList<Object>();
								getSave = (ArrayList<Object>) ois.readObject();
								Character ch = (Character) getSave.get(0);
								HashSet<String> getExploredPlaces = (HashSet<String>) getSave.get(1);
								optionTwo(ch, getExploredPlaces);
								ois.close();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					else {
						try {
							falseButtonException("You have typed false letter/word");
						}catch(FalseButtonException ex){
							System.out.println();
							System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
							beforeInitial();
						}
					}
				}
				else {
					Character ch = new Character(load);
					System.out.println("You have created the player named " + ch.getName());
					System.out.println("Now you are ready to play and explore");
					initial(ch);
				}
	}

	public static void initial(Character ch) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
			System.out.println(" Type 1 to start playing straight-forward level by level \n Type 2 to explore different level by visiting different villain and fighting with them \n Type 3 to create new character \n Type 4 to exit the game");
			String play = sc.nextLine();
			int countVillainNumber = 1;
			if(play.equals("1")) {
				firstOption(countVillainNumber, ch);	
			}else if(play.equals("2")) {
				HashSet<String> exploredCages = new HashSet<String>();
				optionTwo(ch, exploredCages);
			}else if(play.equals("3")) {
				beforeInitial();
			}else if(play.equals("4")) {
				System.out.println("Oh, you are going!!! Anyways, See you later");
			}else {
				try {
					falseButtonException("You have typed false letter/word");
				}catch(FalseButtonException ex){
					System.out.println();
					System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
					initial(ch);
				}
			}
	}

	public static void firstOption(int countVillainNumber, Character ch) throws ClassNotFoundException {
			Scanner sc = new Scanner(System.in);
			System.out.println("Character Stats Before fighting starts: ");
			System.out.println("   Character Name: " + ch.getName());
			System.out.println("   Health: " + ch.getFullHealth());
			System.out.println("   Health Booster Left: " + ch.getNoOfHealthBooster());
			System.out.println("   Experience: " + ch.getExperience());
			Villain villain = VillainControllerFactory.villainFactory(countVillainNumber);
			Fight fight = new Fight(ch, villain);
			String result = fight.fighting();
			if(result.equals("win") && countVillainNumber==5) {
				System.out.println("You have won over all the enemies. Game finished here. Thank you for playing.");
				initial(ch);
			}else if(result.equals("win")){
				countVillainNumber++;
				System.out.println();
				System.out.println("Do you want to proceed playing to next level? Then type y or n.");
				System.out.println("If you want to save the game then type save");
				String option = sc.nextLine();
				if(option.equalsIgnoreCase("y")) {
					firstOption(countVillainNumber, ch);
				}else if(option.equalsIgnoreCase("n")){
					initial(ch);
				}else if(option.equalsIgnoreCase("save")) {
					ArrayList<Object> save = new ArrayList<>();
					save.add(ch);
					save.add(villain);
					save.add(fight);
					save.add(countVillainNumber);
					try {
						File file = new File(ch.getName()+"1.ser");
						file.createNewFile();
						ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, false)));
						out.writeObject(save);
						out.flush();
						out.close();
						System.out.println("Game Saved");
						beforeInitial();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						falseButtonException("You have typed false letter/word");
					}catch(FalseButtonException ex){
						System.out.println();
						System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
						System.out.println("Now you are taking back to the straight-forward version maintaining your current progress");
						firstOption(countVillainNumber, ch);
					}
				}
			}else if(result.equals("loss")) {
				initial(ch);
			}
	}
	
	public static void optionTwo(Character ch, HashSet<String> exploredCages) throws ClassNotFoundException {
			Scanner sc = new Scanner(System.in);
			Random random = new Random(); 
			Explore explore = Explore.getExploreInstance();
			explore.setExploredPlace(exploredCages);
			explore.initializeCage();
			System.out.println("There are total ten level and ten cages are residing in each level"); 
			int percentage = explore.getExploredPlace().size() * 100 / 100;
			System.out.println("You have explored " + percentage + "% places so far");
			System.out.println("Please enter below your new desired exploring level and cage no. (any number between 1 to 10)");
			System.out.print("Level No. : ");
			String index1 = sc.nextLine();
			System.out.print("Cage No. : ");
			String index2 = sc.nextLine();
			String addInSet = index1 + index2;
			
			if(!explore.getExploredPlace().contains(addInSet)) {
				explore.getExploredPlace().add(addInSet);
				int villainNo = random.nextInt(6);
				explore.startExploring(villainNo, index1, index2);
				if(villainNo != 0) {
					Villain userSelectedVillain = VillainControllerFactory.villainFactory(villainNo);
					System.out.println();
					System.out.println(" Villain Name: " + userSelectedVillain.getName());
					System.out.println(" Villain's Ability to attack you: " + userSelectedVillain.getAbilityToAttackCharacter());
					System.out.println(" Villain's Full Health: " + userSelectedVillain.getEnemyFullHealth());
					System.out.println();
					System.out.println("Want to fight with this monster? Then type y or n");
					String monsterFightChoice = sc.nextLine();
					if(monsterFightChoice.equalsIgnoreCase("y")) {
						Fight fight = new Fight(ch, userSelectedVillain);
						String result = fight.fighting();
						if(result.equals("win") || result.equals("loss")){
							System.out.println();
							System.out.println("Want to explore more? Then type y or n. If you want to save, then type save.");
							String moreExplore = sc.nextLine();
							if(moreExplore.equalsIgnoreCase("y")) {
								optionTwo(ch, exploredCages);
							}else if(moreExplore.equalsIgnoreCase("n")) {
								initial(ch);
							}else if(moreExplore.equalsIgnoreCase("save")) {
								saveOptionTwo(ch, explore);
								System.out.println();
								System.out.println("Game Saved.");
								initial(ch);
							}else {
								try {
									falseButtonException("You have typed false letter/word");
								}catch(FalseButtonException ex){
									System.out.println();
									System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
									System.out.println("Now you are taking back to the explore version maintaining your current progress");
									optionTwo(ch, exploredCages);
								}
							
							}
						}
					}else if(monsterFightChoice.equalsIgnoreCase("n")) {
						System.out.println();
						System.out.println("Want to explore more? Then type y or n. If you want to save, then type save.");
						String moreExplore = sc.nextLine();
						if(moreExplore.equalsIgnoreCase("y")) {
							optionTwo(ch, exploredCages);
						}else if(moreExplore.equalsIgnoreCase("n")){
							initial(ch);
						}else if(moreExplore.equalsIgnoreCase("save")){
							saveOptionTwo(ch, explore);
							System.out.println();
							System.out.println("Game Saved.");
							initial(ch);
						}else {
							try {
								falseButtonException("You have typed false letter/word");
							}catch(FalseButtonException ex){
								System.out.println();
								System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
								System.out.println("Now you are taking back to the explore version maintaining your current progress");
								optionTwo(ch, exploredCages);
							}
						
						}
					}else {
							try {
								falseButtonException("You have typed false letter/word");
							}catch(FalseButtonException ex){
								System.out.println();
								System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
								System.out.println("Now you are taking back to the explore version maintaining your current progress");
								optionTwo(ch, exploredCages);
							}
						
					}
				}else {
					System.out.println();
					System.out.println("Want to explore more? Then type y or n. If you want to save, then type save.");
					String moreExplore = sc.nextLine();
					if(moreExplore.equalsIgnoreCase("y")) {
						optionTwo(ch, exploredCages);
					}else if(moreExplore.equalsIgnoreCase("n")){
						initial(ch);
					}else if(moreExplore.equalsIgnoreCase("save")){
						saveOptionTwo(ch, explore);
						System.out.println();
						System.out.println("Game Saved.");
						initial(ch);
					}else {
						try {
							falseButtonException("You have typed false letter/word");
						}catch(FalseButtonException ex){
							System.out.println();
							System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
							System.out.println("Now you are taking back to the explore version maintaining your current progress");
							optionTwo(ch, exploredCages);
						}
					
					}
				}
			}else {
				System.out.println();
				System.out.println("You have already visited this cage before. Please select a new cage to explore.");
				optionTwo(ch, explore.getExploredPlace());
			}
					
	}
	
	public static void saveOptionTwo(Character ch, Explore explore) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("Do you want to SAVE the current state of your game? Then type y or n");
		String saveExplore = sc.nextLine();
		if(saveExplore.equalsIgnoreCase("y")) {
			ArrayList<Object> save = new ArrayList<>();
			save.add(ch);
			save.add(explore.getExploredPlace());
			try {
				File file = new File(ch.getName()+"2.ser");
				file.createNewFile();
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, false)));
				out.writeObject(save);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(saveExplore.equalsIgnoreCase("n")){
			optionTwo(ch, explore.getExploredPlace());
		}else {
			try {
				falseButtonException("You have typed false letter/word");
			}catch(FalseButtonException ex){
				System.out.println();
				System.out.println("You have typed unrecognized button. Please read carefully and then type a letter/word.");
				System.out.println("Now you are taking back to the saving option again. This time be careful");
				saveOptionTwo(ch, explore);
			}
		
		}
	}
	
	public static void falseButtonException(String message) throws FalseButtonException{
		throw new FalseButtonException(message);
	}

}
