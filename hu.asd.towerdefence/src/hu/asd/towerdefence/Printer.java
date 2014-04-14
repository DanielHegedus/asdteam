package hu.asd.towerdefence;

import java.io.IOException;
import java.util.List;

public class Printer {

	public static void printGameOver(boolean playerWon) {
		if (playerWon)
			System.out.println("Congratulations, you have saved Middle Earth");
		else
			System.out.println("The world is in ruins now thanks to you");
	}

	public static void printEnemies(Map map) {
		for (int i = 0; i < map.getMap().size(); i++) {
			if (map.getMap().get(i).getClass() == Road.class)
				if (((Road) map.getMap().get(i)).hasEnemy() != null)
					for (Enemy e : ((Road) map.getMap().get(i)).getEnemies())
						print(e, i % map.getSize(), i / map.getSize());

		}
	}

	public static void print(Enemy e) {
		System.out.println("[" + e.getClass().getSimpleName() + "] HP: "
				+ e.getHP());
	}

	public static void print(Enemy e, int x, int y) {
		System.out.println("[" + e.getClass().getSimpleName() + "] HP: "
				+ e.getHP() + " (" + x + "," + y + ")");
	}

	public static void printGem(Game game) {
		String gem = "None";
		if (game.getMap().getGem() != null)
			gem = game.getMap().getGem().getClass().toString();
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
			if (map.get(i).getClass() == Road.class)
				if (((Road) map.get(i)).hasEnemy() != null)
					System.out.print(((Road) map.get(i)).getEnemies().size());
				else
					System.out.print("U");
			else if (map.get(i).getClass() == Mordor.class)
				System.out.print("M");
			else
				System.out.print("-");

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
}
