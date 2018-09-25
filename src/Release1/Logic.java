package Release1;

import java.util.ArrayList;
import java.util.Random;
public class Logic {
	ArrayList<Monster> player1Team = new ArrayList<Monster>();
	ArrayList<Monster> player2Team = new ArrayList<Monster>();
	Monster mon1;
	Monster mon2;
	Move lastMove;
	public int playerTurn = 0;
	
	Logic(ArrayList<Monster> player1, ArrayList<Monster> player2) {
		this.mon1 = player1.get(0);
		this.mon2 = player2.get(0);
		
		player1Team = player1;
		player2Team = player2;
	}
	public Logic() {
		
	}
	
	public void calculateDamage(Move moveCommitted) {
		lastMove = moveCommitted;
		if(moveCommitted.moveTarget == 0) {
			//monster damage is applied to is monster 1
			if(diceRoll(moveCommitted.hitChance)) {
				System.out.println("Move is a hit");
				if(diceRoll(moveCommitted.critChance)) {
					System.out.println("Move is a crit");
					mon1.decreaseHealth(moveCommitted.attackPower * 10);
				}
				else {
					mon1.decreaseHealth(moveCommitted.attackPower * 5);
				}
			}
			else {
				System.out.println("Your attack missed");
			}
		}
		else {
			//monster damage is applied to is mon2
			if(diceRoll(moveCommitted.hitChance)) {
				System.out.println("Move is a hit");
				if(diceRoll(moveCommitted.critChance)) {
					System.out.println("Move is a crit");
					mon2.decreaseHealth(mon1.attackBattle * 10 * ((10 - mon2.defenseBattle)/10));
				}
				else {
					mon2.decreaseHealth(mon1.attackBattle * 5 * ((10 - mon2.defenseBattle)/10));
				}
				
			}
			else {
				System.out.println("Your attack missed");
			}
		}
	}
	private boolean	diceRoll(int chance) {
		Random rand = new Random();
		int roll = rand.nextInt(11);
		return roll <= chance;	
	}
	// a little helper method to use while debugging
	public void displayData() {
		System.out.println("Char1 Health: " + mon1.healthBattle);
		System.out.println("Char2 Health: " + mon2.healthBattle);
		System.out.println("Last Move: " + lastMove.toString());
		
	}
	public void switchMonster(ArrayList<Monster> playerList, int monster) {
		if(playerList.get(monster).getOnField() == true) {
			System.out.println("The monster you selected is already on the field");
		}
		else {
			for(Monster mon : playerList) {
				mon.setOnField(false);
			}
			playerList.get(monster).setOnField(true);
			for(Monster mon : playerList) {
				if(player1Team.contains(mon)) {
					mon1 = mon;
				}
				else  if (player2Team.contains(mon)){
					mon2 = mon;
				}
				else {
					continue;
				}
			}
		}
	}
	private boolean checkCondition() {
		if(mon1.healthBattle <= 0) {
			System.out.println("Player 1 monster fainted, switching to another monster");
			int counter = 0;
			for(Monster notDead : player1Team) {
				if(notDead.healthBattle > 0) {
					switchMonster(player1Team, counter);
				}
				if(player1Team.get(player2Team.size()).healthBattle <= 0) {
					return true;
				}
			counter += 1;
			}
		}
		if(mon2.healthBattle <= 0) {
			System.out.println("Player 2 monster fainted, switching to another monster");
			int counter = 0;
			for(Monster notDead : player2Team) {
				if(notDead.healthBattle > 0) {
					switchMonster(player2Team, counter);
				}
				if(player2Team.get(player2Team.size()).healthBattle <= 0) {
					return true;
				}
			counter += 1;
			}
		}
		return false;
	}
	public void changeTurn() {
		playerTurn = (playerTurn + 1) % 2;
	}
	
	public void setTeamsAndMons(ArrayList<Monster> team1, ArrayList<Monster> team2) {
		player1Team = team1;
		player2Team = team2;
		
		this.mon1 = team1.get(0);
		this.mon2 = team2.get(0);
		//this is so that the GUI and engine can pass the teams back and forth and remain updated
		//that way you also don't need to create a new engine object every time you do a calculation
	}
	public void setTeams(ArrayList<Monster> team1, ArrayList<Monster> team2) {
		player1Team = team1;
		player2Team = team2;
	}
	
	public ArrayList<Monster> getTeam1() {
		return player1Team;
	}
	
	public ArrayList<Monster> getTeam2() {
		return player2Team;
	}
	
	public void startBattle() {
		player1Team.get(0).setOnField(true);
		player2Team.get(0).setOnField(true);
	}
	public Monster getMon1() {
		return mon1;
	}
	public Monster getMon2() {
		return mon2;
	}
	public int getTurn() {
		return playerTurn;
	}
	
	
}