package hu.asd.towerdefence;
public class Field extends Tile {
	private Tower tower;  // a fielden levo towerunk
	
	//beallitja a parameterben kapott towert a fieldre
	public void setTower(Tower tower) {
		System.out.println("setTower("+ tower.getClass().getName() +")");
	}
	
	//vissza azt a tower-t, amelyik a field-en van
	public Tower getTower() {
		System.out.println("getTower()");
		return tower;
	}
}
