package Release1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class JUnitTesting {
	@Test
	public void testSetMove1() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1,1,1,1);
		testMonster.setMove1(testMove);
		assertEquals(testMonster.getMove1(), testMove);
	}
	@Test
	public void testSetMove2() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1,1,1,1);
		testMonster.setMove2(testMove);
		assertEquals(testMonster.getMove2(), testMove);
	}
	@Test
	public void testSetMove3() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1,1,1,1);
		testMonster.setMove3(testMove);
		assertEquals(testMonster.getMove3(), testMove);
	}
	@Test
	public void testSetMove4() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1,1,1,1);
		testMonster.setMove4(testMove);
		assertEquals(testMonster.getMove4(), testMove);
	}
	@Test
	public void testDefenseBattle() {
		Monster testMonster = new Monster();
		testMonster.setDefenseBattle(5);
		assertEquals(testMonster.getDefenseBattle(), 5);
	}
	@Test
	public void testDefenseMax() {
		Monster testMonster = new Monster();
		testMonster.setDefensePoints(5);
		assertEquals(testMonster.getDefensePoints(), 5);
	}
	@Test
	public void testAttackBattle() {
		Monster testMonster = new Monster();
		testMonster.setAttackBattle(5);
		assertEquals(testMonster.getAttackBattle(), 5);
	}
	@Test
	public void testAttackMax() {
		Monster testMonster = new Monster();
		testMonster.setAttackPoints(5);
		assertEquals(testMonster.getAttackPoints(), 5);
	}
	@Test
	public void testSpeedBattle() {
		Monster testMonster = new Monster();
		testMonster.setSpeedBattle(5);
		assertEquals(testMonster.getSpeedBattle(), 5);
	}
	@Test
	public void testSpeedMax() {
		Monster testMonster = new Monster();
		testMonster.setSpeedPoints(5);
		assertEquals(testMonster.getSpeedPoints(), 5);
	}
	@Test
	public void testHealthBattle() {
		Monster testMonster = new Monster();
		testMonster.setHealthBattle(5);
		assertEquals(testMonster.getHealthBattle(), 5);
	}
	@Test
	public void testHealthMax() {
		Monster testMonster = new Monster();
		testMonster.setMaxHealthPoints(5);
		assertEquals(testMonster.getMaxHealthPoints(), 5);
	}
	@Test
	public void testMonsterName() {
		Monster testMonster = new Monster();
		testMonster.setMonsterName("This is a test");
		assertEquals(testMonster.getMonsterName(), "This is a test");
	}
	
	@Test
	public void testImagePath() {
		Monster testMonster = new Monster();
		testMonster.setMonsterImagePath("This is a test");
		assertEquals(testMonster.getMonsterImagePath(), "This is a test");
	}
	@Test
	public void testSoundPath() {
		Monster testMonster = new Monster();
		testMonster.setSoundFilePath("This is a test");
		assertEquals(testMonster.getSoundFilePath(), "This is a test");
	}
	@Test
	public void testOnField() {
		Monster testMonster = new Monster();
		testMonster.setOnField(true);
		assertEquals(testMonster.getOnField(), true);
	}
	@Test
	public void testResetStats() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		testMonster.setAttackPoints(1);
		testMonster.setDefensePoints(2);
		testMonster.setSpeedPoints(3);
		testMonster.setMaxHealthPoints(4);
		testMonster.resetStats();
		assertEquals(testMonster.getDefenseBattle(), testMonster.getDefensePoints());
		assertEquals(testMonster.getAttackBattle(), testMonster.getAttackPoints());
		assertEquals(testMonster.getSpeedBattle(), testMonster.getSpeedPoints());
		assertEquals(testMonster.getHealthBattle(), testMonster.getMaxHealthPoints());
	}
	@Test
	public void testLevelUP1() {
		Monster testMonster = new Monster();
		testMonster.setMaxHealthPoints(1);
		testMonster.levelUp(1);
		assertEquals(testMonster.getMaxHealthPoints(), 2);
	}
	@Test
	public void testLevelUP2() {
		Monster testMonster = new Monster();
		testMonster.setDefensePoints(1);
		testMonster.levelUp(2);
		assertEquals(testMonster.getDefensePoints(), 2);
	}
	
	@Test
	public void testLevelUP3() {
		Monster testMonster = new Monster();
		testMonster.setAttackPoints(1);
		testMonster.levelUp(3);
		assertEquals(testMonster.getAttackPoints(), 2);
	}
	@Test
	public void testLevelUP4() {
		Monster testMonster = new Monster();
		testMonster.setSpeedPoints(1);
		testMonster.levelUp(4);
		assertEquals(testMonster.getSpeedPoints(), 2);
	}
	@Test
	public void testMonsterFactoryChar() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		assertEquals(testMonster.getMaxHealthPoints(), 120);
		assertEquals(testMonster.getAttackPoints(), 4);
		assertEquals(testMonster.getDefensePoints(), 8);
		assertEquals(testMonster.getSpeedPoints(), 6);
	}
	@Test
	public void testMonsterFactoryStar() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Staryu");
		assertEquals(testMonster.getMaxHealthPoints(), 100);
		assertEquals(testMonster.getAttackPoints(), 6);
		assertEquals(testMonster.getDefensePoints(), 6);
		assertEquals(testMonster.getSpeedPoints(), 6);
	}
	@Test
	public void testDecreaseHealth() {
		Monster testMonster = new Monster();
		testMonster.setHealthBattle(100);
		testMonster.setMaxHealthPoints(100);
		System.out.println(testMonster.getHealthBattle());
		testMonster.decreaseHealth(10);
		System.out.println(testMonster.getHealthBattle());
		assertEquals(testMonster.getHealthBattle(), 90);
	}
	@Test
	public void testDecreaseHealthKill() {
		Monster testMonster = new Monster();
		testMonster.setHealthBattle(100);
		testMonster.decreaseHealth(101);
		assertEquals(testMonster.getHealthBattle(), 0);
	}
	@Test
	public void testDecreaseHealthOver() {
		Monster testMonster = new Monster();
		testMonster.setMaxHealthPoints(100);
		testMonster.setHealthBattle(100);
		testMonster.decreaseHealth(-1);
		assertEquals(testMonster.getHealthBattle(), 100);
	}
	/**
	 * Monster Class is done
	 * Begin Logic class
	 */
	@Test
	public void testChangeTurn() {
		Logic testLogic = new Logic();
		int turn = testLogic.getTurn();
		testLogic.changeTurn();
		assertNotEquals(turn, testLogic.getTurn());
	}
	@Test
	public void testSpecificConstructor() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		tester.monsterFactory("Charizard");
		team1.add(tester);
		team2.add(tester);
		testLogic.setTeamsAndMons(team1, team2, 0, 0);
		Logic secondTest = new Logic(team1, team2);
		assertEquals(secondTest.getTeam1(), testLogic.getTeam1());
		assertEquals(secondTest.getTeam2(), testLogic.getTeam2());
		assertEquals(secondTest.getMon1(), testLogic.getMon1());
		assertEquals(secondTest.getMon2(), testLogic.getMon2());
	}
	@Test
	public void testSetTeams() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		team1.add(new Monster());
		testLogic.setTeams(team1, team1);
		assertEquals(testLogic.getTeam1(), team1);
		assertEquals(testLogic.getTeam2(), team1);
	}
	@Test
	public void testGetMons() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		tester.monsterFactory("Charizard");
		team1.add(tester);
		team2.add(tester);
		testLogic.setTeamsAndMons(team1, team2, 0, 0);
		assertEquals( testLogic.getTeam1().get(0), tester);
		assertEquals(testLogic.getTeam2().get(0), tester);
	}
	/**
	 * there may be a way to test doDamage but at the moment I can't be bothered to try - Alex P, 11:23pm 10/3/2018
	 */
	/**
	 * End Logic
	 * Begin MonsterGUI
	 */
	/**Impossible to test MonsterGUI
	 * End MonsterGUI
	 * End all tests? (coverage as, see if I can get more)
	 * 
	 */
}