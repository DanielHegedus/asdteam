package hu.asd.towerdefence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
	Map map=new Map();
	MapBuilder mapb=new MapBuilder();
	Timer timer=new Timer();

	//jatekter inicializalasa, megcsinalja a map-et
	public void init() {
		System.out.println("init()");
		mapb.createMap();
	}
	
	
	//jatek inditasa 
	public void start() {
		System.out.println("--> timer.");
		timer.start();
	}
	
	//a parameterben megadott varazsko vasarlasa
	public void buyGem(Gem gem) {
		System.out.println("buyGem("+ gem.getClass().getName() +")");
		System.out.print(" --> mp.");
		MagicPower.getMP();	//elkeri, hogy mennyi MagicPowerunk van
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "n";
		
		//vizsgalat, hogy van-e eleg MagicPowerunk
		System.out.println(" [?] Van elég MagicPower-ünk vásárolni? y/n");
		try{
			s = br.readLine();
		}
		catch(IOException e){
			e.printStackTrace();
			}
		
		//ha van eleg MagicPower akkor beallitjuk a gem-ek szamat, es csokkentjuk a MagicPower-t
		if (s.equals("y")){
		
			System.out.print(" --> map.");
			map.setGem(gem);	//megadjuk a gem-ek szamat

			System.out.print(" --> mp.");
			MagicPower.decrease(gem);	//csokkentjuk a MagicPower szamat a gem araval.
		
		}
	}
	
	//jatek vege TODO
	public void gameOver(Object bool) {
		System.out.println("gameover(true)");
	}
	
	//jatek ujrainditasa
	public void restart() {
		System.out.println("restart()");
		System.out.print(" --> game.");
		init();
	}
	
	//jatek mentese TODO
	public void save() {
		System.out.println("save()");
		System.out.print(" -->road.");
		Main.road.getEnemies(); //elkeri a Road-tol, hogy milyen ellensegek vannak rajta
		System.out.println(" <-- l:List");
		
		System.out.print(" -->field.");
		Main.field.getTower(); //elkeri a Field-tol, hogy milyen torony van rajta
		System.out.println(" <-- tower:Tower");
		
		System.out.print(" -->map.");
		Main.map.getData();	//elkeri a tovabbi adatokat a Map-tol
		System.out.println(" <-- gd:Gamedata");
		
	}
	
	//betolti az adatokat a jatekba
	public void load() {
		System.out.println("load()");
		
		System.out.print(" -->road.");
		List<Enemy> enemies = new ArrayList<Enemy>();
		Main.road.setEnemies(enemies); //beallitja a Road.ra az ellensegeket
		
		System.out.print(" -->field.");
		Main.field.setTower(Main.tower); //beallitja  Field-re a tornyot TODO
		
		System.out.print(" -->map.");
		Main.map.setData(Main.gd); //visszaallitja az adatokat a Map-nek TODO
		
		System.out.println("<--");

	}
}
