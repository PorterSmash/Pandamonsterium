
public /*interface */class Move {
	
	int attackPower; //some number between 1 and 10
	int critChance; //represents a percentage
	int hitChance;
	int monsterApplied; //represents which monster the move is applied to. 1 if it is applied to the enemy, 0 if it is applied to itself
	
	public Move(int atkPwr, int crtChc, int htChc) {
		attackPower = atkPwr;
		critChance = crtChc;
		hitChance = htChc;
	}
}
//shouldn't this be an interface?
