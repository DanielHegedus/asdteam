package hu.asd.towerdefence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Map {
	
	//a jatekban szereplo fontosabb objektumok listaja
	private List<Enemy> easy;
	private List<Enemy> medium;
	private List<Enemy> hard;
	private Gem gem;
	private Mordor mordor;
	private List<Tile> map;
	private List<Tower> towers;
	
	//hozzaad a palyahoz egy uj elemet
	public void addTile(Tile tile) {
		System.out.println("addTile(tile)");
	}
	
	//lerak egy tornyot az egyik Tile-ra
	public void addTower(Tile tile) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("addTower(field)");
		
		System.out.print(" --> mp.");
		MagicPower.getMP();	//elkéri mennyi MagicPowerunk van TODO
		System.out.println(" <-- mp:int");
		
		// OPT - vizsgálat, hogy van-e elég mp tornyot építeni
		
		String s = "n";
		
		System.out.println(" [?] Van elég MagicPower-ünk tornyot építeni? y/n");
		try{
			s = br.readLine();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		if (s.equals("y"))
		{
			Tower tower = new Tower();	//megcsináljuk az új tornyot, ha van elég mp
			System.out.println(" --> create tower:Tower");
			
			// LOOP TODO
			
			Field field1 = new Field();
			System.out.print(" --> field1.");
			tower.setField(field1); //megmondjuk a toronynak, hogy melyik fielden lesz
			
			Field field2 = new Field();
			System.out.print(" --> field2.");
			tower.setField(field2);
			
			// LOOP VEGE
			
			System.out.print(" --> mp.");
			MagicPower.decrease(tower); // csokkentjuk a magicpowert a torony araval
		}
		
	}
	
	//hozzaadunk egy uj ellenseget
	public void addEnemy(Enemy enemy) {
		System.out.println("addEnemy("+ enemy.getClass().getName() +")");
		
	}
	
	//beallitjuk, hogy milyen gem-unk van
	public void setGem(Gem gem) {
		System.out.println("setGem("+ gem.getClass().getName() +")");
	}
	
	//visszadja a gem-unket
	public Gem getGem() {
		System.out.println("getGem()");
		return gem;
	}
	
	//torony fejlesztese az adott fielden
	public void upgradeTower(Field field) {
		System.out.println("upgradeTower(Field)");
		System.out.print(" --> map.");
		getGem(); //elkerjuk a gem szamat
		
		
		// ALT -- megnezzuk milyen gem van
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "n";
	
		while (!s.equals("spd") && !s.equals("dmg")){
		System.out.println(" [?] Milyen típusú Gem-ünk van? spd/dmg");
		try{
			s = br.readLine();
		}
		catch(IOException e){
			e.printStackTrace();
			}
		}
		if (s.equals("spd")){
			System.out.println(" --> create spdt:SpdTower");
			SpdTower spdt = new SpdTower();	//megcsinaljuk a gyorsitott lovoereju tornyot
			System.out.print(" --> field.");
			field.setTower(spdt);	//beallitjuk a field-re az uj tornyot
		}
		
		else {
			System.out.println(" --> create dmgt:DmgTower");
			DmgTower dmgt = new DmgTower();	//megcsinaljuk a megerositett lovoereju tornyot
			System.out.print(" --> field.");
			field.setTower(dmgt);	//beallitjuk a fieldre az uj tornyot TODO
		}
		
		// ALT VEGE
			
	}
	
	//minden tickre lefuto metodus 
	public void onTick() {
		System.out.println("onTick()");
		Hobbit anEnemy = new Hobbit();
		System.out.print(" --> anEnemy.");
		anEnemy.move();
		DefTower aTower = new DefTower();
		System.out.print(" --> anEnemy.");
		aTower.onTick();
	
	}
	
	//ellenorzi vannak-e meg ellensegek
	public void checkEnemies() {
		System.out.println("checkEnemies()");
	}
	
	//TODO
	public GameData getData() {
		System.out.println("getData()");
		return new GameData();

	}
	
	//TODO
	public void setData(Object gamedata) {
		System.out.println("setData(Gamedata)");
	}
	
	//torli a listabol a meghalt ellenseget
	public void killEnemy(Enemy enemy) {
		System.out.println("killEnemy("+ enemy.getClass().getName() +")");
	}
	
	//mocsar hozzaadasa TODO
	public void addSwamp(Road road) {
		System.out.println("addSwamp(Road)");
		System.out.print(" --> mp.");
		MagicPower.getMP();
		System.out.println(" <-- i:int");
		
		// OPT
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = "n";
		
		System.out.println(" [?] Van elég MagicPower-ünk mocsárra? y/n");
		try{
			s = br.readLine();
		}
		catch(IOException e){
			e.printStackTrace();
			}
		
		if (s.equals("y")){
		
		Swamp swamp=new Swamp();
		System.out.println(" --> create swamp:Swamp");
		System.out.print(" --> road.");
		
		System.out.println(" <-- l:List");
		
		// LOOP
		//A road.getneighbours visszatérését kapja meg
		Road aNeighbourRoad = new Road();
		System.out.print(" --> swamp.");
		swamp.setNeighbour(aNeighbourRoad);
		
		
		// LOOP VEGE
		
		System.out.print(" --> mp.");
		MagicPower.decrease(swamp);
		
		}
	}
	
	//mocsar fejlesztese
	public void upgradeSwamp(Swamp swamp) {
		System.out.println("upgradeSwamp("+ swamp.getClass().getName() +")");
	}
}
