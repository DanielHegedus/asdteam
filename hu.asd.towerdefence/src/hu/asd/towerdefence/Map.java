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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
		setTowers(new ArrayList<Tower>());
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
		tile.setListener(listener);
		getMap().add(tile);
	}

	// lerak egy tornyot az egyik Tile-ra
	public void addTower(Tile tile) {
		if (tile instanceof Field) {
			Field f = (Field) tile;
			if (f.getTower() == null) { // ellenorzes, hogy van-e mar torony a
										// fielden
				DefTower t = new DefTower();
				if (MagicPower.decrease(t)) {
					t.addListener(listener);
					t.setField((Field) tile);
					towers.add(t);
					Printer.print(t, this); // kiiras
				} else {
					listener.notEnoughMP();
				}

			} else {
				Printer.printError("Field is not empty.");
			}
		} else {
			listener.wrongTileSelected();
		}
	}

	// torony hozzaadasnal a koordinatak atalakitasa Tile objectre
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

		for (Tower t : getTowers()) {
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

	
	public GameData getData() {
		// creating a new gamedata object
		GameData gd = new GameData();

		// setting the MP and gem
		gd.setMP(MagicPower.getMP());
		gd.setGem(gem);

		// adding the enemies
		List<Enemy> enemyList = new ArrayList<Enemy>();

		for (Tile t : map) {
			if (t instanceof Road) {
				Road r = (Road) t;
				for (Enemy e : r.getEnemies()) {
					enemyList.add(e);
				}
			}
		}

		for (Enemy e : enemyList) {
			int[] pos = new int[2];
			pos[0] = map.indexOf(e.getRoad()) / size;
			pos[1] = map.indexOf(e.getRoad()) % size;
			gd.addEnemy(pos, e);
		}

		// adding the towers
		for (Tower t : towers) {
			int[] pos = new int[2];
			pos[0] = map.indexOf(t.getField()) / size;
			pos[1] = map.indexOf(t.getField()) % size;
			gd.addTower(pos, t);
		}

		// adding swamps
		for (Tile t : map) {
			if (t instanceof Swamp) {
				int[] pos = new int[2];
				pos[0] = map.indexOf(t) / size;
				pos[1] = map.indexOf(t) % size;
				gd.addSwamp(pos, (Swamp) t);
			}
		}

		return gd;
	}

	
	public void setData(GameData gd) {
		// restoring the enemies
		for (Entry<int[], Enemy> entry : gd.getEnemies().entrySet()) {
			// get the values
			int[] pos = entry.getKey();
			Enemy e = entry.getValue();
			// get the tile
			Tile t = map.get(pos[0] * size + pos[1]);
			// add the enemy
			((Road) t).enter(e);
		}

		// the towers
		for (Entry<int[], Tower> entry : gd.getTowers().entrySet()) {
			int[] pos = entry.getKey();
			Tower tower = entry.getValue();
			Tile t = map.get(pos[0] * size + pos[1]);
			Field f = (Field) t;
			tower.setField(f);
			towers.add(tower);
		}

		// the swamps
		for (Entry<int[], Swamp> entry : gd.getSwamps().entrySet()) {
			int[] pos = entry.getKey();
			Swamp s = entry.getValue();
			Tile t = map.get(pos[0] * size + pos[1]);
			map.set(pos[0] * size + pos[1], s);
			// tell neighbouring tiles that they have a new neighbour
			for (Tile tl : s.getNeighbours()) {
				tl.setNeighbour(s);
			}
		}

		// set the MP
		MagicPower.setMP(gd.getMp());

		// set the gem
		gem = gd.getGem();
	}

	// mocsar hozzaadasa
	public void addSwamp(Tile tile) {
		if (tile instanceof Road) {
			Swamp s = new Swamp((Road) tile);
			s.setListener(listener);
			if (MagicPower.decrease(s)) {
				map.set(map.indexOf(tile), s);

				// tell neighbouring tiles that they have a new neighbour
				for (Tile t : s.getNeighbours()) {
					t.setNeighbour(s);
				}

				listener.onSwampAdded(s);
			} else
				listener.notEnoughMP();

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

	// koordinatak azonositasa a palyan, majd tovabbadni az upgradenek az
	// azonositott roadot
	public void addSwamp(int x, int y) {
		Tile tile = map.get(x * size + y);
		if (tile instanceof Road)
			addSwamp(tile);
		else
			Printer.printError("You can't put a swamp there.");
	}

	// koordinatak azonositasa a palyan, majd tovabbadni az upgradenek az
	// azonositott swampot
	public void upgradeSwamp(int x, int y) {
		Tile tile = map.get(x * size + y);
		if (tile instanceof Swamp)
			upgradeSwamp((Swamp) tile);
		else
			Printer.printError("No swamp there to upgrade.");
	}

	// torony fejlesztese az adott fielden
	public void upgradeTower(Field field) {
		// fejlesztes SpdTowerre
		if (this.gem instanceof SpdGem) {
			if (field.getTower() != null) {
				Tower prevT = field.getTower();
				SpdTower st = new SpdTower();
				
				towers.remove(prevT);
				towers.add(st);
				
				
				st.addListener(listener);
				st.setField(field);
				field.setTower(st);
				gem = null;
				Printer.printUpgradeTower(prevT, st, this, field); // prints the
																	// output
			} else {
				Printer.printError("There is no tower on this field");
			}
		}
		// fejlesztes DmgTowerre
		else if (this.gem instanceof DmgGem) {
			if (field.getTower() != null) {
				Tower prevT = field.getTower();
				DmgTower dt = new DmgTower();
				
				towers.remove(prevT);
				towers.add(dt);
				
				dt.addListener(listener);
				dt.setField(field);
				field.setTower(dt);
				gem = null;
				Printer.printUpgradeTower(prevT, dt, this, field); // prints the
																	// output
			} else {
				Printer.printError("There is no tower on this field");
			}
		} else {
			Printer.printError("There is no gem for tower upgrade."
					+ " You can buy gems with the buyGem command"
					+ " or use 'dmg' or 'spd' after the command to upgrade the tower without gem.");
		}

	}

	// mocsar fejlesztese
	public void upgradeSwamp(Swamp swamp) {
		// System.out.println("upgradeSwamp(" + swamp.getClass().getName() +
		// ")");
		if (this.gem instanceof SwpGem) {
			SuperSwamp sswamp = new SuperSwamp(swamp);
			sswamp.setListener(listener);
			map.set(map.indexOf(swamp), sswamp);
			gem = null;
			Printer.printUpgradeSwamp(this, sswamp);
			// tell neighbouring tiles that they have a new neighbour
			for (Tile t : sswamp.getNeighbours()) {
				t.setNeighbour(sswamp);
			}
		} else {
			Printer.printError("There is no gem for swamp upgrade."
					+ "Try the upgradeSwamp x y -nogem command or buy gem with 'buyGem swp'.");
		}
	}

	public void upgradeTower(int x, int y) {
		Tile tile = map.get(x * size + y);
		if (tile instanceof Field)
			upgradeTower((Field) tile);
		else
			Printer.printError("This is not a field. You can't put tower there.");
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

}
