/**
 * Tower.java
 * 
 * Base class for defence towers
 * 
 * Changes:
 * -private fields are now protected
 */

package hu.asd.towerdefence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tower implements Serializable{

	protected transient List<Road> neighbours; // lista a torony koruli utakrol
	protected transient List<Road> closeNeighbours;
	protected int speed; // lovesi sebesseg
	protected int power; // lovesi ero
	private int timeleft; // ennyi ido mulva lo
	protected int cost; // torony ara
	protected transient Field field; // field amin a torony van
	protected boolean fog;
	protected int defTimeleft;
	private transient TDActionListener listener;
	private boolean split;
	private String id;

	public Tower() {
		neighbours = new ArrayList<Road>();
		closeNeighbours = new ArrayList<Road>();
	}

	// sebzi a parameterkent megadott ellenseget
	// ?
	public void doDamage(Enemy e) {
		//isSplitting added for debug purposes
		if (Math.random()<0.2 || isSplitting()) {
			setSplit(false);
			Enemy newEnemy;
			try {
				newEnemy = e.getClass().newInstance();
				newEnemy.setActionListener(listener);
				e.getRoad().enter(newEnemy);
				newEnemy.setHP(e.getHP()/2);
				listener.onEnemyCreated(newEnemy);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
		} else
			e.lowerHP(power);
	}
	

	// visszaadja a torony arat
	public int getCost() {
		return cost;
	}

	// beallitja a fieldet amin a torony lesz
	public void setField(Field f) {
		if (neighbours == null)
			neighbours=new ArrayList<Road>();
		
		if (closeNeighbours == null)
			closeNeighbours=new ArrayList<Road>();
		
		this.field = f;
		field.setTower(this);
		for (Tile tl : field.getNeighbours()) {
			if (tl.getClass() == Road.class) {
				neighbours.add((Road) tl);
				closeNeighbours.add((Road) tl);
			}
			for (Tile t : tl.getNeighbours()) {
				if (t.getClass() == Road.class)
					neighbours.add((Road) t);
			}
		}

	}
	
	public Field getField(){
		return field;
	}

	public void onTick() {
		if (getTimeleft() > 0) {
			setTimeleft(getTimeleft() - speed);
			listener.onTowerNotShooting(this);
			return;
		}
		setTimeleft(defTimeleft);
		if (isInFog()) {
			for (Road r : closeNeighbours) {
				if (r.hasEnemy() != null) {
					Enemy e = r.hasEnemy();
					listener.onTowerShooting(this, e);
					e.shoot(this);
					return;
				}

			}
		} else {
			for (Road r : neighbours) {
				if (r.hasEnemy() != null) {
					Enemy e = r.hasEnemy();
					listener.onTowerShooting(this, e);
					e.shoot(this);
					return;
				}
			}
		}
		listener.onNoEnemyInSight(this);
	}

	public boolean isInFog() {
		return fog;
	}

	public void setFog(boolean fog) {
		this.fog = fog;
		listener.onTowerFog(this);
	}

	public TDActionListener getListener() {
		return listener;
	}

	public void addListener(TDActionListener listener) {
		this.listener = listener;
	}

	public boolean isSplitting() {
		return split;
	}

	public void setSplit(boolean split) {
		this.split = split;
	}

	public int getTimeleft() {
		return timeleft;
	}

	public void setTimeleft(int timeleft) {
		this.timeleft = timeleft;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString(){
		return "["+id+":"+this.getClass().getSimpleName()+"]";
	}
}
