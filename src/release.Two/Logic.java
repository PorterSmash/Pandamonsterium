package release.Two;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**********************************************************************
 * Logic class that manipulates the monsters from the monster class.
 * through damage and switches the monster
 * 
 * @author Michelle Vu, Alex Porter, and Justin Kaukonen 
 * @version Fall 2018
 *********************************************************************/

public class Logic {
	/** array list of current monsters in player one's team.*/
	private ArrayList<Monster> player1Team = new ArrayList<Monster>();

	/** array list of current monsters in player two's team.*/
	private ArrayList<Monster> player2Team = new ArrayList<Monster>();

	/** current monster on field for player one's team.*/
	private int mon1;

	/** current monster on field for player two's team.*/
	private int mon2;

	/** shows whose turn it is.*/
	private int playerTurn = 0;

	/** Text that shows previous turns in battle. */
	private String battleLogText;

	/** Turn number for battle log to differentiate between turns. */
	private int turnNum = 1;

	/** Coins the player has to purchase items. */
	private int coins = 0;

	/** The list of items the player currently owns. */
	private ArrayList<String> itemList = new ArrayList<String>();

	/** the round number*/
	private int round = 1;
	
	/******************************************************************
	 * gets the round number
	 * @return round number
	 *****************************************************************/
	public int getRound() {
		return round;
	}
	/******************************************************************
	 * sets the round number
	 * @param round what round you want
	 *****************************************************************/
	public void setRound(int round) {
		this.round = round;
	}

	/******************************************************************
	 * Constructor that creates an arraylist of monsters for player 1.
	 * and player 2
	 * @param player1 array list of player one's monsters
	 * @param player2 array list of player two's monsters
	 *****************************************************************/
	Logic(final ArrayList<Monster> player1, 
			final ArrayList<Monster> player2) {
		this.mon1 = 0; 
		this.mon2 = 0;

		player1Team = player1;
		player2Team = player2;
		battleLogText = "";
	}

	/******************************************************************
	 * Default constructor.
	 *****************************************************************/
	public Logic() {
		battleLogText = "";
	}

	/******************************************************************
	 * Sets up the teams with the initial monsters and sets up their
	 * first monster.
	 * @param team1 array list of monsters for team 1
	 * @param team2 array list of monsters for team 2
	 * @param team1Index picks which monster in team 1 is on field
	 * @param team2Index picks which monster in team 2 is on field
	 *****************************************************************/
	public void setTeamsAndMons(final ArrayList<Monster> team1, 
			final ArrayList<Monster> team2, 
			final int team1Index, final int team2Index) {
		player1Team = team1;
		player2Team = team2;

		if (team1Index == -1) {
			this.mon2 = team2Index;

		} else if (team2Index == -1) {
			this.mon1 = team1Index;

		} else {
			this.mon1 = team1Index;
			this.mon2 = team2Index;
		}

		player1Team.get(mon1).setOnField(true);
		player2Team.get(mon2).setOnField(true);
		//this is so that the GUI and engine can pass the teams
		//back and forth and remain updated
		//that way you also don't need to create a new engine 
		//object every time you do a calculation
	}

	/*****************************************************************
	 * Sets the teams in the engine to stay updated.
	 * @param team1 To set for team one
	 * @param team2 To set for team two
	 ****************************************************************/
	public void setTeams(final ArrayList<Monster> team1, 
			final ArrayList<Monster> team2) {
		player1Team = team1;
		player2Team = team2;
	}	

	/*****************************************************************
	 * Gets the array list of monsters for team 1.
	 * @return array list of monsters for team 1
	 *****************************************************************/
	public ArrayList<Monster> getTeam1() {
		return player1Team;
	}


	/*****************************************************************
	 * Gets the array list of monsters for team 2.
	 * @return array list of monsters for team 2
	 *****************************************************************/
	public ArrayList<Monster> getTeam2() {
		return player2Team;
	}

