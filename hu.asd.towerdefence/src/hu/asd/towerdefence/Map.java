/**
 * Map.java
 * 
 * Class for storing the map and placing towers
 * 
 * Changes since the skeleton:
 * -Enemy list removed
 * -checkEnemies boolean
 * -Road start
 * -killEnemy removed
 * 
 */

package hu.asd.towerdefence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {

	// a jatekban szereplo fontosabb objektumok listaja
	private Gem gem;
	private Mordor mordor;
	private List<Tile> map;
	private List<Tower> towers;
	private Road start;
	private int size;
	private Game game;
	private TDActionListener listener;

	public Map() {
		map = new ArrayList<Tile>();
		towers = new ArrayList<Tower>();
	}

	public Map(Game g) {
		this();
		game = g;
	}

	public void addListener(TDActionListener l) {
		listener = l;
	}

	// hozzaad a palyahoz egy uj elemet
	public void addTile(Tile tile) {
		getMap().add(tile);
	}

	// lerak egy tornyot az egyik Tile-ra
	public void addTower(Tile tile) {
		if (tile instanceof Field) {
			DefTower t = new DefTower();
			if (MagicPower.decrease(t)) {
				t.addListener(listener);
				t.setField((Field) tile);
				towers.add(t);
			} else {
				listener.notEnoughMP();
			}

		} else {
			listener.wrongTileSelected();
		}
	}

	public void addTower(int x, int y) {
		Tile tile = map.get(x * size + y);
		addTower(tile);
	}

	// hozzaadunk egy uj ellenseget
	public void addEnemy(Enemy enemy) {
		enemy.setActionListener(listener);
		start.enter(enemy);
	}

	// beallitjuk, hogy milyen gem-unk van
	public void setGem(Gem gem) {
		this.gem = gem;
	}

	// visszadja a gem-unket
	public Gem getGem() {
		return gem;
	}

	// minden tickre lefuto metodus
	public void onTick() {
		List<Enemy> enemies = new ArrayList<Enemy>();
		for (Tile t : getMap()) {
			if (t instanceof Road) {
				Road r = (Road) t;
				for (Enemy e : r.getEnemies()) {
					enemies.add(e);
				}
			}
		}

		if (mordor.hasEnemy() != null) {
			game.gameOver(false);
		}

		if (enemies.size() == 0 && mordor.hasEnemy() == null)
			game.gameOver(true);

		for (Enemy e : enemies)
			e.move();

		for (Tower t : towers) {
			t.onTick();
		}

	}

	// ellenorzi vannak-e meg ellensegek

	public boolean checkEnemies() {
		for (Tile t : getMap()) {
			if (t.getClass() == Road.class) {
				if (((Road) t).hasEnemy() != null)
					;
				return true;
			}
		}
		return false;
	}

	// TODO
	public GameData getData() {
		System.out.println("getData()");
		return new GameData();

	}

	// TODO
	public void setData(Object gamedata) {
		System.out.println("setData(Gamedata)");
	}

	// mocsar hozzaadasa
	public void addSwamp(Tile tile) {
		if (tile instanceof Road) {
			Swamp s = new Swamp((Road) tile);
			map.set(map.indexOf(tile), s);

			// tell neighbouring tiles that they have a new neighbour
			for (Tile t : s.getNeighbours()) {
				t.setNeighbour(s);
			}

			listener.onSwampAdded(s);
		} else
			listener.wrongTileSelected();
	}

	public Road getStart() {
		return start;
	}

	public void setStart(Road start) {
		this.start = start;
	}

	public List<Tile> getMap() {
		return map;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Mordor getMordor() {
		return mordor;
	}

	public void setMordor(Mordor mordor) {
		this.mordor = mordor;
	}

	public void addSwamp(int x, int y) {
		Tile tile = map.get(x * size + y);
		addSwamp(tile);

	}

	// torony fejlesztese az adott fielden
	public void upgradeTower(Field field) {
		// fejlesztes SpdTowerre
		if (this.gem instanceof SpdGem) {
			if (field.getTower() != null) {
				SpdTower st = new SpdTower();
				st.setField(field);
				field.setTower(st);
				gem = null;
			} else {
				Printer.printError("There is no tower on this field");
			}
		}
		// fejlesztes DmgTowerre
		else if (this.gem instanceof DmgGem) {
			if (field.getTower() != null) {
				DmgTower dt = new DmgTower();
				dt.setField(field);
				field.setTower(dt);
				gem = null;
			} else {
				Printer.printError("There is no tower on this field");
			}
		} else {
			Printer.printError("There is no gem for tower upgrade."
					+ "You can buy gems with the buyGem command.");
		}

	}

	// mocsar fejlesztese
	public void upgradeSwamp(Swamp swamp) {
		// System.out.println("upgradeSwamp(" + swamp.getClass().getName() +
		// ")");
		if (this.gem instanceof SwpGem) {
			SuperSwamp sswamp = new SuperSwamp(swamp);
			map.set(map.indexOf(swamp), sswamp);

			// tell neighbouring tiles that they have a new neighbour
			for (Tile t : sswamp.getNeighbours()) {
				t.setNeighbour(sswamp);
			}
		} else {
			Printer.printError("There is no gem for swamp upgrade.");
		}
	}

	public void upgradeTower(int x, int y) {
		Tile tile = map.get(x * size + y);
		upgradeTower((Field) tile);
	}

}
