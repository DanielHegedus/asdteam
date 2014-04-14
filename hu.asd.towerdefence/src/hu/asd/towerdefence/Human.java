/**
 * Human.java
 * 
 * The second weakest enemy class
 * 
 * Default HP is 10
 */

package hu.asd.towerdefence;
public class Human extends Enemy {

	public Human(){
		super();
		hp=10;
		defHP=10;
	}

	public Human(int i) {
		this();
		hp=i;
	}
}
