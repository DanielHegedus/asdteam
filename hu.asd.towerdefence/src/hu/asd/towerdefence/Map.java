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

	public Map() {
		setMap(new ArrayList<Tile>());
		towers = new ArrayList<Tower>();
	}
	
	public Map(Game g){
		this();
		game=g;
	}

	// hozzaad a palyahoz egy uj elemet
	public void addTile(Tile tile) {
		getMap().add(tile);
	}

	// lerak egy tornyot az egyik Tile-ra
	public void addTower(Tile tile) {
		
	}

	// hozzaadunk egy uj ellenseget
	public void addEnemy(Enemy enemy) {
		start.enter(enemy);
	}

	// beallitjuk, hogy milyen gem-unk van
	public void setGem(Gem gem) {
		System.out.println("setGem(" + gem.getClass().getName() + ")");
	}

	// visszadja a gem-unket
	public Gem getGem() {
		return gem;
	}

	// torony fejlesztese az adott fielden
	public void upgradeTower(Field field) {
		System.out.println("upgradeTower(Field)");
		System.out.print(" --> map.");
		getGem(); // elkerjuk a gem szamat

		// ALT -- megnezzuk milyen gem van

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "n";

		while (!s.equals("spd") && !s.equals("dmg")) {
			System.out.println(" [?] Milyen típusú Gem-ünk van? spd/dmg");
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (s.equals("spd")) {
			System.out.println(" --> create spdt:SpdTower");
			SpdTower spdt = new SpdTower(); // megcsinaljuk a gyorsitott
											// lovoereju tornyot
			System.out.print(" --> field.");
			field.setTower(spdt); // beallitjuk a field-re az uj tornyot
		}

		else {
			System.out.println(" --> create dmgt:DmgTower");
			DmgTower dmgt = new DmgTower(); // megcsinaljuk a megerositett
											// lovoereju tornyot
			System.out.print(" --> field.");
			field.setTower(dmgt); // beallitjuk a fieldre az uj tornyot TODO
		}

		// ALT VEGE

	}

	// minden tickre lefuto metodus
	public void onTick() {
		List<Enemy> enemies = new ArrayList<Enemy>();
		for (Tile t : getMap()) {
			if (t.getClass() == Road.class) {
				Road r = (Road) t;
				for (Enemy e : r.getEnemies()) {
					enemies.add(e);
				}
			}
		}
		
		if (enemies.size()==0)
			game.gameOver(true);
		
		if(mordor.hasEnemy()!=null){
			game.gameOver(false);
		}
		
		for(Enemy e : enemies)
			e.move();
		
		for (Tower t : towers){
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


	// mocsar hozzaadasa TODO
	public void addSwamp(Road road) {
	}

	// mocsar fejlesztese
	public void upgradeSwamp(Swamp swamp) {
		System.out.println("upgradeSwamp(" + swamp.getClass().getName() + ")");
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

	public void setMap(List<Tile> map) {
		this.map = map;
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

}
