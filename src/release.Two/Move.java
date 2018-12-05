package release.Two;

/**********************************************************************
 * Move holds the four types of moves the monsters can 
 * possess. 
 * @author Michelle Vu, Alex Porter, and Justin Kaukonen 
 * @version Fall 2018
 ***********************************************************************/
public class Move {
	/** How strong the move is. */
	private int attackPower;
	
	/** the chance they will get an attack bonus.*/
	private int critChance;
	
	/** the chance they will land the attack.*/
	private int hitChance;
	
	/** who the target is (enemy)*/
	private int moveTarget;
	
	/******************************************************************
	 * Returns attack power.
	 * @return attackPower
	 *****************************************************************/
	public int getAttackPower() {
		return attackPower;
	}
	
	/******************************************************************
	 * Sets attack power.
	 * @param attackPower To set
	 *****************************************************************/
	public void setAttackPower(final int attackPower) {
		this.attackPower = attackPower;
	}
	
	/******************************************************************
	 * Returns critChance.
	 * @return critChance
	 *****************************************************************/
	public int getCritChance() {
		return critChance;
	}
	
	/******************************************************************
	 * Sets critChance.
	 * @param critChance Int to set
	 *****************************************************************/
	public void setCritChance(final int critChance) {
		this.critChance = critChance;
	}
	
	/******************************************************************
	 * Returns hitChance.
	 * @return hitChance
	 *****************************************************************/
	public int getHitChance() {
		return hitChance;
	}
	
	/******************************************************************
	 * Sets hitChance.
	 * @param hitChance Int to set
	 *****************************************************************/
	public void setHitChance(final int hitChance) {
		this.hitChance = hitChance;
	}
	
	/******************************************************************
	 * Returns moveTarget.
	 * @return moveTarget
	 *****************************************************************/
	public int getMoveTarget() {
		return moveTarget;
	}
	
	/******************************************************************
	 * Sets move target.
	 * @param moveTarget Int to set
	 *****************************************************************/
	public void setMoveTarget(final int moveTarget) {
		this.moveTarget = moveTarget;
	}

	/******************************************************************
	 * Constructor that sets up the move's base stats.
	 * @param mveTgt Who the damage is applied to 
	 * @param atkPwr Strength of the move
	 * @param crtChc Chance the move does double damage
	 * @param htChc Chance the attack hits
	 *****************************************************************/
	public Move(final int atkPwr, final int crtChc, 
			final int htChc, final int mveTgt) {
		attackPower = atkPwr;
		critChance = crtChc;
		hitChance = htChc;
		moveTarget = mveTgt;
	}
	
	/******************************************************************
	 * Creates a string that returns each stat.
	 * @return String that represents each stat.
	 *****************************************************************/
	@Override
	public String toString() {
		String toReturn = "";
		toReturn += "Attack Power: " + this.attackPower;
		toReturn += "\nCrit Chance: " + this.critChance;
		toReturn += "\nHit Chance: " + this.hitChance;
		toReturn += "\n Move Target: " + this.moveTarget;
		return toReturn;
	}
}