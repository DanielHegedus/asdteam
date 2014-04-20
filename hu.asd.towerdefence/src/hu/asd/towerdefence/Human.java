/**
 * Human.java
 * 
 * The second weakest enemy class
 * 
 * Default HP is 10
 */

package hu.asd.towerdefence;
public class Human extends Enemy {
//ember ellensegunk osztalya
	private static int idNo = 0;
	
	public Human(){
		super();
		hp=10;
		defHP=10;
		idNo++;
		setId("u"+idNo);
	}

	public Human(int i) {
		this();
		hp=i;
	}
}
