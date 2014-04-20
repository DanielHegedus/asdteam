/**
 *Dwarf.java
 *
 *The strongest enemy class
 *
 *Default HP is 20
 * */

package hu.asd.towerdefence;

public class Dwarf extends Enemy {
//torpe ellenseg osztalya
	private static int idNo = 0;
	
	public Dwarf(){
		super();
		hp=20;
		defHP=20;
		idNo++;
		setId("d"+idNo);
	}

	public Dwarf(int i) {
		this();
		hp=i;
	}
	
}
