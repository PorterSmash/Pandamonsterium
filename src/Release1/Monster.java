package Release1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
 import javax.swing.ImageIcon;
 
 /*********************************************************************
  * Monster class that holds all of the monsters statistics that 
  * implements the monster interface.
  * @author Michelle Vu, Alex Porter, and Justin Kaukonen 
  * @version Fall 2018
  ********************************************************************/
 public class Monster implements MonsterInterface {
	/** the monsters max health points*/
	int maxHealthPoints;
	
	/** how much initial defense the monster has*/
	int defensePoints;
	
	/** how strong the monster initially is*/
	int attackPoints;
	
	/** how fast the monster initially is*/
	int speedPoints;
	
	/** the monsters max health points during battle*/
	int healthBattle;
	
	/** how strong the monster is during battle*/
	int attackBattle;
	
	/** how much defense the monster has during battle*/
	int defenseBattle;
	
	/** how fast the monster is during battle*/
	int speedBattle;
	
	/** what level the monster currently is*/
	int monsterLevel;
	
	/** array list of the moves*/
 	ArrayList<Move> attackList;
 	
 	/** normal attack move*/
	Move move1;
	
	/** heavy attack move */
	Move move2;
	
	/** heal move */
	Move move3;
	
	/** other attack move*/
	Move move4;
	
	/** string that holds the name of image */
 	String monsterImagePath;
 	
 	/** string that holds the name of the sound file */
	String soundFilePath;
	
	/** string that holds the monster name*/
	String monsterName;
	
	/** holds the image of the monster*/
 	ImageIcon spriteImage;
 	
 	/** tells whether the monster is on field or not*/
	private boolean onField;
	
	
	public int getLevel() {
		return monsterLevel;
	}
	
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
 	public ImageIcon getSpriteImage() {
		return spriteImage;
	}
 	public void setSpriteImage(ImageIcon spriteImage) {
		this.spriteImage = spriteImage;
	}
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
 	/*
	 * Reads all the stats of a monster type and sets them for this monster.
	 */
	public void monsterFactory(String monName) {
		Scanner fileIn = null;
		String fileName = "MonsterInfo.txt";
		try {
			// Creates a scanner, and reads each line in the file looking for monster name.
			fileIn = new Scanner(new File(fileName));
			String nextLine;
			while (fileIn.hasNextLine()) {
				nextLine = fileIn.nextLine();
				if (nextLine.equals(monName)) {
					// Reads the activity name, ON icon file path, and OFF icon file path.
					monsterName = nextLine;
					monsterLevel = 1;
					setMaxHealthPoints(fileIn.nextInt());
					setAttackPoints(fileIn.nextInt());
					setDefensePoints(fileIn.nextInt());
					setSpeedPoints(fileIn.nextInt());
					
					//when the monster is first created, we can set these to the values above
					healthBattle  = maxHealthPoints;
					attackBattle  = attackPoints;
					defenseBattle = defensePoints;
					speedBattle   = speedPoints;
					
					attackList = new ArrayList<Move>();
					Move move1 = new Move(fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt()); //light attack
					Move move2 = new Move(fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt()); //heavy attack
					Move move3 = new Move(fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt()); //heal
					Move move4 = new Move(fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt()); //block
					
					this.move1 = move1;
					this.move2 = move2;
					this.move3 = move3;
					this.move4 = move4;
					
					monsterImagePath = fileIn.nextLine();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fileIn != null) {
				fileIn.close();
			}
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