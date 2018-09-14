

package Release1;

import java.util.ArrayList;
import java.util.Random;
public class Logic {
	ArrayList<Charizard> player1Team = new ArrayList<Charizard>();
	ArrayList<Charizard> player2Team = new ArrayList<Charizard>();
	Charizard mon1;
	Charizard mon2;
	Move lastMove;
	
	Logic(ArrayList<Charizard> player1, ArrayList<Charizard> player2) {
		this.mon1 = player1.get(0);
		this.mon2 = player2.get(0);
		
		player1Team = player1;
		player2Team = player2;
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
	public void switchMonster(ArrayList<Charizard> playerList, int monster) {
		if(playerList.get(monster).getOnField() == true) {
			System.out.println("The monster you selected is already on the field");
		}
		else {
			for(Charizard mon : playerList) {
				mon.setOnField(false);
			}
			playerList.get(monster).setOnField(true);
			for(Charizard mon : playerList) {
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
	private void checkCondition() {
		if(mon1.healthBattle <= 0) {
			System.out.println("Player 1 monster fainted, switching to another monster");
			int counter = 0;
			for(Charizard notDead : player1Team) {
				if(notDead.healthBattle > 0) {
					switchMonster(player1Team, counter);
				}
				if(player1Team.get(player2Team.size()).healthBattle <= 0) {
					System.out.println("All your team is dead.");
				}
			counter += 1;
			}
		}
		if(mon2.healthBattle <= 0) {
			System.out.println("Player 2 monster fainted, switching to another monster");
			int counter = 0;
			for(Charizard notDead : player2Team) {
				if(notDead.healthBattle > 0) {
					switchMonster(player2Team, counter);
				}
				if(player2Team.get(player2Team.size()).healthBattle <= 0) {
					System.out.println("All your team is dead.");
				}
			counter += 1;
			}
		}
	}
	
	
}
