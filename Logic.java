

package Release1;

import java.util.Random;
public class Logic {
	Charizard mon1;
	Charizard mon2;
	Move lastMove;
	
	Logic(Charizard mon1, Charizard mon2) {
		this.mon1 = mon1;
		this.mon2 = mon2;
	}
	
	public void calculateDamage(Move moveCommitted) {
		lastMove = moveCommitted;
		if(moveCommitted.moveTarget == 0) {
			//monster damage is applied to is monster 1
			if(diceRoll(moveCommitted.hitChance)) {
				System.out.println("Move is a hit");
				if(diceRoll(moveCommitted.critChance)) {
					System.out.println("Move is a crit");
					mon1.decreaseHealth(mon1.attackBattle * 10);
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
					mon2.decreaseHealth(mon1.attackBattle * 10);
				}
				else {
					mon2.decreaseHealth(mon1.attackBattle * 5);
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
}
