package Release1;

import java.util.ArrayList;
import java.util.Random;
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

	/** holds last move performed.*/
	private Move lastMove;

	/** shows whose turn it is.*/
	private int playerTurn = 0;

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
	}



	/******************************************************************
	 * Default constructor.
	 *****************************************************************/
	public Logic() {

	}


	/******************************************************************
	 * Calculates the damage based on the move input. Can do a 
	 * normal attack, heavy attack,
	 * @param moveCommitted move committed from the move class
	 *****************************************************************/
	public void calculateDamage(final Move moveCommitted) {
		lastMove = moveCommitted;
		ArrayList<Monster> playerList = 
				new ArrayList<Monster>(); 
		//the following three vars make in turn neutral
		ArrayList<Monster> opponentPlayerList = 
				new ArrayList<Monster>();
		int target1; //refers to a defending target(healing)
		int target2; //refers to an attacking target
		if (playerTurn == 0) {
			playerList = player1Team;
			opponentPlayerList = player2Team;
			target1 = mon1;
			target2 = mon2;
	} else {
			playerList = player2Team;
			opponentPlayerList = player1Team;
			target1 = mon2;
			target2 = mon1;
		}
		if (moveCommitted.getMoveTarget() == 0) {
			//monster damage is applied to is monster 1
			System.out.println("Healing");
			if (diceRoll(moveCommitted.getHitChance())) {
				int critVal = 5;
				System.out.println("Move is a hit");
				if (diceRoll(moveCommitted.getCritChance())) {
					System.out.println("Move is a crit");
					critVal = 10;
				}
				playerList.get(target1).
				decreaseHealth(moveCommitted.
						getAttackPower() * critVal);
			
			} else {
				System.out.println("Your attack missed");
			}
		
	} else {
			//monster damage is applied to is mon2
			if (diceRoll(moveCommitted.getHitChance())) {
				int critVal = 5;
				System.out.println("Move is a hit");
				if (diceRoll(moveCommitted.getCritChance())) {
					System.out.println("Move is a crit");
					critVal = 10;
				}
				opponentPlayerList.get(target2).decreaseHealth(
						(playerList.get(target1).
						getAttackBattle() * critVal 
						* ((10 - opponentPlayerList
				.get(target2).getDefenseBattle()))) / 5);
			
	} else {
				System.out.println("Your attack missed");
			}
		}
		changeTurn();
		displayData();
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
	 * a little helper method to use while debugging.
	 *****************************************************************/
	public void displayData() {
		System.out.println("Char1 Health: " 
	+ player1Team.get(mon1).getHealthBattle());
		System.out.println("Char2 Health: " 
	+ player2Team.get(mon2).getHealthBattle());
		System.out.println("Last Move: " + lastMove.toString());

	}


	/******************************************************************
	 * Changes the turn to switch each player.
	 *****************************************************************/
	public void changeTurn() {
		playerTurn = (playerTurn + 1) % 2;
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
		}
		else if (team2Index == -1) {
			this.mon1 = team1Index;
		}
		else {
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
	/**
	 * Sets the teams in the engine to stay updated.
	 * @param team1 To set for team one
	 * @param team2 To set for team two
	 */
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
	 * Starts the game by setting the intitial monsters on the field.
	 *****************************************************************/
	public void startBattle() {
		player1Team.get(0).setOnField(true);
		player2Team.get(0).setOnField(true);
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

}