	/*****************************************************************
	 * Gets you the monster on field for player 1.
	 * @return int for which monster is on field
	 *****************************************************************/
	public int getMon1() {
		return mon1;
	}

	/*****************************************************************
	 * Gets you the monster on field for player 2.
	 * @return int for which monster is on field
	 *****************************************************************/
	public int getMon2() {
		return mon2;
	}

	/*****************************************************************
	 * Gets which turn it is.
	 * @return which player's turn it is
	 *****************************************************************/
	public int getTurn() {
		return playerTurn;
	}

	/*****************************************************************
	 * Gets the text to update the battle log.
	 * @return The battle log text.
	 *****************************************************************/
	public String getBattleText() {
		return battleLogText;
	}

	/******************************************************************
	 * Sets the value of the coins.
	 * @param toSet Coin value to set
	 *****************************************************************/
	public void setCoins(final int toSet) {
		coins = toSet;
	}
	
	/******************************************************************
	 * Returns the number of coins the player has.
	 * @return The number of coins
	 *****************************************************************/
	public int getCoins() {
		//System.out.println("Total coins: " + coins);
		return coins;
	}

	/*****************************************************************
	 * Appends text to the battle log text.
	 * @param battleText the text to add to the battle log
	 *****************************************************************/
	public void addBattleText(final String battleText) {
		battleLogText = battleText + battleLogText;
	}
	/******************************************************************
	 * Calculates the damage from the move committed
	 * @param moveCommitted takes in the move stats from move object
	 * @param move states which move it is 1 through 4
	 * @param moveTarget indicates which team is targeted
	 * @return int of how much damage is dealt
	 *****************************************************************/
	private int calcDamage(final Move moveCommitted, final int move,final int moveTarget) {
		Monster target, attacker;
		if (moveTarget == 0) {
			target = player1Team.get(mon1);
			attacker = player2Team.get(mon2);
			if (itemList.contains("d")) {
				moveCommitted.setHitChance(
						moveCommitted.getHitChance() / 2);
			}
		} else {
			target = player2Team.get(mon2);
			attacker = player1Team.get(mon1);
		}
		int dmgNum = 0;
		if (move != 3) {
			
			if (diceRoll(moveCommitted.getHitChance())) {
				int dmgMultiplier = 1;

				if (diceRoll(moveCommitted.getCritChance())) {
					dmgMultiplier = 2;
					if (itemList.contains("cc") && attacker == 
							player2Team.get(mon2)) {
						dmgMultiplier = 1;
					}
				}
				dmgNum = Math.abs(((moveCommitted.getAttackPower() 
						+ attacker.getAttackBattle()) 
						* dmgMultiplier) - (target.
								getDefenseBattle() / 2));
				
			}
		} else if(move ==3) {
			Random rnd = new Random();
			dmgNum = (int)((2 * (moveCommitted.getAttackPower() + 
					attacker.getAttackBattle())) * rnd.nextDouble()); 
			// Anywhere from 0 to 5 times the sum of move and 
			//attacker power as healing,
			dmgNum = dmgNum * -1; 
			// Still tends to be much weaker than any attack.
		}
		
		// Initial part adds between 0% and 50% damage to the 
		//attack, but it always subtracts 25%, 
		// so it can be anywhere between -25% and 25% damage. 
		//Gives some difference to attacks so 
		// it's not both mons doing the same damage every time.

		Random rdn = new Random();
		dmgNum = dmgNum + (int)(dmgNum/2 * rdn.nextDouble()) - (dmgNum/4); 
		if(itemList.contains("gm") && attacker == player2Team.get(mon2) &&
				target == player1Team.get(mon1)) {
			dmgNum = 0;
		}
		return dmgNum; 
	}

