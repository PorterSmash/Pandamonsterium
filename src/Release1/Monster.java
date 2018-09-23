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
	String monsterName;


	//ImageIcon spriteImage;
	private boolean onField;
	
	public String getMonsterName() {
		return monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	public int getHealthBattle() {
		return healthBattle;
	}

	public void setHealthBattle(int healthBattle) {
		this.healthBattle = healthBattle;
	}

	public int getAttackBattle() {
		return attackBattle;
	}

	public void setAttackBattle(int attackBattle) {
		this.attackBattle = attackBattle;
	}

	public int getDefenseBattle() {
		return defenseBattle;
	}

	public void setDefenseBattle(int defenseBattle) {
		this.defenseBattle = defenseBattle;
	}

	public int getSpeedBattle() {
		return speedBattle;
	}

	public void setSpeedBattle(int speedBattle) {
		this.speedBattle = speedBattle;
	}

	public ArrayList<Move> getAttackList() {
		return attackList;
	}

	public void setAttackList(ArrayList<Move> attackList) {
		this.attackList = attackList;
	}

	public Move getMove1() {
		return move1;
	}

	public void setMove1(Move move1) {
		this.move1 = move1;
	}

	public Move getMove2() {
		return move2;
	}

	public void setMove2(Move move2) {
		this.move2 = move2;
	}

	public Move getMove3() {
		return move3;
	}

	public void setMove3(Move move3) {
		this.move3 = move3;
	}

	public Move getMove4() {
		return move4;
	}

	public void setMove4(Move move4) {
		this.move4 = move4;
	}

	public String getMonsterImagePath() {
		return monsterImagePath;
	}

	public void setMonsterImagePath(String monsterImagePath) {
		this.monsterImagePath = monsterImagePath;
	}

	public String getSoundFilePath() {
		return soundFilePath;
	}

	public void setSoundFilePath(String soundFilePath) {
		this.soundFilePath = soundFilePath;
	}

/*	public ImageIcon getSpriteImage() {
		return spriteImage;
	}

	public void setSpriteImage(ImageIcon spriteImage) {
		this.spriteImage = spriteImage;
	}*/

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public int getSpeedPoints() {
		return speedPoints;
	}

	

	public void monsterFactory(String monsterName) {
		String fileName = "MonsterInfo.txt";
		File monFile = new File(fileName);
        Scanner scnr = null;
        try {
            scnr = new Scanner(monFile);
            while (scnr.hasNextLine()) {
                if (scnr.next().equals(monsterName)) {
                	
                    scnr.nextLine(); //skip past the monster name
                    
                    //there's got to be a better looking way to do this, but I can't think of anything at the moment
                    this.maxHealthPoints = Integer.parseInt(scnr.nextLine());
                    this.defensePoints = Integer.parseInt(scnr.nextLine());
                    this.attackPoints = Integer.parseInt(scnr.nextLine());
                    this.speedPoints = Integer.parseInt(scnr.nextLine());
                    
                    
                    //there's got to be a cleaner way to do this but I can't think of it at the moment
                    this.move1 = new Move(Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()));
                    this.move2 = new Move(Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()));
                    this.move3 = new Move(Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()));
                    this.move4 = new Move(Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()), Integer.parseInt(scnr.nextLine()));
                    
                    this.monsterImagePath = scnr.nextLine();
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
            e.printStackTrace();
        } finally {
            if (scnr != null) {
                scnr.close();
            }
        }
	}
		/*if (monsterName.equals("Charizard")) {
			this.monsterName = "Charizard";
			setMaxHealthPoints(120);
			setAttackPoints(4);
			setDefensePoints(8);
			setSpeedPoints(6);
			
			//when the monster is first created, we can set these to the values above
			healthBattle  = maxHealthPoints;
			int attackBattle  = 8;
			int defenseBattle = 0;
			int speedBattle   = 0;
			
			ArrayList<Move> attackList = new ArrayList<Move>();
			Move move1 = new Move(5, 2, 95, 1); //light attack
			Move move2 = new Move(7, 1, 75, 1); //heavy attack
			Move move3 = new Move(-3, 1, 95, 0); //heal
			Move move4 = new Move(0, 1, 95, 1); //block
			return;
		} else if (monsterName.equals("Staryu")) {
			this.monsterName = "Staryu";
			setMaxHealthPoints(100);
			setAttackPoints(6);
			setDefensePoints(6);
			setSpeedPoints(6);
			
			//when the monster is first created, we can set these to the values above
			healthBattle  = maxHealthPoints;
			int attackBattle  = 8;
			int defenseBattle = 0;
			int speedBattle   = 0;
			
			ArrayList<Move> attackList = new ArrayList<Move>();
			Move move1 = new Move(5, 2, 95, 1);
			Move move2 = new Move(7, 1, 75, 1); 
			Move move3 = new Move(-3, 1, 95, 0);
			Move move4 = new Move(0, 1, 95, 1); //block
			return;
		} else if (monsterName.equals("Nidoking")) {
			this.monsterName = "Nidoking";
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
			return;
		} else if (monsterName.equals("Jolteon")) {
			this.monsterName = "Jolteon";
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
			return;
		} else if (monsterName.equals("Ferguson")) {
			this.monsterName = "Ferguson";
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
			return;
		} else {
			//do nothing?! let user know something failed
			System.out.println("fail");
		}
	}*/
	
	//this level up method will be useless until release 2, because it's just pick a monster and fight atm
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
			return null; //tes
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