package Release1;

public class Move {
	int attackPower;
	int critChance;
	int hitChance;
	int moveTarget;
	
	public Move(int atkPwr, int crtChc, int htChc, int mveTgt) {
		attackPower = atkPwr;
		critChance = crtChc;
		hitChance = htChc;
		moveTarget = mveTgt;
	}
	public String toString() {
		String toReturn = "";
		toReturn += "Attack Power: " + this.attackPower;
		toReturn += "\nCrit Chance: " + this.critChance;
		toReturn += "\nHit Chance: " + this.hitChance;
		toReturn += "\n Move Target: " + this.moveTarget;
		return toReturn;
	}
}