	/*****************************************************************
	 * Applies the damage to a monster.
	 * @param moveDone Move to be committed
	 * @param moveTarget Monster target of the move
	 * @param moveNum Index of move performed
	 ****************************************************************/
	public void doMove(final Move moveDone, 
			final int moveTarget, final int moveNum) {
		Monster target;
		Monster attacker;
		int teamNum;
		if (moveTarget == 0) {
			target = player1Team.get(mon1);
			attacker = player2Team.get(mon2);
			teamNum = 2;
		} else {
			target = player2Team.get(mon2);
			attacker = player1Team.get(mon1);
			teamNum = 1;
		}
		if (moveNum == 3) {
			target = attacker;
		}
		int dmgDone = calcDamage(moveDone, moveNum,moveTarget);
		

		target.decreaseHealth(dmgDone);

		turnNum++;
		String firstOrSecond;
		if (turnNum % 2 == 0) {
			firstOrSecond = "first";
		} else {
			firstOrSecond = "second";
		}
		if (moveNum == 1) { 
			battleLogText = "(Turn " + (turnNum / 2) + ") " + attacker.
					getMonsterName() + " (Team " + teamNum + ") attacked "
					+ target.getMonsterName() + " (Team " + ((teamNum % 2) 
							+ 1) + ") " + firstOrSecond + " for " + dmgDone
					+ " damage.\n" + battleLogText;
		}
		if (moveNum == 2) {
			battleLogText = "(Turn " + (turnNum / 2) + ") " + attacker.
					getMonsterName() + " (Team " + teamNum + 
					") heavily attacked "
					+ target.getMonsterName() + " (Team " + ((teamNum % 2) 
							+ 1) + ") " + firstOrSecond + " for " + dmgDone
					+ " damage.\n" + battleLogText;
		}

		if (moveNum == 3) {
			battleLogText = "(Turn " + (turnNum / 2) + ") " + attacker.
					getMonsterName() + " (Team " + teamNum + 
					") healed themselves "
					+  "with " + Math.abs(dmgDone)
					+ " health points.\n" + battleLogText;
		}

	} 

	/******************************************************************
	 * Randomly chooses a number to determine the chance of certain
	 * moves. Having the roll be less than the chance will allow 
	 * the move to hit, or be critical.
	 * @param chance of the monster of getting a hit or critical
	 * @return boolean whether it will hit or be critical
	 *****************************************************************/
	private boolean	diceRoll(final int chance) {
		Random rand = new Random();
		int roll = rand.nextInt(11);
		return roll <= chance;	
	}

	/******************************************************************
	 * Changes the turn to switch each player.
	 *****************************************************************/
	public void changeTurn() {
		playerTurn = (playerTurn + 1) % 2;
	}

	/*****************************************************************
	 * Increments the number of turns.
	 ****************************************************************/
	public void incTurnNum() {
		turnNum++;
	}

