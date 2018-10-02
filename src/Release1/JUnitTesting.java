package Release1;

import static org.junit.Assert.*;
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
	
	
	
	/**
	 * *********Monster Class*************
	 * monsterFactory
	 * level up
	 * 	Case 1, 2, 3, 4
	 *************************************/
	/**
	 * 
	 */
	
}
