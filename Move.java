
public /*interface */class Move {
	
	int attackPower; //some number between -10 and 10, negative so that the monster can heal
	int critChance; //represents a percentage
	int hitChance;
	int moveTarget; //represents which monster the move is applied to. 1 if it is applied to the enemy, 0 if it is applied to itself
	
	
	public Move(int atkPwr, int crtChc, int htChc, int mveTgt) {
		attackPower = atkPwr;
		critChance = crtChc;
		hitChance = htChc;
		moveTarget = mveTgt;
	}
}
//shouldn't this be an interface?
