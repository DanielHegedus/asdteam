/**
 * Hobbit.java
 * 
 * The weakest enemy class
 * 
 * Default HP is 5
 */

package hu.asd.towerdefence;
public class Hobbit extends Enemy {
//hobbit ellensegunk osztalya
	private static int idNo = 0;
	
	public Hobbit(){
		super();
		hp=5;
		defHP=5;
		idNo++;
		setId("h"+idNo);
	}

	public Hobbit(int i) {
		this();
		hp=i;
	}
}
