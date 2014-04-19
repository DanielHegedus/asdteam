package hu.asd.towerdefence;

import java.io.IOException;
import java.util.List;

public class Printer {
	
	public static void printError(String err){
		System.out.println("ERROR: "+err);
	}

	public static void printGameOver(boolean playerWon) {
		if (playerWon)
			System.out.println("Congratulations, you have saved Middle Earth");
		else
			System.out.println("The world is in ruins now thanks to you");
	}

	public static void printEnemies(Map map) {
		for (int i = 0; i < map.getMap().size(); i++) {
			if (map.getMap().get(i) instanceof Road)
				if (((Road) map.getMap().get(i)).hasEnemy() != null)
					for (Enemy e : ((Road) map.getMap().get(i)).getEnemies())
						print(e, i % map.getSize(), i / map.getSize());

		}
	}

	public static void print(Enemy e) {
		System.out.print("[" +e.getId() + ":" + e.getClass().getSimpleName() + "] HP: "
				+ e.getHP());
	}

	public static void print(Enemy e, int x, int y) {
		print(e," (" + x + "," + y + ")");
	}

	public static void print(Enemy e, String action) {
		print(e);
		System.out.println(action);
	}

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

	// TODO specification
	public static void print(List<Tile> map, int size) {
		for (int i = 0; i < map.size(); i++) {
			Tile t = map.get(i);
			if (t.getClass() == Road.class)
				if (((Road) t).hasEnemy() != null)
					System.out.print(((Road) t).getEnemies().size());
				else
					System.out.print("U");
			else if (t.getClass() == Mordor.class)
				System.out.print("M");
			else if (t.getClass() == Swamp.class)
				if (((Swamp) t).hasEnemy() != null)
					System.out.print("M");
				else
					System.out.print("W");
			else if (t.getClass() == SuperSwamp.class)
				System.out.print("S");
			else {
				Field f = (Field) map.get(i);
				if (f.getTower() != null){
					if(f.getTower() instanceof DmgTower)
						System.out.print("D");
					else if(f.getTower() instanceof SpdTower)
						System.out.print("P");
					else
						System.out.print("X");
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

	public static void print(Tower t) {
		System.out.print("[" + t.getClass().getSimpleName() + "]");
	}

	public static void printShooting(Tower t, Enemy e) {
		print(t);
		System.out.print("shot at");
		print(e);
		System.out.println();

	}

	public static void print(Tower t, String action) {
		print(t);
		System.out.println(action);

	}
	
	
}
