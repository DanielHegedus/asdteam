/**
 * Hobbit.java
 * 
 * The weakest enemy class
 * 
 * Default HP is 5
 */

package hu.asd.towerdefence;
public class Hobbit extends Enemy {

	public Hobbit(){
		super();
		hp=5;
		defHP=5;
	}

	public Hobbit(int i) {
		this();
		hp=i;
	}
}
