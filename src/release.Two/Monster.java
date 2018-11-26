package release.Two;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
//import java.util.ArrayList;
import java.util.Scanner;
//import javax.swing.ImageIcon;

import javafx.scene.control.ChoiceDialog;
 
 /*********************************************************************
  * Monster class that holds all of the monsters statistics and 
  * represents and in game monster.
  * @author Michelle Vu, Alex Porter, and Justin Kaukonen 
  * @version Fall 2018
  ********************************************************************/
 public class Monster {
	/** the monsters max health points.*/
	private int maxHealthPoints;
	
	/** how much initial defense the monster has.*/
	private int defensePoints;
	
	/** how strong the monster initially is.*/
	private int attackPoints;
	
	/** how fast the monster initially is.*/
	private int speedPoints;
	
	/** the monsters max health points during battle.*/
	private int healthBattle;
	
	/** how strong the monster is during battle.*/
	private int attackBattle;
	
	/** how much defense the monster has during battle. */
	private int defenseBattle;
	
	/** how fast the monster is during battle.*/
	private int speedBattle;
	
	/** what level the monster currently is.*/
	private int monsterLevel;
 	
 	/** normal attack move.*/
	private Move move1;
	
	/** heavy attack move.*/
	private Move move2;
	
	/** heal move. */
	private Move move3;
	
	/** other attack move.*/
	private Move move4;
	/** string that holds the name of image. */
	private String monsterImagePath;
 	
 	/** string that holds the name of the sound file. */
	private String soundFilePath;
	
	/** string that holds the monster name.*/
	private String monsterName;
 	
 	/** tells whether the monster is on field or not.*/
	private boolean onField;
	
	/******************************************************************
	 * This method is for release 2.
	 * @return Level of the monster
	 *****************************************************************/
	public final int getLevel() {
		return monsterLevel;
	}
	
	/******************************************************************
	 * Returns the Monsters name in string.
	 * @return String name of monster
	 *****************************************************************/
	public String getMonsterName() {
		return monsterName;
	}
	
	/******************************************************************
	 * Sets the monster name depending on the parameter.
	 * @param monsterName Monster name to set
	 *****************************************************************/
 	public void setMonsterName(final String monsterName) {
		this.monsterName = monsterName;
	}
 	
 	/******************************************************************
 	 * Gets the health of monster during battle.
 	 * @return Battle health of monster
 	 ******************************************************************/
	public int getHealthBattle() {
		return healthBattle;
	}
	
	/******************************************************************
	 * Sets the health of the monster during battle.
	 * @param healthBattle Sets health of monster
	 *****************************************************************/
 	public void setHealthBattle(final int healthBattle) {
		this.healthBattle = healthBattle;
	}
 	
 	/******************************************************************
 	 * Gets the monster's attack stat during battle.
 	 * @return Attack battle stat of monster
 	 *****************************************************************/
 	public int getAttackBattle() {
		return attackBattle;
	}
 	
 	/******************************************************************
 	 * Sets the monster's attack stat during battle.
 	 * @param attackBattle Stat to set
 	 *****************************************************************/
 	public void setAttackBattle(final int attackBattle) {
		this.attackBattle = attackBattle;
	}
 	
 	/******************************************************************
 	 * Returns the monster's defense stat during battle.
 	 * @return Defense battle stat
 	 ******************************************************************/
 	public int getDefenseBattle() {
		return defenseBattle;
	}
 	
 	/******************************************************************
 	 * Sets the monster's defense stat during battle.
 	 * @param defenseBattle Stat to set
 	 *****************************************************************/
 	public void setDefenseBattle(final int defenseBattle) {
		this.defenseBattle = defenseBattle;
	}
 	
 	/******************************************************************
 	 * Returns the monster's speed stat during battle.
 	 * @return Speed battle stat
 	 ******************************************************************/
 	public int getSpeedBattle() {
		return speedBattle;
	}
 	
 	/******************************************************************
 	 * Set the monster's speed stat during battle.
 	 * @param speedBattle Stat to set
 	 *****************************************************************/
 	public void setSpeedBattle(final int speedBattle) {
		this.speedBattle = speedBattle;
	}
 	
 	/******************************************************************
 	 * Returns the first move of the monster (normal attack).
 	 * @return First move which is normal attack
 	 *****************************************************************/
 	public Move getMove1() {
		return move1;
	}
 	
 	/******************************************************************
 	 * Sets the first move, which is normal attack.
 	 * @param move1 First move to set
 	 *****************************************************************/
 	public void setMove1(final Move move1) {
		this.move1 = move1;
	}
 	
 	/******************************************************************
 	 * Returns the second move of the monster (Heavy Attack).
 	 * @return Second move which is heavy attack.
 	 *****************************************************************/
 	public Move getMove2() {
		return move2;
	}
 	
 	/******************************************************************
 	 * Sets the second move, which is heavy attack.
 	 * @param move2 Second move to set
 	 *****************************************************************/
 	public void setMove2(final Move move2) {
		this.move2 = move2;
	}
 	
 	/******************************************************************
 	 * Returns the third move of the monster (Heal).
 	 * @return Third move which is heal
 	 ******************************************************************/
 	public Move getMove3() {
		return move3;
	}
 	
 	/******************************************************************
 	 * Sets the third move, which is heal.
 	 * @param move3 Third move to set
 	 *****************************************************************/
 	public void setMove3(final Move move3) {
		this.move3 = move3;
	}
 	
 	/******************************************************************
 	 * Returns the fourth move of the monster (Special Attack).
 	 * @return Fourth move which is special attack.
 	 *****************************************************************/
 	public Move getMove4() {
		return move4;
	}
 	
 	/******************************************************************
 	 * Sets the fourth move, which is special attack.
 	 * @param move4 Fourth move to set
 	 *****************************************************************/
 	public void setMove4(final Move move4) {
		this.move4 = move4;
	}
 	
 	/******************************************************************
 	 * Returns the image path in png form.
 	 * @return Image path of the monster
 	 *****************************************************************/
 	public String getMonsterImagePath() {
		return monsterImagePath;
	}
 	
 	/******************************************************************
 	 * Sets the image path in png form.
 	 * @param monsterImagePath String for image path
 	 *****************************************************************/
 	public void setMonsterImagePath(final String monsterImagePath) {
		this.monsterImagePath = monsterImagePath;
	}
 	
 	/******************************************************************
 	 * Returns the sound file path for the monster.
 	 * @return sound file path
 	 *****************************************************************/
 	public String getSoundFilePath() {
		return soundFilePath;
	}
 	
 	/******************************************************************
 	 * Sets the sound file path for the monster.
 	 * @param soundFilePath Path for the sound file
 	 *****************************************************************/
 	public void setSoundFilePath(final String soundFilePath) {
		this.soundFilePath = soundFilePath;
	}
 	
 	/******************************************************************
 	 * Returns the max health the monster has.
 	 * @return Max health stat
 	 *****************************************************************/
 	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}
 	
 	/******************************************************************
 	 * Returns the max defense points the monster has.
 	 * @return Max defense points
 	 *****************************************************************/
 	public int getDefensePoints() {
		return defensePoints;
	}
 	
 	/******************************************************************
 	 * Returns the max attack points the monster has.
 	 * @return Max attack points
 	 *****************************************************************/
 	public int getAttackPoints() {
		return attackPoints;
	}
 	
 	/******************************************************************
 	 * Returns the max speed points the monster has.
 	 * @return Max speed points
 	 *****************************************************************/
 	public int getSpeedPoints() {
		return speedPoints;
	}
 	
	/******************************************************************
 	 * Sets the max health points of the monster.
 	 * @param maxHealthPoints Value to set
 	 *****************************************************************/
 	public void setMaxHealthPoints(final int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}
 	
 	/******************************************************************
 	 * Set max defense points of the monster.
 	 * @param defensePoints Value to set
 	 *****************************************************************/
 	public void setDefensePoints(final int defensePoints) {
		this.defensePoints = defensePoints;
	}
 	
 	/******************************************************************
 	 * Set max attack of the monster.
 	 * @param attackPoints Value to set
 	 *****************************************************************/
 	public void setAttackPoints(final int attackPoints) {
		this.attackPoints = attackPoints;
	}
 	
 	/******************************************************************
 	 *  Set max speed of the monster.
 	 * @param speedPoints Value to set
 	 *****************************************************************/
 	public void setSpeedPoints(final int speedPoints) {
		this.speedPoints = speedPoints;
	}
 	
	/******************************************************************
	 * Set the monster to being on the field or not.
	 * @param flag Bool to set
	 *****************************************************************/
	public void setOnField(final boolean flag) {
		onField = flag;
	}
	
	/******************************************************************
	 * Returns onField value, aka whether the monster is on field.
	 * @return Boolean onField
	 *****************************************************************/
	public boolean getOnField() {
		return onField;
	}
	
 	/******************************************************************
 	 * Generates monsters by reading from a file.
 	 * @param monName String for monster to generate
 	 *****************************************************************/
	public void monsterFactory(final String monName) {
		Scanner fileIn = null;
		String fileName = "MonsterInfo.txt";
		try {
			//Reads each line in the file looking for monster name.
			fileIn = new Scanner(
					new File(fileName), "UTF-8");		
			String nextLine;
			while (fileIn.hasNextLine()) {
				nextLine = fileIn.nextLine();
				if (nextLine.equals(monName)) {
		// Read the activity name, ON icon file path, and OFF file path.
					monsterName = nextLine;
					monsterLevel = 1;
					
					
					int[] statArray = new int[21];
					for (int i = 0; i < 20; i++) {
						statArray[i] = Integer.parseInt(
				fileIn.nextLine());
						System.out.println(statArray[i]);
					}
					
					setMonsterImagePath(fileIn.nextLine());
					setMaxHealthPoints(statArray[0]);
					setAttackPoints(statArray[1]);
					setDefensePoints(statArray[2]);
					setSpeedPoints(statArray[3]);
					
		//when a monster is created, we set these to the values above
					healthBattle  = maxHealthPoints;
					attackBattle  = attackPoints;
					defenseBattle = defensePoints;
					speedBattle   = speedPoints;
					
					Move move1 = new Move(statArray[4], 
						statArray[5], statArray[6],
						statArray[7]); //light attack
					Move move2 = new Move(statArray[8],
							statArray[9], 
						statArray[10],
						statArray[11]); //heavy attack
					Move move3 = new Move(statArray[12],
						statArray[13], statArray[14],
						statArray[15]); //heal
					Move move4 = new Move(statArray[16],
						statArray[17], 
						statArray[18], 
						statArray[19]); //block
					
					this.move1 = move1;
					this.move2 = move2;
					this.move3 = move3;
					this.move4 = move4;	
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
		monsterLevel = (maxHealthPoints / 10)
				+ defensePoints 
				+ attackPoints
				+ speedPoints - 27;
	}
	
	public void attemptUpdateLevel() {
			List<String> choices = new ArrayList<>();
			choices.add("Health");
			choices.add("Attack");
			choices.add("Defense");
			choices.add("Speed");

			ChoiceDialog<String> dialog = new ChoiceDialog<>("Health", choices);
			dialog.setTitle("You leveled up!");
			dialog.setHeaderText("Congratulations, your monster " + this.getMonsterName() + " leveled up to level " + monsterLevel + "!");
			dialog.setContentText("Pick a stat to level up:");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				int choice;
				if (result.get() == "Health") {
					choice = 1;
				} else if (result.get() == "Attack") {
					choice = 2;
				} else if (result.get() == "Defense") {
					choice = 3;
				} else {
					choice = 4;
				}
				levelUp(choice);
			    System.out.println("Your choice: " + result.get());
			}
			
			monsterLevel++;
	}
	
	/******************************************************************
	 * Will be used for release 2, this improves the monster.
	 * @param statID Stat to be increased
	 *****************************************************************/
	public void levelUp(final int statID) {
		healthBattle = maxHealthPoints;
		switch (statID) {
		case 1:
			maxHealthPoints += 10;
			break;
		case 2:
			attackPoints += 1;
			break;
		case 3:
			defensePoints += 1;
			break;
		case 4:
			speedPoints += 1;
			break;
		default:
			System.out.println("Invalid stat ID was "
					+ "passed to levelUp method");
		}
		resetStats();
//		monsterLevel += 1; unnecessary since level is calculated based on stats
 	}
	
	/******************************************************************
	 * Decreases the health of the monster.
	 * @param healthDecNum Value to decrease by
	 *****************************************************************/
 	public void decreaseHealth(final int healthDecNum) {
		healthBattle -= healthDecNum;
		if (healthBattle <= 0) {
			healthBattle = 0;
		} else if (healthBattle > maxHealthPoints) {
			healthBattle = maxHealthPoints;
		}
	}
 	
	/******************************************************************
	 * Resets a monsters stats after they faint.
	 *****************************************************************/
	public void resetStats() {
		healthBattle = maxHealthPoints;
		attackBattle = attackPoints;
		speedBattle = speedPoints;
		defenseBattle = defensePoints;
	}
	/**
	 * Sets all the stats in the monster to 0
	 */
	public void setAllZero() {
		maxHealthPoints = 0;
		attackPoints = 0;
		speedPoints = 0;
		defensePoints = 0;
		monsterLevel = 0;
		resetStats();
	}
	/**
	 * Set the monster's level
	 */
	public void resetMonsterLevel() {
		monsterLevel = (maxHealthPoints / 10)
				+ defensePoints 
				+ attackPoints
				+ speedPoints - 27;
	}
}