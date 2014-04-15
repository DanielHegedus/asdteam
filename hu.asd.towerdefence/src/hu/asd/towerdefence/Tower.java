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
	protected int cost; // torony ara
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
		e.lowerHP(power);
	}

	public void doDamage(Hobbit h) {
		if (Math.random() < 0.2) {
			h.getRoad().enter(new Hobbit(h.defHP / 2));
		} else
			h.lowerHP(power);
	}

	public void doDamage(Dwarf d) {
		if (Math.random() < 0.2) {
			d.getRoad().enter(new Dwarf(d.defHP / 2));
		} else
			d.lowerHP(power);
	}

	public void doDamage(Elf e) {
		if (Math.random() < 0.2) {
			e.getRoad().enter(new Elf(e.defHP / 2));
		} else
			e.lowerHP(power);
	}

	public void doDamage(Human h) {
		if (Math.random() < 0.2) {
			h.getRoad().enter(new Human(h.defHP / 2));
		} else
			h.lowerHP(power);
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
