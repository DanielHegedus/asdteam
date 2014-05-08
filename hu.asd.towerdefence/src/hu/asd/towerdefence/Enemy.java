/**Enemy.java
 * 
 * Base class for enemy objects
 * 
 * Changes since skeleton:
 * -protected fields
 * 
 * */

package hu.asd.towerdefence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Enemy implements Serializable{

	protected Integer hp; // ellensegunk elete
	protected Integer blockTime; // number of ticks until the enemy can move
	public Integer defHP;
	protected transient Road road;
	protected transient Road previousRoad;
	protected transient TDActionListener listener;
	private int chooseRoad = 0; // option for disabling choosing random road
								// when at crossroads, set 0 for random, 1 for
								// left, 2 for right

	private String id; // id added for easier debugging

	public Enemy() {
		blockTime = 0;
	}

	public Enemy(int hp) {
		this();
		this.hp = hp;
	}

	// lovi az ellensegunket a parameterben megadott tower, meghivja a
	// doDamage-et
	public void shoot(Tower tower) {
		tower.doDamage(this);
	}

	// visszaadja az ellenseg hp-jat
	public int getHP() {
		return hp;
	}

	// beallitja az ellenseg hp-jat
	public void setHP(int i) {
		hp = i;
	}

	// csokkenti az ellenseg hp-jat a parameterben megadott int i ertekkel
	public void lowerHP(int i) {
		hp -= i;
		listener.onEnemyAction(this);
		if (hp <= 0) { // death
			MagicPower.increase(this);
			listener.onMPAction();
			// kill it by removing references
			road.leave(this);

		}
	}

	// minden hivasnal eggyel csokkenti a blocktime-ot, visszaadja annak
	// értékét.
	public int timeToMove() {
		return --blockTime;
	}

	// beallitja a blocktime valtozo erteket
	public void setBlockTime(int blockTime) {
		this.blockTime = blockTime;
		listener.onEnemyAction(this);
	}

	// beallitja, hogy melyik uton van az ellenseg, illetve a previousRoadot is
	public void setRoad(Road road) {
		this.previousRoad = this.road;
		this.road = road;
	}

	// visszaadja melyik uton van az ellenseg
	public Road getRoad() {
		return road;
	}

	// mozgatja az ellenséget
	public void move() {

		// if in swamp decrease blocktime and return
		if (timeToMove() > 0) {
			//listener.onEnemyAction(this);
			return;
		}

		road.leave(this);
	//	listener.onEnemyAction(this);
		List<Road> validRoads = new ArrayList<Road>();
		for (Tile t : road.getNeighbours()) {
			if ((t.getClass() == Road.class || t.getClass() == Mordor.class
					|| t.getClass() == Swamp.class || t.getClass() == SuperSwamp.class)
					&& t != previousRoad) {
				validRoads.add((Road) t);
			}
		}
		if (validRoads.size() > 1 && (Math.random() < 0.5 || chooseRoad == 1)) {
			validRoads.get(1).enter(this);
			listener.onMapAction(road);
		} else if (validRoads.size() > 0) {
			validRoads.get(0).enter(this);
			listener.onMapAction(road);
		} else {
			previousRoad.enter(this);
			listener.onMapAction(road);
		}
		chooseRoad = 0; // turn random back on
	}

	public void setActionListener(TDActionListener listener) {
		this.listener = listener;
	}
	
	public TDActionListener getActionListener(){
		return listener;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getChooseRoad() {
		return chooseRoad;
	}

	public void setChooseRoad(int chooseRoad) {
		this.chooseRoad = chooseRoad;
	}
	
	public String toString(){
		return "["+id+":"+this.getClass().getSimpleName()+"]";
	}
}
