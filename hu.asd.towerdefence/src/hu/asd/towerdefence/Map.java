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

	private static final int WAIT = 10;
	private Gem gem;
	private Mordor mordor;
	private List<Tile> map;
	private List<Tower> towers;
	private Road start;
	private int size;
	private Game game;
	private TDActionListener listener;
	private int counter;
	private List<Enemy> enemiesToAdd;

	public Map() {
		counter=0;
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
					listener.onMPAction();
					t.addListener(listener);
					t.setField((Field) tile);
					towers.add(t);
					listener.onTowerAction(t);
				} else {
					listener.onError(TDActionListener.NO_MP);
				}

			} else {
				listener.onError(TDActionListener.WRONG_TILE);
			}
		} else {
			listener.onError(TDActionListener.WRONG_TILE);
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
		listener.onGemAction(gem);
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

		if (enemies.size() == 0 && mordor.hasEnemy() == null 
				&& getEnemiesToAdd().get(getEnemiesToAdd().size()-1)==null)
			game.gameOver(true);

		for (Enemy e : enemies)
			e.move();

		for (Tower t : getTowers()) {
			t.onTick();
		}
		
		//veletlenszeruen kodot rak az egyik toronyra
		if (Math.random()>0.9 && towers.size()>0){
			int index=((int) (Math.random()*towers.size()));
			towers.get(index).setFog(true);
			//System.out.println(index);
		}
		
		
		if (counter>=WAIT && counter-WAIT<getEnemiesToAdd().size()){
			Enemy e = getEnemiesToAdd().get(counter-WAIT);
			if (e!= null){
				addEnemy(e);
				getEnemiesToAdd().set(counter-WAIT, null);
			}
			counter++;
		}else{
			counter++;
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
		gd.setCounter(counter);

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
		
		// the towers
		for (Entry<int[], Tower> entry : gd.getTowers().entrySet()) {
			int[] pos = entry.getKey();
			Tower tower = entry.getValue();
			Tile t = map.get(pos[0] * size + pos[1]);
			Field f = (Field) t;
			tower.setField(f);
			tower.addListener(listener);
			towers.add(tower);
		}

		// the swamps
		for (Entry<int[], Swamp> entry : gd.getSwamps().entrySet()) {
			int[] pos = entry.getKey();
			Swamp s = entry.getValue();
			s.setListener(listener);
			Tile prev = map.set(pos[0] * size + pos[1], s);
			
			for (Tile t : prev.getNeighbours()){
				s.setNeighbour(t);
			}
			// tell neighbouring tiles that they have a new neighbour
			for (Tile tl : s.getNeighbours()) {
				tl.setNeighbour(s);
				tl.removeNeighbour(prev);
			}
		}
		
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

		// set the MP
		MagicPower.setMP(gd.getMp());

		// set the gem
		gem = gd.getGem();
		
		//the counter
		this.counter=gd.getCounter();
	}

	// mocsar hozzaadasa
	public void addSwamp(Tile tile) {
		if (tile.getClass() == Road.class) {
			Swamp s = new Swamp((Road) tile);
			s.setListener(listener);
			if (MagicPower.decrease(s)) {
				listener.onMPAction();
				Tile prev = map.set(map.indexOf(tile), s);

				// tell neighbouring tiles that they have a new neighbour
				for (Tile t : s.getNeighbours()) {
					t.setNeighbour(s);
					t.removeNeighbour(prev);
				}
				
				//toroljuk az elozot
				prev=null;
				
				listener.onMapAction(s);
			} else
				listener.onError(TDActionListener.NO_MP);

		} else
			listener.onError(TDActionListener.WRONG_TILE);
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
			listener.onError(TDActionListener.WRONG_TILE);
	}

	// koordinatak azonositasa a palyan, majd tovabbadni az upgradenek az
	// azonositott swampot
	public void upgradeSwamp(int x, int y) {
		Tile tile = map.get(x * size + y);
		if (tile instanceof Swamp)
			upgradeSwamp((Swamp) tile);
		else
			listener.onError(TDActionListener.WRONG_TILE);
	}

	// torony fejlesztese az adott fielden
	public void upgradeTower(Field field) {
		// fejlesztes SpdTowerre
		if (this.gem instanceof SpdGem) {
			if (field.getTower() != null && !(field.getTower() instanceof SpdTower)) {
				Tower prevT = field.getTower();
				SpdTower st = new SpdTower();
				
				towers.remove(prevT);
				towers.add(st);
				
				st.addListener(listener);
				st.setField(field);
				field.setTower(st);
				setGem(null);
				listener.onMapAction(field);
																	// output
			} else {
				listener.onError(TDActionListener.WRONG_TILE);
			}
		}
		// fejlesztes DmgTowerre
		else if (this.gem instanceof DmgGem) {
			if (field.getTower() != null && !(field.getTower() instanceof DmgTower)) {
				Tower prevT = field.getTower();
				DmgTower dt = new DmgTower();
				
				towers.remove(prevT);
				towers.add(dt);
				
				dt.addListener(listener);
				dt.setField(field);
				field.setTower(dt);
				setGem(null);
				listener.onMapAction(field);
			} else {
				listener.onError(TDActionListener.WRONG_TILE);
			}
		} else {
			listener.onError(TDActionListener.NO_GEM);
		}

	}

	// mocsar fejlesztese
	public void upgradeSwamp(Swamp swamp) {
		// System.out.println("upgradeSwamp(" + swamp.getClass().getName() +
		// ")");
		if (this.gem instanceof SwpGem) {
			SuperSwamp sswamp = new SuperSwamp(swamp);
			sswamp.setListener(listener);
			Tile prev = map.set(map.indexOf(swamp), sswamp);
			setGem(null);
			listener.onMapAction(sswamp);
			// tell neighbouring tiles that they have a new neighbour
			for (Tile t : sswamp.getNeighbours()) {
				t.setNeighbour(sswamp);
				t.removeNeighbour(prev);
			}
		} else {
			listener.onError(TDActionListener.NO_GEM);
		}
	}

	public void upgradeTower(int x, int y) {
		Tile tile = map.get(x * size + y);
		if (tile instanceof Field)
			upgradeTower((Field) tile);
		else
			listener.onError(TDActionListener.WRONG_TILE);
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public List<Enemy> getEnemiesToAdd() {
		return enemiesToAdd;
	}

	public void setEnemiesToAdd(List<Enemy> enemiesToAdd) {
		counter=0;
		this.enemiesToAdd = enemiesToAdd;
	}

}
