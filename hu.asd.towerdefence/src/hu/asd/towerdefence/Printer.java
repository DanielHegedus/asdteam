package hu.asd.towerdefence;

import java.io.IOException;
import java.util.List;

public class Printer {
	 
	//error message
	public static void printError(String err){
		System.out.println("ERROR: "+err);
	}
	
	//jatek vege kiirasok
	public static void printGameOver(boolean playerWon) {
		if (playerWon){
			System.out.println("Congratulations, you have saved Middle Earth");
			System.out.println("Game Over - You won");
			//System.exit(0);
		}
		else{
			System.out.println("The world is in ruins now thanks to you");
			System.out.println("Game Over - You lost");
			System.exit(0);
		}
	}
	
	//print enemies on the map
	public static void printEnemies(Map map) {
		for (int i = 0; i < map.getMap().size(); i++) {
			if (map.getMap().get(i) instanceof Road)
				if (((Road) map.getMap().get(i)).hasEnemy() != null)
					for (Enemy e : ((Road) map.getMap().get(i)).getEnemies()){
						print(e, i % map.getSize(), i / map.getSize());
						System.out.println();
					}

		}
	}

	//print an enemy with id name and hp
	public static void print(Enemy e) {
		System.out.print("[" +e.getId() + ":" + e.getClass().getSimpleName() + "] HP: "
				+ e.getHP());
	}

	//print enemy with coords
	public static void print(Enemy e, int x, int y) {
		print(e," (" + x + "," + y + ")");
	}

	//print enemy and action
	public static void print(Enemy e, String action) {
		print(e);
		System.out.print(action);
	}
	
	//print which gem you have
	public static void printGem(Game game) {
		String gem = "None";
		if (game.getMap().getGem() != null)
			gem = game.getMap().getGem().getClass().getSimpleName();
		System.out.println("[GEM] " + gem);
	}

	public static void printMP() {
		System.out.println("[MP] " + MagicPower.getMP());
	}

	public static void print(Map map) {
		print(map.getMap(), map.getSize());
	}

	// print the map elements
	public static void print(List<Tile> map, int size) {
		for (int i = 0; i < map.size(); i++) {
			Tile t = map.get(i);
			if (t.getClass() == Road.class)
				if (((Road) t).hasEnemy() != null)
					//System.out.print(((Road) t).getEnemies().size());
					System.out.print("*");
				else
					System.out.print("U");
			else if (t.getClass() == Mordor.class)
				System.out.print("M");
			else if (t.getClass() == Swamp.class)
				if (((Swamp) t).hasEnemy() != null)
					System.out.print("S");
				else
					System.out.print("S");
			else if (t.getClass() == SuperSwamp.class)
				System.out.print("W");
			else {
				Field f = (Field) map.get(i);
				if (f.getTower() != null){
					if(f.getTower() instanceof DmgTower)
						System.out.print("D");
					else if(f.getTower() instanceof SpdTower)
						System.out.print("P");
					else
						System.out.print("O");
				}
				else
					System.out.print("-");

			}

			if (i % size == size - 1)
				System.out.println();
		}
	}

	public static void clear() {
		try {
			Runtime.getRuntime().exec("cls");
		} catch (IOException e) {

		}
	}

	//print: tower name
	public static void print(Tower t) {
		Field f = t.getField();
		Tower twr = f.getTower();
		System.out.print("[" + twr.getClass().getSimpleName() + "]");
	}
	
	//print: tower is created
	public static void print(Tower t, Map m) {
		Field f = t.getField();
		int x = (m.getMap().indexOf(f))/m.getSize();
		int y = (m.getMap().indexOf(f))%m.getSize();
		System.out.print("["+ x + "," + y + ":");
		System.out.print(t.getClass().getSimpleName() + "] created\n");
	}

	//print tower shoot at enemy
	public static void printShooting(Tower t, Enemy e) {
		print(t);
		System.out.print("shot at");
		print(e);
		System.out.println();

	}

	public static void print(Tower t, String action, Map m) {
		Field f = t.getField();
		int x = (m.getMap().indexOf(f))/m.getSize();
		int y = (m.getMap().indexOf(f))%m.getSize();
		System.out.print("["+ x + "," + y + ":");
		System.out.print(t.getClass().getSimpleName() + "] ");
		System.out.println(action);

	}
	
	//print out which tower upgraded to 
	public static void printUpgradeTower(Tower prevT, Tower upT, Map m, Field f){
		int x = (m.getMap().indexOf(f))/m.getSize();
		int y = (m.getMap().indexOf(f))%m.getSize();
		System.out.print("["+ x + "," + y + ":");
		//print(prevT);
		System.out.print(prevT.getClass().getSimpleName());
		System.out.print("] upgraded to ");
		System.out.print("["+ x + "," + y + ":");
		//print(upT);
		System.out.print(upT.getClass().getSimpleName());
		System.out.print("]\n");
	}
	
	//print about the swamp upgrade
	public static void printUpgradeSwamp(Map m, Swamp swp) {
		int x = (m.getMap().indexOf(swp))/m.getSize();
		int y = (m.getMap().indexOf(swp))%m.getSize();
		System.out.print("["+ x + "," + y + ":Swamp");
		System.out.print("] upgraded to ");
		System.out.print("["+ x + "," + y + ":SuperSwamp");
		System.out.print("]\n");
	}

	public static void print(Tile t, String action, Map m) {
		int x = (m.getMap().indexOf(t))/m.getSize();
		int y = (m.getMap().indexOf(t))%m.getSize();
		System.out.println("["+x+","+y+":"+t.getClass().getSimpleName()+"] " + action);		
	}
	
	//print coords
	public static void printCoords(Tile t, Map m) {
		int x = (m.getMap().indexOf(t))/m.getSize();
		int y = (m.getMap().indexOf(t))%m.getSize();
		System.out.print(x+","+y);
	}
	
	
}
