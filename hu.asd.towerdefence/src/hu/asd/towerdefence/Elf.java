/**
 * Elf.java
 * 
 * The second strongest enemy class
 * 
 * Default HP is 15
 */

package hu.asd.towerdefence;

public class Elf extends Enemy {

	public Elf(){
		super();
		hp=15;
		defHP=10;
	}

	public Elf(int i) {
		this();
		hp=i;
	}
	
}
