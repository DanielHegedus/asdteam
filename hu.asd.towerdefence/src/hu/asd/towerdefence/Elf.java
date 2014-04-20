/**
 * Elf.java
 * 
 * The second strongest enemy class
 * 
 * Default HP is 15
 */

package hu.asd.towerdefence;

public class Elf extends Enemy {
//tunde ellenseg osztalya
	private static int idNo = 0;

	public Elf(){
		super();
		hp=15;
		defHP=10;
		idNo++;
		setId("e"+idNo);
	}

	public Elf(int i) {
		this();
		hp=i;
	}
	
}
