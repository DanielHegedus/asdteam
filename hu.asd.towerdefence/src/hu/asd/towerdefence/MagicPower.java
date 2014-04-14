package hu.asd.towerdefence;
public class MagicPower {
	private static int mp; //varazseronk szama
	
	//varazsero novelese az Enemy hp-janak ertekevel
	public static void increase(Enemy enemy) {
		System.out.println("increase("+ enemy.getClass().getName() +")");
	}
	
	//csokkenti a varazserot a gem araval
	public static void decrease(Gem gem) {
		System.out.println("decrease("+ gem.getClass().getName() +")");
	}
	
	//csokkenti a varazserot a tower araval
	public static void decrease(Tower tower) {
		System.out.println("decrease("+ tower.getClass().getName() +")");
	}
	
	//csokkenti a varazserot a swamp araval
	public static void decrease(Swamp swamp) {
		System.out.println("decrease("+ swamp.getClass().getName() +")");
	}
	
	//visszadja a varazseronk erteket 
	public static int getMP() {
		System.out.println("getMP()");
		return mp;
	}
	
	//beallitja az mp valtozot
	public static void setMP(int i) {
		System.out.println("setMP(i)");
	}
}