	/*****************************************************************
	 * Gives the player an item(String) for a price.
	 * @param price Price of the item
	 ****************************************************************/
	public void itemShop(final int price) {
		if (price > coins) {
			System.out.println("You do not have enough"
					+ " coins to purchase that");

		}else {
			switch (price) {
			case 100:
				itemList.add("hb");
				coins = coins - price;
				for (Monster mon : player1Team) {
					mon.setMaxHealthPoints(mon.getMaxHealthPoints()+30);
					mon.setHealthBattle(mon.getMaxHealthPoints());
				}
			
				//for each monster in player team, healthMax += 30.
				break;
			case 300:
				itemList.add("d");
				coins = coins - price;
				//Dodger, enemy moves miss twice as often.
				break;
			
			case 500:
				itemList.add("cc");
				coins = coins - price;
				//enemy monster crit chance is now 0, always.
				//<takes two lines of code>
				break;
			case 10000:
				itemList.add("gm");
				coins = coins - price;
				//god mode, all enemy hit chances are now 0.
			default:
				System.out.print("Nothing has been bought");
				break;
			}
		}
	

	}
	/*****************************************************************
	 * Generates a team of monsters to battle against the player when they
	 * are playing alone.
	 * @param levelID amount of stats that the enemy team has
	 * CHANGE THE INT PARAMETER LATER TO A STRING SO THAT THE LEVEL 
	 * IS NOT DETERMINED JUST BY A NUMBER
	 * Note, levelId will be at least 4, more likely 10 
	 * sum(allStatValues) basically
	 *****************************************************************/
	public void generateEnemyTeam(final int levelID) {
		 
		player2Team.clear();
		int singleMonsterStats = levelID / 3; //monsterLevel for each monster
		Random rnd = new Random();
		String[] monsterList = {"Charizard", "Staryu", 
				"Nidoking", "Jolteon", "Squirtle", "Raichu","Eevee","Gengar",
				"Blaziken","Swellow","Ivysaur","Rattata",
				"Magikarp","Beedrill","Ludicolo","Troll", 
				"Far-Out Man", "Gnomagician", "Magma Golem (Boss)"};
		for(int i = 0; i < 2; i ++) { // generate 2 normal monsters
			Monster computerMonster = new Monster();
			computerMonster.monsterFactory(monsterList[rnd.nextInt(18)]); 
			// auto-assigns info other than stats to monster
			computerMonster.setAllZero(); // sets all stats to 0
			initializeComputerMonster(singleMonsterStats, computerMonster); 
			// loops through, adding stats
			player2Team.add(computerMonster); 
			// adds monster to enemy team, repeat
		}
		// generate boss monster
		Monster computerMonster = new Monster();
		computerMonster.monsterFactory(monsterList[18]); 
		// auto-assigns info other than stats to monster
		computerMonster.setAllZero(); // sets all stats to 0
		initializeComputerMonster((int)(singleMonsterStats * 1.5),
				computerMonster); 
		// loops through, adding stats, more powerful (times 1.5)
		player2Team.add(computerMonster); // adds monster 
		//to enemy team, repeat
		

	}
	/******************************************************************
	 * Creates a text file that contains all necessary information
	 * to continue with an already played game. This is only useful
	 * in a single player context.
	 *****************************************************************/
	public void saveGame() {
		//Creates a txt file on the players desktop that contains
		//all the information needed to continue with a game. This includes:
		//- Monsters and monster levels
		//- Items held
		//- coins in the coin bank
		
		String fileName = this.toString() + ".txt";
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (Monster mon : player1Team) {
				writer.println(mon.getMonsterName());
				writer.println(mon.getMaxHealthPoints());
				writer.println(mon.getAttackPoints());
				writer.println(mon.getDefensePoints());
				writer.println(mon.getSpeedPoints()); 
			}
			for (String item : itemList) {
				writer.print(item + ",");
			}
			writer.println();
			writer.println(this.coins);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
	/******************************************************************
	 * Heals the health for both teams
	 *****************************************************************/
	public void healTeam() {
		for (Monster mon : player1Team) {
			mon.setHealthBattle(mon.getMaxHealthPoints());
		}
		for (Monster mon2 : player2Team) {
			mon2.setHealthBattle(mon2.getMaxHealthPoints());
		}

	}
	/******************************************************************
	 * Reads in information from a data file and continues a game.
	 * @param fileName
	 *****************************************************************/
	public void loadGame(final String fileName) {

		Scanner fileIn = null;
		
		
		boolean check = new File(fileName).exists();
		if (check) {
			try {
				fileIn = new Scanner(
						new File(fileName), "UTF-8");	
				player1Team.clear();
				String currentLine = "";
				for (int i = 3; i > 0; i--) {
					Monster readMonster = new Monster();
					currentLine = fileIn.nextLine();
					readMonster(fileIn, currentLine, readMonster);
					player1Team.add(readMonster);
				}
				if (!currentLine.equals(",")) {
					String[] items = currentLine.split(",");
					for (int i = 0; i < items.length; i++) {
						itemList.add(items[i]);
					}
					currentLine = fileIn.nextLine();
				} else {
					currentLine = fileIn.nextLine();
				}
				currentLine = fileIn.nextLine();
				coins = Integer.parseInt(currentLine);	


			} catch (FileNotFoundException e) {
				System.out.println("error");
				
			} finally {
				if (fileIn != null) {
					fileIn.close();
				}
			}
		}
		
		

	}
	/******************************************************************
	 * Reads the monster in a file
	 * @param fileIn what file
	 * @param currentLine what line to read
	 * @param firstMon which monster
	 *****************************************************************/
	private void readMonster(final Scanner fileIn, String currentLine
			, final Monster firstMon) {
		//System.out.println("Name" + currentLine);
		firstMon.monsterFactory(currentLine);
		currentLine = fileIn.nextLine();
		//System.out.println("Health" + currentLine);
		firstMon.setMaxHealthPoints(
				Integer.parseInt(currentLine));
		currentLine = fileIn.nextLine();
		//System.out.println("Attack" + currentLine);
		firstMon.setAttackPoints(Integer.parseInt(currentLine));
		currentLine = fileIn.nextLine();
	//	System.out.println("Defense" + currentLine);
		firstMon.setDefensePoints(
				Integer.parseInt(currentLine));
		currentLine = fileIn.nextLine();
	//	System.out.println("Speed" + currentLine);
		firstMon.setSpeedPoints(Integer.parseInt(currentLine));
		firstMon.setHealthBattle(firstMon.getMaxHealthPoints());
		firstMon.setAttackBattle(firstMon.getAttackPoints());
		firstMon.setDefenseBattle(firstMon.getDefensePoints());
		firstMon.setSpeedBattle(firstMon.getSpeedPoints());
	}
	/******************************************************************
	 * Randomizes and sets the stats of the monster.
	 * @param compMonster Monster being created
	 * @param monsterLevel Level of the monster
	 *****************************************************************/
	private void initializeComputerMonster(final int monsterLevel, 
			final Monster compMonster) {
		Random rnd = new Random();
		compMonster.setMaxHealthPoints(60); 
		
		int totalLoops = 0;
		if (compMonster.getLevel() > 3) {
			//totalLoops = 21 + monsterLevel + rnd.nextInt(5) - 2; 
			// 0 though 4, minus 2, so +- 2 levels
			//totalLoops = 15 + monsterLevel + rnd.nextInt(5) - 2;
		} else {
			totalLoops = 21 + monsterLevel + rnd.nextInt(2); 
			// can be one level above at most
		}
		for (int i = 0; i < totalLoops; i++) { 
			// Levels up 1 stat per level + the initial 18 
			//stat points, randomly distributed
			compMonster.levelUp(rnd.nextInt(4) + 1);
		}
		
		compMonster.setAttackBattle(compMonster.getAttackPoints());
		compMonster.setDefenseBattle(compMonster.getDefensePoints());
		compMonster.setSpeedBattle(compMonster.getSpeedPoints());
		compMonster.setHealthBattle(compMonster.getMaxHealthPoints());
	}
	/*****************************************************************
	 * Main Method to test the game
	 *****************************************************************/
	public static void main(String[] args) {
		Logic engine = new Logic();
		Monster team1 = new Monster();
		Monster team2 = new Monster();

		team1.monsterFactory("Charizard");
		team2.monsterFactory("Jolteon");
		engine.getTeam1().add(team1);
		engine.getTeam1().add(team1);
		engine.getTeam1().add(team1); 

		engine.getTeam2().add(team2);
		engine.getTeam2().add(team2);
		engine.getTeam2().add(team2);

		engine.setCoins(1000000);
		engine.getItemList().add("ss");
		String loader = engine.toString();
		engine.saveGame();
		System.out.println("Done saving");

		engine.loadGame(loader + ".txt");

	}
	
	/*****************************************************************
	 * gets the item list that contains the shop items
	 * @returns ItemsList
	 *****************************************************************/
	public ArrayList<String> getItemList() {
		return itemList;
	}

}