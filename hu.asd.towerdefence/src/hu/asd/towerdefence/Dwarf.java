/**
 *Dwarf.java
 *
 *The strongest enemy class
 *
 *Default HP is 20
 * */

package hu.asd.towerdefence;

public class Dwarf extends Enemy {

	public Dwarf(){
		super();
		hp=20;
		defHP=20;
	}

	public Dwarf(int i) {
		this();
		hp=i;
	}
	
}
