import java.util.ArrayList;

public interface MonsterInterface {
	int maxHealthPoints;
	int defensePoints;
	int attackPoints;
	int speedPoints;
	
	int healthBattle;
	int attackBattle;
	int defenseBattle;
	int speedBattle;
	
	ArrayList attackList;
	Move move1;
	Move move2;
	Move move3;
	Move move4;
	
	String monsterImagePath;
	String soundFilePath;
	
	public void levelUp(int statID);
	public void decreaseHealth(int healthDecNum);
	public Move getAttack();
}



skibblydibbly
