/**
 * Swamp.java
 * 
 * Blocks the entering enemy's movement for @param power ticks 
 */

package hu.asd.towerdefence;

import java.io.Serializable;

public class Swamp extends Road implements Serializable{
	
	protected int power;	//mocsarunk lassito ereje
	private TDActionListener listener;
	protected static int cost=5;	//mocsarunk ara
	
	public Swamp(){
		super();
		power=3;
		cost=5;
	}
	
	/**constructor that gets the the enemies and neighbours from the base road*/
	public Swamp(Road r) {
		this();
		neighbours=r.getNeighbours();
		enemies=r.getEnemies();
	}
	
	@Override
	public void enter(Enemy e){
		super.enter(e);
		block(e); 
		
	}

	//blockolja az ellensegunk tovabbhaladasat bizonyos ideig
	public void block(Enemy enemy) {
		enemy.setBlockTime(power);
	}
	
	//visszaadja a mocsar arat
	public int getCost() {
		return cost;
	}

	
}
