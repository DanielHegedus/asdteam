/**Enemy.java
 * 
 * Base class for enemy objects
 * 
 * Changes since skeleton:
 * -protected fields
 * 
 * */

package hu.asd.towerdefence;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy {

	protected Integer hp; // ellensegunk elete
	protected Integer blockTime; // akkor mehet tovabb az ellenseg, ha ez nulla, ha
	protected Integer defHP;							// mocsarba lep, akkor lesz nem nulla, igy
								// lassul a mozgasa
	protected Road road;
	protected Road previousRoad;
	protected TDActionListener listener;

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
		listener.onEnemyDamage(this, i);
		if (hp<=0){ //death
			MagicPower.increase(this);
			listener.onMPGain();
			//kill it by removing references
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
		listener.onEnemyBlock(this);
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
		if (timeToMove()>0){
			listener.onEnemyBlock(this);
			return;
		}
		
		road.leave(this);
		List<Road> validRoads = new ArrayList<Road>();
		for (Tile t : road.getNeighbours()) {
			if ((t.getClass() == Road.class || t.getClass() == Mordor.class || t.getClass()==Swamp.class || t.getClass()==SuperSwamp.class) && t != previousRoad) {
				validRoads.add((Road) t);
			}
		}
		if (validRoads.size() > 1 && Math.random()<0.5) {
			validRoads.get(1).enter(this);
		} else if (validRoads.size()>0){
			validRoads.get(0).enter(this);
		}else
			previousRoad.enter(this);
	}
	
	public void setActionListener(TDActionListener listener){
		this.listener=listener;
	}
}
