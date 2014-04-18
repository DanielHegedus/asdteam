/**
 * Tower.java
 * 
 * Base class for defence towers
 * 
 * Changes:
 * -private fields are now protected
 */

package hu.asd.towerdefence;

import java.util.ArrayList;
import java.util.List;

public class Tower {

	protected List<Road> neighbours; // lista a torony koruli utakrol
	protected List<Road> closeNeighbours;
	protected int speed; // lovesi sebesseg
	protected int power; // lovesi ero
	protected int timeleft; // ennyi ido mulva lo
	protected static int cost; // torony ara
	protected Field field; // field amin a torony van
	protected boolean fog;
	protected int defTimeleft;
	private TDActionListener listener;

	public Tower() {
		neighbours = new ArrayList<Road>();
		closeNeighbours = new ArrayList<Road>();
	}

	// sebzi a parameterkent megadott ellenseget
	// ?
	public void doDamage(Enemy e) {
		if (Math.random()<0.2) {
			Enemy newEnemy;
			try {
				newEnemy = e.getClass().newInstance();
				newEnemy.setActionListener(listener);
				e.getRoad().enter(newEnemy);
				newEnemy.setHP(e.getHP()/2);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
		} else
			e.lowerHP(power);
	}
	
	public void doDamage(Enemy e, boolean split) {
		if (split) {
			Enemy newEnemy;
			try {
				newEnemy = e.getClass().newInstance();
				newEnemy.setActionListener(listener);
				e.getRoad().enter(newEnemy);
				newEnemy.setHP(e.getHP()/2);
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

	public void onTick() {
		if (timeleft > 0) {
			timeleft -= speed;
			listener.onTowerNotShooting(this);
			return;
		}
		timeleft = defTimeleft;
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
		listener.onTowerNotShooting(this);
	}

	public boolean isInFog() {
		return fog;
	}

	public void setFog(boolean fog) {
		this.fog = fog;
	}

	public TDActionListener getListener() {
		return listener;
	}

	public void addListener(TDActionListener listener) {
		this.listener = listener;
	}
}
