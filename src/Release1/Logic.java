package Release1;

import java.util.ArrayList;
import java.util.Random;
public class Logic {
	ArrayList<Monster> player1Team = new ArrayList<Monster>();
	ArrayList<Monster> player2Team = new ArrayList<Monster>();
	int mon1;
	int mon2;
	Move lastMove;
	public int playerTurn = 0;
	
	Logic(ArrayList<Monster> player1, ArrayList<Monster> player2) {
		this.mon1 = 0;
		this.mon2 = 0;
		
		player1Team = player1;
		player2Team = player2;
	}
	public Logic() {
		
	}
	
	public void calculateDamage(Move moveCommitted) {
		lastMove = moveCommitted;
		ArrayList<Monster> playerList = new ArrayList<Monster>(); //the following three vars make in turn neutral
		ArrayList<Monster> opponentPlayerList = new ArrayList<Monster>();
		int target1; //refers to a defending target(healing)
		int target2; //refers to an attacking target
		if(playerTurn == 0) {
			playerList = player1Team;
			opponentPlayerList = player2Team;
			target1 = mon1;
			target2 = mon2;
		}
		else {
			playerList = player2Team;
			opponentPlayerList = player1Team;
			target1 = mon2;
			target2 = mon1;
		}
		if(moveCommitted.moveTarget == 0) {
			//monster damage is applied to is monster 1
			System.out.println("Healing");
			if(diceRoll(moveCommitted.hitChance)) {
				int critVal = 5;
				System.out.println("Move is a hit");
				if(diceRoll(moveCommitted.critChance)) {
					System.out.println("Move is a crit");
					critVal = 10;
				}
				playerList.get(target1).decreaseHealth(moveCommitted.attackPower * critVal);
			}
			else {
				System.out.println("Your attack missed");
			}
		}
		else {
			//monster damage is applied to is mon2
			if(diceRoll(moveCommitted.hitChance)) {
				int critVal = 5;
				System.out.println("Move is a hit");
				if(diceRoll(moveCommitted.critChance)) {
					System.out.println("Move is a crit");
					critVal = 10;
				}
				opponentPlayerList.get(target2).decreaseHealth((playerList.get(target1).attackBattle * critVal * ((10 - opponentPlayerList.get(target2).defenseBattle)))/5);
			}
			else {
				System.out.println("Your attack missed");
			}
		}
		changeTurn();
		displayData();
	}
	private boolean	diceRoll(int chance) {
		Random rand = new Random();
		int roll = rand.nextInt(11);
		return roll <= chance;	
	}
	// a little helper method to use while debugging
	public void displayData() {
		System.out.println("Char1 Health: " + player1Team.get(mon1).healthBattle);
		System.out.println("Char2 Health: " + player2Team.get(mon2).healthBattle);
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
			for(int i = 0; i < playerList.size(); i++ ) {
				if(player1Team.contains(playerList.get(monster))) {
					mon1 = i;
				}
				else  if (player2Team.contains(playerList.get(monster))){
					mon2 = i;
				}
				else {
					continue;
				}
			}
		}
	}
	private boolean checkCondition() {
		if(player1Team.get(mon1).healthBattle <= 0) {
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
		if(player2Team.get(mon2).healthBattle <= 0) {
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
	
	public void setTeamsAndMons(ArrayList<Monster> team1, ArrayList<Monster> team2, int team1Index, int team2Index) {
		player1Team = team1;
		player2Team = team2;
		
		if(team1Index == -1) {
			this.mon2 = team2Index;
		}
		else if(team2Index == -1) {
			this.mon1 = team1Index;
		}
		else {
		this.mon1 = team1Index;
		this.mon2 = team2Index;
		}
		
		player1Team.get(mon1).setOnField(true);
		player2Team.get(mon2).setOnField(true);
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
	public int getMon1() {
		return mon1;
	}
	public int getMon2() {
		return mon2;
	}
	public int getTurn() {
		return playerTurn;
	}
	
	
}