package Release1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Monster implements MonsterInterface {
	int maxHealthPoints;
	int defensePoints;
	int attackPoints;
	int speedPoints;

	int healthBattle;
	int attackBattle;
	int defenseBattle;
	int speedBattle;

	ArrayList<Move> attackList;
	Move move1;
	Move move2;
	Move move3;
	Move move4;

	String monsterImagePath;
	String soundFilePath;

	ImageIcon spriteImage;
	private boolean onField;

	public void monsterFactory(String monsterName, String fileName) {
		File monFile = new File(fileName);
		Scanner scnr = null;
		try {
			scnr = new Scanner(monFile);
			while (scnr.hasNextLine()) {
				if (scnr.next().equals(monsterName)) {
					// set all stats in whichever order we want to arrange them, just keep getting scnr.next() until line is done
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (scnr != null) {
				scnr.close();
			}
		}
	}
	
	public void monsterFactory(String monsterName) {
		if (monsterName.equals("Charizard")) {
			setMaxHealthPoints(120);
			setAttackPoints(4);
			setDefensePoints(8);
			setSpeedPoints(6);
			
			//when the monster is first created, we can set these to the values above
			int healthBattle  = maxHealthPoints;
			int attackBattle  = 8;
			int defenseBattle = 0;
			int speedBattle   = 0;
			
			ArrayList<Move> attackList = new ArrayList<Move>();
			Move move1 = new Move(5, 2, 95, 1); //light attack
			Move move2 = new Move(7, 1, 75, 1); //heavy attack
			Move move3 = new Move(-3, 1, 95, 0); //heal
			Move move4 = new Move(0, 1, 95, 1); //block
			
		} else if (monsterName.equals("Staryu")) {
			setMaxHealthPoints(100);
			setAttackPoints(6);
			setDefensePoints(6);
			setSpeedPoints(6);
			
			//when the monster is first created, we can set these to the values above
			int healthBattle  = maxHealthPoints;
			int attackBattle  = 8;
			int defenseBattle = 0;
			int speedBattle   = 0;
			
			ArrayList<Move> attackList = new ArrayList<Move>();
			Move move1 = new Move(5, 2, 95, 1);
			Move move2 = new Move(7, 1, 75, 1); 
			Move move3 = new Move(-3, 1, 95, 0);
			Move move4 = new Move(0, 1, 95, 1); //block
			
		} else if (monsterName.equals("Nidoking")) {
			setMaxHealthPoints(150);
			setAttackPoints(8);
			setDefensePoints(6);
			setSpeedPoints(2);
			
			//when the monster is first created, we can set these to the values above
			int healthBattle  = maxHealthPoints;
			int attackBattle  = 8;
			int defenseBattle = 0;
			int speedBattle   = 0;
			
			ArrayList<Move> attackList = new ArrayList<Move>();
			Move move1 = new Move(5, 2, 95, 1);
			Move move2 = new Move(7, 1, 75, 1); 
			Move move3 = new Move(-3, 1, 95, 0);
			Move move4 = new Move(0, 1, 95, 1); //block
			
		} else if (monsterName.equals("Jolteon")) {
			setMaxHealthPoints(80);
			setAttackPoints(6);
			setDefensePoints(4);
			setSpeedPoints(8);
			
			//when the monster is first created, we can set these to the values above
			int healthBattle  = maxHealthPoints;
			int attackBattle  = 8;
			int defenseBattle = 0;
			int speedBattle   = 0;
			
			ArrayList<Move> attackList = new ArrayList<Move>();
			Move move1 = new Move(5, 2, 95, 1);
			Move move2 = new Move(7, 1, 75, 1); 
			Move move3 = new Move(-3, 1, 95, 0);
			Move move4 = new Move(0, 1, 95, 1); //block
			
		} else if (monsterName.equals("Ferguson")) {
			setMaxHealthPoints(999);
			setAttackPoints(999);
			setDefensePoints(999);
			setSpeedPoints(999);
			
			//when the monster is first created, we can set these to the values above
			int healthBattle  = maxHealthPoints;
			int attackBattle  = 999;
			int defenseBattle = 999;
			int speedBattle   = 999;
			
			ArrayList<Move> attackList = new ArrayList<Move>();
			Move move1 = new Move(120, 20, 100, 1);
			Move move2 = new Move(120, 20, 100, 1); 
			Move move3 = new Move(120, 20, 100, 0);
			Move move4 = new Move(120, 20, 100, 1); //block
		} else {
			//do nothing?! let user know something failed
		}
	}
	
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
			System.out.println("Invalid stat ID was passed to levelUp method");
		}

	}

	public void decreaseHealth(int healthDecNum) {
		healthBattle -= healthDecNum;
		if (healthBattle <= 0) {
			healthBattle = 0;
			//faint ();
		} else if (healthBattle > maxHealthPoints) {
			healthBattle = maxHealthPoints;
		}
	}

	public Move getMove(int atkNum) {
		if (atkNum >= 1 && atkNum <= 4) {
			return attackList.get(atkNum);
		} else {
			return null;
		}
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
		// TODO also set battle hp, defense, attack, etc...
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public void setSpeedPoints(int speedPoints) {
		this.speedPoints = speedPoints;
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