/**
 * Field.java
 * 
 * Class for non-Road map elements
 * Can be base for towers
 */

package hu.asd.towerdefence;
public class Field extends Tile {
	private Tower tower;  // a fielden levo towerunk
	
	//beallitja a parameterben kapott towert a fieldre
	public void setTower(Tower tower) {
		this.tower=tower;
	}
	
	//vissza azt a tower-t, amelyik a field-en van
	public Tower getTower() {
		return tower;
	}
}
