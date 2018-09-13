package Release1;

public class StartBattle {
	public static void main(String[] args) {
		Charizard Char1 = new Charizard();
		Charizard Char2 = new Charizard();
		
		Logic engine = new Logic(Char1, Char2);
		//Attack the other Charizard
		engine.calculateDamage(Char1.move1);
		System.out.println();
		engine.displayData();
		System.out.println();
		//Heal our own charizard
		engine.calculateDamage(Char1.move3);
		System.out.println();
		engine.displayData();
	}
}
