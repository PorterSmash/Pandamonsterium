package release.Two;

public interface MonsterInterface {
	public void monsterFactory(String name);
	public void levelUp(int statID);
	public void decreaseHealth(int healthDecNum);
	public Move getMove(int moveNum);
}
