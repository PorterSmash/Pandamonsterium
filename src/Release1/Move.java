package Release1;

/**********************************************************************
 * Move holds the four types of moves the monsters can 
 * possess. 
 * @author Michelle Vu, Alex Porter, and Justin Kaukonen 
 * @version Fall 2018
 ***********************************************************************/
public class Move {
	/** how strong the move is*/
	int attackPower;
	
	/** the chance they will get an attack bonus*/
	int critChance;
	
	/** the chance they will land the attack*/
	int hitChance;
	
	/** move target?*/
	int moveTarget;
	
	/******************************************************************
	 * Constructor that sets up the move's base stats
	 * @param how strong the move's attack is
	 * @param the chance the move has to get an attack bonus
	 * @param the chance the move will land
	 * @param mveTgt
	 *****************************************************************/
	public Move(int atkPwr, int crtChc, int htChc, int mveTgt) {
		attackPower = atkPwr;
		critChance = crtChc;
		hitChance = htChc;
		moveTarget = mveTgt;
	}
	
	
	/******************************************************************
	 * Creates a string that returns each stat
	 *****************************************************************/
	public String toString() {
		String toReturn = "";
		toReturn += "Attack Power: " + this.attackPower;
		toReturn += "\nCrit Chance: " + this.critChance;
		toReturn += "\nHit Chance: " + this.hitChance;
		toReturn += "\n Move Target: " + this.moveTarget;
		return toReturn;
	}
}