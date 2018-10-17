package release.One;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * This class tests the classes and methods in the 
 * Release1 package.
 * @author alexj
 *
 */
public class JUnitTesting {
	/**********************************************
	 * Test.
	 *********************************************/
	@Test
	public void testMonsterLevel() {
		Monster testMonster = new Monster();
		assertEquals(testMonster.getLevel(), 0);
	}
	
	/**********************************************
	 * Test level up of monster.
	 *********************************************/
	@Test
	public void testInvalidLevelUp() {
		Monster testMonster = new Monster();
		testMonster.levelUp(10);
		System.out.println("The above line should "
				+ "read that an invalid stat ID "
				+ "was passed to the method");
	}
	/**********************************************
	 * Test setting first move.
	 *********************************************/
	@Test
	public void testSetMove1() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1, 1, 1, 1);
		testMonster.setMove1(testMove);
		assertEquals(testMonster.getMove1(), testMove);
	}
	/**********************************************
	 * Test setting second move.
	 *********************************************/
	@Test
	public void testSetMove2() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1, 1, 1, 1);
		testMonster.setMove2(testMove);
		assertEquals(testMonster.getMove2(), testMove);
	}
	/**********************************************
	 * Test setting third move.
	 *********************************************/
	@Test
	public void testSetMove3() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1, 1, 1, 1);
		testMonster.setMove3(testMove);
		assertEquals(testMonster.getMove3(), testMove);
	}
	/**********************************************
	 * Test setting fourth move.
	 *********************************************/
	@Test
	public void testSetMove4() {
		Monster testMonster = new Monster();
		Move testMove = new Move(1, 1, 1, 1);
		testMonster.setMove4(testMove);
		assertEquals(testMonster.getMove4(), testMove);
	}
	/**********************************************
	 * Test get/set of defense..
	 *********************************************/
	@Test
	public void testDefenseBattle() {
		Monster testMonster = new Monster();
		testMonster.setDefenseBattle(5);
		assertEquals(testMonster.getDefenseBattle(), 5);
	}
	/**********************************************
	 * Test get/set max defense.
	 *********************************************/
	@Test
	public void testDefenseMax() {
		Monster testMonster = new Monster();
		testMonster.setDefensePoints(5);
		assertEquals(testMonster.getDefensePoints(), 5);
	}
	/**********************************************
	 * Test get/set attack.
	 *********************************************/
	@Test
	public void testAttackBattle() {
		Monster testMonster = new Monster();
		testMonster.setAttackBattle(5);
		assertEquals(testMonster.getAttackBattle(), 5);
	}
	/**********************************************
	 * Test get/set max attack.
	 *********************************************/
	@Test
	public void testAttackMax() {
		Monster testMonster = new Monster();
		testMonster.setAttackPoints(5);
		assertEquals(testMonster.getAttackPoints(), 5);
	}
	/**********************************************
	 * Test get/set speed.
	 *********************************************/
	@Test
	public void testSpeedBattle() {
		Monster testMonster = new Monster();
		testMonster.setSpeedBattle(5);
		assertEquals(testMonster.getSpeedBattle(), 5);
	}
	/**********************************************
	 * Test get/set max speed.
	 *********************************************/
	@Test
	public void testSpeedMax() {
		Monster testMonster = new Monster();
		testMonster.setSpeedPoints(5);
		assertEquals(testMonster.getSpeedPoints(), 5);
	}
	/**********************************************
	 * Test get/set health.
	 *********************************************/
	@Test
	public void testHealthBattle() {
		Monster testMonster = new Monster();
		testMonster.setHealthBattle(5);
		assertEquals(testMonster.getHealthBattle(), 5);
	}
	/**********************************************
	 * Test get/set max health.
	 *********************************************/
	@Test
	public void testHealthMax() {
		Monster testMonster = new Monster();
		testMonster.setMaxHealthPoints(5);
		assertEquals(testMonster.getMaxHealthPoints(), 5);
	}
	/**********************************************
	 * Test get/set monster name.
	 *********************************************/
	@Test
	public void testMonsterName() {
		Monster testMonster = new Monster();
		testMonster.setMonsterName("This is a test");
		assertEquals(testMonster.getMonsterName(), "This is a test");
	}
	/**********************************************
	 * Test get/set image path.
	 *********************************************/
	@Test
	public void testImagePath() {
		Monster testMonster = new Monster();
		testMonster.setMonsterImagePath("This is a test");
		assertEquals(testMonster.getMonsterImagePath(),
			 "This is a test");
	}
	/**********************************************
	 * Test get/set sound path.
	 *********************************************/
	@Test
	public void testSoundPath() {
		Monster testMonster = new Monster();
		testMonster.setSoundFilePath("This is a test");
		assertEquals(testMonster.getSoundFilePath(), "This is a test");
	}
	/**********************************************
	 * Test get/set onfield stat.
	 *********************************************/
	@Test
	public void testOnField() {
		Monster testMonster = new Monster();
		testMonster.setOnField(true);
		assertEquals(testMonster.getOnField(), true);
	}
	/**********************************************
	 * Test reset defense stat.
	 *********************************************/
	@Test
	public void testResetStats1() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		testMonster.setAttackPoints(1);
		testMonster.setDefensePoints(2);
		testMonster.setSpeedPoints(3);
		testMonster.setMaxHealthPoints(4);
		testMonster.resetStats();
		assertEquals(testMonster.getDefenseBattle(),
				testMonster.getDefensePoints());
	}
	/**********************************************
	 * Test reset attack stat.
	 *********************************************/
	@Test
	public void testResetStats2() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		testMonster.setAttackPoints(1);
		testMonster.setDefensePoints(2);
		testMonster.setSpeedPoints(3);
		testMonster.setMaxHealthPoints(4);
		testMonster.resetStats();
		assertEquals(testMonster.getAttackBattle(),
				testMonster.getAttackPoints());
	}
	/**********************************************
	 * Test reset speed stat.
	 *********************************************/
	@Test
	public void testResetStats3() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		testMonster.setAttackPoints(1);
		testMonster.setDefensePoints(2);
		testMonster.setSpeedPoints(3);
		testMonster.setMaxHealthPoints(4);
		testMonster.resetStats();
		assertEquals(testMonster.getSpeedBattle(),
				testMonster.getSpeedPoints());
	}
	/**********************************************
	 * Test reset health stat.
	 *********************************************/
	@Test
	public void testResetStats4() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		testMonster.setAttackPoints(1);
		testMonster.setDefensePoints(2);
		testMonster.setSpeedPoints(3);
		testMonster.setMaxHealthPoints(4);
		testMonster.resetStats();
		assertEquals(testMonster.getHealthBattle(),
				testMonster.getMaxHealthPoints());
	}
	/**********************************************
	 * Test leveling up stat id 1.
	 *********************************************/
	@Test
	public void testLevelUP1() {
		Monster testMonster = new Monster();
		testMonster.setMaxHealthPoints(1);
		testMonster.levelUp(1);
		assertEquals(testMonster.getMaxHealthPoints(), 2);
	}
	/**********************************************
	 * Test level up stat id 2.
	 *********************************************/
	@Test
	public void testLevelUP2() {
		Monster testMonster = new Monster();
		testMonster.setDefensePoints(1);
		testMonster.levelUp(2);
		assertEquals(testMonster.getDefensePoints(), 2);
	}
	/**********************************************
	 * Test level up stat id 3.
	 *********************************************/
	@Test
	public void testLevelUP3() {
		Monster testMonster = new Monster();
		testMonster.setAttackPoints(1);
		testMonster.levelUp(3);
		assertEquals(testMonster.getAttackPoints(), 2);
	}
	/**********************************************
	 * Test level up stat id 4.
	 *********************************************/
	@Test
	public void testLevelUP4() {
		Monster testMonster = new Monster();
		testMonster.setSpeedPoints(1);
		testMonster.levelUp(4);
		assertEquals(testMonster.getSpeedPoints(), 2);
	}
	/**********************************************
	 * Test monster factory health.
	 *********************************************/
	@Test
	public void testMonsterFactoryChar1() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		assertEquals(testMonster.getMaxHealthPoints(), 120);
	}
	/**********************************************
	 * Test monster factory attack.
	 *********************************************/
	@Test
	public void testMonsterFactoryChar2() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		assertEquals(testMonster.getAttackPoints(), 4);
	}
	/**********************************************
	 * Test monster factory defense.
	 *********************************************/
	@Test
	public void testMonsterFactoryChar3() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		assertEquals(testMonster.getDefensePoints(), 8);
	}
	/**********************************************
	 * Test monster factory speed.
	 *********************************************/
	@Test
	public void testMonsterFactoryChar4() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Charizard");
		assertEquals(testMonster.getSpeedPoints(), 6);
	}
	/**********************************************
	 * Test monster factory health for different monster.
	 *********************************************/
	@Test
	public void testMonsterFactoryStar1() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Staryu");
		assertEquals(testMonster.getMaxHealthPoints(), 100);
	}
	/**********************************************
	 * Test Test monster factory attack for different monster.
	 *********************************************/
	@Test
	public void testMonsterFactoryStar2() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Staryu");
		assertEquals(testMonster.getAttackPoints(), 6);
	}
	/**********************************************
	 * Test monster factory defense for different monster.
	 *********************************************/
	@Test
	public void testMonsterFactoryStar3() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Staryu");
		assertEquals(testMonster.getDefensePoints(), 6);
	}
	/**********************************************
	 * Test monster factory speed for different monster.
	 *********************************************/
	@Test
	public void testMonsterFactoryStar4() {
		Monster testMonster = new Monster();
		testMonster.monsterFactory("Staryu");
		assertEquals(testMonster.getSpeedPoints(), 6);
	}
	/**********************************************
	 * Test decreasing health of a monster.
	 *********************************************/
	@Test
	public void testDecreaseHealth() {
		Monster testMonster = new Monster();
		testMonster.setHealthBattle(100);
		testMonster.setMaxHealthPoints(100);
		testMonster.decreaseHealth(10);
		assertEquals(testMonster.getHealthBattle(), 90);
	}
	/**********************************************
	 * Test decreasing health past 0 of a monster.
	 *********************************************/
	@Test
	public void testDecreaseHealthKill() {
		Monster testMonster = new Monster();
		testMonster.setHealthBattle(100);
		testMonster.decreaseHealth(101);
		assertEquals(testMonster.getHealthBattle(), 0);
	}
	/**********************************************
	 * Test increasing health past max of a monster.
	 *********************************************/
	@Test
	public void testDecreaseHealthOver() {
		Monster testMonster = new Monster();
		testMonster.setMaxHealthPoints(100);
		testMonster.setHealthBattle(100);
		testMonster.decreaseHealth(-1);
		assertEquals(testMonster.getHealthBattle(), 100);
	}
	/**********************************************
	 * Test changing turn.
	 *********************************************/
	@Test
	public void testChangeTurn() {
		Logic testLogic = new Logic();
		int turn = testLogic.getTurn();
		testLogic.changeTurn();
		assertNotEquals(turn, testLogic.getTurn());
	}
	/**********************************************
	 * Test logic specific constructor.
	 *********************************************/
	@Test
	public void testSpecificConstructor1() {
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
	}
	/**********************************************
	 * Test logic specific constructor second param.
	 *********************************************/
	@Test
	public void testSpecificConstructor2() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		tester.monsterFactory("Charizard");
		team1.add(tester);
		team2.add(tester);
		testLogic.setTeamsAndMons(team1, team2, 0, 0);
		Logic secondTest = new Logic(team1, team2);
		assertEquals(secondTest.getTeam2(), testLogic.getTeam2());
	}
	/**********************************************
	 * Test logic specific constructor third param.
	 *********************************************/
	@Test
	public void testSpecificConstructor3() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		tester.monsterFactory("Charizard");
		team1.add(tester);
		team2.add(tester);
		testLogic.setTeamsAndMons(team1, team2, 0, 0);
		Logic secondTest = new Logic(team1, team2);
		assertEquals(secondTest.getMon1(), testLogic.getMon1());
	}
	/**********************************************
	 * Test specific construtor fourth param.
	 *********************************************/
	@Test
	public void testSpecificConstructor4() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		tester.monsterFactory("Charizard");
		team1.add(tester);
		team2.add(tester);
		testLogic.setTeamsAndMons(team1, team2, 0, 0);
		Logic secondTest = new Logic(team1, team2);
		assertEquals(secondTest.getMon2(), testLogic.getMon2());
	}
	/**********************************************
	 * Test setting first team.
	 *********************************************/
	@Test
	public void testSetTeams1() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		team1.add(new Monster());
		testLogic.setTeams(team1, team1);
		assertEquals(testLogic.getTeam1(), team1);
	}
	/**********************************************
	 * Test setting second team.
	 *********************************************/
	@Test
	public void testSetTeams2() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		team1.add(new Monster());
		testLogic.setTeams(team1, team1);
		assertEquals(testLogic.getTeam2(), team1);
	}
	/**********************************************
	 * Test setting only first team.
	 *********************************************/
	@Test
	public void testSetTeamsAndMons1() {
		Logic testEngine = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		team1.add(tester);
		team2.add(tester);
		testEngine.setTeamsAndMons(team1, team2, -1, 0);
	}
	/**********************************************
	 * Test setting only second team.
	 *********************************************/
	@Test
	public void testSetTeamsAndMons2() {
		Logic testEngine = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		team1.add(tester);
		team2.add(tester);
		testEngine.setTeamsAndMons(team1, team2, 0, -1);
	}
	/**********************************************
	 * Test getting first monster.
	 *********************************************/
	@Test
	public void testGetMons1() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		tester.monsterFactory("Charizard");
		team1.add(tester);
		team2.add(tester);
		testLogic.setTeamsAndMons(team1, team2, 0, 0);
		System.out.println(testLogic.getMon1());
		assertEquals(testLogic.getMon1(), 0);
	}
	/**********************************************
	 * Test getting second monster.
	 *********************************************/
	@Test
	public void testGetMons2() {
		Logic testLogic = new Logic();
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		Monster tester = new Monster();
		tester.monsterFactory("Charizard");
		team1.add(tester);
		team2.add(tester);
		testLogic.setTeamsAndMons(team1, team2, 0, 0);
		assertEquals(testLogic.getMon2(), 0);
	}
	/**********************************************
	 * Test doMove where param is 1
	 *********************************************/
	@Test
	public void testDoMove1() {
		Logic testEngine = new Logic();
		Monster target = new Monster();
		Monster attacker = new Monster();
		target.monsterFactory("Charizard");
		attacker.monsterFactory("Jolteon");
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		team1.add(target);
		team2.add(attacker);
		testEngine.setTeamsAndMons(team1, team2, 0, 0);
		Move testMove = attacker.getMove1();
		testMove.setCritChance(0);
		testMove.setHitChance(10);
		testEngine.doMove(testMove, 1, 1);
		assertEquals(testEngine.getBattleText(), 
	"(Turn 1) Charizard (Team 1) attacked Jolteon "
	+ "(Team 2) first for 7 damage.\n");
	}
	/**********************************************
	 * Test doMove where param is 2
	 *********************************************/
	@Test
	public void testDoMove2() {
		Logic testEngine = new Logic();
		Monster target = new Monster();
		Monster attacker = new Monster();
		target.monsterFactory("Charizard");
		attacker.monsterFactory("Jolteon");
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		team1.add(target);
		team2.add(attacker);
		testEngine.setTeamsAndMons(team1, team2, 0, 0);
		Move testMove = attacker.getMove2();
		testMove.setCritChance(0);
		testMove.setHitChance(10);
		testEngine.doMove(testMove, 1, 2);
		assertEquals(testEngine.getBattleText(), 
				"(Turn 1) Charizard (Team 1) "
	+ "heavily attacked Jolteon (Team 2) first for 9 damage.\n");
	}
	/**********************************************
	 * Test doMove where param is 3
	 *********************************************/
	@Test
	public void testDoMove3() {
		Logic testEngine = new Logic();
		Monster target = new Monster();
		Monster attacker = new Monster();
		target.monsterFactory("Charizard");
		attacker.monsterFactory("Jolteon");
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		team1.add(target);
		team2.add(attacker);
		testEngine.setTeamsAndMons(team1, team2, 0, 0);
		Move testMove = attacker.getMove1();
		attacker.setMove3(testMove);
		testMove.setCritChance(0);
		testMove.setHitChance(10);
		testEngine.doMove(testMove, 1, 3);
		assertEquals(testEngine.getBattleText(), 
	"(Turn 1) Charizard (Team 1) healed "
	+ "themselves with 7 health points.\n");
	}
	/**********************************************
	 * Test doMove where param is 4
	 *********************************************/
	@Test
	public void testDoMove4() {
		Logic testEngine = new Logic();
		Monster target = new Monster();
		Monster attacker = new Monster();
		target.monsterFactory("Charizard");
		attacker.monsterFactory("Jolteon");
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		team1.add(target);
		team2.add(attacker);
		testEngine.setTeamsAndMons(team1, team2, 0, 0);
		Move testMove = attacker.getMove4();
		testMove.setCritChance(0);
		testMove.setHitChance(10);
		testEngine.doMove(testMove, 1, 4);
		assertEquals(testEngine.getBattleText(), 
	"(Turn 1) Charizard (Team 1) special attacked "
	+ "Jolteon (Team 2) first for 2 damage.\n");
	}
	/**********************************************
	 * Test where move target is 0.
	 *********************************************/
	@Test
	public void testMoveTarget0() {
		Logic testEngine = new Logic();
		Monster target = new Monster();
		Monster attacker = new Monster();
		target.monsterFactory("Charizard");
		attacker.monsterFactory("Jolteon");
		ArrayList<Monster> team1 = new ArrayList<Monster>();
		ArrayList<Monster> team2 = new ArrayList<Monster>();
		team1.add(target);
		team2.add(attacker);
		testEngine.setTeamsAndMons(team1, team2, 0, 0);
		Move testMove = attacker.getMove1();
		testMove.setMoveTarget(0);
		testMove.setCritChance(0);
		testMove.setHitChance(10);
		attacker.getMove1().setCritChance(0);
		testEngine.doMove(testMove, 0, 1);
	assertEquals(testEngine.getBattleText(), "(Turn 1) Jolteon (Team 2) "
	+ "attacked Charizard (Team 1) first for 7 damage.\n");
	}
	/**********************************************
	 * Test get/set battleText.
	 *********************************************/
	@Test
	public void testBattleText() {
		Logic testEngine = new Logic();
		testEngine.addBattleText("Test");
		assertEquals("Test", testEngine.getBattleText());
	}
	/**********************************************
	 * Test readonly turn number value.
	 *********************************************/
	@Test
	public void testTurnNumber() {
		//this is a read only value
		Logic testEngine = new Logic();
		testEngine.incTurnNum();
	}
	/**********************************************
	 * Test setting attack from move.
	 *********************************************/
	@Test
	public void testMoveAttack() {
		Move testMove = new Move(1, 1, 1, 1);
		testMove.setAttackPower(0);
		assertEquals(testMove.getAttackPower(), 0);
	}
	/**********************************************
	 * Test setting target from move.
	 *********************************************/
	@Test
	public void testMoveTarget() {
		Move testMove = new Move(1, 1, 1, 1);
		testMove.setMoveTarget(0);
		assertEquals(testMove.getMoveTarget(), 0);
	}
	/**********************************************
	 * Test move toString method.
	 *********************************************/
	@Test
	public void testToString() {
		Move testMove = new Move(1, 1, 1, 1);
		assertEquals(testMove.toString(), "Attack Power: 1\n" 
				+ "Crit Chance: 1\n" 
				+ "Hit Chance: 1\n" 
				+ " Move Target: 1");
	}
	/**
	 * 
	 */
}
