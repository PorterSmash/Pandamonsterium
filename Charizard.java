package Release1;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Charizard implements MonsterInterface {
	
	boolean onField;
	
	int maxHealthPoints = 100;
	int defensePoints   = 4;
	int attackPoints    = 8;
	int speedPoints     = 6;
	
	//when the monster is first created, we can set these to the values above
	int healthBattle  = maxHealthPoints;
	int attackBattle  = 8;
	int defenseBattle = 0;
	int speedBattle   = 0;
	
	ArrayList attackList;
	Move move1 = new Move(5, 2, 95, 1); //light attack
	Move move2 = new Move(7, 1, 75, 1); //heavy attack
	Move move3 = new Move(-3, 1, 95, 0); //heal
	Move move4 = new Move(0, 1, 95, 1); //block
	
	String monsterImagePath;
	String soundFilePath;
	
	ImageIcon spriteImage;
	
	
	@Override
	public void levelUp(int statID) {
		switch(statID) {
		case 1:
			maxHealthPoints += 1;
			break;
		case 2:
			defensePoints += 1;
			break;
		case 3:
			attackPoints += 1;
			break;
		case 4:
			speedPoints += 1;
			break;
		default:
			System.out.println("INvalid statID was passed to levelUp method");
		}
		
	}
	@Override
	public void decreaseHealth(int healthDecNum) {
		healthBattle -= healthDecNum;
		
	}

	@Override
	public Move getAttack() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setOnField(boolean flag) {
		onField = flag;
	}
	public boolean getOnField() {
		return onField;
	}
	public void resetStats() {
		healthBattle = maxHealthPoints;
		attackBattle = attackPoints;
		speedBattle = speedPoints;
		defenseBattle = defensePoints;
	}

}