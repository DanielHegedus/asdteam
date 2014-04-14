/**
 * MagicPower.java
 * 
 * Class for managing MP
 * 
 * Changes:
 * -decrease methods are now boolean
 */

package hu.asd.towerdefence;
public class MagicPower {
	private static int mp=0; //varazseronk szama
	
	//varazsero novelese az Enemy hp-janak ertekevel
	public static void increase(Enemy enemy) {
		mp+=enemy.defHP;
	}
	
	//csokkenti a varazserot a gem araval
	public static boolean decrease(Gem gem) {
		if (mp>=gem.getCost()){
			mp-=gem.getCost();
			return true;
		}
		return false;
	}
	
	//csokkenti a varazserot a tower araval
	public static boolean decrease(Tower tower) {
		if (mp>=tower.getCost()){
			mp-=tower.getCost();
			return true;
		}
		return false;
	}
	
	//csokkenti a varazserot a swamp araval
	public static boolean decrease(Swamp swamp) {
		if (mp>=swamp.getCost()){
			mp-=swamp.getCost();
			return true;
		}
		return false;
	}
	
	//visszadja a varazseronk erteket 
	public static int getMP() {
		return mp;
	}
	
	//beallitja az mp valtozot
	public static void setMP(int i) {
		mp=i;
	}
}
